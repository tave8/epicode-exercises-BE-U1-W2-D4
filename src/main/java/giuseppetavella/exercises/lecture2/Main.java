package giuseppetavella.exercises.lecture2;

import giuseppetavella.entities.Customer;
import giuseppetavella.entities.Order;
import giuseppetavella.entities.Product;
import giuseppetavella.entities.SampleData;

import java.util.Comparator;
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
        Map<String, Double> totalOrderSalesByCustomer = getTotalOrderSalesByCustomer(orders);
        // exercise 3
        List<Product> mostExpensiveProducts = getMostExpensiveProducts(products); 

        System.out.println();
        System.out.println("-----------");
        System.out.println("ORDERS LIST BY CUSTOMER");
        System.out.println("-----------");
        ordersByCustomer.forEach((uniqueCustomerLabel, customerOrders) -> {
            String msg = uniqueCustomerLabel + ": "  +customerOrders;
            System.out.println(msg);
        });

        System.out.println();
        System.out.println("----------");
        System.out.println("TOTAL ORDER SALES BY CUSTOMER");
        System.out.println("-----------");
        totalOrderSalesByCustomer.forEach((uniqueCustomerLabel, totalOrderSales) -> {
            String msg = uniqueCustomerLabel + ": "  +totalOrderSales;
            System.out.println(msg);
        });

        System.out.println();
        System.out.println("----------");
        System.out.println("MOST EXPENSIVE PRODUCTS");
        System.out.println("-----------");
        mostExpensiveProducts.forEach((expensiveProduct) -> {
            String msg = expensiveProduct.toString();
            System.out.println(msg);
        });

    }

    /**
     * Exercise 3
     */
    static List<Product> getMostExpensiveProducts(List<Product> products, int limit) throws IllegalArgumentException {
        // most expensive products means:
        // order by price, descending
        // limit x products
        if(limit < 0) {
            throw new IllegalArgumentException("Limit " + limit + " is illegal.");
        }
        return products.stream()
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(limit)
                .toList();
    }
    
    static List<Product> getMostExpensiveProducts(List<Product> products) throws IllegalArgumentException {
        return getMostExpensiveProducts(products, 5);
    }

    /**
     * Exercise 2
     */
    static Map<String, Double> getTotalOrderSalesByCustomer(List<Order> orders) {
        //  1 customer -> N orders
        //  N orders <-> N products
        //  for each customer, sum the total of each order total
        // a customer has N orders
        // each  order has the order total
        return orders.stream()
                .collect(
                        Collectors.groupingBy(
                                order -> order.getCustomer().getUniqueLabel(),
                                Collectors.summingDouble(order -> order.calculateTotal())
                        )
                );
    }

    /**
     * Exercise 1
     */
    static Map<String, List<Order>> getOrdersByCustomer(List<Order> orders) {
        return orders.stream()
                .collect(Collectors.groupingBy(order -> order.getCustomer().getUniqueLabel()));
    }
    
}
