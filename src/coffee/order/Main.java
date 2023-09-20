package coffee.order;

public class Main {

    public static void main(String[] args) {

        CoffeeOrderBoard orders = new CoffeeOrderBoard();
        orders.add("Alex");
        orders.add("Den");
        orders.add("Stas");
        orders.add("Iryna");
        orders.add("Alex");
        orders.add("Den");
        orders.add("Stas");
        orders.add("Iryna");


        orders.draw();

        orders.deliver();
        orders.deliver(10);
        orders.draw();
        orders.deliver(3);

        orders.deliver(5);
        orders.draw();
    }
}
