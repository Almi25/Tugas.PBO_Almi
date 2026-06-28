public class Percobaan7 {
    static void cekUmur(int umur) {
        if (umur < 0) {
            throw new IllegalArgumentException("Umur tidak boleh negatif!");
        } else if (umur < 18) {
            throw new IllegalArgumentException("Harus berusia 18+ untuk masuk!");
        }
        System.out.println("Umur Anda: " + umur + " tahun");
    }
    
    public static void main(String[] args) {
        try {
            cekUmur(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("Pesan Error: " + e.getMessage());
        }
    }
}