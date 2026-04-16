import java.util.Scanner;

// PART 1: CLASS BANK (METHOD OVERLOADING)
class Bank {
    protected String namaBank = "Bank Umum";
    protected double saldo = 1000000;

    // Method untuk menampilkan suku bunga standar
    public void sukuBunga() {
        System.out.println("Suku Bunga standar adalah 3%");
    }

    // ========== METHOD OVERLOADING ==========
    
    // 1. Transfer ke rekening lain (dalam bank yang sama)
    public void transferUang(int jumlah, String rekeningTujuan) {
        System.out.println("\n--- TRANSFER INTERNAL ---");
        System.out.println("Jumlah      : Rp " + jumlah);
        System.out.println("Rek. Tujuan : " + rekeningTujuan);
        System.out.println("Biaya Admin : Rp 0 (Gratis)");
        System.out.println("Status      : Transfer Berhasil!");
    }

    // 2. Transfer ke rekening lain di bank berbeda
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        int biayaAdmin = hitungBiayaTransfer(bankTujuan);
        System.out.println("\n--- TRANSFER ANTAR BANK ---");
        System.out.println("Jumlah      : Rp " + jumlah);
        System.out.println("Rek. Tujuan : " + rekeningTujuan);
        System.out.println("Bank Tujuan : " + bankTujuan);
        System.out.println("Biaya Admin : Rp " + biayaAdmin);
        System.out.println("Status      : Transfer Berhasil!");
    }

    // 3. Transfer dengan tambahan berita/pesan
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan, String berita) {
        int biayaAdmin = hitungBiayaTransfer(bankTujuan);
        System.out.println("\n--- TRANSFER DENGAN BERITA ---");
        System.out.println("Jumlah      : Rp " + jumlah);
        System.out.println("Rek. Tujuan : " + rekeningTujuan);
        System.out.println("Bank Tujuan : " + bankTujuan);
        System.out.println("Berita      : " + berita);
        System.out.println("Biaya Admin : Rp " + biayaAdmin);
        System.out.println("Status      : Transfer Berhasil!");
    }

    // BONUS: Hitung biaya transfer
    protected int hitungBiayaTransfer(String bankTujuan) {
        switch (bankTujuan.toUpperCase()) {
            case "BNI": case "BCA": case "MANDIRI": case "BRI":
                return 6500;
            default:
                return 7500;
        }
    }
}

// PART 2: CLASS BANKBNI (METHOD OVERRIDING)
class BankBNI extends Bank {
    
    public BankBNI() {
        this.namaBank = "BNI";
    }

    @Override
    public void sukuBunga() {
        System.out.println("Suku Bunga BNI adalah 4%");
    }

    @Override
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        bankTujuan = "BNI"; // Override bank tujuan
        System.out.println("\n--- TRANSFER VIA BNI ---");
        System.out.println("Jumlah      : Rp " + jumlah);
        System.out.println("Rek. Tujuan : " + rekeningTujuan);
        System.out.println("Bank Tujuan : " + bankTujuan);
        System.out.println("Biaya Admin : Rp 0 (Sesama BNI Gratis)");
        System.out.println("Status      : Transfer Berhasil!");
    }
}

// PART 2: CLASS BANKBCA (METHOD OVERRIDING)
class BankBCA extends Bank {
    
    public BankBCA() {
        this.namaBank = "BCA";
    }

    @Override
    public void sukuBunga() {
        System.out.println("Suku Bunga BCA adalah 4.5%");
    }

    @Override
    public void transferUang(int jumlah, String rekeningTujuan, String bankTujuan) {
        bankTujuan = "BCA"; // Override bank tujuan
        System.out.println("\n--- TRANSFER VIA BCA ---");
        System.out.println("Jumlah      : Rp " + jumlah);
        System.out.println("Rek. Tujuan : " + rekeningTujuan);
        System.out.println("Bank Tujuan : " + bankTujuan);
        System.out.println("Biaya Admin : Rp 0 (Sesama BCA Gratis)");
        System.out.println("Status      : Transfer Berhasil!");
    }
}

// MAIN CLASS
public class BankSystem {
    public static void main(String[] args) {
        
        // PART 1: METHOD OVERLOADING
        Bank bankUmum = new Bank();
        bankUmum.sukuBunga();
        bankUmum.transferUang(500000, "1234567890");
        bankUmum.transferUang(750000, "0987654321", "BCA");
        bankUmum.transferUang(1000000, "1122334455", "BNI", "Pembayaran invoice");

        // PART 2: METHOD OVERRIDING
        BankBNI bankBNI = new BankBNI();
        bankBNI.sukuBunga();
        bankBNI.transferUang(500000, "7777888899", "MANDIRI");

        BankBCA bankBCA = new BankBCA();
        bankBCA.sukuBunga();
        bankBCA.transferUang(600000, "3344556677", "BNI");
    }
}