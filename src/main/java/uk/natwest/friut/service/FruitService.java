package uk.natwest.friut.service;

import org.springframework.stereotype.Service;
import uk.natwest.friut.cache.FruitStore;
import uk.natwest.friut.domain.FruitBasket;
import uk.natwest.friut.exception.FruitNotFoundException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FruitService {

    private final FruitStore fruitStore;

    public FruitService(FruitStore fruitStore) {
        this.fruitStore = fruitStore;
    }

    public static final String FRUIT_BASKET_NOT_FOUND_MESSAGE = "FruitBasket with such parameters not found!!!";

    public List<FruitBasket> getAllFruits() {
        return fruitStore.getFruitBaskets();
    }

    public BigDecimal calculateCostByFruitNameAndQuantity(String fruitName, Integer quantity) {
        final List<FruitBasket> fruitBasketList = getBasketsByName(fruitName);
        if (fruitBasketList.isEmpty() || (quantity > calculateCount(fruitBasketList))) {
            throw new FruitNotFoundException(FRUIT_BASKET_NOT_FOUND_MESSAGE);
        }
        return getCheapestItem(fruitBasketList);
    }

    int calculateCount(List<FruitBasket> fruitBasketsByName) {
        return fruitBasketsByName.stream()
                .mapToInt(FruitBasket::getCount)
                .sum();
    }

    BigDecimal getCheapestItem(List<FruitBasket> fruitBasketsByName) {
        return fruitBasketsByName.stream()
                .map(fruitBasket -> fruitBasket.getCost().divide(
                        BigDecimal.valueOf(fruitBasket.getCount()),
                        2,
                        RoundingMode.HALF_UP)
                )
                .min(Comparator.naturalOrder())
                .orElseThrow(() -> new FruitNotFoundException(FRUIT_BASKET_NOT_FOUND_MESSAGE));
    }

    List<FruitBasket> getBasketsByName(String fruitName) {
        return fruitStore.getFruitBaskets()
                .stream()
                .filter((fruitBasket) -> fruitBasket.getName().equals(fruitName))
                .collect(Collectors.toList());
    }

}