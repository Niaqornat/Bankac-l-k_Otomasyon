import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusteriDuzenle extends JFrame {
	 private BankaVeritabani bankaVeritabani;
    private JTextField txtMusteriID;
    private JTextField txtIsim;
    private JTextField txtSoyisim;
    private JTextField txtKullaniciAdi;
    private JTextField txtSifre;
    private JTextField txtTcNo;

    private Musteri musteri;

    public MusteriDuzenle(Musteri musteri) {
    	bankaVeritabani = new BankaVeritabani();
        this.musteri = musteri;
        initUI();
        setMusteriData();
    }

    public MusteriDuzenle() {
        initUI();
    }

    private void initUI() {
        setTitle("Müşteri Düzenle");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(7, 2));

        JLabel lblMusteriID = new JLabel("Müşteri ID:");
        txtMusteriID = new JTextField();
        JLabel lblIsim = new JLabel("İsim:");
        txtIsim = new JTextField();
        JLabel lblSoyisim = new JLabel("Soyisim:");
        txtSoyisim = new JTextField();
        JLabel lblKullaniciAdi = new JLabel("Kullanıcı Adı:");
        txtKullaniciAdi = new JTextField();
        JLabel lblSifre = new JLabel("Şifre:");
        txtSifre = new JTextField();
        JLabel lblTcNo = new JLabel("TC No:");
        txtTcNo = new JTextField();
        JButton duzenleButton = new JButton("Düzenle");

        duzenleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int musteriID = Integer.parseInt(txtMusteriID.getText());
                    String isim = txtIsim.getText();
                    String soyisim = txtSoyisim.getText();
                    String kullaniciAdi = txtKullaniciAdi.getText();
                    String sifre = txtSifre.getText();
                    String tcNo = txtTcNo.getText();
                    
                    Musteri updatedMusteri = new Musteri(musteriID, isim, soyisim, kullaniciAdi, sifre, tcNo);
                    boolean duzenlendi = BankaVeritabani.updateMusteri(updatedMusteri);

                    if (duzenlendi) {
                        JOptionPane.showMessageDialog(MusteriDuzenle.this, "Müşteri bilgileri güncellendi.");
                    } else {
                        JOptionPane.showMessageDialog(MusteriDuzenle.this, "Müşteri bulunamadı.");
                    }

                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MusteriDuzenle.this, "Geçerli bir Müşteri ID giriniz.");
                }
            }
        });



        panel.add(lblMusteriID);
        panel.add(txtMusteriID);
        panel.add(lblIsim);
        panel.add(txtIsim);
        panel.add(lblSoyisim);
        panel.add(txtSoyisim);
        panel.add(lblKullaniciAdi);
        panel.add(txtKullaniciAdi);
        panel.add(lblSifre);
        panel.add(txtSifre);
        panel.add(lblTcNo);
        panel.add(txtTcNo);
        panel.add(new JPanel()); 
        panel.add(duzenleButton);

        add(panel);
    }

    private void setMusteriData() {
        if (musteri != null) {
            txtMusteriID.setText(String.valueOf(musteri.getId()));
            txtIsim.setText(musteri.getAd());
            txtSoyisim.setText(musteri.getSoyad());
            txtKullaniciAdi.setText(musteri.getKullaniciAdi());
            txtSifre.setText(musteri.getSifre());
            txtTcNo.setText(musteri.getTcNo());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MusteriDuzenle().setVisible(true);
            }
        });
    }
}
