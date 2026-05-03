package No_2;
import java.util.ArrayList;
import java.util.ArrayDeque;

public class ContohCollection {
    public static void main(String[] args) {
        // ArrayList: Menyimpan daftar nama mahasiswa
        ArrayList<String> listMahasiswa = new ArrayList<>();
        listMahasiswa.add("Ferdi");
        listMahasiswa.add("Andi");
        System.out.println("ArrayList: " + listMahasiswa);

        // ArrayDeque: Simulasi antrian pendaftaran
        ArrayDeque<String> antrian = new ArrayDeque<>();
        antrian.addLast("Antrian 1");
        antrian.addLast("Antrian 2");
        antrian.addFirst("Urgent - Antrian 0"); // Menambah ke depan
        System.out.println("ArrayDeque: " + antrian);
    }
}
