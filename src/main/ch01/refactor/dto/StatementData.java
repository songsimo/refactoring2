package ch01.refactor.dto;

import java.util.List;
import java.util.Objects;

public record StatementData(
        String customer,
        List<PlayData> performance,
        int totalAmount,
        int volumeCredits
) {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        StatementData that = (StatementData) o;
        return totalAmount == that.totalAmount && volumeCredits == that.volumeCredits && Objects.equals(customer, that.customer) && Objects.equals(performance, that.performance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customer, performance, totalAmount, volumeCredits);
    }
}
