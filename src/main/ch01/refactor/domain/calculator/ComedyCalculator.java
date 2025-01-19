package ch01.refactor.domain.calculator;

import ch01.refactor.domain.Performance;
import ch01.refactor.domain.Play;

public class ComedyCalculator extends PerformanceCalculator {
    public ComedyCalculator(Performance performance, Play play) {
        super(performance, play);
    }

    @Override
    public int amount() {
        int result = 30_000;

        if(performance.audience() > 20) {
            result += 10_000 + 500 * (performance.audience() - 20);
        }

        result += 300 * performance.audience();

        return result;
    }

    @Override
    public int volumeCredits() {
        return super.volumeCredits() + (int) Math.floor((double) performance.audience() / 5);
    }
}
