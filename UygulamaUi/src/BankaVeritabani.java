import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankaVeritabani {
	
	private static final String URL = "jdbc:sqlite:E:\\BankaVt\\denemevt.db";
	
	

	
	public static Musteri getMusteriById(int musteriId) {
	    try (Connection connection = baglan()) {
	        String sorgu = "SELECT * FROM musteriler WHERE id = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(sorgu)) {
	            preparedStatement.setInt(1, musteriId);

	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    int id = resultSet.getInt("id");
	                    String ad = resultSet.getString("ad");
	                    String soyad = resultSet.getString("soyad");
	                    String kullaniciAdi = resultSet.getString("kullaniciAdi");
	                    String sifre = resultSet.getString("sifre");
	                    String tcNo = resultSet.getString("tcNo");

	                    return new Musteri(id, ad, soyad, kullaniciAdi, sifre, tcNo);
	                } else {
	                    System.err.println("Müşteri bulunamadı.");
	                }
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.err.println("Veritabanı hatası: " + e.getMessage());
	    }

	    return null; 
	}
	
	public static List<KrediBasvuru> getKrediBasvurular() {
	    List<KrediBasvuru> krediBasvurular = new ArrayList<>();
	    
	    try (Connection connection = baglan()) {
	        if (connection != null) {
	            String sql = "SELECT * FROM kredi_basvurulari";
	            
	            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
	                 ResultSet resultSet = preparedStatement.executeQuery()) {

	                while (resultSet.next()) {
	                    int krediBasvuruId = resultSet.getInt("id");
	                    int musteriId = resultSet.getInt("musteri_id");
	                    double miktar = resultSet.getDouble("miktar");
	                    boolean onayDurumu = resultSet.getBoolean("onay_durumu");

	                    Musteri musteri = getMusteriById(musteriId); 
	                    KrediBasvuru krediBasvuru = new KrediBasvuru(musteri, miktar);
	                    krediBasvuru.setOnayDurumu(onayDurumu);
	                    krediBasvurular.add(krediBasvuru);
	                }
	            }
	        } else {
	            System.err.println("Veritabanı bağlantısı oluşturulamadı!");
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return krediBasvurular;
	}

	
	
	public static void krediBasvurulariTablosunuOlustur() {
	    Connection connection = baglan();
	    
	    

	    if (connection != null) {
	        try {
	            String createTableSQL = "CREATE TABLE IF NOT EXISTS kredi_basvurulari ("
	                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
	                    + "musteri_id INTEGER,"
	                    + "miktar REAL,"
	                    + "onay_durumu BOOLEAN,"
	                    + "FOREIGN KEY (musteri_id) REFERENCES musteriler(id))";

	            try (PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL)) {
	                preparedStatement.executeUpdate();
	                System.out.println("Kredi Basvurulari tablosu oluşturuldu!");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.err.println("Kredi Basvurulari tablosu oluşturma hatası: " + e.getMessage());
	        } finally {
	            kapat(connection);
	        }
	    } else {
	        System.err.println("Bağlantı oluşturulamadı!");
	    }
	}

	
	
	public class KrediBasvurusuEkraniniBaslat {

	    public static void main(String[] args) {
	        
	        BankaVeritabani bankaVeritabani = new BankaVeritabani();

	      
	        Musteri musteri = new Musteri(1, "Ahmet", "Demir", "ahmet123", "sifre123", "123456789");

	        
	        KrediBasvurusuEkranı krediBasvurusuEkranı = new KrediBasvurusuEkranı(musteri);
	        krediBasvurusuEkranı.setVisible(true);
	    }
	}
	
	 public static double getMusteriBakiye(int musteriID) {
	        try (Connection connection = baglan()) {
	            String sorgu = "SELECT bakiye FROM musteriler WHERE id = ?";
	            try (PreparedStatement preparedStatement = connection.prepareStatement(sorgu)) {
	                preparedStatement.setInt(1, musteriID);

	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    if (resultSet.next()) {
	                        return resultSet.getDouble("bakiye");
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return 0.0; 
	    }
	
	
	public static boolean bakiyeyiArtir(int musteriID, double miktar) {
        try (Connection connection = baglan()) {
            String sorgu = "UPDATE musteriler SET bakiye = bakiye + ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sorgu)) {
                preparedStatement.setDouble(1, miktar);
                preparedStatement.setInt(2, musteriID);

                int etkilenenSatir = preparedStatement.executeUpdate();

                return etkilenenSatir > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	public static boolean bakiyeyiAzalt(int musteriID, double miktar) {
        try (Connection connection = baglan()) {
            String sorgu = "UPDATE musteriler SET bakiye = bakiye - ? WHERE id = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sorgu)) {
                preparedStatement.setDouble(1, miktar);
                preparedStatement.setInt(2, musteriID);

                int etkilenenSatir = preparedStatement.executeUpdate();

                return etkilenenSatir > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	public static boolean updateMusteri(Musteri musteri) {
	    Connection connection = baglan();
	    boolean result = false;

	    if (connection != null) {
	        try {
	        	String sql = "UPDATE musteriler SET ad=?, soyad=?, kullaniciAdi=?, sifre=?, tcNo=? WHERE id=?";

	            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	                preparedStatement.setString(1, musteri.getAd());
	                preparedStatement.setString(2, musteri.getSoyad());
	                preparedStatement.setString(3, musteri.getKullaniciAdi());
	                preparedStatement.setString(4, musteri.getSifre());
	                preparedStatement.setString(5, musteri.getTcNo());
	                preparedStatement.setInt(6, musteri.getId());

	                int rowsAffected = preparedStatement.executeUpdate();
	                result = (rowsAffected > 0);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            kapat(connection);
	        }
	    }

	    return result;
	}


	public static List<Musteri> getMusteriler() {
	    List<Musteri> musteriler = new ArrayList<>();
	    Connection connection = baglan();

	    if (connection != null) {
	        try {
	            String sql = "SELECT * FROM musteriler";
	            try (PreparedStatement preparedStatement = connection.prepareStatement(sql);
	                 ResultSet resultSet = preparedStatement.executeQuery()) {

	                while (resultSet.next()) {
	                    int id = resultSet.getInt("id");
	                    String ad = resultSet.getString("ad");
	                    String soyad = resultSet.getString("soyad");
	                    String kullaniciAdi = resultSet.getString("kullaniciAdi");
	                    String sifre = resultSet.getString("sifre");
	                    String tcNo = resultSet.getString("tcNo");

	                    Musteri musteri = new Musteri(id, ad, soyad, kullaniciAdi, sifre, tcNo);
	                    musteriler.add(musteri);
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            kapat(connection);
	        }
	    }

	    return musteriler;
	}

	
	public static boolean musterisil(int musteriId) {
        Connection connection = baglan();

        if (connection != null) {
            try {
                String sql = "DELETE FROM musteriler WHERE id = ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, musteriId);

                    int affectedRows = preparedStatement.executeUpdate();
                    return affectedRows > 0;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                kapat(connection);
            }
        }

        return false;
    }
	
	public static void updateMusteriBakiye(int musteriID, double yeniBakiye) {
	    try (Connection connection = baglan()) {
	        String sorgu = "UPDATE musteriler SET bakiye = ? WHERE id = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(sorgu)) {
	            preparedStatement.setDouble(1, yeniBakiye);
	            preparedStatement.setInt(2, musteriID);

	            int etkilenenSatir = preparedStatement.executeUpdate();

	            if (etkilenenSatir <= 0) {
	                System.out.println("Bakiye güncellenemedi.");
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	public static void addMusteri(Musteri musteri) {
        Connection connection = baglan();
        if (connection != null) {
            try {
                String sql = "INSERT INTO musteriler (id, ad, soyad, kullaniciAdi, sifre, tcNo, bakiye) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setInt(1, musteri.getId());
                    preparedStatement.setString(2, musteri.getAd());
                    preparedStatement.setString(3, musteri.getSoyad());
                    preparedStatement.setString(4, musteri.getKullaniciAdi());
                    preparedStatement.setString(5, musteri.getSifre());
                    preparedStatement.setString(6, musteri.getTcNo());
                    preparedStatement.setDouble(7, musteri.getBakiye());
                    preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                kapat(connection);
            }
        }
    }
	
	public static void musteriTablosunuOlustur() {
        Connection connection = baglan();

        if (connection != null) {
            try {
                String createTableSQL = "CREATE TABLE IF NOT EXISTS musteriler ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + "ad TEXT NOT NULL,"
                        + "soyad TEXT NOT NULL,"
                        + "kullaniciAdi TEXT NOT NULL,"
                        + "sifre TEXT NOT NULL,"
                        + "tcNo TEXT NOT NULL,"
                        + "bakiye REAL NOT NULL)";

                try (PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL)) {
                    preparedStatement.executeUpdate();
                    System.out.println("Müşteri tablosu oluşturuldu!");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Müşteri tablosu oluşturma hatası: " + e.getMessage());
            } finally {
                kapat(connection);
            }
        } else {
            System.err.println("Bağlantı oluşturulamadı!");
        }
    }
	
	
	public static boolean bankacıgirisKontrol(String kullaniciAdi, String sifre) {
        String sql = "SELECT * FROM bankacilar WHERE kullaniciAdi = ? AND sifre = ?";
        
        try (Connection connection = baglan();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, kullaniciAdi);
            preparedStatement.setString(2, sifre);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	public static void bankaciSil(int bankaciId) {
        String sql = "DELETE FROM bankacilar WHERE id = ?";

        try (Connection connection = baglan();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, bankaciId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
	
	public static void bankaciEkle(Bankaci bankaci) {
        String sql = "INSERT INTO bankacilar (id, ad, soyad, kullaniciAdi, sifre) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = baglan();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, bankaci.getId());
            preparedStatement.setString(2, bankaci.getAd());
            preparedStatement.setString(3, bankaci.getSoyad());
            preparedStatement.setString(4, bankaci.getKullaniciAdi());
            preparedStatement.setString(5, bankaci.getSifre());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

	
	 public static void updateBankaci(Bankaci bankaci) {
	        try (Connection connection = baglan()) {
	            if (connection != null) {
	                String sql = "UPDATE bankacilar SET ad=?, soyad=?, kullaniciAdi=?, sifre=? WHERE id=?";
	                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	                    preparedStatement.setString(1, bankaci.getAd());
	                    preparedStatement.setString(2, bankaci.getSoyad());
	                    preparedStatement.setString(3, bankaci.getKullaniciAdi());
	                    preparedStatement.setString(4, bankaci.getSifre());
	                    preparedStatement.setInt(5, bankaci.getId());

	                    preparedStatement.executeUpdate();
	                }
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	
	

	 public static List<Bankaci> getBankacilar() {
	        List<Bankaci> bankacilar = new ArrayList<>();
	        String sql = "SELECT * FROM bankacilar";

	        try (Connection connection = baglan();
	             PreparedStatement preparedStatement = connection.prepareStatement(sql);
	             ResultSet resultSet = preparedStatement.executeQuery()) {

	            while (resultSet.next()) {
	                int id = resultSet.getInt("id");
	                String ad = resultSet.getString("ad");
	                String soyad = resultSet.getString("soyad");
	                String kullaniciAdi = resultSet.getString("kullaniciAdi");
	                String sifre = resultSet.getString("sifre");

	                Bankaci bankaci = new Bankaci(id, ad, soyad, kullaniciAdi, sifre);
	                bankacilar.add(bankaci);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return bankacilar;
	    }
	

	
   
	public static void tablolariOlustur() {	
	    Connection connection = baglan();
	    
	    
	    
	    if (connection != null) {
	        try {
	            String createTableSQL = "CREATE TABLE IF NOT EXISTS bankacilar ("
	                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
	                    + "ad TEXT NOT NULL,"
	                    + "soyad TEXT NOT NULL,"
	                    + "kullaniciAdi TEXT NOT NULL,"
	                    + "sifre TEXT NOT NULL)";
	            
	            try (PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL)) {
	                preparedStatement.executeUpdate();
	                System.out.println("Tablo oluşturuldu!");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	            System.err.println("Tablo oluşturma hatası: " + e.getMessage());
	        } finally {
	            kapat(connection);
	        }
	    } else {
	        System.err.println("Bağlantı oluşturulamadı!");
	    }
	}


    public static Connection baglan() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(URL);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void kapat(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void bankacilariVeritabaninaEkle(List<Bankaci> bankacilar) {
        Connection connection = baglan();
        if (connection != null) {
            try {
                for (Bankaci bankaci : bankacilar) {
                    String sql = "INSERT INTO bankacilar (id, ad, soyad, kullaniciAdi, sifre) VALUES (?, ?, ?, ?, ?)";
                    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                        preparedStatement.setInt(1, bankaci.getId());
                        preparedStatement.setString(2, bankaci.getAd());
                        preparedStatement.setString(3, bankaci.getSoyad());
                        preparedStatement.setString(4, bankaci.getKullaniciAdi());
                        preparedStatement.setString(5, bankaci.getSifre());
                        preparedStatement.executeUpdate();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                kapat(connection);
            }
        }
    }

    
}
