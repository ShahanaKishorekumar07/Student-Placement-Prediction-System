import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PlacementSystem {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("========================================");
        System.out.println("   STUDENT PLACEMENT PREDICTION SYSTEM");
        System.out.println("========================================");

        while (true) {

            System.out.println("\n1. Enter Student Details");
            System.out.println("2. Exit");

            System.out.print("\nEnter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 1) {
                predictPlacement();
            }
            else if (choice == 2) {
                System.out.println("\nThank you!");
                break;
            }
            else {
                System.out.println("\nInvalid choice.");
            }
        }

        scanner.close();
    }


    public static void predictPlacement() {

        scanner.nextLine();

        System.out.println("\n===== Enter Student Details =====");

        System.out.print("Student Name: ");
        String name = scanner.nextLine();

        System.out.print("Department: ");
        String department = scanner.nextLine();

        System.out.print("CGPA: ");
        double cgpa = scanner.nextDouble();

        System.out.print("Attendance: ");
        double attendance = scanner.nextDouble();

        System.out.print("Internships: ");
        int internships = scanner.nextInt();

        System.out.print("Projects: ");
        int projects = scanner.nextInt();

        System.out.print("Backlogs: ");
        int backlogs = scanner.nextInt();

        System.out.println("\n===== Student Details =====");

        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("CGPA: " + cgpa);
        System.out.println("Attendance: " + attendance + "%");
        System.out.println("Internships: " + internships);
        System.out.println("Projects: " + projects);
        System.out.println("Backlogs: " + backlogs);

        sendToMLModel(
                cgpa,
                attendance,
                internships,
                projects,
                backlogs
        );
    }


    public static void sendToMLModel(
            double cgpa,
            double attendance,
            int internships,
            int projects,
            int backlogs) {

        try {

            String jsonData =
                    "{"
                    + "\"cgpa\":" + cgpa + ","
                    + "\"attendance\":" + attendance + ","
                    + "\"internships\":" + internships + ","
                    + "\"projects\":" + projects + ","
                    + "\"backlogs\":" + backlogs
                    + "}";

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("http://127.0.0.1:5000/predict"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonData))
                    .build();

            HttpResponse<String> response =
                    client.send(
                            request,
                            HttpResponse.BodyHandlers.ofString()
                    );

            System.out.println("\n========================================");
            System.out.println("        ML PREDICTION RESULT");
            System.out.println("========================================");

            System.out.println(response.body());

            System.out.println("========================================");

        }
        catch (Exception e) {

            System.out.println("\nERROR: Unable to connect to ML server.");

            System.out.println("Make sure Flask is running at:");
            System.out.println("http://127.0.0.1:5000");

            System.out.println("\nDetails: " + e.getMessage());
        }
    }
}