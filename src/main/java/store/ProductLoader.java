package store;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductLoader {
    public static List<Product> loadProducts(String filename) {
        List<String> lines = readFile(filename);
        return parseLines(lines);
    }

    private static List<String> readFile(String filename) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                ProductLoader.class.getClassLoader().getResourceAsStream(filename)))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                lines.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private static List<Product> parseLines(List<String> lines) {
        List<Product> products = new ArrayList<>();
        for (String line : lines) {
            Product product = parseProduct(line);
            if (product != null) {
                products.add(product);
            }
        }
        return products;
    }

    private static Product parseProduct(String line) {
        String[] fields = line.split(",");
        if (fields.length < 3) {
            return null;
        }

        String name = fields[0].trim();
        int price = parseInteger(fields[1].trim());
        int quantity = parseInteger(fields[2].trim());

        String promotion = null;
        if (fields.length > 3 && !fields[3].trim().isEmpty()) {
            promotion = fields[3].trim();
        }

        if (price > 0 && quantity >= 0) {
            return new Product(name, price, quantity, promotion);
        }
        return null;
    }

    private static int parseInteger(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}