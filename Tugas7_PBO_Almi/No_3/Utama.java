package No_3;

public class Utama {
    public static void main(String[] args) {
        Produk barang1 = new Aksesoris("B001", "Gelang Bead", 15000);
        
        Keranjang<Produk> keranjang = new Keranjang<>();
        keranjang.tambahItem(barang1);
        keranjang.tambahItem(new Aksesoris("B002", "Cincin Custom", 20000));

        System.out.println("=== Sistem Inventaris Toko ===");
        for (Produk p : keranjang.getSemuaItem()) {
            if (p instanceof OperasiData) {
                ((OperasiData) p).cetakStruk();
            }
        }
    }
}