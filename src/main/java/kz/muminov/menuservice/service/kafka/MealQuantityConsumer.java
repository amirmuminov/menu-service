package kz.muminov.menuservice.service.kafka;

import kz.muminov.menuservice.model.entity.Meal;
import kz.muminov.menuservice.model.entity.Order;
import kz.muminov.menuservice.repository.MealRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MealQuantityConsumer {

    private final MealRepository mealRepository;

    @KafkaListener(topics = "restaurant-events", groupId = "group_id")
    public void consume(Order order) throws IOException {
        System.out.println("Consuming event");

        for(Meal meal: order.getMeals()){
            Meal mealFromDb = mealRepository.findById(meal.getId()).get();

            mealFromDb.setQuantity(mealFromDb.getQuantity() - 1);

            mealRepository.save(mealFromDb);

        }

    }

}
