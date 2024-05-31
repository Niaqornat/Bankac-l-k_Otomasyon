import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class YeniBankaciEkleEkranı extends JFrame {

    public YeniBankaciEkleEkranı() {
        setTitle("Banka Uygulaması - Yeni Bankacı Ekle");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(350, 250);
        setLocationRelativeTo(null);
        JPanel yeniBankaciEklePanel = new JPanel(new GridLayout(6, 2, 10, 10));
        yeniBankaciEklePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel lblId = new JLabel("ID:");
        JTextField txtId = new JTextField();
        txtId.setPreferredSize(new Dimension(200, 25));
        JLabel lblAd = new JLabel("Ad:");
        JTextField txtAd = new JTextField();
        txtAd.setPreferredSize(new Dimension(200, 25));
        JLabel lblSoyad = new JLabel("Soyad:");
        JTextField txtSoyad = new JTextField();
        txtSoyad.setPreferredSize(new Dimension(200, 25));
        JLabel lblKullaniciAdi = new JLabel("Kullanıcı Adı:");
        JTextField txtKullaniciAdi = new JTextField();
        txtKullaniciAdi.setPreferredSize(new Dimension(200, 25));
        JLabel lblSifre = new JLabel("Şifre:");
        JPasswordField txtSifre = new JPasswordField();
        txtSifre.setPreferredSize(new Dimension(200, 25));
        JButton ekleButton = new JButton("Ekle");
        ekleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(txtId.getText());
                    String ad = txtAd.getText();
                    String soyad = txtSoyad.getText();
                    String kullaniciAdi = txtKullaniciAdi.getText();
                    String sifre = new String(txtSifre.getPassword());

                    Bankaci yeniBankaci = new Bankaci(id, ad, soyad, kullaniciAdi, sifre);
                    
                    // Yeni kod: Bankacıyı veritabanına ekle
                    BankaVeritabani.bankaciEkle(yeniBankaci);
                    
                    JOptionPane.showMessageDialog(YeniBankaciEkleEkranı.this, "Yeni bankacı eklendi!");
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(YeniBankaciEkleEkranı.this, "Geçerli bir ID giriniz.");
                }
            }
        });
        yeniBankaciEklePanel.add(lblId);
        yeniBankaciEklePanel.add(txtId);
        yeniBankaciEklePanel.add(lblAd);
        yeniBankaciEklePanel.add(txtAd);
        yeniBankaciEklePanel.add(lblSoyad);
        yeniBankaciEklePanel.add(txtSoyad);
        yeniBankaciEklePanel.add(lblKullaniciAdi);
        yeniBankaciEklePanel.add(txtKullaniciAdi);
        yeniBankaciEklePanel.add(lblSifre);
        yeniBankaciEklePanel.add(txtSifre);
        yeniBankaciEklePanel.add(new JLabel());
        yeniBankaciEklePanel.add(ekleButton);

        add(yeniBankaciEklePanel);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new YeniBankaciEkleEkranı().setVisible(true);
            }
        });
    }
}
