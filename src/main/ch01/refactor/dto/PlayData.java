package ch01.refactor.dto;

import java.util.Objects;

public record PlayData(
        String name,
        int amount,
        int seats
) {
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PlayData playData = (PlayData) o;
        return seats == playData.seats && amount == playData.amount && Objects.equals(name, playData.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amount, seats);
    }
}
