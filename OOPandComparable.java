import java.util.*;

public class OOPandComparable {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        ArrayList<Pet> Pets = new ArrayList<Pet>();
        for (int i = 0; i<n; i++) {
            Pets.add(new Pet(in.nextLine(), in.nextLine(), in.nextDouble()));
            in.nextLine();
        }

        Collections.sort(Pets);

        System.out.printf("%-11s %8s %8s\n", "Pet name","Type","Price");
        for(Pet c: Pets) {
            c.printInfo();
        }
    }
}

class Pet implements Comparable<Pet> {
    private String name;
    private String type;
    private double price;

    public Pet(String name, String type, double price) {
        this.name = name;
        this.type = type;
        this.price = price;
    }

    public void printInfo() {
        System.out.printf("%-10s %" +
                "8s: %8.2f\n", name, type, price);
    }

    public int compareTo(Pet other) {
        if (Double.compare(price,other.price) != 0) {
            return Double.compare(price,other.price);
        }
        else
            return name.compareTo(other.name);
    }
}