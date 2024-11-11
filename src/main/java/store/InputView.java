package store;

import camp.nextstep.edu.missionutils.Console;

import java.util.List;

public class InputView {
    public String readItem() {
        System.out.println("\n구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        String orderDetails = Console.readLine();
        validateItemFormat(orderDetails);
        return orderDetails;
    }

    public static void validateItemFormat(String orderDetails) {
        if (!orderDetails.matches("(\\[[가-힣]+-[1-9][0-9]*\\])(,\\[[가-힣]+-[1-9][0-9]*\\])*(,)*")) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
        }
    }

    public static void validateStock(List<Product> products, String orderDetails) {
        String[] items = orderDetails.split(",");
        for (String item : items) {
            String[] parts = item.replaceAll("[\\[\\]]", "").split("-");
            String itemName = parts[0];
            int itemQuantity = Integer.parseInt(parts[1]);

            Product product = products.stream()
                    .filter(p -> p.getName().equals(itemName))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("[ERROR] 존재하지 않는 상품입니다. 다시 입력해 주세요."));

            if (itemQuantity > product.getQuantity()) {
                throw new IllegalArgumentException("[ERROR] 재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요.");
            }
        }
    }

    public boolean isMembership() {
        System.out.print("\n멤버십 할인을 받으시겠습니까? (Y/N)\n");
        String membershipDiscount = Console.readLine();
        return membershipDiscount.equalsIgnoreCase("Y");
    }

}
