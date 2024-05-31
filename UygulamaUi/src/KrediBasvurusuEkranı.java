import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class KrediBasvurusuEkranı extends JFrame {

    private Musteri musteri;

    public KrediBasvurusuEkranı(Musteri musteri) {
        this.musteri = musteri;

        setTitle("Kredi Başvurusu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel lblMiktar = new JLabel("Kredi Miktarı:");
        JTextField txtMiktar = new JTextField();
        JButton basvurButton = new JButton("Başvur");
        basvurButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double krediMiktar = Double.parseDouble(txtMiktar.getText());
                KrediBasvuru krediBasvuru = new KrediBasvuru(musteri, krediMiktar);
                ManagerData.krediBasvuruEkle(krediBasvuru);
                JOptionPane.showMessageDialog(KrediBasvurusuEkranı.this, "Kredi başvurusu yapıldı. Başvuru sonuçlarını bekleyiniz.");

               
                
             

                dispose();
            }
        });

        panel.add(lblMiktar);
        panel.add(txtMiktar);
        panel.add(basvurButton);
        panel.add(new JPanel());

        add(panel);
    }
}
