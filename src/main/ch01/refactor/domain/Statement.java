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
            result += new PerformanceCalculator(perf, playFor(perf)).volumeCreditsFor();
        }
        return result;
    }

    private int amountFor(Performance perf) throws Exception {
        return new PerformanceCalculator(perf, playFor(perf)).amount();
    }

    private Play playFor(Performance aPerformance) {
        return plays.getPlayById(aPerformance.playID());
    }
}
