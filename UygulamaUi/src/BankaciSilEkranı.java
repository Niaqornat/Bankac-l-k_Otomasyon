import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BankaciSilEkranı extends JFrame {

    public BankaciSilEkranı() {
        setTitle("Banka Uygulaması - Bankacıyı Sil");
        setSize(300, 200);
        setLocationRelativeTo(null);

        JPanel bankaciSilPanel = new JPanel(new GridLayout(3, 1));

        JTextField bankaciIdField = new JTextField();
        JButton silButton = new JButton("Bankacıyı Sil");

        bankaciSilPanel.add(new JLabel("Bankacı ID:"));
        bankaciSilPanel.add(bankaciIdField);
        bankaciSilPanel.add(silButton);

        silButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int bankaciId = Integer.parseInt(bankaciIdField.getText());
                    
                    // Yeni kod: Bankacıyı veritabanından sil
                    BankaVeritabani.bankaciSil(bankaciId);
                    
                    JOptionPane.showMessageDialog(BankaciSilEkranı.this, "Bankacı Başarıyla Silindi");
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(BankaciSilEkranı.this, "Geçerli bir Bankacı ID giriniz.");
                }
            }
        });
        add(bankaciSilPanel);
    }

    private void deleteBankaci(int bankaciId) {
        BankaciBilgileri.deleteBankaci(bankaciId);
    }
}
