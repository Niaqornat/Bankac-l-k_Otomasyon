import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerData {
    private static List<KrediBasvuru> krediBasvurulari = new ArrayList<>();

    public static void krediBasvuruEkle(KrediBasvuru krediBasvuru) {
        
        if (krediBasvuru.getMusteri() != null) {
            krediBasvurulari.add(krediBasvuru);

            
            try (Connection connection = BankaVeritabani.baglan()) {
                if (connection != null) {
                    String sql = "INSERT INTO kredi_basvurulari (musteri_id, miktar, onay_durumu) VALUES (?, ?, ?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                        preparedStatement.setInt(1, krediBasvuru.getMusteri().getId());
                        preparedStatement.setDouble(2, krediBasvuru.getMiktar());
                        preparedStatement.setBoolean(3, krediBasvuru.isOnayDurumu());

                        preparedStatement.executeUpdate();
                    }
                } else {
                    System.err.println("Veritabanı bağlantısı oluşturulamadı!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.err.println("Hata: Müşteri null. Kredi başvurularını alamıyor.");
           
        }
    }


   

    
}
