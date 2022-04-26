package bbpsp.backend.domain.enums.batterweight;

public enum WeightSLG {

    Age(1),
    G(1),
    AB(1),
    PA(1),
    H(1),
    R(1),
    RBI(1),
    BB(1),
    IBB(1),
    SO(1),
    HBP(1),
    SF(1),
    SH(1),
    GDP(1),
    SB(1),
    CS(1),
    AVG(1),
    BB_PCT(1),
    K_PCT(1),
    OBP(1),
    BABIP(1),
    wOBA(1),
    wRAA(1),
    wRC(1);


    private final double value;

    WeightSLG(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
