package No_3;

public abstract class Produk {
    private String kode;
    protected String nama; // Harus protected

    public Produk(String kode, String nama) {
        this.kode = kode;
        this.nama = nama;
    }

    public String getKode() { return kode; }
    public abstract double hitungHarga();
}