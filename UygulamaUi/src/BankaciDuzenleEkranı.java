import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankaciDuzenleEkranı extends JFrame {

    private Bankaci bankaci;

    public BankaciDuzenleEkranı(Bankaci bankaci) {
        this.bankaci = bankaci;

        setTitle("Banka Uygulaması - Bankacıyı Düzenle");
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel bankaciDuzenlePanel = new JPanel(new GridLayout(5, 1));

        JTextField adField = new JTextField(bankaci.getAd());
        JTextField soyadField = new JTextField(bankaci.getSoyad());
        JTextField kullaniciAdiField = new JTextField(bankaci.getKullaniciAdi());
        JTextField sifreField = new JTextField(bankaci.getSifre());
        JButton duzenleButton = new JButton("Bankacıyı Düzenle");

        bankaciDuzenlePanel.add(new JLabel("Ad:"));
        bankaciDuzenlePanel.add(adField);
        bankaciDuzenlePanel.add(new JLabel("Soyad:"));
        bankaciDuzenlePanel.add(soyadField);
        bankaciDuzenlePanel.add(new JLabel("Kullanıcı Adı:"));
        bankaciDuzenlePanel.add(kullaniciAdiField);
        bankaciDuzenlePanel.add(new JLabel("Şifre:"));
        bankaciDuzenlePanel.add(sifreField);
        bankaciDuzenlePanel.add(duzenleButton);

        duzenleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBankaci(adField.getText(), soyadField.getText(), kullaniciAdiField.getText(), sifreField.getText());
                JOptionPane.showMessageDialog(BankaciDuzenleEkranı.this, "Bankacı Başarıyla Düzenlendi");
                dispose(); 
            }
        });

        add(bankaciDuzenlePanel);
    }

    private void updateBankaci(String ad, String soyad, String kullaniciAdi, String sifre) {
        bankaci.setAd(ad);
        bankaci.setSoyad(soyad);
        bankaci.setKullaniciAdi(kullaniciAdi);
        bankaci.setSifre(sifre);
        BankaVeritabani.updateBankaci(bankaci);
    }

}
