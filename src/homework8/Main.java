package homework8;

import java.util.ArrayList;
import java.util.Comparator;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {

        List<Product> products = new ArrayList<>();

        products.add(Product.builder()
                .id(1001)
                .type(Type.BOOK)
                .price(65.5)
                .discount(true)
                .created(LocalDateTime.of(2023,7,30,14,29))
                .build());

        products.add(Product.builder()
                .id(1003)
                .type(Type.TOY)
                .price(150.0)
                .discount(false)
                .created(LocalDateTime.of(2023,10,1,18,0))
                .build());

        products.add(Product.builder()
                .id(1007)
                .type(Type.BOOK)
                .price(260.0)
                .discount(true)
                .created(LocalDateTime.of(2023,9,15,13,0))
                .build());

        products.add(Product.builder()
                .id(1012)
                .type(Type.BOOK)
                .price(15.0)
                .discount(false)
                .created(LocalDateTime.of(2023,9, 10,16,0))
                .build());

        products.add(Product.builder()
                .id(1045)
                .type(Type.TOY)
                .price(80.0)
                .discount(true)
                .created(LocalDateTime.of(2022,9,20,14,30))
                .build());


        System.out.println("Method getByTypeAndPrice: ");
        System.out.println(getByTypeAndPrice(products));
        System.out.println("Method getByTypeAndDiscountAllowed: ");
        System.out.println(getByTypeAndHasDiscount(products));
        System.out.println("Method getCheapest: ");
        System.out.println(getCheapest(products, Type.PHONE));
        System.out.println(getCheapest(products, Type.BOOK));
        System.out.println("Method getLastThreeCreated: ");
        System.out.println(getLastThreeCreated(products));
        System.out.println("Method calculateTotalCosts: ");
        System.out.println(calculateTotalCosts(products));
        System.out.println("Method groupingProducts: ");
        System.out.println(groupingProducts(products));

    }

    private static List<Product> getByTypeAndPrice(List<Product> products) {

        return products.stream()
                .filter(p -> p.getType() == Type.BOOK)
                .filter(p -> p.getPrice() > 250)
                .toList();
    }

    private static List<Product> getByTypeAndHasDiscount(List<Product> products) {

        return products.stream()
                .filter(p -> p.getType().equals(Type.BOOK))
                .filter(p -> p.isHasDiscount())
                .map(p -> { return new Product(
                        p.getId(),
                        p.getType(),
                        p.getPrice() * 0.9,
                        p.isHasDiscount(),
                        p.getDate());})
                .collect(Collectors.toList());
    }

    private static Product getCheapest(List<Product> products, Type type) {

        Product product = products.stream()
                .filter(p -> p.getType().equals(type))
                .min(Comparator.comparing(Product::getPrice))
                .orElse(null);

        if (product == null)
            System.out.println("Product \"" + type + "\" not found");

        return product;

    }

    private static List<Product> getLastThreeCreated(List<Product> products) {

        return products.stream()
                .sorted(Comparator.comparing(Product::getDate).reversed())
                .limit(3)
                .toList();
    }

    private static double calculateTotalCosts(List<Product> products) {

        return products.stream()
                .filter(p -> (p.getDate().getYear()) == LocalDateTime.now().getYear())
                .filter(p -> p.getType().equals(Type.BOOK))
                .filter(p -> p.getPrice() <= 75.0)
                .mapToDouble(Product::getPrice)
                .sum();
    }

    private static Map<Type, List<Product>> groupingProducts(List<Product> products) {

        return products.stream().collect(Collectors.groupingBy(Product::getType));
    }
}
