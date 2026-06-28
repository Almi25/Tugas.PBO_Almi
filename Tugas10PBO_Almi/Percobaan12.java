class SaldoTidakCukupException extends Exception {
    public SaldoTidakCukupException(String message) {
        super(message);
    }
}

public class Percobaan12 {
    static void tarikTunaiBatasan(double saldo, double jumlah) throws SaldoTidakCukupException {
        if (jumlah > saldo) {
            throw new SaldoTidakCukupException("Saldo tidak cukup! Saldo: " + saldo + 
                                              ", Penarikan: " + jumlah);
        }
        double sisaSaldo = saldo - jumlah;
        System.out.println("Penarikan berhasil!");
        System.out.println("Jumlah: " + jumlah);
        System.out.println("Sisa saldo: " + sisaSaldo);
    }
    
    public static void main(String[] args) {
        try {
            tarikTunaiBatasan(500000, 600000);
        } catch (SaldoTidakCukupException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}