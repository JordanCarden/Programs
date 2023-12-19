import java.util.*;
import java.io.*;
public class ReadWriteFile {
    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("input.txt");
        PrintWriter output = new PrintWriter("output.txt");
        Scanner in = new Scanner(input);
        ArrayList<Movie> Movies = new ArrayList<Movie>();
        while (in.hasNextLine()) {
            String[] name = in.nextLine().split(";");
            Movies.add(new Movie(name[0], name[1], Integer.parseInt(name[2]), Double.parseDouble(name[3])));
        }

        Collections.sort(Movies, Collections.reverseOrder());

        output.println("My Movie Library  \n" +
                "*******************************************************************");
        for (Movie c : Movies) {
            output.println(c.printInfo());
        }
        output.close();
    }
}

class Movie implements Comparable<Movie> {
    private String title;
    private String director;
    private int year;
    private double rating;

    Movie(String title, String director, int year, double rating) throws FileNotFoundException {
        this.title = title;
        this.director = director;
        this.year = year;
        this.rating = rating;
    }

    public String printInfo() {
        return this.title + ", Directed by " + this.director + ", Came out in: " + this.year + ", Has a rating of " + this.rating;
    }

    @Override
    public int compareTo(Movie other) {
        if (Double.compare(rating, other.rating) != 0) {
            return Double.compare(rating, other.rating);
        } else
            return title.compareTo(other.title);
    }
}


