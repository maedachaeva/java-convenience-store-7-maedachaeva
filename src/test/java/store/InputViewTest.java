package store;

import org.junit.jupiter.api.Test;
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
}
