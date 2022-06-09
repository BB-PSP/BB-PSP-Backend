package bbpsp.backend.domain.service;

import bbpsp.backend.domain.domain.persist.BatterStat;
import bbpsp.backend.domain.domain.persist.PitcherStat;
import bbpsp.backend.domain.domain.persist.Player;
import bbpsp.backend.domain.domain.persist.Season;
import bbpsp.backend.domain.dto.response.PredictBatterDTO;
import bbpsp.backend.domain.dto.response.PredictPitcherDTO;
import bbpsp.backend.domain.enums.PositionInfo;
import bbpsp.backend.domain.execption.NoSuchPlayerException;
import bbpsp.backend.domain.execption.NoSuchSeasonException;
import bbpsp.backend.domain.repository.PlayerRepository;
import bbpsp.backend.domain.repository.SeasonRepository;
import bbpsp.backend.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PredictionService {

    private final PlayerRepository playerRepository;
    private final SeasonRepository seasonRepository;
    private final int BATTER_FEATURE_COUNT = 26;
    private final int BATTER_PREDICT_FEATURE_COUNT = 11;
    private final int PITCHER_FEATURE_COUNT = 31;
    private final int PITCHER_PREDICT_FEATURE_COUNT = 10;
    private final String DATA_PATH = "/home/ubuntu/travis-ci/zip/src/main/resources/static/predict/";
//    private final String DATA_PATH = "src/main/resources/static/predict/";
    private final String START_PITCHER_WEIGHT_DATA_PATH = DATA_PATH + "PITCHER_WEIGHT_DATA_START.csv";
    private final String RELIEF_PITCHER_WEIGHT_DATA_PATH = DATA_PATH + "PITCHER_WEIGHT_DATA_RELIEF.csv";

    public List<PredictBatterDTO> predictAllBatters(int year) {
        List<PredictBatterDTO> predictBatterDTOList = new ArrayList<>();
        Season season = seasonRepository.findByYear(year)
                .orElseThrow(() -> new NoSuchSeasonException(ErrorCode.NO_SUCH_SEASON));
        playerRepository.findAllByYearWithStat(season.getId())
                .stream()
                .filter(player -> !player.getPosition().equals(PositionInfo.P))
                .forEach(player -> predictBatterDTOList.add(predictBatterStat(player.getBatterStat(), player)));
        return predictBatterDTOList;
    }

    public List<PredictPitcherDTO> predictAllPitchers(int year) {
        List<PredictPitcherDTO> predictPitcherDTOList = new ArrayList<>();
        Season season = seasonRepository.findByYear(year)
                .orElseThrow(() -> new NoSuchSeasonException(ErrorCode.NO_SUCH_SEASON));
        playerRepository.findAllByYearWithStat(season.getId())
                .stream()
                .filter(player -> player.getPosition().equals(PositionInfo.P))
                .forEach(player -> {
                    if (player.getPitcherStat().getIP() >= 100) {
                        predictPitcherDTOList.add(predictPitcherStat(player.getPitcherStat(), player, START_PITCHER_WEIGHT_DATA_PATH));
                    } else {
                        predictPitcherDTOList.add(predictPitcherStat(player.getPitcherStat(), player, RELIEF_PITCHER_WEIGHT_DATA_PATH));
                    }
                });
        return predictPitcherDTOList;
    }

    public PredictBatterDTO predictOneBatter(int year, String name, LocalDate birth) {
        Season season = seasonRepository.findByYear(year)
                .orElseThrow(() -> new NoSuchSeasonException(ErrorCode.NO_SUCH_SEASON));
        Player player = playerRepository.findByNameAndBirthWithYear(name, birth, season.getId())
                .orElseThrow(() -> new NoSuchPlayerException(ErrorCode.NO_SUCH_PLAYER));
        BatterStat batterStat = player.getBatterStat();
        return predictBatterStat(batterStat, player);
    }

    public PredictPitcherDTO predictOnePitcher(int year, String name, LocalDate birth) {
        Season season = seasonRepository.findByYear(year)
                .orElseThrow(() -> new NoSuchSeasonException(ErrorCode.NO_SUCH_SEASON));
        Player player = playerRepository.findByNameAndBirthWithYear(name, birth, season.getId())
                .orElseThrow(() -> new NoSuchPlayerException(ErrorCode.NO_SUCH_PLAYER));
        if (player.getPitcherStat().getIP() >= 100) {
            return predictPitcherStat(player.getPitcherStat(), player, START_PITCHER_WEIGHT_DATA_PATH);
        }
        return predictPitcherStat(player.getPitcherStat(), player, RELIEF_PITCHER_WEIGHT_DATA_PATH);
    }

    private PredictBatterDTO predictBatterStat(BatterStat batterStat, Player player) {
        String BATTER_WEIGHT_DATA_PATH = DATA_PATH + "BATTER_WEIGHT_DATA.csv";
        List<List<String>> batterWeightList = readCSV(BATTER_WEIGHT_DATA_PATH);
        // G, AB, PA, AVG, R, RBI, H, HR, BB, OBP, SLG
        double[] predictedBatterStatArray = new double[BATTER_PREDICT_FEATURE_COUNT];
        int[] indexArray = {1, 2, 3, 20, 9, 10, 4, 8, 11, 21, 22};
        // Type,Age,G,AB,PA,H,1B,2B,3B,HR,R,RBI,BB,IBB,SO,HBP,SF,SH,GDP,SB,CS,AVG,OBP,SLG,OPS,ISO,BABIP
        double[] batterStatArray = makeBatterStatArray(batterStat, player);
        for (int i = 0; i < BATTER_PREDICT_FEATURE_COUNT; i++) {
            double sum = 0;
            for (int j = 0; j < BATTER_FEATURE_COUNT; j++) {
                sum += batterStatArray[j] * Double.parseDouble(batterWeightList.get(i + 1).get(j + 1));
            }
            batterStatArray[indexArray[i]] = sum;
        }
        batterStatArray[21] = batterStatArray[5] / batterStatArray[3];
        for (int i = 0; i < BATTER_PREDICT_FEATURE_COUNT; i++) {
            double sum = 0;
            for (int j = 0; j < BATTER_FEATURE_COUNT; j++) {
                sum += batterStatArray[j] * Double.parseDouble(batterWeightList.get(i + 1).get(j + 1));
            }
            predictedBatterStatArray[i] = Math.round(sum * 1000) / 1000.0;
        }
        double pAVG = Math.round(predictedBatterStatArray[6] / predictedBatterStatArray[1] * 1000) / 1000.0;
        double pOPS = Math.round(predictedBatterStatArray[9] + predictedBatterStatArray[10] * 1000) / 1000.0;
        int pG = Math.min((int) predictedBatterStatArray[0], 144);
        return PredictBatterDTO.createDTO(player.getName(), player.getAge() + 1, pG,
                (int) predictedBatterStatArray[1], (int) predictedBatterStatArray[2], pAVG,
                (int) predictedBatterStatArray[4], (int) predictedBatterStatArray[5], (int) predictedBatterStatArray[6],
                (int) predictedBatterStatArray[7], (int) predictedBatterStatArray[8], predictedBatterStatArray[9],
                predictedBatterStatArray[10], pOPS);
    }

    private PredictPitcherDTO predictPitcherStat(PitcherStat pitcherStat, Player player, String pitcherWeightDataPath) {
        List<List<String>> pitcherWeightList = readCSV(pitcherWeightDataPath);
        // G, IP, ERA, WHIP, W, L, SO, HLD, S, ER
        double[] predictedPitcherStatArray = new double[PITCHER_PREDICT_FEATURE_COUNT];
        int[] indexArray = {4, 11, 3, 27, 1, 2, 22, 10, 8, 15};
        // Age,W,L,ERA,G,GS,CG,ShO,SV,BS,HLD,IP,TBF,H,R,ER,HR,BB,IBB,HBP,WP,BK,SO,K_9,BB_9,H_9,HR_9,WHIP,BABIP,LOB_PCT,FIP
        double[] pitcherStatArray = makePitcherStatArray(pitcherStat, player);
//        for (int i = 0; i < PITCHER_PREDICT_FEATURE_COUNT; i++) {
//            double sum = 0;
//            for (int j = 0; j < PITCHER_FEATURE_COUNT; j++) {
//                sum += pitcherStatArray[j] * Double.parseDouble(pitcherWeightList.get(i + 1).get(j + 1));
//            }
//            pitcherStatArray[indexArray[i]] = sum;
//        }
        for (int i = 0; i < PITCHER_PREDICT_FEATURE_COUNT; i++) {
            double sum = 0;
            for (int j = 0; j < PITCHER_FEATURE_COUNT; j++) {
                sum += pitcherStatArray[j] * Double.parseDouble(pitcherWeightList.get(i + 1).get(j + 1));
            }
            predictedPitcherStatArray[i] = Math.round(sum * 100) / 100.0;
        }
        double pIP = tuneIP(predictedPitcherStatArray[1]);
        double pERA = Math.round((predictedPitcherStatArray[9] / predictedPitcherStatArray[2]) * 100) / 100.0;
//        int pHLD = pIP > 100 ? 0 : Math.max((int) predictedPitcherStatArray[7], 0);
//        int pSV = pIP > 100 ? 0 : Math.max((int) predictedPitcherStatArray[8], 0);
        return PredictPitcherDTO.createDTO(player.getName(), player.getAge() + 1, (int) predictedPitcherStatArray[0],
                pIP, pERA, predictedPitcherStatArray[3], (int) predictedPitcherStatArray[4],
                (int) predictedPitcherStatArray[5], (int) predictedPitcherStatArray[6], (int) predictedPitcherStatArray[7], (int) predictedPitcherStatArray[8]);
    }

    private double[] makeBatterStatArray(BatterStat batterStat, Player player) {
        double[] batterStatArray = new double[BATTER_FEATURE_COUNT];
        batterStatArray[0] = player.getAge() + 1;
        batterStatArray[1] = batterStat.getG();
        batterStatArray[2] = batterStat.getAB();
        batterStatArray[3] = batterStat.getPA();
        batterStatArray[4] = batterStat.getH();
        batterStatArray[5] = batterStat.get_1B();
        batterStatArray[6] = batterStat.get_2B();
        batterStatArray[7] = batterStat.get_3B();
        batterStatArray[8] = batterStat.getHR();
        batterStatArray[9] = batterStat.getR();
        batterStatArray[10] = batterStat.getRBI();
        batterStatArray[11] = batterStat.getBB();
        batterStatArray[12] = batterStat.getIBB();
        batterStatArray[13] = batterStat.getSO();
        batterStatArray[14] = batterStat.getHBP();
        batterStatArray[15] = batterStat.getSF();
        batterStatArray[16] = batterStat.getSH();
        batterStatArray[17] = batterStat.getGDP();
        batterStatArray[18] = batterStat.getSB();
        batterStatArray[19] = batterStat.getCS();
        batterStatArray[20] = batterStat.getAVG();
        batterStatArray[21] = batterStat.getOBP();
        batterStatArray[22] = batterStat.getSLG();
        batterStatArray[23] = batterStat.getOBP() + batterStat.getSLG();
        batterStatArray[24] = batterStat.getSLG() - batterStat.getAVG();
        batterStatArray[25] = (double) (batterStat.getH() - batterStat.getHR()) /
                                (double) (batterStat.getAB() - batterStat.getSO() - batterStat.getHR() + batterStat.getSF());
        // BABIP = (H - HR) / (AB - BB - HBP - SO - HR);
        return batterStatArray;
    }

    private double tuneIP(double v) {
        double pIP = Math.round(v * 10) / 10.0;
        if ((pIP - (int) pIP) > 0.2) {
            pIP = 0.2 + (int) pIP;
        }
        return pIP;
    }

    private double[] makePitcherStatArray(PitcherStat pitcherStat, Player player) {
        // Age,W,L,ERA,G,GS,CG,ShO,SV,BS,HLD,IP,TBF,H,R,ER,HR,BB,IBB,HBP,WP,BK,SO,K_9,BB_9,H_9,HR_9,WHIP,BABIP,LOB_PCT,FIP
        double[] pitcherStatArray = new double[PITCHER_FEATURE_COUNT];
        pitcherStatArray[0] = player.getAge() + 1;
        pitcherStatArray[1] = pitcherStat.getW();
        pitcherStatArray[2] = pitcherStat.getL();
        pitcherStatArray[3] = pitcherStat.getERA();
        pitcherStatArray[4] = pitcherStat.getG();
        pitcherStatArray[5] = pitcherStat.getGS();
        pitcherStatArray[6] = pitcherStat.getCG();
        pitcherStatArray[7] = pitcherStat.getSHO();
        pitcherStatArray[8] = pitcherStat.getSV();
        pitcherStatArray[9] = 0; // BS
        pitcherStatArray[10] = pitcherStat.getHLD();
        pitcherStatArray[11] = pitcherStat.getIP();
        pitcherStatArray[12] = pitcherStat.getTBF();
        pitcherStatArray[13] = pitcherStat.getH();
        pitcherStatArray[14] = pitcherStat.getR();
        pitcherStatArray[15] = pitcherStat.getER();
        pitcherStatArray[16] = pitcherStat.getHR();
        pitcherStatArray[17] = pitcherStat.getBB();
        pitcherStatArray[18] = pitcherStat.getIBB();
        pitcherStatArray[19] = pitcherStat.getHBP();
        pitcherStatArray[20] = pitcherStat.getWP();
        pitcherStatArray[21] = pitcherStat.getBK();
        pitcherStatArray[22] = pitcherStat.getSO();
        pitcherStatArray[23] = pitcherStat.getK_9();
        pitcherStatArray[24] = pitcherStat.getBB_9();
        pitcherStatArray[25] = pitcherStat.getH_9();
        pitcherStatArray[26] = pitcherStat.getHR_9();
        pitcherStatArray[27] = pitcherStat.getWHIP();
        pitcherStatArray[28] = pitcherStat.getBABIP();
        pitcherStatArray[29] = pitcherStat.getLOB();
        pitcherStatArray[30] = pitcherStat.getFIP();
        return pitcherStatArray;
    }

    private List<List<String>> readCSV(String path) {
        List<List<String>> csvList = new ArrayList<>();
        File csv = new File(path);
        BufferedReader br = null;
        String line = "";

        try {
            br = new BufferedReader(new FileReader(csv));
            while ((line = br.readLine()) != null) { // readLine()은 파일에서 개행된 한 줄의 데이터를 읽어온다.
                String[] lineArr = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)", -1); // 파일의 한 줄을 ,로 나누어 배열에 저장 후 리스트로 변환한다.
                String[] lineArr2 = new String[lineArr.length];
                int i = 0;
                for (String s : lineArr) {
                    if (s.contains("\"")) {
                        s = s.replace("\"", "");
                    }
                    lineArr2[i] = s;
                    i++;
                }
                List<String> aLine = Arrays.asList(lineArr2);
                csvList.add(aLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close(); // 사용 후 BufferedReader를 닫아준다.
                }
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
        return csvList;
    }

}
