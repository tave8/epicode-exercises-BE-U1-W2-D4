package giuseppetavella.exercises.lecture2;

import giuseppetavella.entities.Customer;
import giuseppetavella.entities.Order;
import giuseppetavella.entities.Product;
import giuseppetavella.entities.SampleData;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Map<String, List> sampleData = SampleData.getSampleData();

        List<Product> products = sampleData.get("products");
        List<Order> orders = sampleData.get("orders");
        List<Customer> customers = sampleData.get("customers");
        
        // exercise 1
        Map<String, List<Order>> ordersByCustomer = getOrdersByCustomer(orders);
        // exercise 2
        // Map<String, Double> totalOrderSalesByCustomer = getTotalOrderSalesByCustomer(orders);

        System.out.println();
        System.out.println("---- ORDERS BY CUSTOMER -----");
        ordersByCustomer.forEach((uniqueCustomerLabel, customerOrders) -> {
            String msg = uniqueCustomerLabel + ": "  +customerOrders;
            System.out.println(msg);
        });

        System.out.println();
        System.out.println("---- TOTAL ORDER SALES BY CUSTOMER -----");
        // ordersByCustomer.forEach((uniqueCustomerLabel, totalOrderSales) -> {
        //     String msg = uniqueCustomerLabel + ": "  +totalOrderSales;
        //     System.out.println(msg);
        // });

    }
    
    static Map<String, List<Order>> getOrdersByCustomer(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.groupingBy(order -> order.getCustomer().getUniqueLabel()));
    }
    
    // static Map<String, Double> getTotalOrderSalesByCustomer(List<Order> orders) {
    //     //  1 customer -> N orders
    //     //  N orders <-> N products
    //    
    // }
    
}
