package ch01.refactor.domain.calculator;

import ch01.refactor.domain.Performance;
import ch01.refactor.domain.Play;

public abstract class PerformanceCalculator implements Calculator {
    protected Performance performance;
    protected Play play;

    public PerformanceCalculator(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }

    public abstract int amount();

    public int volumeCredits() {
        return Math.max(performance.audience() - 30, 0);
    }
}
