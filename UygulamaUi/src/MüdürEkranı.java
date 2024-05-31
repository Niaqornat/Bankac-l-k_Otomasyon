import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List; 
public class MüdürEkranı extends JFrame {

    private DefaultTableModel tableModel;
    private Musteri musteri;
    
    public MüdürEkranı() {
    	
    	
       
        setTitle("Banka Uygulaması - Banka Müdürü");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 300);
        setLocationRelativeTo(null);
        JPanel managerScreenPanel = new JPanel(new GridLayout(5, 1));
        JButton addBankerButton = new JButton("Yeni Bankacı Ekle");
        JButton editBankerButton = new JButton("Bankacıyı Düzenle");
        JButton deleteBankerButton = new JButton("Bankacıyı Sil");
        JButton approveCreditButton = new JButton("Kredi Başvurularını Onayla/Reddet");
        JButton listBankersButton = new JButton("Bankacıları Listele");
        JButton exitButton = new JButton("Çıkış");
        addBankerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openYeniBankaciEkle();
            }
        });
        editBankerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openBankaciDuzenle();
            }
        });
        deleteBankerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openBankaciSil();
            }
        });
        approveCreditButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Hiçbir kontrol yapmadan direkt olarak MusteriKredileriEkranı'nı açıyoruz.
                List<KrediBasvuru> krediBasvurulari = BankaVeritabani.getKrediBasvurular();
                MusteriKredileriEkranı musteriKredileri = new MusteriKredileriEkranı(krediBasvurulari);
                musteriKredileri.setVisible(true);
            }
        });


        listBankersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listBankacilar();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	AnaMenüEkranı MainMenuFrame = new AnaMenüEkranı();
            	MainMenuFrame.setVisible(true);
            	 dispose();                           
            }
        });

        managerScreenPanel.add(addBankerButton);
        managerScreenPanel.add(editBankerButton);
        managerScreenPanel.add(deleteBankerButton);
        managerScreenPanel.add(approveCreditButton);
        managerScreenPanel.add(listBankersButton);
        managerScreenPanel.add(exitButton);

        add(managerScreenPanel);
    }

    private void listBankacilar() {
        List<Bankaci> bankacilar = BankaVeritabani.getBankacilar();

        String[] columnNames = {"ID", "Ad", "Soyad", "Kullanıcı Adı", "Şifre"};
        Object[][] data = new Object[bankacilar.size()][5];

        for (int i = 0; i < bankacilar.size(); i++) {
            Bankaci bankaci = bankacilar.get(i);
            data[i][0] = bankaci.getId();
            data[i][1] = bankaci.getAd();
            data[i][2] = bankaci.getSoyad();
            data[i][3] = bankaci.getKullaniciAdi();
            data[i][4] = bankaci.getSifre();
        }

        tableModel = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel);

        JFrame tableFrame = new JFrame("Bankacı Listesi");
        tableFrame.setSize(500, 300);
        tableFrame.setLocationRelativeTo(null);

        JScrollPane scrollPane = new JScrollPane(table);
        tableFrame.add(scrollPane, BorderLayout.CENTER);

        tableFrame.setVisible(true);
    }  
    private void openMusteriKredileriFrame(List<KrediBasvuru> krediBasvurulari) {
        if (musteri != null) {
            
            Musteri musteriFromDB = BankaVeritabani.getMusteriById(musteri.getId());
            if (musteriFromDB != null) {             
                MusteriKredileriEkranı musteriKredileri = new MusteriKredileriEkranı(krediBasvurulari);
                musteriKredileri.setVisible(true);
            } 
        } else {
            System.out.println("Hata: Müşteri nesnesi null.");
        }
    }

    private void openYeniBankaciEkle() {
        YeniBankaciEkleEkranı yeniBankaciEkle = new YeniBankaciEkleEkranı();
        yeniBankaciEkle.setVisible(true);
    }

    private void openBankaciDuzenle() {
        try {
            String selectedBankaciId = JOptionPane.showInputDialog(this, "Düzenlenecek Bankacı ID:");
            int bankaciId = Integer.parseInt(selectedBankaciId);            
            ArrayList<Bankaci> bankacilar = BankaciBilgileri.getBankacilar();
            for (Bankaci bankaci : bankacilar) {
                if (bankaci.getId() == bankaciId) {
                    BankaciDuzenleEkranı duzenleFrame = new BankaciDuzenleEkranı(bankaci);
                    duzenleFrame.setVisible(true);
                    break;
                }
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Geçersiz Bankacı ID Girildi", "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void openBankaciSil() {
    	BankaciSilEkranı bankaciSilFrame = new BankaciSilEkranı();
        bankaciSilFrame.setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MüdürEkranı().setVisible(true);
            }
        });
    }
}
