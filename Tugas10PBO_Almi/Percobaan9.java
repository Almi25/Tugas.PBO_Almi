public class Percobaan9 {
    static void level3() throws Exception {
        throw new Exception("Error dari level 3");
    }
    
    static void level2() throws Exception {
        level3();
    }
    
    static void level1() throws Exception {
        level2();
    }
    
    public static void main(String[] args) {
        try {
            level1();
        } catch (Exception e) {
            System.out.println("Exception tertangkap di main: " + e.getMessage());
            System.out.println("Stack trace:");
            e.printStackTrace();
        }
    }
}