package uk.natwest.friut.api;

import org.springframework.web.bind.annotation.RestController;
import uk.natwest.friut.domain.FruitBasket;
import uk.natwest.friut.service.FruitService;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class FruitController implements FruitApi {

    private final FruitService fruitService;

    public FruitController(FruitService fruitService) {
        this.fruitService = fruitService;
    }

    @Override
    public List<FruitBasket> getAll() {
        return fruitService.getAllFruits();
    }

    @Override
    public BigDecimal calculateCostByFruitNameAndQuantity(String fruitName, Integer quantity) {
        return fruitService.calculateCostByFruitNameAndQuantity(fruitName, quantity);
    }
}
