public class Percobaan3 {
    public static void main(String[] args) {
        int a = 10;
        int b = 0;
        
        try {
            int hasil = a / b;
            System.out.println("Hasil: " + hasil);
        } catch (ArithmeticException e) {
            System.out.println("Error: Tidak boleh dibagi dengan 0!");
        }
    }
}