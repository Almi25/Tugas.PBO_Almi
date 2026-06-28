public class Percobaan5 {
    public static void main(String[] args) {
        try {
            int[] data = {5, 10, 15};
            System.out.println(data[10]);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("=== Exception Information ===");
            System.out.println("getMessage(): " + e.getMessage());
            System.out.println("\ntoString(): " + e.toString());
            System.out.println("\nprintStackTrace():");
            e.printStackTrace();
        }
    }
}