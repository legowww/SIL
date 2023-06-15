package org.example.desing_pattern.decorator;

public class Pizza implements Order{

    private double price;

    private String label;

    public Pizza(double price, String label) {
        this.price = price;
        this.label = label;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String getLabel() {
        return this.label;
    }
}
