package homework8;

import java.time.LocalDateTime;

enum Type {BOOK, TOY, PHONE};
public class Product {

    private int id;
    private Type type;
    private double price;
    private boolean hasDiscount;
    private LocalDateTime date;

    public Product(int id, Type type, double price, boolean hasDiscount, LocalDateTime date) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.hasDiscount = hasDiscount;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isHasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", type=" + type +
                ", price=" + price +
                ", discount=" + hasDiscount +
                ", date=" + date +
                '}';
    }

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    static class ProductBuilder {

        private int id;
        private Type type;
        private double price;
        private boolean hasDiscount;
        private LocalDateTime created;

        public ProductBuilder id(int id) {
            this.id = id;
            return this;
        }

        public ProductBuilder type(Type type) {
            this.type = type;
            return this;
        }

        public ProductBuilder price(double price) {
            this.price = price;
            return this;
        }

        public ProductBuilder discount(boolean discount) {
            this.hasDiscount = discount;
            return this;
        }

        public ProductBuilder created(LocalDateTime created) {
            this.created = created;
            return this;
        }

        public Product build() {

            return new Product(id, type, price, hasDiscount, created);
        }
    }
}
