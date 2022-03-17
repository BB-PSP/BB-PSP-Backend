package bbpsp.backend.domain.service;

import bbpsp.backend.domain.domain.persist.BatterStat;
import bbpsp.backend.domain.domain.persist.PitcherStat;
import bbpsp.backend.domain.domain.persist.Player;
import bbpsp.backend.domain.domain.persist.Season;
import bbpsp.backend.domain.dto.response.*;
import bbpsp.backend.domain.enums.PositionInfo;
import bbpsp.backend.domain.execption.NoSuchBatterStatException;
import bbpsp.backend.domain.execption.NoSuchPitcherStatException;
import bbpsp.backend.domain.execption.NoSuchPlayerException;
import bbpsp.backend.domain.execption.NoSuchSeasonException;
import bbpsp.backend.domain.repository.BatterStatRepository;
import bbpsp.backend.domain.repository.PitcherStatRepository;
import bbpsp.backend.domain.repository.PlayerRepository;
import bbpsp.backend.domain.repository.SeasonRepository;
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

    public List<PitcherStatNPlayerDTO> findAllByYear(int year, int offset, int limit) {
        Season season = seasonRepository.findByYear(year)
                .orElseThrow(() -> new NoSuchSeasonException(ErrorCode.NO_SUCH_SEASON));

        PageRequest pageRequest = PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, "name"));
        List<PitcherStatNPlayerDTO> pitcherStatNPlayerDTOList = new ArrayList<>();
        playerRepository.findPlayerByYear(season.getId(), pageRequest)
                .stream()
                .filter(p -> p.getPosition().equals(PositionInfo.PITCHER))
                .forEach(p -> {
                    PitcherStat pitcherStat = pitcherStatRepository.findById(p.getPitcherStat().getId())
                            .orElseThrow(() -> new NoSuchPitcherStatException(ErrorCode.NO_SUCH_PITCHER_STAT));
                    PitcherStatDTO pitcherStatDTO = PitcherStatDTO.createDTO(pitcherStat);
                    PlayerDTO playerDTO = PlayerDTO.createPlayerDTO(p);

                    pitcherStatNPlayerDTOList.add(PitcherStatNPlayerDTO.createDTO(pitcherStatDTO, playerDTO));
                });
        return pitcherStatNPlayerDTOList;
    }

    public PlayerInfoNPitcherAllStatDTO findAllWithOnePitcher(String name, LocalDate birth) {
        List<PitcherStatWithYearDTO> pitcherStatWithYearDTOList = new ArrayList<>();
        List<PlayerDTO> playerDTOList = new ArrayList<>();
        playerRepository.findByNameAndBirth(name, birth)
                .stream()
                .filter(p -> p.getPosition().equals(PositionInfo.PITCHER))
                .forEach(p -> {
                    if (p.getTeam().getSeason().getYear() == 2021) playerDTOList.add(PlayerDTO.createPlayerDTO(p));
                    PitcherStat pitcherStat = pitcherStatRepository.findById(p.getPitcherStat().getId())
                            .orElseThrow(() -> new NoSuchPitcherStatException(ErrorCode.NO_SUCH_PITCHER_STAT));
                    PitcherStatWithYearDTO pitcherStatWithYearDTO = PitcherStatWithYearDTO.createDTO(pitcherStat, p.getTeam().getSeason().getYear(), p.getTeam().getName());

                    pitcherStatWithYearDTOList.add(pitcherStatWithYearDTO);
                });
        return PlayerInfoNPitcherAllStatDTO.createDTO(pitcherStatWithYearDTOList, playerDTOList.get(0));
    }

    public PitcherStatNPlayerDTO findOneByBirthAndName(int year, String name, LocalDate birth) {
        Season season = seasonRepository.findByYear(year)
                .orElseThrow(() -> new NoSuchSeasonException(ErrorCode.NO_SUCH_SEASON));
        Player player = playerRepository.findByNameAndBirth(name, birth)
                .stream()
                .filter(p -> p.getTeam().getSeason().getId().equals(season.getId()) && p.getPosition().equals(PositionInfo.PITCHER))
                .findFirst()
                .orElseThrow(() -> new NoSuchPlayerException(ErrorCode.NO_SUCH_PLAYER));
        PitcherStat pitcherStat = pitcherStatRepository.findById(player.getPitcherStat().getId())
                .orElseThrow(() -> new NoSuchPitcherStatException(ErrorCode.NO_SUCH_PITCHER_STAT));

        PlayerDTO playerDTO = PlayerDTO.createPlayerDTO(player);
        PitcherStatDTO pitcherStatDTO = PitcherStatDTO.createDTO(pitcherStat);
        return PitcherStatNPlayerDTO.createDTO(pitcherStatDTO, playerDTO);
    }
}
