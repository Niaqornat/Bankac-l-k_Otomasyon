import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MusteriKredileriEkranı extends JFrame {
    public MusteriKredileriEkranı(List<KrediBasvuru> krediBasvurulari) {
        if (krediBasvurulari == null) {
            throw new IllegalArgumentException("Kredi başvuruları null olamaz");
        }

        setTitle("Banka Uygulaması - Müşteri Kredileri");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        String[][] krediler = new String[krediBasvurulari.size()][3];
        for (int i = 0; i < krediBasvurulari.size(); i++) {
            KrediBasvuru krediBasvuru = krediBasvurulari.get(i);
            Musteri musteri = krediBasvuru.getMusteri();
            krediler[i][0] = musteri.getAd();
            krediler[i][1] = musteri.getSoyad();
            krediler[i][2] = String.valueOf(krediBasvuru.getMiktar());
        }
        String[] columnNames = {"Müşteri Adı", "Müşteri Soyadı", "Kredi Miktarı"};
        JTable kredilerTable = new JTable(krediler, columnNames);
        JScrollPane scrollPane = new JScrollPane(kredilerTable);
        JButton onaylaButton = new JButton("Onayla");
        JButton reddetButton = new JButton("Reddet");

        onaylaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = kredilerTable.getSelectedRow();
                if (selectedRow != -1) {
                    KrediBasvuru krediBasvuru = krediBasvurulari.get(selectedRow);
                    krediBasvuru.setOnayDurumu(true);
                    JOptionPane.showMessageDialog(MusteriKredileriEkranı.this, "Kredi başvurusu onaylandı!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(MusteriKredileriEkranı.this, "Lütfen bir kredi seçin.");
                }
            }
        });

        reddetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = kredilerTable.getSelectedRow();
                if (selectedRow != -1) {
                    KrediBasvuru krediBasvuru = krediBasvurulari.get(selectedRow);
                    krediBasvuru.setOnayDurumu(false);
                    JOptionPane.showMessageDialog(MusteriKredileriEkranı.this, "Kredi başvurusu reddedildi!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(MusteriKredileriEkranı.this, "Lütfen bir kredi seçin.");
                }
            }
        });
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(onaylaButton);
        buttonPanel.add(reddetButton);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    public static void main(String[] args) {
        
    	List<KrediBasvuru> krediBasvurulari = BankaVeritabani.getKrediBasvurular(); 

        MusteriKredileriEkranı ekran = new MusteriKredileriEkranı(krediBasvurulari);
        ekran.setVisible(true);
    }
}
