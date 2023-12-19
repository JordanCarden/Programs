import java.util.*;

public class SearchAndSort {
    public static int bpass = 0;
    public static int lpass = 0;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String string = in.nextLine();
        String[] stringArray = string.split(",");
        exchangeSort(stringArray, stringArray.length);
        String target = in.next();
        System.out.println(Arrays.toString(stringArray));
        System.out.println("Binary search - Target: " + target + ", found at index " + binarySearch(stringArray, target) + " with " + bpass + " steps");
        System.out.print("Linear search - Target: " + target + ", found at index " + linsearch(stringArray, target, stringArray.length) + " with " + lpass + " steps");



    }

    static void exchangeSort(String[] string, int size) {
        int i, j;
        String temp;
        for (i = 0; i < size - 1; i++) {
            for (j = i + 1; j < size; j++) {
                if (string[i].compareTo(string[j]) > 0) {
                    // Swapping
                    temp = string[i];
                    string[i] = string[j];
                    string[j] = temp;
                }
            }
        }
    }

    public static int binarySearch(String[] a, String x) {
        int low = 0;
        int high = a.length - 1;
        int mid;

        while (low <= high) {
            mid = (low + high) / 2;
            bpass++;
            if (a[mid].compareTo(x) < 0) {
                low = mid + 1;
            } else if (a[mid].compareTo(x) > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return -1;
    }

    public static int linsearch(String[] string, String search, int length)
    {
        int i;
        int flag = 0;
        for(i = 0; i<length; i++)
        {
            lpass++;
            if (search.equals(string[i]))
            {
                flag = 1;
                break;
            }

        }
        if (flag ==1)
        {
            return (i);
        }
        else
        {
            System.out.println("Word not found.");
        }
        return i;
    }
}