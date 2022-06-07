package bbpsp.backend.domain.service;

import bbpsp.backend.domain.domain.persist.BatterStat;
import bbpsp.backend.domain.domain.persist.Player;
import bbpsp.backend.domain.domain.persist.Team;
import bbpsp.backend.domain.dto.request.PlayerRangeDTO;
import bbpsp.backend.domain.dto.response.PlayerListDTO;
import bbpsp.backend.domain.dto.response.batterstat.BatterAllStatNInfoDTO;
import bbpsp.backend.domain.dto.response.batterstat.BatterStatDTO;
import bbpsp.backend.domain.dto.response.batterstat.BatterStatNPlayerDTO;
import bbpsp.backend.domain.dto.response.PlayerDTO;
import bbpsp.backend.domain.dto.response.batterstat.BatterStatWithYearDTO;
import bbpsp.backend.domain.dto.response.pitcherstat.PitcherStatDTO;
import bbpsp.backend.domain.dto.response.pitcherstat.PitcherStatNPlayerDTO;
import bbpsp.backend.domain.enums.PositionInfo;
import bbpsp.backend.domain.execption.*;
import bbpsp.backend.domain.repository.BatterStatRepository;
import bbpsp.backend.domain.repository.PlayerRepository;
import bbpsp.backend.domain.domain.persist.Season;
import bbpsp.backend.domain.repository.SeasonRepository;
import bbpsp.backend.domain.repository.TeamRepository;
import bbpsp.backend.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BatterStatService {
    private final BatterStatRepository batterStatRepository;
    private final PlayerRepository playerRepository;
    private final SeasonRepository seasonRepository;
    private final TeamRepository teamRepository;

    public List<BatterStatNPlayerDTO> findAllByYear(int year, int offset, int limit) {
        Season season = seasonRepository.findByYear(year)
                .orElseThrow(() -> new NoSuchSeasonException(ErrorCode.NO_SUCH_SEASON));

        PageRequest pageRequest = PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, "name"));
        List<BatterStatNPlayerDTO> list = new ArrayList<>();
        playerRepository.findPlayerByYear(season.getId(), pageRequest)
                .stream()
                .filter(p -> !p.getPosition().equals(PositionInfo.P))
                .forEach(p -> {
                    BatterStat batterStat = batterStatRepository.findById(p.getBatterStat().getId())
                            .orElseThrow(() -> new NoSuchBatterStatException(ErrorCode.NO_SUCH_BATTER_STAT));
                    BatterStatDTO batterStatDTO = BatterStatDTO.createBatterStatDTO(batterStat);
                    PlayerDTO playerDTO = PlayerDTO.createPlayerDTO(p);

                    list.add(BatterStatNPlayerDTO.createDTO(batterStatDTO, playerDTO));
                });
        return list;
    }

    public BatterAllStatNInfoDTO findAllWithOneBatter(String name, LocalDate birth) {
        List<BatterStatWithYearDTO> batterStatWithYearDTOList = new ArrayList<>();
        List<PlayerDTO> playerDTOList = new ArrayList<>();
        playerRepository.findByNameAndBirth(name, birth)
                .stream()
                .filter(p -> !p.getPosition().equals(PositionInfo.P))
                .forEach(p -> {
                    if (p.getTeam().getSeason().getYear() == 2021) {
                        playerDTOList.add(PlayerDTO.createPlayerDTO(p));
                    }
                    BatterStat batterStat = batterStatRepository.findById(p.getBatterStat().getId())
                            .orElseThrow(() -> new NoSuchBatterStatException(ErrorCode.NO_SUCH_BATTER_STAT));
                    batterStatWithYearDTOList.add(BatterStatWithYearDTO.createBatterStatDTO(batterStat, p.getTeam().getSeason().getYear(), p.getTeam().getName()));
                });
        return BatterAllStatNInfoDTO.createDTO(batterStatWithYearDTOList, playerDTOList.get(0));
    }


    public BatterStatNPlayerDTO findOneByUniqueIdAndYear(int year, String name, LocalDate birth) {
        Season season = seasonRepository.findByYear(year)
                .orElseThrow(() -> new NoSuchSeasonException(ErrorCode.NO_SUCH_SEASON));
        Player player = playerRepository.findByNameAndBirth(name, birth)
                .stream()
                .filter(p -> p.getTeam().getSeason().getId().equals(season.getId()) && !p.getPosition().equals(PositionInfo.P))
                .findFirst()
                .orElseThrow(() -> new NoSuchPlayerException(ErrorCode.NO_SUCH_PLAYER));
        BatterStat batterStat = batterStatRepository.findById(player.getBatterStat().getId())
                .orElseThrow(() -> new NoSuchBatterStatException(ErrorCode.NO_SUCH_BATTER_STAT));

        PlayerDTO playerDTO = PlayerDTO.createPlayerDTO(player);
        BatterStatDTO batterStatDTO = BatterStatDTO.createBatterStatDTO(batterStat);
        return BatterStatNPlayerDTO.createDTO(batterStatDTO, playerDTO);
    }

    public List<BatterStatNPlayerDTO> findAllByTeam(int year, String symbol) {
        Team team = teamRepository.findOneByYearAndSymbol(year, symbol)
                .orElseThrow(() -> new NoSuchTeamException(ErrorCode.NO_SUCH_TEAM));
        List<BatterStatNPlayerDTO> batterStatNPlayerDTOList = new ArrayList<>();
        playerRepository.findAllByTeamId(team.getId())
                .stream()
                .filter(player -> !(player.getPosition().equals(PositionInfo.P)))
                .forEach(player -> {
                    BatterStat batterStat = batterStatRepository.findById(player.getBatterStat().getId())
                            .orElseThrow(() -> new NoSuchBatterStatException(ErrorCode.NO_SUCH_BATTER_STAT));
                    BatterStatDTO batterStatDTO = BatterStatDTO.createBatterStatDTO(batterStat);
                    PlayerDTO playerDTO = PlayerDTO.createPlayerDTO(player);
                    batterStatNPlayerDTOList.add(BatterStatNPlayerDTO.createDTO(batterStatDTO, playerDTO));
                });
        return batterStatNPlayerDTOList;
    }

    public List<BatterStatNPlayerDTO> findRightBatter(PlayerRangeDTO playerRangeDTO, int year) {
        List<BatterStatNPlayerDTO> batterStatNPlayerDTOList = new ArrayList<>();
        if (playerRangeDTO.getPositionList().contains(PositionInfo.P)) {
            throw new IllegalPositionSelectException(ErrorCode.MUST_NOT_EXIST_PITCHER);
        }
        playerRepository.findBattersByAgeWithTeam(playerRangeDTO.getAgeStart(), playerRangeDTO.getAgeEnd(), year)
                .stream()
                .filter(player -> playerRangeDTO.getSalaryStart() <= player.getSalary() && player.getSalary() <= playerRangeDTO.getSalaryEnd())
                .filter(player -> playerRangeDTO.getTeamList().contains(player.getTeam().getName()))
                .filter(player -> playerRangeDTO.getPositionList().contains(player.getPosition()))
                .forEach(player -> {
                    BatterStat batterStat = batterStatRepository.findById(player.getBatterStat().getId())
                            .orElseThrow(() -> new NoSuchBatterStatException(ErrorCode.NO_SUCH_BATTER_STAT));
                    BatterStatDTO batterStatDTO = BatterStatDTO.createBatterStatDTO(batterStat);
                    PlayerDTO playerDTO = PlayerDTO.createPlayerDTO(player);
                    batterStatNPlayerDTOList.add(BatterStatNPlayerDTO.createDTO(batterStatDTO, playerDTO));
                });
        return batterStatNPlayerDTOList;
    }

    public List<BatterStatNPlayerDTO> recommendBatter(int year, String name, LocalDate birth) {
        List<BatterStatNPlayerDTO> dtoList = new ArrayList<>();
        Season season = seasonRepository.findByYear(year)
                .orElseThrow(() -> new NoSuchSeasonException(ErrorCode.NO_SUCH_SEASON));
        Player player = playerRepository.findByNameAndBirthWithYear(name, birth, season.getId())
                .orElseThrow(() -> new NoSuchPlayerException(ErrorCode.NO_SUCH_PLAYER));
        BatterStat batterStat = player.getBatterStat();
        playerRepository.findAllByYearWithStat(season.getId())
                .stream()
                .filter(p -> !p.getPosition().equals(PositionInfo.P))
                .filter(p -> !(p.getName().equals(player.getName()) && p.getBirth().equals(player.getBirth())))
                .filter(p -> {
                    ArrayList<PositionInfo> infielderList = makeInfielderList();
                    if (infielderList.contains(player.getPosition())) {
                        return p.getPosition().equals(PositionInfo.IF)
                                || p.getPosition().equals(player.getPosition());
                    } else {
                        return p.getPosition().equals(PositionInfo.OF);
                    }
                })
                .filter(p -> p.getBatterStat().getPA() >= batterStat.getPA() * 2 / 3)
                .filter(p -> p.getBatterStat().getH() >= batterStat.getH()
                        || p.getBatterStat().getHR() >= batterStat.getHR()
                        || p.getBatterStat().getOBP() >= batterStat.getOBP()
                        || p.getBatterStat().getOBP() + p.getBatterStat().getSLG() >= batterStat.getOBP() + batterStat.getSLG())
                .forEach(p -> {
                    PlayerDTO playerDTO = PlayerDTO.createPlayerDTO(p);
                    BatterStatDTO batterStatDTO = BatterStatDTO.createBatterStatDTO(p.getBatterStat());
                    dtoList.add(BatterStatNPlayerDTO.createDTO(batterStatDTO, playerDTO));
                });
        return dtoList;
    }

    private ArrayList<PositionInfo> makeInfielderList() {
        ArrayList<PositionInfo> list = new ArrayList<>();
        list.add(PositionInfo._1B);
        list.add(PositionInfo._2B);
        list.add(PositionInfo._3B);
        list.add(PositionInfo.SS);
        list.add(PositionInfo.IF);
        return list;
    }
}
