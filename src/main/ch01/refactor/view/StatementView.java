package ch01.refactor.view;

import ch01.refactor.dto.PlayData;
import ch01.refactor.dto.StatementData;

import java.text.NumberFormat;
import java.util.Locale;

public class StatementView {
    public String htmlStatement(StatementData data) {
        return readerHtml(data);
    }

    private String readerHtml(StatementData data) {
        StringBuilder result = new StringBuilder(String.format("<h1>청구 내역 (고객명: %s)</h1>\n", data.customer()));
        result.append("<table>\n");
        result.append("<tr><th>연극</th><th>좌석 수</th><th>금액</th></tr>\n");

        for(PlayData playData: data.performance()) {
            result.append(String.format("  <tr><td>%s</td><td>%d석</td>", playData.name(), playData.seats()));
            result.append(String.format("<td>%s</td></tr>\n", usd(playData.amount())));
        }

        result.append("</table>\n");
        result.append(String.format("<p>총액: <em>%s</em></p>\n", usd(data.totalAmount())));
        result.append(String.format("<p>적립 포인트: <em>%d점</em></p>\n", data.volumeCredits()));

        return result.toString();
    }

    private String usd(long aNumber) {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        numberFormat.setMaximumFractionDigits(2);

        return numberFormat.format(aNumber / 100);
    }
}
