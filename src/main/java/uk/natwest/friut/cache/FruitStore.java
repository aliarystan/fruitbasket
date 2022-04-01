package uk.natwest.friut.cache;

import org.springframework.stereotype.Component;
import uk.natwest.friut.domain.FruitBasket;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Component
public class FruitStore {

    private final List<FruitBasket> fruitBaskets = Arrays.asList(
            new FruitBasket("apple", 10, new BigDecimal("9.99")),
            new FruitBasket("banana", 20, new BigDecimal("19.99")),
            new FruitBasket("orange", 10, new BigDecimal("29.99")),
            new FruitBasket("watermelon", 10, new BigDecimal("29.99")),
            new FruitBasket("papaya", 20, new BigDecimal("9.99")),
            new FruitBasket("apple", 9, new BigDecimal("9.89")),
            new FruitBasket("banana", 10, new BigDecimal("19.99")),
            new FruitBasket("apple", 50, new BigDecimal("25.00"))
    );

    public List<FruitBasket> getFruitBaskets() {
        return fruitBaskets;
    }
}
