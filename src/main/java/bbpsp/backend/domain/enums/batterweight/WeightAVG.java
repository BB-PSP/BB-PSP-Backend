package bbpsp.backend.domain.enums.batterweight;

public enum WeightAVG {

    Age(1.14001536e-06),
    OBP(4.27877646e-01),
    SLG(1.40510622e-01),
    G(9.11344342e-05),
    PA(2.55649351e-04),
    AB(-4.19706014e-04),
    R(-2.37105700e-05),
    H(4.48246849e-04),
    _1B(4.92285552e-04),
    _2B(2.56070581e-04),
    _3B(-3.28206504e-05),
    HR(-2.67288633e-04),
    RBI(-3.29033919e-06),
    SB(-2.44525468e-05),
    CS(2.59355509e-05),
    BB(-7.33110567e-04),
    HBP(-7.55809287e-04),
    IBB(-2.08593352e-05),
    SO(1.15983241e-06),
    GDP(-4.04268358e-05),
    SH(-2.10677817e-04),
    SF(5.76353518e-05);

    private final double value;

    WeightAVG(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
