import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankaciEkranı extends JFrame {
	private BankaVeritabani bankaVeritabani;
    public BankaciEkranı() {
    	bankaVeritabani = new BankaVeritabani();
        setTitle("Banka Uygulaması - Bankacı Ekranı");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        JPanel bankaciScreenPanel = new JPanel(new GridLayout(6, 1));

        JButton yeniHesapButton = new JButton("Yeni Müşteriye Hesap Aç");
        JButton hesapIslemButton = new JButton("Hesap İşlemleri");
        JButton musteriduzenleButton = new JButton("Müşteri Düzenle");
        JButton musteriSilButton = new JButton("Müşteri Sil");
        JButton musteriListesiButton = new JButton("Müşteri Listesi");
        JButton cikisButton = new JButton("Çıkış");

        yeniHesapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openYeniHesap();
            }
        });

        hesapIslemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openHesapIslemleri();
            }
        });

        musteriduzenleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMusteriDuzenleFrame();
            }
        });

        musteriSilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	openMusteriSil();
            }
        });

        musteriListesiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openMusteriListesiFrame();
            }
        });

        cikisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AnaMenüEkranı AnaMenüEkranı = new AnaMenüEkranı();
                AnaMenüEkranı.setVisible(true);
                dispose(); 
            }
        });

        bankaciScreenPanel.add(yeniHesapButton);
        bankaciScreenPanel.add(hesapIslemButton);
        bankaciScreenPanel.add(musteriduzenleButton);
        bankaciScreenPanel.add(musteriSilButton);
        bankaciScreenPanel.add(musteriListesiButton);
        bankaciScreenPanel.add(cikisButton);

        add(bankaciScreenPanel);
    }

    private void openYeniHesap() {
        YeniHesap yeniHesap = new YeniHesap();
        yeniHesap.setVisible(true);
    }

    private void openHesapIslemleri() {
        HesapIslemleriEkranı hesapIslemleri= new HesapIslemleriEkranı();
        hesapIslemleri.setVisible(true);
    }

    private void openMusteriDuzenleFrame() {
        try {
            String selectedMusteriId = JOptionPane.showInputDialog(this, "Düzenlenecek Müşteri ID:");
            int musteriId = Integer.parseInt(selectedMusteriId);

            
            Musteri duzenlenecekMusteri = BankaciBilgileri.getMusteriById(musteriId);

            if (duzenlenecekMusteri != null) {
                MusteriDuzenle duzenleFrame = new MusteriDuzenle(duzenlenecekMusteri);
                duzenleFrame.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Müşteri Bulunamadı", "Hata", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Geçersiz Müşteri ID Girildi", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void openMusteriSil() {
    	MusteriSil musteriSil = new MusteriSil();
        musteriSil.setVisible(true);
    }

    private void openMusteriListesiFrame() {
        MusteriListesi musteriListesi = new MusteriListesi();
        musteriListesi.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BankaciEkranı().setVisible(true);
            }
        });
    }
}
