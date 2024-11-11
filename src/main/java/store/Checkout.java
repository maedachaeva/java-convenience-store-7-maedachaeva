package store;

import java.util.List;

public class Checkout {
    public static int calculateTotalAmount(List<Product> products, String orderDetails) {
        int totalAmount = 0;

        String[] items = orderDetails.split(",");
        for (String item : items) {
            String[] parts = item.replaceAll("[\\[\\]]", "").split("-");
            String itemName = parts[0];
            int itemQuantity = Integer.parseInt(parts[1]);

            Product product = products.stream()
                    .filter(p -> p.getName().equals(itemName))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요."));

            totalAmount += product.getPrice() * itemQuantity;
        }
        return totalAmount;
    }
}
