package org.example.desing_pattern.decorator;

public class Main {
    public static void main(String[] args) {
        Order fourSeasonsPizza = new DoubleExtra(new RegularExtra(new Pizza(1000, "pizza"), "페퍼로니", 200), "칠리", 300);

        System.out.println("fourSeasonsPizza = " + fourSeasonsPizza.getPrice());
    }
}
