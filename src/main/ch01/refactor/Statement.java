package ch01.refactor;

import ch01.dto.Invoice;
import ch01.dto.Performance;
import ch01.dto.Play;
import ch01.dto.Plays;

import java.text.NumberFormat;
import java.util.Locale;

public class Statement {
    private final Plays plays;

    public Statement(Plays plays) {
        this.plays = plays;
    }

    public String statement(Invoice invoice, Plays plays) throws Exception {
        int totalAmount = 0;
        int volumeCredits = 0;
        String result = String.format("청구 내역 (고객명: %s)\n", invoice.customer());
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        numberFormat.setMaximumFractionDigits(2);

        for(Performance perf: invoice.performances()) {
            Play play = playFor(perf);

            // 포인트를 적립한다.
            volumeCredits += Math.max(perf.audience() - 30, 0);
            // 희극 관객 5명마다 추가 포인트를 제공한다.
            if("comedy".equals(play.type())) volumeCredits += (int) Math.floor((double) perf.audience() / 5);

            // 청구 내역을 출력한다.
            result += String.format("  %s: %s (%d석)\n", playFor(perf).name(), numberFormat.format(amountFor(perf)/100), perf.audience());
            totalAmount += amountFor(perf);
        }

        result += String.format("총액: %s\n", numberFormat.format(totalAmount/100));
        result += String.format("적립 포인트: %d점\n", volumeCredits);

        return result;
    }

    // 값이 바뀌지 않는 변수는 매개변수로 전달
    private int amountFor(Performance perf) throws Exception {
        int thisAmount = 0;  // 변수를 초기화하는 코드
        switch(playFor(perf).type()) {
            case "tragedy": // 비극
                thisAmount = 40000;
                if(perf.audience() > 30) {
                    thisAmount += 1000 * (perf.audience() - 30);
                }
                break;
            case "comedy": // 희극
                thisAmount = 30000;
                if(perf.audience() > 20) {
                    thisAmount += 10000 + 500 * (perf.audience() - 20);
                }
                thisAmount += 300 * perf.audience();
                break;
            default:
                throw new Exception(String.format("알 수 없는 장르: %s", playFor(perf).type()));
        }

        return thisAmount;
    }

    private Play playFor(Performance aPerformance) {
        return plays.getPlayById(aPerformance.playID());
    }
}
