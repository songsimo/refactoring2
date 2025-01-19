package ch01.refactor.domain;

public class PerformanceCalculator {
    private Performance performance;
    private Play play;

    public PerformanceCalculator(Performance performance, Play play) {
        this.performance = performance;
        this.play = play;
    }

    public int amount() throws Exception {
        int result = 0;  // 변수를 초기화하는 코드
        switch(play.type()) {
            case "tragedy": // 비극
                result = 40000;
                if(performance.audience() > 30) {
                    result += 1000 * (performance.audience() - 30);
                }
                break;
            case "comedy": // 희극
                result = 30000;
                if(performance.audience() > 20) {
                    result += 10000 + 500 * (performance.audience() - 20);
                }
                result += 300 * performance.audience();
                break;
            default:
                throw new Exception(String.format("알 수 없는 장르: %s", play.type()));
        }

        return result;
    }

    public int volumeCreditsFor() {
        int volumeCredits = 0;

        volumeCredits += Math.max(performance.audience() - 30, 0);
        if("comedy".equals(play.type())) volumeCredits += (int) Math.floor((double) performance.audience() / 5);

        return volumeCredits;
    }
}
