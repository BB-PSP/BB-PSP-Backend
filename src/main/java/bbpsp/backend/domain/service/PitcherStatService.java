package bbpsp.backend.domain.service;

import bbpsp.backend.domain.domain.persist.*;
import bbpsp.backend.domain.dto.request.PlayerRangeDTO;
import bbpsp.backend.domain.dto.response.*;
import bbpsp.backend.domain.dto.response.pitcherstat.PitcherStatDTO;
import bbpsp.backend.domain.dto.response.pitcherstat.PitcherStatNPlayerDTO;
import bbpsp.backend.domain.dto.response.pitcherstat.PitcherStatWithYearDTO;
import bbpsp.backend.domain.dto.response.pitcherstat.PitcherAllStatNInfoDTO;
import bbpsp.backend.domain.enums.PositionInfo;
import bbpsp.backend.domain.execption.*;
import bbpsp.backend.domain.repository.PitcherStatRepository;
import bbpsp.backend.domain.repository.PlayerRepository;
import bbpsp.backend.domain.repository.SeasonRepository;
import bbpsp.backend.domain.repository.TeamRepository;
import bbpsp.backend.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PitcherStatService {

    private final PitcherStatRepository pitcherStatRepository;
    private final PlayerRepository playerRepository;
    private final SeasonRepository seasonRepository;
    private final TeamRepository teamRepository;

    public List<PitcherStatNPlayerDTO> findAllByYear(int year, int offset, int limit) {
        Season season = seasonRepository.findByYear(year)
                .orElseThrow(() -> new NoSuchSeasonException(ErrorCode.NO_SUCH_SEASON));

        PageRequest pageRequest = PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, "name"));
        List<PitcherStatNPlayerDTO> pitcherStatNPlayerDTOList = new ArrayList<>();
        playerRepository.findPlayerByYear(season.getId(), pageRequest)
                .stream()
                .filter(p -> p.getPosition().equals(PositionInfo.P))
                .forEach(p -> {
                    PitcherStat pitcherStat = pitcherStatRepository.findById(p.getPitcherStat().getId())
                            .orElseThrow(() -> new NoSuchPitcherStatException(ErrorCode.NO_SUCH_PITCHER_STAT));
                    PitcherStatDTO pitcherStatDTO = PitcherStatDTO.createDTO(pitcherStat);
                    PlayerDTO playerDTO = PlayerDTO.createPlayerDTO(p);

                    pitcherStatNPlayerDTOList.add(PitcherStatNPlayerDTO.createDTO(pitcherStatDTO, playerDTO));
                });
        return pitcherStatNPlayerDTOList;
    }

    public PitcherAllStatNInfoDTO findAllWithOnePitcher(String name, LocalDate birth) {
        List<PitcherStatWithYearDTO> pitcherStatWithYearDTOList = new ArrayList<>();
        List<PlayerDTO> playerDTOList = new ArrayList<>();
        playerRepository.findByNameAndBirth(name, birth)
                .stream()
                .filter(p -> p.getPosition().equals(PositionInfo.P))
                .forEach(p -> {
                    if (p.getTeam().getSeason().getYear() == 2021) {
                        playerDTOList.add(PlayerDTO.createPlayerDTO(p));
                    }
                    PitcherStat pitcherStat = pitcherStatRepository.findById(p.getPitcherStat().getId())
                            .orElseThrow(() -> new NoSuchPitcherStatException(ErrorCode.NO_SUCH_PITCHER_STAT));
                    PitcherStatWithYearDTO pitcherStatWithYearDTO = PitcherStatWithYearDTO.createDTO(pitcherStat, p.getTeam().getSeason().getYear(), p.getTeam().getName());

                    pitcherStatWithYearDTOList.add(pitcherStatWithYearDTO);
                });
        return PitcherAllStatNInfoDTO.createDTO(pitcherStatWithYearDTOList, playerDTOList.get(0));
    }

    public PitcherStatNPlayerDTO findOneByBirthAndName(int year, String name, LocalDate birth) {
        Season season = seasonRepository.findByYear(year)
                .orElseThrow(() -> new NoSuchSeasonException(ErrorCode.NO_SUCH_SEASON));
        Player player = playerRepository.findByNameAndBirth(name, birth)
                .stream()
                .filter(p -> p.getTeam().getSeason().getId().equals(season.getId()) && p.getPosition().equals(PositionInfo.P))
                .findFirst()
                .orElseThrow(() -> new NoSuchPlayerException(ErrorCode.NO_SUCH_PLAYER));
        PitcherStat pitcherStat = pitcherStatRepository.findById(player.getPitcherStat().getId())
                .orElseThrow(() -> new NoSuchPitcherStatException(ErrorCode.NO_SUCH_PITCHER_STAT));

        PlayerDTO playerDTO = PlayerDTO.createPlayerDTO(player);
        PitcherStatDTO pitcherStatDTO = PitcherStatDTO.createDTO(pitcherStat);
        return PitcherStatNPlayerDTO.createDTO(pitcherStatDTO, playerDTO);
    }

    public List<PitcherStatNPlayerDTO> findAllByTeam(int year, String symbol) {
        Team team = teamRepository.findOneByYearAndSymbol(year, symbol)
                .orElseThrow(() -> new NoSuchTeamException(ErrorCode.NO_SUCH_TEAM));
        List<PitcherStatNPlayerDTO> pitcherStatNPlayerDTOList = new ArrayList<>();
        playerRepository.findAllByTeamId(team.getId())
                .stream()
                .filter(player -> player.getPosition().equals(PositionInfo.P))
                .forEach(player -> {
                    PitcherStat pitcherStat = pitcherStatRepository.findById(player.getPitcherStat().getId())
                            .orElseThrow(() -> new NoSuchPitcherStatException(ErrorCode.NO_SUCH_PITCHER_STAT));
                    PitcherStatDTO pitcherStatDTO = PitcherStatDTO.createDTO(pitcherStat);
                    PlayerDTO playerDTO = PlayerDTO.createPlayerDTO(player);
                    pitcherStatNPlayerDTOList.add(PitcherStatNPlayerDTO.createDTO(pitcherStatDTO, playerDTO));
                });
        return pitcherStatNPlayerDTOList;
    }

    public List<PitcherStatNPlayerDTO> findRightPitcher(PlayerRangeDTO playerRangeDTO, int year) {
        if (!playerRangeDTO.getPositionList().contains(PositionInfo.P)) {
            throw new IllegalPositionSelectException(ErrorCode.MUST_NOT_EXIST_FIELDER);
        }
        List<PitcherStatNPlayerDTO> pitcherStatNPlayerDTOList = new ArrayList<>();
        playerRepository.findPitchersByAgeWithTeam(playerRangeDTO.getAgeStart(), playerRangeDTO.getAgeEnd(), year)
                .stream()
                .filter(player -> playerRangeDTO.getSalaryStart() <= player.getSalary() && player.getSalary() <= playerRangeDTO.getSalaryEnd())
                .filter(player -> playerRangeDTO.getTeamList().contains(player.getTeam().getName()))
                .filter(player -> playerRangeDTO.getPositionList().contains(player.getPosition()))
                .forEach(player -> {
                    PitcherStat pitcherStat = pitcherStatRepository.findById(player.getPitcherStat().getId())
                            .orElseThrow(() -> new NoSuchPitcherStatException(ErrorCode.NO_SUCH_PITCHER_STAT));
                    PitcherStatDTO pitcherStatDTO = PitcherStatDTO.createDTO(pitcherStat);
                    PlayerDTO playerDTO = PlayerDTO.createPlayerDTO(player);
                    pitcherStatNPlayerDTOList.add(PitcherStatNPlayerDTO.createDTO(pitcherStatDTO, playerDTO));
                });
        return pitcherStatNPlayerDTOList;
    }

    public List<PitcherStatNPlayerDTO> recommendPitcher(int year, String name, LocalDate birth) {
        List<PitcherStatNPlayerDTO> dtoList = new ArrayList<>();
        Season season = seasonRepository.findByYear(year)
                .orElseThrow(() -> new NoSuchSeasonException(ErrorCode.NO_SUCH_SEASON));
        Player player = playerRepository.findByNameAndBirthWithYear(name, birth, season.getId())
                .orElseThrow(() -> new NoSuchPlayerException(ErrorCode.NO_SUCH_PLAYER));
        PitcherStat pitcherStat = player.getPitcherStat();
        if (pitcherStat.getIP() >= 100) { // 투수가 선발이라고 간주
            playerRepository.findAllByYearWithStat(season.getId())
                    .stream()
                    .filter(p -> p.getPosition().equals(PositionInfo.P))
                    .filter(p -> !(p.getName().equals(player.getName()) && p.getBirth().equals(player.getBirth())))
                    .filter(p -> p.getPitcherStat().getIP() >= 100)
                    .filter(p -> p.getPitcherStat().getW() >= pitcherStat.getW()
                            || p.getPitcherStat().getSO() >= pitcherStat.getSO()
                            || p.getPitcherStat().getERA() <= pitcherStat.getERA()
                            || (p.getPitcherStat().getBB() + p.getPitcherStat().getHBP() < pitcherStat.getBB() + pitcherStat.getHBP()
                                    && p.getPitcherStat().getIP() >= pitcherStat.getIP())
                    )
                    .forEach(p -> {
                        PlayerDTO playerDTO = PlayerDTO.createPlayerDTO(p);
                        PitcherStatDTO pitcherStatDTO = PitcherStatDTO.createDTO(p.getPitcherStat());
                        dtoList.add(PitcherStatNPlayerDTO.createDTO(pitcherStatDTO, playerDTO));
                    });
        } else { // 투수가 불펜이라고 간주
            playerRepository.findAllByYearWithStat(season.getId())
                    .stream()
                    .filter(p -> p.getPosition().equals(PositionInfo.P))
                    .filter(p -> !(p.getName().equals(player.getName()) && p.getBirth().equals(player.getBirth())))
                    .filter(p -> p.getPitcherStat().getIP() < 100)
                    .filter(p -> {
                        if (pitcherStat.getSV() >= pitcherStat.getHLD()) {
                            return p.getPitcherStat().getSV() > pitcherStat.getSV();
                        } else {
                            return p.getPitcherStat().getHLD() > pitcherStat.getHLD();
                        }
                    })
                    .filter(p -> p.getPitcherStat().getIP() > pitcherStat.getIP() && p.getPitcherStat().getWHIP() <= pitcherStat.getWHIP())
                    .forEach(p -> {
                        PlayerDTO playerDTO = PlayerDTO.createPlayerDTO(p);
                        PitcherStatDTO pitcherStatDTO = PitcherStatDTO.createDTO(p.getPitcherStat());
                        dtoList.add(PitcherStatNPlayerDTO.createDTO(pitcherStatDTO, playerDTO));
                    });
        }

        return dtoList;
    }
}
