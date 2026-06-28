public class Percobaan4 {
    public static void main(String[] args) {
        try {
            // Input dari user
            String nama = "123ABC";
            int umur = Integer.parseInt(nama);
            
            int[] data = {10, 20};
            System.out.println(data[5]);
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Input harus angka!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Index di luar batas!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}