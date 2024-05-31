import javax.swing.*;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class BankaciGirişEkranı extends JFrame {
	 private BankaVeritabani bankaVeritabani;
    public BankaciGirişEkranı() {
    	bankaVeritabani = new BankaVeritabani();
        setTitle("Banka Uygulaması - Bankacı Girişi");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(300, 150);
        setLocationRelativeTo(null);
        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        JLabel lblUsername = new JLabel("Kullanıcı Adı:");
        JTextField txtUsername = new JTextField();
        JLabel lblPassword = new JLabel("Şifre:");
        JPasswordField txtPassword = new JPasswordField();
        JButton loginButton = new JButton("Giriş");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = new String(txtPassword.getPassword());  

                // Yeni kod: Giriş bilgilerini kontrol et
                if (BankaVeritabani.bankacıgirisKontrol(username, password)) {
                    openBankaciScreen();
                } else {
                    JOptionPane.showMessageDialog(BankaciGirişEkranı.this, "Geçersiz kullanıcı adı veya şifre!");
                }
            }
        });
        loginPanel.add(lblUsername);
        loginPanel.add(txtUsername);
        loginPanel.add(lblPassword);
        loginPanel.add(txtPassword);
        loginPanel.add(new JLabel()); 
        loginPanel.add(loginButton);
        add(loginPanel);
    }

    private void openBankaciScreen() {
    	BankaciEkranı bankaciScreen = new BankaciEkranı();
        bankaciScreen.setVisible(true);
        this.dispose(); 
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BankaciGirişEkranı().setVisible(true);
            }
        });
    }
}
