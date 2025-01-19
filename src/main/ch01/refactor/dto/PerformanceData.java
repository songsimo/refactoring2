package ch01.refactor.dto;

import java.util.Objects;

public record PerformanceData(
        String playID,
        int audience
) {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PerformanceData that = (PerformanceData) o;
        return audience == that.audience && Objects.equals(playID, that.playID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playID, audience);
    }
}
