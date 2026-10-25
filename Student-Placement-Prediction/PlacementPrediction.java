import java.util.Scanner;

public class PlacementPrediction {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println(" Student Placement Prediction");
        System.out.println("=================================");

        System.out.print("Enter CGPA: ");
        double cgpa = sc.nextDouble();

        System.out.print("Enter Attendance (%): ");
        double attendance = sc.nextDouble();

        System.out.print("Enter 10th Percentage: ");
        double tenth = sc.nextDouble();

        System.out.print("Enter 12th Percentage: ");
        double twelfth = sc.nextDouble();

        System.out.print("Enter Number of Certifications: ");
        int certifications = sc.nextInt();

        System.out.print("Enter Number of Internships: ");
        int internships = sc.nextInt();

        System.out.print("Enter Technical Skill Score (1-10): ");
        int technical = sc.nextInt();

        System.out.print("Enter Communication Skill Score (1-10): ");
        int communication = sc.nextInt();

        System.out.print("Enter Number of Projects: ");
        int projects = sc.nextInt();

        System.out.print("Enter Number of Backlogs: ");
        int backlogs = sc.nextInt();

        // Simple prediction score
        int score = 0;

        if (cgpa >= 7.0)
            score++;

        if (attendance >= 75)
            score++;

        if (tenth >= 70)
            score++;

        if (twelfth >= 70)
            score++;

        if (certifications >= 2)
            score++;

        if (internships >= 1)
            score++;

        if (technical >= 7)
            score++;

        if (communication >= 7)
            score++;

        if (projects >= 2)
            score++;

        if (backlogs == 0)
            score++;

        System.out.println("\n=================================");

        if (score >= 6) {
            System.out.println("Prediction: LIKELY TO BE PLACED");
        } else {
            System.out.println("Prediction: NOT LIKELY TO BE PLACED");
        }

        System.out.println("=================================");

        sc.close();
    }
}