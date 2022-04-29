package bbpsp.backend.domain.enums.batterweight;

public enum WeightAVG {

    Age(1.0961e-03),
    OBP(5.0474e-01),
    SLG(1.0687e-01),
    G(2.3839e-04),
    PA(3.8193e-04),
    AB(-7.1149e-04),
    R(-1.9901e-04),
    H(1.1311e-03),
    _1B(5.8586e-04),
    _2B(1.6308e-04),
    _3B(6.0522e-04),
    HR(-2.2307e-04),
    RBI(-3.7155e-05),
    SB(-1.2485e-04),
    CS(7.0357e-04),
    BB(-1.3923e-03),
    HBP(-1.3830e-03),
    IBB(-3.8686e-04),
    SO(1.4426e-04),
    GDP(9.2574e-05),
    SH(-2.7035e-04),
    SF(2.2683e-04);

    private final double value;

    WeightAVG(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
