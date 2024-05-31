import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HesapIslemleriEkranı extends JFrame {

    private JTextField txtMusteriID;
    private JTextField txtMiktar;
    private JRadioButton rbtnYatirma;
    private JRadioButton rbtnCekme;
    private JButton btnIslem;
    public HesapIslemleriEkranı() {
        setTitle("Hesap İşlemleri");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        JPanel panel = new JPanel(new GridLayout(5, 2));
        JLabel lblMusteriID = new JLabel("Müşteri ID:");
        txtMusteriID = new JTextField();
        JLabel lblMiktar = new JLabel("Miktar:");
        txtMiktar = new JTextField();
        JLabel lblIslemTuru = new JLabel("İşlem Türü:");
        rbtnYatirma = new JRadioButton("Para Yatırma");
        rbtnCekme = new JRadioButton("Para Çekme");
        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(rbtnYatirma);
        btnGroup.add(rbtnCekme);
        btnIslem = new JButton("İşlemi Gerçekleştir");

        btnIslem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int musteriID = Integer.parseInt(txtMusteriID.getText());
                    double miktar = Double.parseDouble(txtMiktar.getText());

                    if (rbtnYatirma.isSelected()) {
                        
                        BankaciBilgileri.paraYatir(musteriID, miktar);
                        JOptionPane.showMessageDialog(HesapIslemleriEkranı.this, "Para yatırma işlemi başarılı.");
                    } else if (rbtnCekme.isSelected()) {
                      
                    	BankaciBilgileri.paraCek(musteriID, miktar);
                        JOptionPane.showMessageDialog(HesapIslemleriEkranı.this, "Para çekme işlemi başarılı.");
                    } else {
                        JOptionPane.showMessageDialog(HesapIslemleriEkranı.this, "İşlem türü seçilmedi.", "Hata", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(HesapIslemleriEkranı.this, "Geçerli bir Müşteri ID ve miktar giriniz.", "Hata", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        panel.add(lblMusteriID);
        panel.add(txtMusteriID);
        panel.add(lblMiktar);
        panel.add(txtMiktar);
        panel.add(lblIslemTuru);
        panel.add(rbtnYatirma);
        panel.add(new JPanel()); 
        panel.add(rbtnCekme);
        panel.add(new JPanel()); 
        panel.add(btnIslem);
        add(panel);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HesapIslemleriEkranı().setVisible(true);
            }
        });
    }
}
