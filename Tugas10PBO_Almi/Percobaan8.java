public class Percobaan8 {
    static void bacaFile(String filename) throws Exception {
        if (filename.isEmpty()) {
            throw new Exception("Nama file tidak boleh kosong!");
        }
        System.out.println("File " + filename + " dibuka");
    }
    
    public static void main(String[] args) {
        try {
            bacaFile("");
        } catch (Exception e) {
            System.out.println("Exception tertangkap: " + e.getMessage());
        } finally {
            System.out.println("Proses selesai (finally block)");
        }
    }
}