package uk.natwest.friut.cache;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DisplayName("FruitStore Test")
public class FruitStoreTest {

    @Test
    @DisplayName("Should return expected List of Fruit Baskets")
    void getFruitBaskets() {
        // given
        final var expectedListSize = 8;
        var fruitStore = new FruitStore();

        //when
        var fruitBaskets = fruitStore.getFruitBaskets();

        //then
        assertThat(fruitBaskets).isNotNull();
        assertThat(fruitBaskets.size()).isEqualTo(expectedListSize);
    }
}
