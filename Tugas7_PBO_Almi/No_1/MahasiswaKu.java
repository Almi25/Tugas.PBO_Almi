package No_1;
public class MahasiswaKu {

    public static void main(String[] args) {
        // Membuat objek m dari class generic Mahasiswa
        // T1=String (NIM), T2=String (Nama), T3=Integer (Kelas)
        Mahasiswa <String, String, Integer> m = new Mahasiswa<>();

        // Mengatur nilai menggunakan method setter
        m.setNim("1102020");
        m.setName("Ferdi");
        m.setClas(21);

        // Menampilkan nilai menggunakan method getter
        System.out.println(m.getNim());
        System.out.println(m.getName());
        System.out.println(m.getClas());
    }
}