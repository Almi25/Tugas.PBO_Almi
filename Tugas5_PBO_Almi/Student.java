public class Student extends Person {
    private int numCourses = 0;
    private String[] courses = new String[10];
    private int[] grades = new int[10];

    public Student(String name, String address) {
        super(name, address);
    }

    public void addCourseGrade(String course, int grade) {
        if (numCourses < courses.length) {
            courses[numCourses] = course;
            grades[numCourses] = grade;
            numCourses++;
        } else {
            System.out.println("Maksimal mata kuliah tercapai!");
        }
    }

    public void printGrades() {
        for (int i = 0; i < numCourses; i++) {
            System.out.println(courses[i] + ": " + grades[i]);
        }
    }

    public double getAverageGrade() {
        if (numCourses == 0) return 0; // hindari bagi 0

        int sum = 0;
        for (int i = 0; i < numCourses; i++) {
            sum += grades[i];
        }
        return (double) sum / numCourses;
    }

    @Override
    public String toString() {
        return "Student: " + super.toString();
    }
}