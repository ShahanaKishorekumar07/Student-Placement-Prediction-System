import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Student {

    private String name;
    private String department;
    private double cgpa;
    private double attendance;
    private int internships;
    private int projects;
    private int backlogs;

    public Student(String name, String department, double cgpa,
                   double attendance, int internships,
                   int projects, int backlogs) {

        this.name = name;
        this.department = department;
        this.cgpa = cgpa;
        this.attendance = attendance;
        this.internships = internships;
        this.projects = projects;
        this.backlogs = backlogs;
    }

    public void displayStudentDetails() {

        System.out.println("\n===== Student Details =====");
        System.out.println("Name: " + name);
        System.out.println("Department: " + department);
        System.out.println("CGPA: " + cgpa);
        System.out.println("Attendance: " + attendance + "%");
        System.out.println("Internships: " + internships);
        System.out.println("Projects: " + projects);
        System.out.println("Backlogs: " + backlogs);
    }

    public void predictPlacement() {

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
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("\n===== ML Prediction =====");
            System.out.println("Response: " + response.body());

        } catch (Exception e) {

            System.out.println("\nUnable to connect to ML server.");
            System.out.println("Make sure Flask is running.");
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println(" Student Placement Prediction");
        System.out.println("=================================");

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Department: ");
        String department = scanner.nextLine();

        System.out.print("Enter CGPA: ");
        double cgpa = scanner.nextDouble();

        System.out.print("Enter Attendance: ");
        double attendance = scanner.nextDouble();

        System.out.print("Enter Number of Internships: ");
        int internships = scanner.nextInt();

        System.out.print("Enter Number of Projects: ");
        int projects = scanner.nextInt();

        System.out.print("Enter Number of Backlogs: ");
        int backlogs = scanner.nextInt();

        Student student = new Student(
                name,
                department,
                cgpa,
                attendance,
                internships,
                projects,
                backlogs
        );

        student.displayStudentDetails();

        student.predictPlacement();

        scanner.close();
    }
}