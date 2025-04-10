import java.util.*;

class CourseFullException extends Exception {
    public CourseFullException(String message) {
        super(message);
    }
}

class PrerequisiteNotMetException extends Exception {
    public PrerequisiteNotMetException(String message) {
        super(message);
    }
}

class Course {
    private String name;
    private int capacity;
    private int enrolled;
    private String prerequisite;

    public Course(String name, int capacity, String prerequisite) {
        this.name = name;
        this.capacity = capacity;
        this.prerequisite = prerequisite;
        this.enrolled = 0;
    }

    public void enroll(String student, boolean hasCompletedPrerequisite)
            throws CourseFullException, PrerequisiteNotMetException {

        if (!hasCompletedPrerequisite) {
            throw new PrerequisiteNotMetException("Complete " + prerequisite + " before enrolling.");
        }

        if (enrolled >= capacity) {
            throw new CourseFullException("Course " + name + " is full.");
        }

        enrolled++;
        System.out.println("Enrollment successful! " + student + " enrolled in " + name);
    }
}

public class UniversityEnrollment {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Course advancedJava = new Course("Advanced Java", 2, "Core Java");

        try {
            System.out.print("Enter your name: ");
            String name = sc.nextLine();

            System.out.print("Have you completed Core Java? (yes/no): ");
            String status = sc.nextLine().trim().toLowerCase();
            boolean hasCompleted = status.equals("yes");

            advancedJava.enroll(name, hasCompleted);

        } catch (CourseFullException | PrerequisiteNotMetException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
        }
    }
}
