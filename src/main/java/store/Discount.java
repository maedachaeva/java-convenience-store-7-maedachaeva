package store;

public class Discount {
    private static final int MEMBERSHIP_DISCOUNT_LIMIT = 8000;
    private static final double MEMBERSHIP_DISCOUNT_RATE = 0.3;

    public static int calculateMembershipDiscount(int totalAmount, boolean isMembership) {
        if (!isMembership) {
            return 0;
        }
        int discount = (int) (totalAmount * MEMBERSHIP_DISCOUNT_RATE);
        return Math.min(discount, MEMBERSHIP_DISCOUNT_LIMIT);
    }

    /**
     * 프로모션 할인을 계산합니다.
     * (프로모션 적용 방식에 따라 추가적인 메서드 구현 가능)
     * @param product 상품 객체
     * @param quantity 구매 수량
     * @return 프로모션이 적용된 할인 금액
     */
    /*public static int calculatePromotionDiscount(Product product, int quantity) {
        // 1+1, 2+1 프로모션 할인 로직 구현
        // 예시: 상품이 프로모션 대상인지 확인 후 해당 할인 금액 계산
        // 로직은 향후 세부 프로모션 정책에 따라 구체화할 수 있음
        return 0;  // 예시용 기본값 반환
    }*/
}
