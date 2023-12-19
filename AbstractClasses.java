import java.util.Scanner;
public class AbstractClasses {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Rectangle R1 = new Rectangle(in.nextDouble(), in.nextDouble());
        Rectangle R2 = new Rectangle(in.nextDouble(), in.nextDouble());
        Square S1 = new Square(in.nextDouble());
        Square S2 = new Square(in.nextDouble());
        Circle C1 = new Circle(in.nextDouble());
        Circle C2 = new Circle(in.nextDouble());

        System.out.printf("Area of first rectangle: %.4f\n", R1.calculateArea());
        System.out.printf("Perimeter of first rectangle: %.4f\n", R1.calculatePerimeter());
        System.out.printf("Area of second rectangle: %.4f\n", R2.calculateArea());
        System.out.printf("Perimeter of second rectangle: %.4f\n", R2.calculatePerimeter());
        System.out.println("The two objects have the same area: " + equalArea(R1, R2));
        System.out.printf("Area of first square: %.4f\n", S1.calculateArea());
        System.out.printf("Perimeter of first square: %.4f\n", S1.calculatePerimeter());
        System.out.printf("Area of second square: %.4f\n", S2.calculateArea());
        System.out.printf("Perimeter of second square: %.4f\n", S2.calculatePerimeter());
        System.out.println("The two objects have the same area: " + equalArea(S1, S2));
        System.out.printf("Area of first circle: %.4f\n", C1.calculateArea());
        System.out.printf("Circumference of first circle: %.4f\n", C1.calculatePerimeter());
        System.out.printf("Area of second circle: %.4f\n", C2.calculateArea());
        System.out.printf("Circumference of second circle: %.4f\n", C2.calculatePerimeter());
        System.out.print("The two objects have the same area: " + equalArea(C1, C2));
    }

    public static boolean equalArea(Shape A, Shape B) {
        if (A.calculateArea() == B.calculateArea()) {
            return true;
        }
        else {
            return false;
        }
    }
}

abstract class Shape {
    protected double height;

    public Shape(double height) {
        this.height = height;
    }

    abstract double calculateArea();

    abstract double calculatePerimeter();
}

class Rectangle extends Shape {
    private double width;

    public Rectangle(double width, double height) {
        super(height);
        this.width = width;
    }

    @Override
    double calculatePerimeter() {
        return 2*width + 2*height;
    }
    @Override
    double calculateArea() {
        return width*height;
    }
}

class Square extends Rectangle {

    public Square(double side) {
        super(side, side);
    }
}

class Circle extends Shape {
    public Circle(double height) {
        super(height);
    }

    @Override
    double calculatePerimeter() {
        return Math.PI*height;
    }

    @Override
    double calculateArea() {
        return (Math.pow(height/2, 2))*Math.PI;
    }
}