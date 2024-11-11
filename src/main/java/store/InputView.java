package store;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public String readItem() {
        System.out.println("구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])");
        String orderDetails = Console.readLine();
        return orderDetails;
    }

    public static void validateItemFormat(String orderDetails) {
        if (!orderDetails.matches("\"\\\\[[가-힣]+-[1-9][0-9]*\\\\]\"")) {
            throw new IllegalArgumentException("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
        }
    }
}
