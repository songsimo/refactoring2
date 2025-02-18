package ch04;

import java.util.List;

public class Province {
    private String name;
    private List<Producer> producers;
    private int totalProduction;
    private int demand;
    private int price;

    public Province(String name, List<Producer> producers, int totalProduction, int demand, int price) {
        this.name = name;
        this.producers = producers;
        this.totalProduction = totalProduction;
        this.demand = demand;
        this.price = price;
    }

    private void addProducer(Producer producer) {
        this.producers.add(producer);
        this.totalProduction += producer.getProduction();
    }

    public void addTotalProduction(int totalProduction) {
        this.totalProduction += totalProduction;
    }

    public int shortFall() {
        return this.demand - this.totalProduction;
    }

    public int profit() {
        return this.getDemandValue() - this.demandCost();
    }

    private int demandCost() {
        List<Producer> producerList = this.producers.stream()
                                            .sorted((o1, o2) -> o1.getCost() - o2.getCost())
                                            .toList();
        int remainingDemand = this.demand;
        int result = 0;

        for(Producer producer : producerList) {
            int constribution = Math.min(remainingDemand, producer.getProduction());
            remainingDemand -= constribution;
            result += constribution * producer.getCost();
        }

        return result;
    }

    private int getDemandValue() {
        return this.satisfiedDemand() * this.price;
    }

    private int satisfiedDemand() {
        return Math.min(this.demand, this.totalProduction);
    }

    public String getName() {
        return name;
    }

    public List<Producer> getProducers() {
        return producers;
    }

    public int getTotalProduction() {
        return totalProduction;
    }

    public void setTotalProduction(int totalProduction) {
        this.totalProduction = totalProduction;
    }

    public int getDemand() {
        return demand;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
