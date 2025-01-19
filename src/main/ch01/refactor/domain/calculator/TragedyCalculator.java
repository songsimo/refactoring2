package ch01.refactor.domain.calculator;

import ch01.refactor.domain.Performance;
import ch01.refactor.domain.Play;

public class TragedyCalculator extends PerformanceCalculator {
    public TragedyCalculator(Performance performance, Play play) {
        super(performance, play);
    }

    @Override
    public int amount() {
        int result = 40_000;

        if(performance.audience() > 30) {
            result += 1_000 * (performance.audience() - 30);
        }

        return result;
    }
}
