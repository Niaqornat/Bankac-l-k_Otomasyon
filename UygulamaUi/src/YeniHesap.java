import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class YeniHesap extends JFrame {

    public YeniHesap() {
        setTitle("Yeni Müşteriye Hesap Aç");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        JPanel yeniHesapPanel = new JPanel(new GridLayout(7, 2));
        JLabel lblIsim = new JLabel("İsim:");
        JTextField txtIsim = new JTextField();
        JLabel lblSoyisim = new JLabel("Soyisim:");
        JTextField txtSoyisim = new JTextField();
        JLabel lblKullaniciAdi = new JLabel("Kullanıcı Adı:");
        JTextField txtKullaniciAdi = new JTextField();
        JLabel lblSifre = new JLabel("Şifre:");
        JPasswordField txtSifre = new JPasswordField();
        JLabel lblTcNo = new JLabel("TC No:");
        JTextField txtTcNo = new JTextField();
        JLabel lblMusteriID = new JLabel("Müşteri ID:");
        JTextField txtMusteriID = new JTextField();
        JButton kaydetButton = new JButton("Kaydet");
        kaydetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int musteriID = Integer.parseInt(txtMusteriID.getText());
                    String isim = txtIsim.getText();
                    String soyisim = txtSoyisim.getText();
                    String kullaniciAdi = txtKullaniciAdi.getText();
                    String sifre = new String(txtSifre.getPassword());
                    String tcNo = txtTcNo.getText();
                    
                    // Yeni Müşteri nesnesi oluşturuldu
                    Musteri yeniMusteri = new Musteri(musteriID, isim, soyisim, kullaniciAdi, sifre, tcNo);
                    
                    // BankaVeritabani sınıfındaki metodu kullanarak müşteriyi veritabanına ekleyin
                    BankaVeritabani.addMusteri(yeniMusteri);
                    
                    JOptionPane.showMessageDialog(YeniHesap.this, "Yeni müşteri eklendi!");
                    
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(YeniHesap.this, "Geçerli bir Müşteri ID giriniz.");
                }
            }
        });

        yeniHesapPanel.add(lblIsim);
        yeniHesapPanel.add(txtIsim);
        yeniHesapPanel.add(lblSoyisim);
        yeniHesapPanel.add(txtSoyisim);
        yeniHesapPanel.add(lblKullaniciAdi);
        yeniHesapPanel.add(txtKullaniciAdi);
        yeniHesapPanel.add(lblSifre);
        yeniHesapPanel.add(txtSifre);
        yeniHesapPanel.add(lblTcNo);
        yeniHesapPanel.add(txtTcNo);
        yeniHesapPanel.add(lblMusteriID);
        yeniHesapPanel.add(txtMusteriID);
        yeniHesapPanel.add(new JPanel()); 
        yeniHesapPanel.add(kaydetButton);
        add(yeniHesapPanel);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new YeniHesap().setVisible(true);
            }
        });
    }
}
