package giuseppetavella.exercises.lecture1;

import giuseppetavella.entities.SampleData;
import giuseppetavella.entities.Customer;
import giuseppetavella.entities.Order;
import giuseppetavella.entities.Product;
import giuseppetavella.enums.CustomerTier;
import giuseppetavella.enums.OrderStatus;
import giuseppetavella.enums.ProductCategory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        // SAMPLE DATA
        // TODO 3: cannot use raw List?
        Map<String, List> sampleData = SampleData.getSampleData();

        // TODO 2: fix "unchecked assignment"
        List<Product> products = sampleData.get("products");
        List<Order> orders = sampleData.get("orders");
        List<Customer> customers = sampleData.get("customers");

        // exercise 1
        List<Product> expensiveBooks = getExpensiveBooks(products);
        // exercise 2
        List<Order> ordersWithBabyProducts = getOrdersWithBabyProducts(orders);
        // exercise 3
        List<Product> discountedBoyProducts = getDiscountedBoyProducts(products);
        // exercise 4
        List<Product> productsBetweenDateRangeOfTier2Customers = getProductsBetweenDateRangeOfTier2Customers(orders);

        System.out.println("---- EXPENSIVE BOOKS -----");
        for (Product book : expensiveBooks) {
            System.out.println(book);
        }

        System.out.println();
        System.out.println("---- ORDERS WITH BABY PRODUCTS");
        for (Order order : ordersWithBabyProducts) {
            System.out.println(order);
        }

        System.out.println();
        System.out.println("---- DISCOUNTED BOY PRODUCTS ");
        for (Product product : discountedBoyProducts) {
            System.out.println(product);
        }

        System.out.println();
        System.out.println("---- PRODUCTS ORDERED BETWEEN DATE RANGE, ORDERED BY TIER 2 CUSTOMERS");
        System.out.println(productsBetweenDateRangeOfTier2Customers);
    }


    /**
     * Exercise 4
     */
    static List<Product> getProductsBetweenDateRangeOfTier2Customers(List<Order> orders) {
        LocalDate startDate = LocalDate.of(2026, 3, 18);
        LocalDate endDate = LocalDate.of(2026, 5, 18);

        // predicates
        Predicate<Order> isOrderAfterStartDate = order -> order.getOrderDate().isAfter(startDate);
        Predicate<Order> isOrderBeforeEndDate = order -> order.getOrderDate().isBefore(endDate);
        Predicate<Order> isOrderInDateRange = isOrderAfterStartDate.and(isOrderBeforeEndDate);

        // streams
        Stream<Order> filteredOrders = orders.stream()
                .filter(order -> order.getCustomer().getTier().equals(CustomerTier.TWO))
                .filter(isOrderInDateRange);

        List<Order> orderList = filteredOrders.toList();

        // METHOD 1: for enhanced
        List<Product> allProducts = new ArrayList<>();
        for (Order order : orderList) {
            allProducts.addAll(order.getProducts());
        }

        return allProducts;

        //     METHOD 2:
        //     1 order -> N products
        // Stream<Product> productsOfOrders = ordersBetweenDateRange.flatMap(order -> order.getProducts().stream());
        //
        // return productsOfOrders.toList();
    }

    /**
     * Exercise 3
     */
    static List<Product> getDiscountedBoyProducts(List<Product> products) {
        return products.stream()
                .filter(product -> product.getCategory().equals(ProductCategory.BOY))
                .map(currProduct -> {
                    double discountedPrice = currProduct.getPrice() - (currProduct.getPrice() * 0.1);
                    // return a new product with same data 
                    // but discounted price 
                    return new Product(
                            currProduct.getId(),
                            currProduct.getName(),
                            discountedPrice,
                            currProduct.getCategory()
                    );
                }).toList();
    }

    /**
     * Exercise 2
     */
    static List<Order> getOrdersWithBabyProducts(List<Order> orders) {
        // predicates
        Predicate<Product> productIsBabyProduct = product -> product.getCategory().equals(ProductCategory.BABY);
        Predicate<Order> orderHasAtLeastOneBabyProduct = order -> order.getProducts().stream().anyMatch(productIsBabyProduct);

        return orders.stream()
                .filter(orderHasAtLeastOneBabyProduct)
                .map(currOrder -> {
                    Order newOrder = new Order(
                            currOrder.getId(),
                            currOrder.getCustomer(),
                            currOrder.getStatus(),
                            currOrder.getOrderDate(),
                            currOrder.getDeliveryDate()
                    );
                    // add products of current order, to products of new order
                    // add the product only if it's a baby product
                    currOrder.getProducts().forEach(product -> {
                        if (product.getCategory().equals(ProductCategory.BABY)) {
                            newOrder.addProduct(product);
                        }
                    });
                    return newOrder;
                }).toList();
    }

    /**
     * Exercise 1
     */
    static List<Product> getExpensiveBooks(List<Product> products) {
        Predicate<Product> isBook = product -> product.getCategory().equals(ProductCategory.BOOK);
        Predicate<Product> isExpensive = product -> product.getPrice() > 100;
        Predicate<Product> isExpensiveBook = isBook.and(isExpensive);
        return products.stream()
                .filter(isExpensiveBook)
                .toList();
    }



}
