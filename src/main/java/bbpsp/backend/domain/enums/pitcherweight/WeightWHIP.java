package bbpsp.backend.domain.enums.pitcherweight;

public enum WeightWHIP {

    Age(1),
    W(1),
    L(1),
    ERA(1),
    G(1),
    GS(1),
    CG(1),
    ShO(1),
    SV(1),
    BS(1),
    HLD(1),
    IP(1),
    TBF(1),
    H(1),
    R(1),
    ER(1),
    HR(1),
    BB(1),
    IBB(1),
    HBP(1),
    WP(1),
    BK(1),
    SO(1),
    K_9(1),
    BABIP(1),
    LOB_PCT(1),
    FIP(1);

    private final double value;

    WeightWHIP(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
