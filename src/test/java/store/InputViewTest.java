package store;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class InputViewTest {
    private void assertInvalidFormat(String orderDetails) {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> InputView.validateItemFormat(orderDetails))
                .withMessage("[ERROR] 올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.");
    }

    @Test
    void 주문_형식에_하이픈이_없으면_예외_발생() {
        assertInvalidFormat("[사이다2]");
    }

    @Test
    void 주문_형식에_수량이_없으면_예외_발생() {
        assertInvalidFormat("[사이다-]");
    }

    @Test
    void 주문_형식에_대괄호가_없으면_예외_발생() {
        assertInvalidFormat("사이다-2");
    }

    @Test
    void 존재하지_않는_상품_입력_테스트() {
        List<Product> products = ProductLoader.loadProducts("products.md");

        String invalidOrderDetails = "[아이스크림-2]";

        assertThrows(IllegalArgumentException.class, () -> {
            InputView.validateStock(products, invalidOrderDetails);
        });
    }
}
