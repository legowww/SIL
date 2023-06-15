package org.example.desing_pattern.decorator;

public class RegularExtra extends Extra{

    public RegularExtra(Order order, String label, double price) {
        super(order, label, price);
    }

    @Override
    public double getPrice() {
        return this.price + order.getPrice();
    }
}
