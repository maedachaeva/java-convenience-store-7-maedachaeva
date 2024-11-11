package store;

public class Product {
    private String name;
    private int price;
    private int quantity;
    private String promotion;

    public Product(String name, int price, int quantity, String promotion) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.promotion = promotion;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getPromotion() {
        return promotion;
    }

    public boolean isOutOfQuantity() {
        return quantity == 0;
    }

    @Override
    public String toString() {
        String formattedPrice = String.format("%,d", price);
        if (isOutOfQuantity()) {
            return String.format("- %s %s원 재고 없음", name, formattedPrice);
        }

        if (promotion != null && !promotion.isEmpty() && !"null".equals(promotion)) {
            return String.format("- %s %s원 %d개 %s", name, formattedPrice, quantity, promotion);
        }

        return String.format("- %s %s원 %d개", name, formattedPrice, quantity);
    }
}
