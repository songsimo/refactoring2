package ch01.refactor.domain;

import ch01.refactor.dto.PlayData;
import ch01.refactor.dto.StatementData;

import java.util.ArrayList;
import java.util.List;

public class Statement {
    private final Invoice invoice;
    private final Plays plays;

    public Statement(Invoice invoice, Plays plays) {
        this.invoice = invoice;
        this.plays = plays;
    }

    public StatementData statement() throws Exception {
        return new StatementData(invoice.customer(), getPlay(), totalAmount(), totalVolumeCredits());
    }

    private List<PlayData> getPlay() throws Exception {
        List<PlayData> playDataList = new ArrayList<>();

        for(Performance perf: invoice.performances()) {
            String name = playFor(perf).name();
            int amount = amountFor(perf);
            int seats = perf.audience();

            playDataList.add(new PlayData(name, amount, seats));
        }

        return playDataList;
    }

    private int totalAmount() throws Exception {
        int result = 0;
        for(Performance perf: invoice.performances()) {
            result += amountFor(perf);
        }

        return result;
    }

    private int totalVolumeCredits() {
        int result = 0;
        for(Performance perf: invoice.performances()) {
            result += volumeCreditsFor(perf);
        }
        return result;
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
}
