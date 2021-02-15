import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void beforeEach(){
        System.out.println("At BeforeEach");
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE

        Restaurant restaurantSpy = Mockito.spy(restaurant);
        Mockito.when(restaurantSpy.getCurrentTime()).thenReturn(LocalTime.of(13,59,00));
        boolean result = restaurantSpy.isRestaurantOpen();
        assertThat(result, equalTo(true));
        //System.out.println(restaurantSpy.getCurrentTime()); //13:59
        //System.out.println(result); //true
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE

        Restaurant restaurantSpy = Mockito.spy(restaurant);
        Mockito.when(restaurantSpy.getCurrentTime()).thenReturn(LocalTime.of(8,59,00));
        boolean result = restaurantSpy.isRestaurantOpen();
        assertThat(result, equalTo(false));
        //System.out.println(restaurantSpy.getCurrentTime()); //08:59
        //System.out.println(result); //false

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
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
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>TOTAL ORDER VALUE<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

    @Test
    public void adding_items_to_the_order_should_return_total_order_value() {
        List<String> orderItems = new ArrayList<String>();
        orderItems.add("Sweet corn soup");
        //orderItems.add("Vegetable lasagne");
        int totalCost = restaurant.getTotalOrderValue(orderItems, restaurant);
        assertThat(totalCost, equalTo(119));
        System.out.println(totalCost);
    }

    //<<<<<<<<<<<<<<<<<<<<<<<TOTAL ORDER VALUE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
}