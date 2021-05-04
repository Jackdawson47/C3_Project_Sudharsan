import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {

    Restaurant restaurant;

   @BeforeEach
   public void setup() {
       LocalTime openingTime = LocalTime.parse("10:30:00");
       LocalTime closingTime = LocalTime.parse("22:00:00");
       restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
       restaurant.addToMenu("Sweet corn soup",119);
       restaurant.addToMenu("Vegetable lasagne", 269);
   }


    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
       Restaurant spiedRestaurant = Mockito.spy(restaurant);
       Mockito.when(spiedRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("10:30:00"));
       assertTrue(spiedRestaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        Restaurant spiedRestaurant = Mockito.spy(restaurant);
        Mockito.when(spiedRestaurant.getCurrentTime()).thenReturn(LocalTime.parse("22:00:00"));
        assertFalse(spiedRestaurant.isRestaurantOpen());
    }





    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }


    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {


        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }


    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {


        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }

   // you need to add another method that returns the order value, given the name of the items in <String> format.

    @Test
    public void when_names_of_items_are_given_it_should_return_total_order_value () {
       int totalOderValue = restaurant.orderValue("Sweet corn soup");
    }

}