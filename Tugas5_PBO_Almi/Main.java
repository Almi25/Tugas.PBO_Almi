import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Program Simulasi Mahasiswa dan Dosen ===\n");
        
        // Input data Mahasiswa
        System.out.println("--- Input Data Mahasiswa ---");
        System.out.print("Masukkan nama mahasiswa: ");
        String studentName = scanner.nextLine();
        System.out.print("Masukkan alamat mahasiswa: ");
        String studentAddress = scanner.nextLine();
        
        Student student = new Student(studentName, studentAddress);
        
        System.out.print("Berapa mata kuliah yang ingin ditambahkan? ");
        int numStudentCourses = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        for (int i = 0; i < numStudentCourses; i++) {
            System.out.print("Masukkan nama mata kuliah ke-" + (i + 1) + ": ");
            String course = scanner.nextLine();
            System.out.print("Masukkan nilai untuk " + course + ": ");
            int grade = scanner.nextInt();
            scanner.nextLine(); // consume newline
            student.addCourseGrade(course, grade);
        }
        
        System.out.println();
        
        // Input data Dosen
        System.out.println("--- Input Data Dosen ---");
        System.out.print("Masukkan nama dosen: ");
        String teacherName = scanner.nextLine();
        System.out.print("Masukkan alamat dosen: ");
        String teacherAddress = scanner.nextLine();
        
        Teacher teacher = new Teacher(teacherName, teacherAddress);
        
        System.out.print("Berapa mata kuliah yang diampu? ");
        int numTeacherCourses = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        for (int i = 0; i < numTeacherCourses; i++) {
            System.out.print("Masukkan nama mata kuliah ke-" + (i + 1) + ": ");
            String course = scanner.nextLine();
            boolean added = teacher.addCourse(course);
            if (!added) {
                System.out.println("Gagal: Mata kuliah '" + course + "' sudah ada!");
            }
        }
        
        System.out.println("\n=== HASIL OUTPUT ===\n");
        
        // Output Mahasiswa
        System.out.println("--- Data Mahasiswa ---");
        System.out.println(student.toString());
        student.printGrades();
        System.out.printf("Rata-rata Nilai: %.2f%n", student.getAverageGrade());
        
        System.out.println();
        
        // Output Dosen
        System.out.println("--- Data Dosen ---");
        System.out.println(teacher.toString());
        System.out.println("Mata Kuliah yang diampu:");
        for (String course : teacher.getCourses()) {
            System.out.println("  - " + course);
        }
        
        // Demo removeCourse
        System.out.println("\n--- Demo Remove Course ---");
        if (teacher.getNumCourses() > 0) {
            String courseToRemove = teacher.getCourses()[0];
            System.out.println("Mencoba menghapus: " + courseToRemove);
            boolean removed = teacher.removeCourse(courseToRemove);
            System.out.println("Berhasil dihapus: " + removed);
            
            System.out.println("Mata Kuliah setelah dihapus:");
            for (String course : teacher.getCourses()) {
                System.out.println("  - " + course);
            }
        }
        
        // Demo setAddress
        System.out.println("\n--- Demo Update Address ---");
        System.out.print("Masukkan alamat baru untuk mahasiswa: ");
        String newAddress = scanner.nextLine();
        student.setAddress(newAddress);
        System.out.println("Alamat mahasiswa setelah diupdate: " + student.getAddress());
        System.out.println(student.toString());
        
        scanner.close();
        System.out.println("\n=== Program Selesai ===");
    }
}