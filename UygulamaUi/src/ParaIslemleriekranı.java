import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ParaIslemleriekranı extends JFrame {

    private Musteri musteri;

    public ParaIslemleriekranı(Musteri musteri) {
        this.musteri = musteri;

        setTitle("Para İşlemleri");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(3, 2));

        JLabel lblMiktar = new JLabel("Miktar:");
        JTextField txtMiktar = new JTextField();

        JButton paraYatirButton = new JButton("Para Yatır");
        JButton paraCekButton = new JButton("Para Çek");

        paraYatirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double miktar = Double.parseDouble(txtMiktar.getText());

                    if (BankaVeritabani.bakiyeyiArtir(musteri.getId(), miktar)) {
                        musteri.setBakiye(musteri.getBakiye() + miktar);
                        JOptionPane.showMessageDialog(ParaIslemleriekranı.this, "Para yatırma işlemi başarılı. Yeni bakiye: " + musteri.getBakiye());
                    } else {
                        JOptionPane.showMessageDialog(ParaIslemleriekranı.this, "Bakiye güncellenirken bir hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ParaIslemleriekranı.this, "Geçerli bir miktar giriniz.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        paraCekButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double miktar = Double.parseDouble(txtMiktar.getText());

                    if (BankaVeritabani.bakiyeyiAzalt(musteri.getId(), miktar)) {
                        musteri.setBakiye(musteri.getBakiye() - miktar);
                        JOptionPane.showMessageDialog(ParaIslemleriekranı.this, "Para çekme işlemi başarılı. Yeni bakiye: " + musteri.getBakiye());
                    } else {
                        JOptionPane.showMessageDialog(ParaIslemleriekranı.this, "Yetersiz bakiye veya bakiye güncellenirken bir hata oluştu.", "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(ParaIslemleriekranı.this, "Geçerli bir miktar giriniz.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        panel.add(lblMiktar);
        panel.add(txtMiktar);
        panel.add(paraYatirButton);
        panel.add(paraCekButton);
        panel.add(new JPanel());

        add(panel);
    }
}
