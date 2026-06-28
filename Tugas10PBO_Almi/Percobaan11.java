class BilanganNegatifException extends Throwable {
    public BilanganNegatifException(String message) {
        super(message);
    }
}

public class Percobaan11 {
    static void cekBilangan(int num) throws BilanganNegatifException {
        if (num < 0) {
            throw new BilanganNegatifException("Bilangan tidak boleh negatif: " + num);
        }
        System.out.println("Bilangan positif: " + num);
    }
    
    public static void main(String[] args) {
        try {
            cekBilangan(-10);
        } catch (BilanganNegatifException e) {
            System.out.println("Custom Exception tertangkap: " + e.getMessage());
        }
    }
}