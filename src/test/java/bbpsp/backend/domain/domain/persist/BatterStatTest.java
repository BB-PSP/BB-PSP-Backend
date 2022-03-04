package bbpsp.backend.domain.domain.persist;

import bbpsp.backend.domain.enums.BatInfo;
import bbpsp.backend.domain.enums.PitchInfo;
import bbpsp.backend.domain.enums.PositionInfo;
import bbpsp.backend.domain.repository.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootTest
class BatterStatTest {
    @Autowired SeasonRepository seasonRepository;
    @Autowired PlayerRepository playerRepository;
    @Autowired TeamRepository teamRepository;
    @Autowired BatterStatRepository batterStatRepository;



    @Test
    public void init() throws Exception {
        Season season = Season.createSeason(2021);
        seasonRepository.save(season);
        Season season2 = Season.createSeason(2020);
        seasonRepository.save(season2);

        Team team = Team.createTeam(season, "Hanwha Eagles", "대전", "logo.url.colour", "logo.url.black",  "수베로",
                "www.hw.com", 1983, "주황", 1, 2021,
                10, 30, 0, "--");
        teamRepository.save(team);
        Team team2 = Team.createTeam(season2, "Hanwha Eagles", "대전", "logo.url.colour", "logo.url.black",  "수베로",
                "www.hw.com", 1983, "주황", 1, 2021,
                10, 30, 0, "--");
        teamRepository.save(team2);

        Team team3 = Team.createTeam(season, "Hanwha Eagles2", "대전2", "logo.url.colour", "logo.url.black",  "수베로",
                "www.hw.com", 1983, "주황", 1, 2021,
                10, 30, 0, "--");
        teamRepository.save(team3);

        Player player = Player.createPlayer(team, "강민재", "image.url", 27, LocalDate.of(1993, 1, 1),
                PositionInfo.PITCHER, BatInfo.RIGHT_HAND, PitchInfo.RIGHT_UNDER, 180.0, 75.0, "oo고-xx대",
                10000, 3, "35", null, null);
        playerRepository.save(player);

        Player player2 = Player.createPlayer(team2, "강민재", "image.url2222", 27, LocalDate.of(1993, 1, 1),
                PositionInfo.PITCHER, BatInfo.RIGHT_HAND, PitchInfo.RIGHT_UNDER, 180.0, 75.0, "oo고-ㅁㅁ대",
                10000, 3, "35", null, null);
        playerRepository.save(player2);


    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void playerOne2OneTest() throws Exception {
        // given
        Player findPlayer = playerRepository.findAllByName("강민재").get(0);
        BatterStat batterStat = BatterStat.createBatterStat(144, 0.321, 321, 300, 10, 152,
                30, 5, 24, 230, 77, 9, 3, 79, 8, 132,
                5, 0.643, 0.389, 4);
        batterStatRepository.save(batterStat);
        // when
        Player player = playerRepository.findById(findPlayer.getId()).get();
        System.out.println("=================");
        // then
        System.out.println("player.getBatterStat() = " + player.getBatterStat());
    }


}