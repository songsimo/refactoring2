package ch04;

import java.util.ArrayList;
import java.util.List;

public class Province {
    private String name;
    private List<Producer> producers;
    private int totalProduction;
    private int demand;
    private int price;

    public Province(ProvinceDTO provinceDTO) {
        this.name = provinceDTO.name();
        this.producers = new ArrayList<>();
        this.totalProduction = 0;
        this.demand = provinceDTO.demand();
        this.price = provinceDTO.price();
        provinceDTO.producers().forEach(this::addProducer);
    }

    private void addProducer(ProducerDTO producer) {
        this.producers.add(new Producer(this, producer.toProducer()));
        this.totalProduction += producer.production();
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
            int contribution = Math.min(remainingDemand, producer.getProduction());
            remainingDemand -= contribution;
            result += contribution * producer.getCost();
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

    public void setProduction(final int idx, final int production) {
        this.producers.get(idx).setProduction(production);
    }
}
