package giuseppetavella.entities;

import giuseppetavella.enums.CustomerTier;
import giuseppetavella.enums.OrderStatus;
import giuseppetavella.enums.ProductCategory;

import java.util.List;
import java.util.Map;

public class SampleData {
    /**
     *
     * return {
     * products: List
     * customers: List
     * orders: List
     * }
     */
    public static Map<String, List> getSampleData() {
        // ***** PRODUCTS
        Product product1 = new Product(1, "book1", 12.34, ProductCategory.BOOK);
        Product product2 = new Product(2, "book2", 12.34, ProductCategory.BOOK);
        Product product3 = new Product(3, "book3", 102.34, ProductCategory.BOOK);
        Product product4 = new Product(4, "baby1", 23, ProductCategory.BABY);
        Product product5 = new Product(5, "boy1", 100, ProductCategory.BOY);

        // ***** CUSTOMERS
        Customer customer1 = new Customer(1, "Giuseppe", CustomerTier.ONE);
        Customer customer2 = new Customer(2, "Maria", CustomerTier.ONE);
        Customer customer3 = new Customer(3, "Giovanna", CustomerTier.TWO);
        Customer customer4 = new Customer(4, "Mariello", CustomerTier.TWO);

        // ***** ORDERS
        Order order1 = new Order(1, customer1, OrderStatus.DELIVERED);
        Order order2 = new Order(2, customer1, OrderStatus.SHIPPED);
        Order order3 = new Order(3, customer3, OrderStatus.SHIPPED);
        Order order4 = new Order(4, customer4, OrderStatus.SHIPPED);
        Order order5 = new Order(5, customer4, OrderStatus.SHIPPED);

        // ***** EDIT ENTITY RELATIONSHIPS
        order1.addProduct(product4);
        order1.addProduct(product3);
        order2.addProduct(product3);
        order2.addProduct(product4);
        order4.addProduct(product4);
        order5.addProduct(product5);
        order5.addProduct(product3);

        // products
        List<Product> products = List.of(
                product1,
                product2,
                product3,
                product4,
                product5
        );

        // customers
        List<Customer> customers = List.of(
                customer1,
                customer2,
                customer3,
                customer4
        );

        // orders
        List<Order> orders = List.of(
                order1,
                order2,
                order3,
                order4,
                order5
        );

        return Map.of(
                "products", products,
                "customers", customers,
                "orders", orders
        );
    }
}
