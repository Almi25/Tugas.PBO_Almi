public class Percobaan6 {
    static int bagi(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Pembagi tidak boleh 0!");
        }
        return a / b;
    }
    
    public static void main(String[] args) {
        try {
            int hasil = bagi(10, 0);
            System.out.println("Hasil: " + hasil);
        } catch (ArithmeticException e) {
            System.out.println("Error tertangkap: " + e.getMessage());
        }
    }
}