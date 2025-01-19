package ch01.refactor;

import ch01.dto.Invoice;
import ch01.dto.Performance;
import ch01.dto.Play;
import ch01.dto.Plays;

import java.text.NumberFormat;
import java.util.Locale;

public class Statement {
    private final Invoice invoice;
    private final Plays plays;

    public Statement(Invoice invoice, Plays plays) {
        this.invoice = invoice;
        this.plays = plays;
    }

    public String statement(Invoice invoice) throws Exception {
        int totalAmount = 0;
        String result = String.format("청구 내역 (고객명: %s)\n", invoice.customer());

        for(Performance perf: invoice.performances()) {
            // 청구 내역을 출력한다.
            result += String.format("  %s: %s (%d석)\n", playFor(perf).name(), usd(amountFor(perf)), perf.audience());
            totalAmount += amountFor(perf);
        }

        result += String.format("총액: %s\n", usd(totalAmount));
        result += String.format("적립 포인트: %d점\n", totalVolumeCredits());

        return result;
    }

    private int totalVolumeCredits() {
        int volumeCredits = 0;
        for(Performance perf: invoice.performances()) {
            volumeCredits += volumeCreditsFor(perf);
        }
        return volumeCredits;
    }

    private int volumeCreditsFor(Performance aPerformance) {
        int volumeCredits = 0;

        volumeCredits += Math.max(aPerformance.audience() - 30, 0);
        if("comedy".equals(playFor(aPerformance).type())) volumeCredits += (int) Math.floor((double) aPerformance.audience() / 5);

        return volumeCredits;
    }

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

    private String usd(long aNumber) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        numberFormat.setMaximumFractionDigits(2);

        return numberFormat.format(aNumber / 100);
    }
}
