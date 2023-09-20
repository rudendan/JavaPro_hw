package coffee.order;

import java.util.LinkedList;
import java.util.Queue;

public class CoffeeOrderBoard {

    private int generateNumber = 1;
    Queue<Order> orders = new LinkedList<>();

    public void add(String name) {
        orders.add(new Order(name, generateNumber++));
    }

    public void deliver() {
        Order order = orders.poll();
        if (order != null)
            System.out.println("Order №" + order.getOrderNumber()
                    + " for " + order.getName() + " was delivered in order of queue");
        else
            System.out.println("All orders were delivered");
    }

    public void deliver(int orderNumber) {

        boolean isFound = false;

        for (Order order : orders) {
            if (order.getOrderNumber() == orderNumber) {
                isFound = true;
                System.out.println("Order №" + order.getOrderNumber()
                        + " for " + order.getName() + " was delivered by order number");
                orders.remove(order);
                break;
            }
        }
        if (!isFound)
            System.out.println("There was no such order №" + orderNumber);
    }

    public void draw() {
        System.out.println("Num | Name");
        for (Order order : orders) {
            System.out.printf("%-3s | %-10s\n", order.getOrderNumber(), order.getName());
        }
        System.out.printf("--------------------------------%n");
    }
}
