package No_3;

import java.util.ArrayList;

public class Keranjang<T> {
    private ArrayList<T> daftarItem = new ArrayList<>();

    public void tambahItem(T item) {
        daftarItem.add(item);
    }

    public ArrayList<T> getSemuaItem() {
        return daftarItem;
    }
}