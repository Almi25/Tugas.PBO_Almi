public class TransferFeeCalculation {

    public static int hitungBiayaTransfer(String bankTujuan) {
        if (bankTujuan == null) {
            return 5000;
        }

        if (bankTujuan.equalsIgnoreCase("BNI")) {
            return 0;
        }

        return 5000;
    }

    public static void main(String[] args) {
        int biaya = hitungBiayaTransfer("BNI");
        System.out.println("Biaya transfer: " + biaya);
    }
}