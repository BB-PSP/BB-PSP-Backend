package bbpsp.backend.domain.service;

import bbpsp.backend.domain.domain.persist.BatterStat;
import bbpsp.backend.domain.domain.persist.Player;
import bbpsp.backend.domain.dto.response.BatterStatDTO;
import bbpsp.backend.domain.dto.response.BatterStatNPlayerDTO;
import bbpsp.backend.domain.dto.response.PlayerDTO;
import bbpsp.backend.domain.enums.PositionInfo;
import bbpsp.backend.domain.execption.NoSuchBatterStatException;
import bbpsp.backend.domain.execption.NoSuchPlayerException;
import bbpsp.backend.domain.repository.BatterStatRepository;
import bbpsp.backend.domain.repository.PlayerRepository;
import bbpsp.backend.domain.execption.NoSuchSeasonException;
import bbpsp.backend.domain.domain.persist.Season;
import bbpsp.backend.domain.repository.SeasonRepository;
import bbpsp.backend.global.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BatterStatService {
    private final BatterStatRepository batterStatRepository;
    private final PlayerRepository playerRepository;
    private final SeasonRepository seasonRepository;

    public List<BatterStatNPlayerDTO> findAllByYear(int year, int offset, int limit) {
        Season season = seasonRepository.findByYear(year)
                .orElseThrow(() -> new NoSuchSeasonException(ErrorCode.NO_SUCH_SEASON));

        PageRequest pageRequest = PageRequest.of(offset, limit, Sort.by(Sort.Direction.ASC, "name"));
        List<BatterStatNPlayerDTO> list = new ArrayList<>();
        playerRepository.findPlayerByYear(season.getId(), pageRequest)
                .stream()
                .filter(p -> p.getPosition().equals(PositionInfo.BATTER))
                .forEach(p -> {
                    BatterStat batterStat = batterStatRepository.findById(p.getBatterStat().getId())
                            .orElseThrow(() -> new NoSuchBatterStatException(ErrorCode.NO_SUCH_BATTER_STAT));
                    BatterStatDTO batterStatDTO = BatterStatDTO.createBatterStatDTO(batterStat);
                    PlayerDTO playerDTO = PlayerDTO.createPlayerDTO(p);

                    list.add(BatterStatNPlayerDTO.createDTO(batterStatDTO, playerDTO));
                });
        return list;
    }

    public List<BatterStatNPlayerDTO> findAllWithOneBatter(Long uniqueId) {
        List<BatterStatNPlayerDTO> batterStatNPlayerDTOList = new ArrayList<>();
        playerRepository.findByUniqueId(uniqueId)
                .stream()
                .filter(p -> p.getPosition().equals(PositionInfo.BATTER))
                .forEach(p -> {
                    BatterStat batterStat = batterStatRepository.findById(p.getBatterStat().getId())
                            .orElseThrow(() -> new NoSuchBatterStatException(ErrorCode.NO_SUCH_BATTER_STAT));
                    BatterStatDTO batterStatDTO = BatterStatDTO.createBatterStatDTO(batterStat);
                    PlayerDTO playerDTO = PlayerDTO.createPlayerDTO(p);
                    batterStatNPlayerDTOList.add(BatterStatNPlayerDTO.createDTO(batterStatDTO, playerDTO));
                });
        return batterStatNPlayerDTOList;
    }


    public BatterStatNPlayerDTO findOneByUniqueIdAndYear(int year, Long uniqueId) {
        Season season = seasonRepository.findByYear(year)
                .orElseThrow(() -> new NoSuchSeasonException(ErrorCode.NO_SUCH_SEASON));
        Player player = playerRepository.findByUniqueId(uniqueId)
                .stream()
                .filter(p -> p.getSeason().getId().equals(season.getId()) && p.getPosition().equals(PositionInfo.BATTER))
                .findFirst()
                .orElseThrow(() -> new NoSuchPlayerException(ErrorCode.NO_SUCH_PLAYER));
        BatterStat batterStat = batterStatRepository.findById(player.getBatterStat().getId())
                .orElseThrow(() -> new NoSuchBatterStatException(ErrorCode.NO_SUCH_BATTER_STAT));

        PlayerDTO playerDTO = PlayerDTO.createPlayerDTO(player);
        BatterStatDTO batterStatDTO = BatterStatDTO.createBatterStatDTO(batterStat);
        return new BatterStatNPlayerDTO(batterStatDTO, playerDTO);
    }
}
