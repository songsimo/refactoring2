package ch04;

public class Producer {
    private Province province;
    private String name;
    private int cost;
    private int production;

    public Producer(Province province, String name, int cost, int production) {
        this.province = province;
        this.name = name;
        this.cost = cost;
        this.production = production;
    }

    public Producer(Province province, Producer producer) {
        this(producer);
        this.province = province;
    }

    private Producer(Producer producer) {
        this.province = producer.province;
        this.cost = producer.cost;
        this.name = producer.name;
        this.production = producer.production;
    }

    public int getProduction() {
        return production;
    }

    public void setProduction(Integer production) {
        final int amount = production == null ? 0 : production;
        this.province.addTotalProduction(amount - this.production);
        this.production = amount;
    }

    public Province getProvince() {
        return province;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }
}
