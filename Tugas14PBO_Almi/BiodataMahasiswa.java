import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BiodataMahasiswa extends JFrame {

    private JTextField fieldNim, fieldNama, fieldProdi;
    private JTextArea areaOutput;
    private JButton btnTampilkan, btnReset;

    public BiodataMahasiswa() {
        setTitle("Aplikasi Biodata Mahasiswa");
        setSize(550, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Menggunakan BorderLayout dengan margin eksternal agar rapi
        setLayout(new BorderLayout(10, 10));

        // 1. Panel Input (Atas)
        JPanel panelInput = new JPanel(new GridLayout(3, 2, 5, 5));
        panelInput.setBorder(BorderFactory.createTitledBorder("Input Data"));

        fieldNim = new JTextField();
        fieldNama = new JTextField();
        fieldProdi = new JTextField();

        panelInput.add(new JLabel("NIM"));
        panelInput.add(fieldNim);
        panelInput.add(new JLabel("Nama"));
        panelInput.add(fieldNama);
        panelInput.add(new JLabel("Program Studi"));
        panelInput.add(fieldProdi);

        // 2. Panel Tombol (Tengah)
        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        btnTampilkan = new JButton("Tampilkan");
        btnReset = new JButton("Reset");
        
        panelTombol.add(btnTampilkan);
        panelTombol.add(btnReset);

        // Menggabungkan Panel Input dan Panel Tombol ke bagian Utara (Top)
        JPanel panelAtas = new JPanel(new BorderLayout());
        panelAtas.add(panelInput, BorderLayout.CENTER);
        panelAtas.add(panelTombol, BorderLayout.SOUTH);

        // 3. Panel Output (Bawah)
        JPanel panelOutput = new JPanel(new BorderLayout());
        panelOutput.setBorder(BorderFactory.createTitledBorder("Output"));

        areaOutput = new JTextArea();
        areaOutput.setEditable(false);
        // Menggunakan font Monospaced agar tanda titik dua (:) bisa sejajar lurus
        areaOutput.setFont(new Font("Monospaced", Font.PLAIN, 12)); 
        
        JScrollPane scrollPane = new JScrollPane(areaOutput);
        panelOutput.add(scrollPane, BorderLayout.CENTER);

        // Menambahkan panel utama ke Frame
        add(panelAtas, BorderLayout.NORTH);
        add(panelOutput, BorderLayout.CENTER);

        // --- Logika Action Listener ---

        // Logika ketika tombol Tampilkan diklik
        btnTampilkan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nim = fieldNim.getText();
                String nama = fieldNama.getText();
                String prodi = fieldProdi.getText();

                // Format string agar rapi menggunakan tab/spasi manual pada font Monospaced
                String hasil = "========== BIODATA MAHASISWA ==========\n\n"
                        + String.format("%-15s : %s\n", "NIM", nim)
                        + String.format("%-15s : %s\n", "Nama", nama)
                        + String.format("%-15s : %s\n", "Program Studi", prodi);

                areaOutput.setText(hasil);
            }
        });

        // Logika ketika tombol Reset diklik
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fieldNim.setText("");
                fieldNama.setText("");
                fieldProdi.setText("");
                areaOutput.setText("");
            }
        });
    }

    public static void main(String[] args) {
        // Menjalankan GUI di thread yang aman (Event Dispatch Thread)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BiodataMahasiswa().setVisible(true);
            }
        });
    }
}