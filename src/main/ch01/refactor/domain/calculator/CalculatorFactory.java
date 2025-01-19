package ch01.refactor.domain.calculator;

import ch01.refactor.domain.Performance;
import ch01.refactor.domain.Play;

public class CalculatorFactory {
    public static PerformanceCalculator createPerformanceCalculator(Performance aPerformance, Play aPlay) {
        return switch (aPlay.type()) {
            case "tragedy" -> new TragedyCalculator(aPerformance, aPlay);
            case "comedy" -> new ComedyCalculator(aPerformance, aPlay);
            default -> throw new IllegalArgumentException(String.format("알 수 없는 장르: %s", aPlay.type()));
        };
    }
}
