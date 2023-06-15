package org.example.desing_pattern.decorator;

public class DoubleExtra extends Extra{
    public DoubleExtra(Order order, String label, double price) {
        super(order, label, price);
    }

    @Override
    public double getPrice() {
        return (this.price*2) + order.getPrice();
    }

    @Override
    public String getLabel() {
        return order.getLabel() + ", 더블 " + this.label;
    }
}
