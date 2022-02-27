package bbpsp.backend.domain.domain.persist;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity @Getter
@Table(name = "pticher_stat")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PitcherStat {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pitcher_stat_id")
    private Long id;

    private Double ERA;
    private Long G;
    private Long SHO;
    private Long W;
    private Long L;
    private Long SV;
    private Long HLD;
    private Double WPCT;
    private Long TBF;
    private Double IP;
    private Long H;
    private Long HR;
    private Long BB;
    private Long HBP;
    private Long SO;
    private Long R;
    private Long ER;

    @OneToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "player_id")
    private Player player;

    public static PitcherStat createPitcherStat(Double ERA, Long G, Long SHO, Long W,
                                                Long L, Long SV, Long HLD, Double WPCT,
                                                Long TBF, Double IP, Long H, Long HR, Long BB, Long HBP,
                                                Long SO, Long R, Long ER, Player player) {
        return new PitcherStat(
                ERA, G, SHO, W, L, SV, HLD, WPCT, TBF, IP,
                H, HR, BB, HBP, SO, R, ER, player
        );
    }

    public void changePitcherStat(PitcherStat pitcherStat) {
        this.ERA = pitcherStat.getERA();
        this.G = pitcherStat.getG();
        this.SHO = pitcherStat.getSHO();
        this.W = pitcherStat.getW();
        this.L = pitcherStat.getL();
        this.SV = pitcherStat.getSV();
        this.HLD = pitcherStat.getHLD();
        this.WPCT = pitcherStat.getWPCT();
        this.TBF = pitcherStat.getTBF();
        this.IP = pitcherStat.getIP();
        this.H = pitcherStat.getH();
        this.HR = pitcherStat.getHR();
        this.BB = pitcherStat.getBB();
        this.HBP = pitcherStat.getHBP();
        this.SO = pitcherStat.getSO();
        this.R = pitcherStat.getR();
        this.ER = pitcherStat.getER();
        this.player = pitcherStat.getPlayer();
    }

    private PitcherStat(Double ERA, Long G, Long SHO, Long W,
                       Long L, Long SV, Long HLD, Double WPCT,
                       Long TBF, Double IP, Long H, Long HR, Long BB, Long HBP,
                       Long SO, Long R, Long ER, Player player) {
        this.ERA = ERA;
        this.G = G;
        this.SHO = SHO;
        this.W = W;
        this.L = L;
        this.SV = SV;
        this.HLD = HLD;
        this.WPCT = WPCT;
        this.TBF = TBF;
        this.IP = IP;
        this.H = H;
        this.HR = HR;
        this.BB = BB;
        this.HBP = HBP;
        this.SO = SO;
        this.R = R;
        this.ER = ER;
        this.player = player;
    }
}
