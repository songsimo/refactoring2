package ch04;

public class Producer {
    private Province province;
    private int cost;
    private String name;
    private int production;


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
