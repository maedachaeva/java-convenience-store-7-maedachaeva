package store;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        String filename = "products.md";

        List<Product> products = ProductLoader.loadProducts(filename);

        OutputView outputView = new OutputView();
        outputView.printGreeting();
        outputView.printProducts(products);

        InputView inputView = new InputView();
        String orderDetails = inputView.readItem();

        try {
            InputView.validateItemFormat(orderDetails);
            InputView.validateStock(products, orderDetails);

            int totalAmount = Checkout.calculateTotalAmount(products, orderDetails);

            boolean isMembership = inputView.isMembership();

            outputView.printReceipt(products, orderDetails, totalAmount);
            //System.out.println("내실돈 " + String.format("%,d", totalAmount) + "원");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
