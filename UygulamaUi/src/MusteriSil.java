import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusteriSil extends JFrame {

    public MusteriSil() {
        setTitle("Müşteri Sil");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel(new GridLayout(3, 2));
        JLabel label = new JLabel("Müşteri ID:");
        JTextField txtMusteriID = new JTextField();
        JButton silButton = new JButton("Sil");

        silButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int musteriID = Integer.parseInt(txtMusteriID.getText());                                     
                    boolean silindi = BankaciBilgileri.musterisil(musteriID);
                    if (silindi) {
                        JOptionPane.showMessageDialog(MusteriSil.this, "Müşteri silindi.");
                    } else {
                        JOptionPane.showMessageDialog(MusteriSil.this, "Müşteri bulunamadı.");
                    }                
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MusteriSil.this, "Geçerli bir Müşteri ID giriniz.");
                }
            }
        });
        panel.add(label);
        panel.add(txtMusteriID);
        panel.add(new JPanel()); 
        panel.add(silButton);
        add(panel);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MusteriSil().setVisible(true);
            }
        });
    }
}
