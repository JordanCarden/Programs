import java.util.Scanner;
import java.util.PriorityQueue;

class Triage implements Comparable<Triage> {
    String name;
    String problem;
    String severity;

    public Triage(String name, String problem, String severity) {
        this.name = name;
        this.problem = problem;
        this.severity = severity;
    }

    @Override
    public int compareTo(Triage other) {
        String[] priorityOrder = {"mild", "moderate", "severe"};

        int thisPriority = getIndex(this.severity, priorityOrder);
        int otherPriority = getIndex(other.severity, priorityOrder);

        return Integer.compare(otherPriority, thisPriority);
    }

    private int getIndex(String value, String[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }
}

public class PriorityQueues {
    public static void main(String[] args) {
        PriorityQueue<Triage> triageQueue = new PriorityQueue<>();
        Scanner scanner = new Scanner(System.in);
        printMenu();
        System.out.println();

        while (true) {

            System.out.print("Enter menu option: ");
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    intakePatient(scanner, triageQueue);
                    break;
                case 2:
                    callPatientIn(triageQueue);
                    break;
                case 3:
                    System.out.println(triageQueue.size() + " Patients remaining.");
                    break;
                case 4:
                    patientGaveUp(scanner, triageQueue);
                    break;
                case 5:
                    printMenu();
                    break;
                case 6:
                    System.out.println("End program");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("Welcome to the ER…");
        System.out.println("1. Intake patient");
        System.out.println("2. Call patient in");
        System.out.println("3. How many patients waiting");
        System.out.println("4. Patient gave up and left");
        System.out.println("5. Print Options");
        System.out.println("6. End program");
    }

    private static void intakePatient(Scanner scanner, PriorityQueue<Triage> triageQueue) {
        System.out.print("Patient name: ");
        String name = scanner.nextLine();

        System.out.print("Patient problem: ");
        String problem = scanner.nextLine();

        System.out.print("Enter severity (mild, moderate, severe): ");
        String severity = scanner.nextLine().toLowerCase();

        Triage patient = new Triage(name, problem, severity);
        triageQueue.add(patient);
    }

    private static void callPatientIn(PriorityQueue<Triage> triageQueue) {
        if (triageQueue.isEmpty()) {
            System.out.println("No patients waiting");
        }
        else {
            Triage nextPatient = triageQueue.poll();
            System.out.println("Calling patient, " + nextPatient.name);
            System.out.println("Submitted with " + nextPatient.problem);
        }
    }

    private static void patientGaveUp(Scanner scanner, PriorityQueue<Triage> triageQueue) {
        System.out.print("What patient left? ");
        String patientName = scanner.nextLine();

        boolean found = false;

        for (Triage patient : triageQueue) {
            if (patient.name.equalsIgnoreCase(patientName)) {
                triageQueue.remove(patient);
                System.out.println(patient.name + " left");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Patient not found");
        }
    }
}