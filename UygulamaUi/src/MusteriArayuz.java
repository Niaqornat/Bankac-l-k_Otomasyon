import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusteriArayuz extends JFrame {
    private Musteri musteri;
    public MusteriArayuz(Musteri musteri) {
        this.musteri = musteri;
        setTitle("Müşteri Arayüzü");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel(new GridLayout(4, 1));
        JButton hesapBilgileriButton = new JButton("Hesap Bilgileri");
        JButton paraIslemleriButton = new JButton("Para İşlemleri");
        JButton krediBasvurusuButton = new JButton("Kredi Başvurusu");
        JButton cikisButton = new JButton("Çıkış");

        hesapBilgileriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openHesapBilgileriFrame();
            }
        });
        paraIslemleriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openParaIslemleriFrame();
            }
        });
        krediBasvurusuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openKrediBasvurusuFrame();
            }
        });
        cikisButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
            }
        });
        panel.add(hesapBilgileriButton);
        panel.add(paraIslemleriButton);
        panel.add(krediBasvurusuButton);
        panel.add(cikisButton);

        add(panel);
    }
    private void openHesapBilgileriFrame() {
        double guncelBakiye = BankaVeritabani.getMusteriBakiye(musteri.getId());
        JOptionPane.showMessageDialog(this, "Hesap Bilgileri\nBakiye: " + guncelBakiye);
    }
    private void openParaIslemleriFrame() {
        ParaIslemleriekranı paraIslemleri = new ParaIslemleriekranı(musteri);
        paraIslemleri.setVisible(true);
    }
    private void openKrediBasvurusuFrame() {
        KrediBasvurusuEkranı krediBasvurusu = new KrediBasvurusuEkranı(musteri);
        krediBasvurusu.setVisible(true);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                
            }
        });
    }
}
