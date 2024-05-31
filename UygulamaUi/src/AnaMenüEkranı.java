import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
public class AnaMenüEkranı extends JFrame {
    private static final String MANAGER_USERNAME = "batuhan";
    private static final String MANAGER_PASSWORD = "1234";
    private BankaVeritabani bankaVeritabani;
   
    public AnaMenüEkranı() {    	
    	bankaVeritabani = new BankaVeritabani();  
        setTitle("Banka Uygulaması - Ana Menü");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);
        JPanel mainMenuPanel = new JPanel(new GridLayout(3, 1));
        JButton managerButton = new JButton("Banka Müdürü");
        JButton bankerButton = new JButton("Bankacı");
        JButton customerButton = new JButton("Müşteri");
        managerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLoginScreen();
            }
        });
        bankerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BankaciGirişEkranı BankaciGirişEkranı = new BankaciGirişEkranı();
                BankaciGirişEkranı.setVisible(true);
            }
        });
        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	
            	MusteriGiriş MusteriGiriş= new MusteriGiriş();
            	MusteriGiriş.setVisible(true);
              
            }
        });

        mainMenuPanel.add(managerButton);
        mainMenuPanel.add(bankerButton);
        mainMenuPanel.add(customerButton);

        add(mainMenuPanel);
    }

    private void openLoginScreen() {
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();

        Object[] fields = {"Kullanıcı Adı:", usernameField, "Şifre:", passwordField};

        int option = JOptionPane.showConfirmDialog(null, fields, "Giriş", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            char[] passwordChars = passwordField.getPassword();
            String password = new String(passwordChars);

            if (username != null && !username.isEmpty() && password != null && !password.isEmpty()
                    && username.equals(MANAGER_USERNAME) && password.equals(MANAGER_PASSWORD)) {
                MüdürEkranı managerScreen = new MüdürEkranı();
                managerScreen.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Geçersiz kullanıcı adı veya şifre!");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AnaMenüEkranı().setVisible(true);
            }
        });
    }
}
