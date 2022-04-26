package bbpsp.backend.domain.enums.etc;

public enum WeightwOBA {

    wLeagueAvgOBA(0.307),
    wOBAScale(1.329),
    wBB(0.696),
    wHBP(0.729),
    w1B(0.902),
    w2B(1.301),
    w3B(1.659),
    wHR(2.167),
    runSB(0.200),
    runCS(-0.381),
    R_PA(0.109),
    R_W(9.191),
    cFIP(3.074);


    private final double value;

    WeightwOBA(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
