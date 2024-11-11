package store;

import java.util.List;

public class OutputView {
    private static final String WELCOME_MESSAGE = "안녕하세요. W편의점입니다.\n현재 보유하고 있는 상품입니다.\n";

    public void printGreeting() {
        System.out.println(WELCOME_MESSAGE);
    }

    public void printProducts(List<Product> products) {
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public void printReceipt(List<Product> products, String orderDetails, int totalAmount) {
        int totalQuantity = 0;
        int totalPrice = 0;

        System.out.println("\n==============W 편의점================");
        System.out.println("상품명\t\t수량\t금액");

        String[] items = orderDetails.split(",");
        for (String item : items) {
            String[] parts = item.replaceAll("[\\[\\]]", "").split("-");
            String itemName = parts[0];
            int itemQuantity = Integer.parseInt(parts[1]);

            Product product = products.stream()
                    .filter(p -> p.getName().equals(itemName))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요."));

            int itemTotal = product.getPrice() * itemQuantity;
            totalQuantity += itemQuantity;
            totalPrice += itemTotal;

            System.out.println(itemName + "\t\t" + itemQuantity + "\t" + String.format("%,d", itemTotal));
        }

        System.out.println("=============증\t정===============");
        for (String item : items) {
            String[] parts = item.replaceAll("[\\[\\]]", "").split("-");
            String itemName = parts[0];
            int itemQuantity = Integer.parseInt(parts[1]);

            Product product = products.stream()
                    .filter(p -> p.getName().equals(itemName))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요."));

            if (product.getPromotion() != null) {
                int bonusQuantity = 0;
                if (product.getPromotion().equals("탄산2+1") && itemQuantity >= 2) {
                    bonusQuantity = itemQuantity / 2;
                } else if (product.getPromotion().equals("MD추천상품") || product.getPromotion().equals("반짝할인")) {
                    bonusQuantity = itemQuantity;
                }

                if (bonusQuantity > 0) {
                    System.out.println(itemName + "\t\t" + bonusQuantity);
                }
            }
        }

        System.out.println("====================================");
        System.out.println("총구매액\t" + totalQuantity + "\t" + String.format("%,d", totalAmount));
    }
}
