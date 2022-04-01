package uk.natwest.friut.domain;

import java.math.BigDecimal;

public class FruitBasket {
    private String name;
    private int count;
    private BigDecimal cost;

    public FruitBasket(String name, int count, BigDecimal cost) {
        this.name = name;
        this.count = count;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
}
