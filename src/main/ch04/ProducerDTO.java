package ch04;

public record ProducerDTO(
        String name,
        int cost,
        int production
) {
    public Producer toProducer() {
        return new Producer(null, name, cost, production);
    }
}
