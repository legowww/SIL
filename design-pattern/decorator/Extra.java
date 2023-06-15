package org.example.desing_pattern.decorator;

public abstract class Extra implements Order{
    protected Order order; //장식자는 타겟 인스턴스를 합성으로 가지고 있어야 한다.
    protected String label; //공유변수
    protected double price; //공유변수

    public Extra(Order order, String label, double price) {
        this.order = order;
        this.label = label;
        this.price = price;
    }

    public abstract double getPrice();

    public String getLabel() {
        return order.getLabel() + ", " + this.label;
    }
}
