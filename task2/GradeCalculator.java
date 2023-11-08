import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int numSubjects = 0;
        while (numSubjects <= 0) {
            System.out.print("Enter the number of subjects: ");
            if (scanner.hasNextInt()) {
                numSubjects = scanner.nextInt();
                if (numSubjects <= 0) {
                    System.out.println("Invalid input. Please enter a positive integer.");
                }
            } else {
                System.out.println("Invalid input. Please enter a positive integer.");
                scanner.next(); 
            }
        }
        scanner.nextLine(); 

        String[] subjects = new String[numSubjects];
        double[] marks = new double[numSubjects];

        Set<String> subjectSet = new HashSet<>();
        System.out.println("\n");

        for (int i = 0; i < numSubjects; i++) {
            while (true) {
                System.out.print("Enter the name of subject " + (i + 1) + ": ");
                String subjectName = scanner.nextLine();

                if (!subjectName.isEmpty() && !subjectSet.contains(subjectName)) {
                    subjects[i] = subjectName;
                    subjectSet.add(subjectName);
                    break;
                } else if (subjectName.isEmpty()) {
                    System.out.println("Subject name cannot be empty. Please enter a valid name.");
                } else if (subjectSet.contains(subjectName)) {
                    System.out.println("Subject name already exists. Please enter a unique name.");
                }
            }
        }
        
        System.out.println("\n");
        for (int i = 0; i < numSubjects; i++) {
            while (true) {
                System.out.print("Enter marks obtained in " + subjects[i] + ": ");
                if (scanner.hasNextDouble()) {
                    marks[i] = scanner.nextDouble();
                    if (marks[i] >= 0 && marks[i] <= 100) {
                        break;
                    } else {
                        System.out.println("Invalid marks. Please enter marks between 0 and 100.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.next(); 
                }
            }
            scanner.nextLine(); 
        }

        double totalMarks = 0;
        for (double mark : marks) {
            totalMarks += mark;
        }

        double averagePercentage = totalMarks / (numSubjects * 100) * 100;

        String grade;
        if (averagePercentage >= 90) {
            grade = "A+";
        } else if (averagePercentage >= 80) {
            grade = "A";
        } else if (averagePercentage >= 70) {
            grade = "B";
        } else if (averagePercentage >= 60) {
            grade = "C";
        } else if (averagePercentage >= 50) {
            grade = "D";
        } else {
            grade = "F";
        }
        
        System.out.println("\n----------------------------------------");
        System.out.println("               Results                  ");
        System.out.println("----------------------------------------");
        System.out.printf("%-20s %10s\n", "Total Marks Obtained:", totalMarks);
        System.out.printf("%-20s %10.2f%%\n", "Average Percentage:", averagePercentage);
        System.out.printf("%-20s %10s\n", "Grade:", grade);
        System.out.println("----------------------------------------");

        scanner.close();
    }
}
