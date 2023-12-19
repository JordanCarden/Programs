import java.util.*;
class CoffeeShopMenu{
    private String name;
    private double price;

    public CoffeeShopMenu(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void printMenu() {
        System.out.print(name + ", " + price);
    }
}
class BakeryItem extends CoffeeShopMenu{
    private boolean warmed;

    public BakeryItem(String name, double price, boolean warmed) {
        super(name, price);
        this.warmed = warmed;
    }

    @Override
    public void printMenu() {
        super.printMenu();
        if (warmed) {
            System.out.print(", Warmed");
        }
    }
}
class Drink extends CoffeeShopMenu{
    private String size;

    public Drink(String name, double price, String size) {
        super(name, price);
        this.size = size;
    }

    @Override
    public void printMenu() {
        super.printMenu();
        System.out.print(", " + size);
    }
}
class Coffee extends Drink{
    private String milkChoice;

    public Coffee(String name, double price, String size, String milkChoice) {
        super(name, price, size);
        this.milkChoice = milkChoice;
    }

    public void printMenu() {
        super.printMenu();
        System.out.print(", " + milkChoice);
    }
}
class Frappuccino extends Coffee {
    private boolean hasWhipCream;

    public Frappuccino(String name, double price, String size, String milkChoice, boolean hasWhipCream) {
        super(name, price, size, milkChoice);
        this.hasWhipCream = hasWhipCream;
    }

    public void printMenu() {
        super.printMenu();
        if(hasWhipCream) {
            System.out.print(", with whipcream");
        }
    }

}
public class Inheritance{
    public static void main(String[] args) {
        ArrayList<CoffeeShopMenu> items = new ArrayList<>();
        items.add(new BakeryItem("Croissant", 4.99, true));
        items.add(new BakeryItem("Croissant", 4.99, false));
        items.add(new Coffee("Latte", 5.35, "large", "Oat milk"));
        items.add(new Coffee("Latte", 4.35, "large", "Whole milk"));
        items.add(new Frappuccino("Pumpkin spice frappuccino", 5.97, "medium", "Almond milk", true));
        items.add(new Frappuccino("Pumpkin spice frappuccino", 5.37, "small", "Whole milk", false));
        items.add(new Drink("Earl Grey Tea", 3.23, "small"));

        for (CoffeeShopMenu item: items) {
            item.printMenu();
            System.out.println();//new line for output, this line can be deleted if you structure your printMenu() method so you don't need it.
        }
    }
}