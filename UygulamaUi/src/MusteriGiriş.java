import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusteriGiriş extends JFrame {

    private Musteri girisYapanMusteri; 
    private BankaVeritabani bankaVeritabani;
    public MusteriGiriş() {
    	bankaVeritabani = new BankaVeritabani(); 
        setTitle("Müşteri Girişi");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel lblKullaniciAdi = new JLabel("Kullanıcı Adı:");
        JTextField txtKullaniciAdi = new JTextField();
        JLabel lblSifre = new JLabel("Şifre:");
        JPasswordField txtSifre = new JPasswordField();
        JButton girisButton = new JButton("Giriş");
        girisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kullaniciAdi = txtKullaniciAdi.getText();
                String sifre = new String(txtSifre.getPassword());              
                girisYapanMusteri = BankaciBilgileri.musteriGiris(kullaniciAdi, sifre);
                if (girisYapanMusteri != null) {
                    JOptionPane.showMessageDialog(MusteriGiriş.this, "Giriş başarılı. Hoş geldiniz, " + girisYapanMusteri.getAd() + " " + girisYapanMusteri.getSoyad() + "!");                  
                    openMusteriArayuz();
                    dispose(); 
                } else {
                    JOptionPane.showMessageDialog(MusteriGiriş.this, "Kullanıcı adı veya şifre hatalı!", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(lblKullaniciAdi);
        panel.add(txtKullaniciAdi);
        panel.add(lblSifre);
        panel.add(txtSifre);
        panel.add(new JPanel()); 
        panel.add(girisButton);
        add(panel);
    }   
    private void openMusteriArayuz() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MusteriArayuz(girisYapanMusteri).setVisible(true);
            }
        });
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MusteriGiriş().setVisible(true);
            }
        });
    }
}
