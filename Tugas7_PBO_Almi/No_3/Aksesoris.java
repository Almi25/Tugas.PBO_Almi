package No_3;

public class Aksesoris extends Produk implements OperasiData {
    private double hargaBase;

    public Aksesoris(String kode, String nama, double hargaBase) {
        super(kode, nama);
        this.hargaBase = hargaBase;
    }

    @Override
    public double hitungHarga() {
        return hargaBase;
    }

    @Override
    public void cetakStruk() {
        // "nama" tidak akan merah jika Produk.java pakai "protected"
        System.out.println("Item: " + nama + " | Total: Rp" + hitungHarga());
    }
}