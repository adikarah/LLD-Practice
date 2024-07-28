package main.foodkart;

import main.foodkart.models.FoodDetails;
import main.foodkart.models.Restaurant;
import main.foodkart.repository.FoodRepository;
import main.foodkart.repository.OrderRepository;
import main.foodkart.repository.RestaurantRepository;

import java.util.Arrays;
import java.util.List;

public class FoodKartApplication {

    public static void main(String[] args) {

        FoodRepository foodRepository = new FoodRepository();
        RestaurantRepository restaurantRepository = new RestaurantRepository(foodRepository);
        OrderRepository orderRepository = new OrderRepository(restaurantRepository);

        FoodDetails foodDetails11 = new FoodDetails("Jalebi", 120.0);
        FoodDetails foodDetails12 = new FoodDetails("Jalebi", 125.0);
        FoodDetails foodDetails21 = new FoodDetails("Chhola Bhatura", 150.0);
        FoodDetails foodDetails22 = new FoodDetails("Chhola Bhatura", 140.0);
        FoodDetails foodDetails31 = new FoodDetails("Samosa", 20.0);
        FoodDetails foodDetails32 = new FoodDetails("Samosa", 20.0);
        FoodDetails foodDetails41 = new FoodDetails("RasMalai", 100.0);
        FoodDetails foodDetails51 = new FoodDetails("Rajma Chawal", 140.0);
        FoodDetails foodDetails61 = new FoodDetails("GolGappa", 30.0);
        FoodDetails foodDetails71 = new FoodDetails("SonPapdi", 220.0);
        FoodDetails foodDetails81 = new FoodDetails("Kadhai Paneer", 320.0);

        restaurantRepository.addRestaurant(List.of(foodDetails11, foodDetails21, foodDetails31), 10);
        restaurantRepository.addRestaurant(List.of(foodDetails12, foodDetails21, foodDetails31, foodDetails81), 10);
        restaurantRepository.addRestaurant(List.of(foodDetails22, foodDetails21, foodDetails32, foodDetails41), 15);
        restaurantRepository.addRestaurant(List.of(foodDetails51, foodDetails61, foodDetails32, foodDetails41, foodDetails71), 25);

        System.out.println(Arrays.toString(restaurantRepository.getAllRestaurantDetails().toArray()));

        System.out.println(Arrays.toString(restaurantRepository.getRestaurantWithLowestPrice("Jalebi").stream().map(Restaurant::getRestaurantId).toArray()));

    }
}
