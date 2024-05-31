import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class MusteriListesi extends JFrame {
	private BankaVeritabani bankaVeritabani;
    public MusteriListesi() {
    	bankaVeritabani = new BankaVeritabani();
        setTitle("Banka Uygulaması - Müşteri Listesi");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);

        JPanel musteriListesiPanel = new JPanel(new BorderLayout());

        // Veritabanı işlemleri SwingWorker içinde yapılıyor.
        SwingWorker<ArrayList<Musteri>, Void> worker = new SwingWorker<ArrayList<Musteri>, Void>() {
            @Override
            protected ArrayList<Musteri> doInBackground() throws Exception {
                return BankaciBilgileri.getMusteriler();
            }

            @Override
            protected void done() {
                try {
                    ArrayList<Musteri> musteriler = get();
                    String[] columnNames = {"ID", "Ad", "Soyad", "Kullanıcı Adı", "Şifre", "TC No"};
                    Object[][] data = new Object[musteriler.size()][6];

                    for (int i = 0; i < musteriler.size(); i++) {
                        Musteri musteri = musteriler.get(i);
                        data[i][0] = musteri.getId();
                        data[i][1] = musteri.getAd();
                        data[i][2] = musteri.getSoyad();
                        data[i][3] = musteri.getKullaniciAdi();
                        data[i][4] = musteri.getSifre();
                        data[i][5] = musteri.getTcNo();
                    }

                    DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
                    JTable table = new JTable(tableModel);

                    JScrollPane scrollPane = new JScrollPane(table);
                    musteriListesiPanel.add(scrollPane, BorderLayout.CENTER);

                    add(musteriListesiPanel);
                    revalidate();
                    repaint();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        };

        worker.execute(); // SwingWorker'ı başlat
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MusteriListesi().setVisible(true);
            }
        });
    }
}