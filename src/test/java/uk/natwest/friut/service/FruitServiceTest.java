package uk.natwest.friut.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.natwest.friut.cache.FruitStore;
import uk.natwest.friut.domain.FruitBasket;
import uk.natwest.friut.exception.FruitNotFoundException;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("FruitService Test")
@ExtendWith(MockitoExtension.class)
public class FruitServiceTest {

    @Mock
    private FruitStore store;

    @Test
    @DisplayName("getAllFruits should call FruitStore.getFruitBaskets")
    void getAllFruits() {
        //given
        var fruitService = new FruitService(store);
        var fruitBaskets = List.of(new FruitBasket("apple", 10, new BigDecimal("9.99")));

        //when
        when(store.getFruitBaskets()).thenReturn(fruitBaskets);
        fruitService.getAllFruits();

        //then
        verify(store).getFruitBaskets();
    }

    @Test
    @DisplayName("calculateCostByFruitNameAndQuantity should return expected cost with correct parameters")
    void calculateCostByFruitNameAndQuantity() {
        //given
        var fruitName = "apple";
        var quantity = 10;
        var fruitService = new FruitService(store);
        var fruitBaskets = List.of(
                new FruitBasket("apple", 5, new BigDecimal("5.55")),
                new FruitBasket("apple", 6, new BigDecimal("9.99"))
        );

        //when
        when(store.getFruitBaskets()).thenReturn(fruitBaskets);
        var result = fruitService.calculateCostByFruitNameAndQuantity(fruitName, quantity);

        //then
        assertThat(result).isEqualTo(new BigDecimal("1.11"));
    }

    @Test
    @DisplayName("calculateCostByFruitNameAndQuantity should throw NotFoundException for not existed fruitName")
    void calculateCostByFruitNameAndQuantityWhenFruitNameNotExist() {
        //given
        var fruitName = "not_exist";
        var quantity = 4;
        var fruitService = new FruitService(store);
        var fruitBaskets = List.of(
                new FruitBasket("apple", 5, new BigDecimal("5.55")),
                new FruitBasket("apple", 6, new BigDecimal("9.99"))
        );

        //when
        when(store.getFruitBaskets()).thenReturn(fruitBaskets);

        //then
        assertThrows(FruitNotFoundException.class, () -> fruitService.calculateCostByFruitNameAndQuantity(fruitName, quantity));
    }

    @Test
    @DisplayName("calculateCostByFruitNameAndQuantity should throw NotFoundException when Quantity more than count of FruitBaskets")
    void calculateCostByFruitNameAndQuantityWhenQuantityMoreThanCount() {
        //given
        var fruitName = "apple";
        var quantity = 12;
        var fruitService = new FruitService(store);
        var fruitBaskets = List.of(
                new FruitBasket("apple", 5, new BigDecimal("5.55")),
                new FruitBasket("apple", 6, new BigDecimal("9.99"))
        );

        //when
        when(store.getFruitBaskets()).thenReturn(fruitBaskets);

        //then
        assertThrows(FruitNotFoundException.class, () -> fruitService.calculateCostByFruitNameAndQuantity(fruitName, quantity));
    }

    @Test
    @DisplayName("calculateCostByFruitNameAndQuantity should throw NotFoundException when request parameters is null")
    void calculateCostByFruitNameAndQuantityWhenRequestParametersIsNull() {
        //given
        var fruitService = new FruitService(store);
        var fruitBaskets = List.of(new FruitBasket("apple", 5, new BigDecimal("5.55")));

        //when
        when(store.getFruitBaskets()).thenReturn(fruitBaskets);

        //then
        assertThrows(FruitNotFoundException.class, () -> fruitService.calculateCostByFruitNameAndQuantity(null, null));
    }

    @Test
    @DisplayName("calculateCount method should return expected count")
    void calculateCount() {
        //given
        var fruitService = new FruitService(null);
        var expectedCount = 11;
        var fruitBaskets = List.of(
                new FruitBasket("apple", 5, new BigDecimal("5.55")),
                new FruitBasket("apple", 6, new BigDecimal("9.99"))
        );

        //when
        var result = fruitService.calculateCount(fruitBaskets);

        //then
        assertThat(result).isEqualTo(expectedCount);
    }

    @Test
    @DisplayName("getCheapestItem method should return cheapest item")
    void getCheapestItem() {
        //given
        var fruitService = new FruitService(null);
        var cheapestItem = new BigDecimal("1.11");
        var fruitBaskets = List.of(
                new FruitBasket("apple", 5, new BigDecimal("5.55")),
                new FruitBasket("apple", 6, new BigDecimal("9.99"))
        );

        //when
        var result = fruitService.getCheapestItem(fruitBaskets);

        //then
        assertThat(result).isEqualTo(cheapestItem);
    }

    @Test
    @DisplayName("getBasketsByName method should return list of fruit basket filtered by name")
    void getBasketsByName() {
        //given
        var fruitName = "apple";
        var fruitService = new FruitService(store);
        FruitBasket pineapple = new FruitBasket("pineapple", 5, new BigDecimal("5.55"));
        var fruitBaskets = List.of(
                pineapple,
                new FruitBasket("apple", 5, new BigDecimal("5.55")),
                new FruitBasket("apple", 6, new BigDecimal("9.99"))
        );

        //when
        when(store.getFruitBaskets()).thenReturn(fruitBaskets);

        var result = fruitService.getBasketsByName(fruitName);

        //then
        assertThat(result, not(hasItem(pineapple)));
    }
}
