import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();
    private int totalOrderValue;

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {
        boolean isRestaurantOpen;
       int isOpen = getCurrentTime().compareTo(openingTime);
       int isClosed = closingTime.compareTo(getCurrentTime());

       if ((isOpen == 1 && isClosed == 1) || (isOpen == 0 && isClosed == 1)) {
           isRestaurantOpen = true;
       }
       else {
           isRestaurantOpen = false;
       }
       return isRestaurantOpen;

    }

    public LocalTime getCurrentTime(){
        return  LocalTime.now();
    }

    public List<Item> getMenu() {
        return menu;
    }

    private Item findItemByName(String itemName){
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                return item;
        }
        return null;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }

    public int orderValue(String... itemNames) {
        totalOrderValue = 0;
        for (String names : itemNames) {
            Item item = findItemByName(names);
            totalOrderValue = totalOrderValue + item.getPrice();
        }
        return totalOrderValue;
    }


    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }


}
