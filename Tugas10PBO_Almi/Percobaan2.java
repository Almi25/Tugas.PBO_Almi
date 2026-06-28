public class Percobaan2 {
    public static void main(String[] args) {
        int[] data = {10, 20, 30, 40, 50};
        
        try {
            System.out.println("Akses array:");
            for (int i = 0; i <= 5; i++) {
                System.out.println("data[" + i + "] = " + data[i]);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Index di luar batas array!");
            System.out.println("Pesan: " + e.getMessage());
        }
        
        System.out.println("\nProgram selesai");
    }
}