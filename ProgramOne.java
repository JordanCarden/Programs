import java.util.ArrayList;
import java.util.Scanner;
public class ProgramOne {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<PaneraItem> PaneraItems = new ArrayList<PaneraItem>();
        ArrayList<PaneraUser> PaneraUsers = new ArrayList<PaneraUser>();
        printMenu();
        int menuOption;
        do {
            System.out.print("Enter a menu option: ");
            menuOption = in.nextInt();
            in.nextLine();
            switch (menuOption) {
                case 1:
                    System.out.print("Enter name for Food item: ");
                    String fname = in.nextLine();
                    System.out.print("Enter item price: ");
                    double fprice = in.nextDouble();
                    in.nextLine();
                    System.out.print("Enter item side: ");
                    String fside = in.next();
                    System.out.print("Enter item size: ");
                    String fsize = in.next();
                    PaneraItems.add(new Food(fname, fprice, fside, fsize));
                    break;

                case 2:
                    System.out.print("Enter name for Beverage item: ");
                    String bname = in.nextLine();
                    System.out.print("Enter item price: ");
                    double bprice = in.nextDouble();
                    in.nextLine();
                    System.out.print("Enter item size: ");
                    String bsize = in.next();
                    System.out.print("Is this item a sip club item (yes/no)? ");
                    boolean sipClubItem;
                    if (in.next().equalsIgnoreCase("yes")) {
                        sipClubItem = true;
                    }
                    else {
                        sipClubItem = false;
                    }
                    PaneraItems.add(new Beverage(bname, bprice, sipClubItem, bsize));
                    break;

                case 3:
                    System.out.print("Enter name for Coffee item: ");
                    String cname = in.nextLine();
                    System.out.print("Enter milk type: ");
                    String milk = in.nextLine();
                    System.out.print("Enter item price: ");
                    double cprice = in.nextDouble();
                    in.nextLine();
                    System.out.print("Enter item size: ");
                    String csize = in.next();
                    System.out.print("Is this item a sip club item (yes/no)? ");
                    boolean csipClubItem;
                    if (in.next().equalsIgnoreCase("yes")) {
                        csipClubItem = true;
                    }
                    else {
                        csipClubItem = false;
                    }
                    PaneraItems.add(new Coffee(cname, cprice, csipClubItem, milk, csize));
                    break;

                case 4:
                    System.out.print("Enter user’s name: ");
                    String uname = in.nextLine();
                    System.out.print("Is this user a sip club member (yes/no)? ");
                    if (in.nextLine().equals("yes")) {
                        PaneraUsers.add(new PaneraUser(uname, true));
                    }
                    else {
                        PaneraUsers.add(new PaneraUser(uname, false));
                    }
                    break;

                case 5:
                    double total = 0.0;
                    ArrayList<PaneraItem> OrderedItems = new ArrayList<PaneraItem>();

                    System.out.print("How many items in order? ");
                    int itemCount = in.nextInt();
                    in.nextLine();
                    System.out.print("Name for order: ");
                    String userName = in.nextLine();

                    boolean isSipClubMember = false;

                    for (PaneraUser user : PaneraUsers) {
                        if (user.getName().equalsIgnoreCase(userName)) {
                            isSipClubMember = user.isSipClubMember();
                            break;
                        }
                    }

                    for (int i = 1; i <= itemCount; i++) {
                        boolean orderDone = false;
                        do {
                            System.out.print("Enter order " + i + ": ");
                            String itemSize = in.next();
                            String itemName = in.nextLine();

                            for (int j = 0; j < PaneraItems.size(); j++) {
                                String fix = " " + PaneraItems.get(j).getName();
                                if (PaneraItems.get(j).getSize().equalsIgnoreCase(itemSize) && fix.equalsIgnoreCase(itemName)) {
                                    OrderedItems.add(PaneraItems.get(j));
                                    orderDone = true;
                                }
                            }
                            if (!orderDone) {
                                System.out.println("Item not found");
                            }
                        } while (!orderDone);
                    }

                    for (PaneraItem item : OrderedItems) {
                        total += item.getPrice();
                    }

                    for (PaneraItem item : OrderedItems) {
                        if (item instanceof Beverage) {
                            Beverage beverage = (Beverage) item;
                            if (isSipClubMember && beverage.isSipClubItem()) {
                                total -= item.getPrice();
                            }

                        }
                    }

                    System.out.printf("Total is: $%.2f\n", total);
                    break;

                case 6:
                    System.out.print("How many items are you importing? ");
                    int numberItemsToAdd = in.nextInt();
                    in.nextLine();
                    System.out.println("Input bulk added items below");
                    for(int i = 0; i < numberItemsToAdd; i++) {
                        String[] item = in.nextLine().split(",");
                        if (item[0].equals("Food")) {
                            PaneraItems.add(new Food(item[1], Double.parseDouble(item[2]), item[3], item[4]));
                        }
                        else if (item[0].equals("Coffee")) {
                            PaneraItems.add(new Coffee(item[1], Double.parseDouble(item[2]), Boolean.parseBoolean(item[3]), item[4], item[5]));
                        }
                        else {
                            PaneraItems.add(new Beverage(item[1], Double.parseDouble(item[2]), Boolean.parseBoolean(item[3]), item[4]));
                        }
                    }
                    break;

                case 7:
                    printMenu();
                    break;

                case 8:
                    System.out.println("Exiting...");
                    break;
            }
        } while (menuOption != 8);
    }

    public static void printMenu() {
        System.out.println("1. Add Food Object to menu\n" +
                "2. Add Beverage Object to menu\n" +
                "3. Add Coffee Object to menu\n" +
                "4. Add Panera User\n" +
                "5. Order\n" +
                "6. Bulk add menu items\n" +
                "7. Print menu options\n" +
                "8. Exit\n");
    }

}

class PaneraItem {
    private String Name;
    private double Price;
    private String Size;

    public PaneraItem(String name, double price, String size) {
        Name = name;
        Price = price;
        Size = size;
    }

    public String getName() {
        return Name;
    }

    public double getPrice() {
        return Price;
    }

    public String getSize() {
        return Size;
    }
}

class Food extends PaneraItem {
    private String Side;
    public Food(String name, double price, String side, String size) {
        super(name, price, size);
        Side = side;
    }
}

class Beverage extends PaneraItem {
    private boolean SipClubItem;
    public Beverage(String name, double price, boolean sipClubItem, String size) {
        super(name, price, size);
        SipClubItem = sipClubItem;
    }
    public boolean isSipClubItem() {
        return SipClubItem;
    }
}

class Coffee extends Beverage {
    private String MilkType;
    public Coffee(String name, double price, boolean sipClubItem, String milk, String size) {
        super(name, price, sipClubItem, size);
        MilkType = milk;
    }
}
class PaneraUser {
    private String Name;
    private boolean SipClubMember;

    public PaneraUser(String name, boolean sipClubMember) {
        Name = name;
        SipClubMember = sipClubMember;
    }

    public String getName() {
        return Name;
    }

    public boolean isSipClubMember() {
        return SipClubMember;
    }
}