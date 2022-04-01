package uk.natwest.friut.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import uk.natwest.friut.domain.FruitBasket;

import java.math.BigDecimal;
import java.util.List;

public interface FruitApi {

    @GetMapping("/allFruitBaskets")
    List<FruitBasket> getAll();

    @GetMapping("/allFruitBaskets/{fruitName}/{quantity}")
    BigDecimal calculateCostByFruitNameAndQuantity(@PathVariable String fruitName, @PathVariable Integer quantity);

}
