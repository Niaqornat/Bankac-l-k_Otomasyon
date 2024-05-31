import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

public class BankaciBilgileri {
	
	
	
    private static ArrayList<Bankaci> bankacilar = new ArrayList<>();
    private static ArrayList<Musteri> musteriler = new ArrayList<>();
    static {    
           
        bankacilar.addAll(BankaVeritabani.getBankacilar());
      
        musteriler.addAll(BankaVeritabani.getMusteriler());
        
    }

    public static ArrayList<Bankaci> getBankacilar() {
        return bankacilar;
    }

    public static void addBankaci(Bankaci bankaci) {
        bankacilar.add(bankaci);
    }

    public static void updateBankaci(Bankaci updatedBankaci) {
        for (Bankaci bankaci : bankacilar) {
            if (bankaci.getId() == updatedBankaci.getId()) {
                bankaci.setAd(updatedBankaci.getAd());
                bankaci.setSoyad(updatedBankaci.getSoyad());
                bankaci.setKullaniciAdi(updatedBankaci.getKullaniciAdi());
                bankaci.setSifre(updatedBankaci.getSifre());
                break;
            }
        }
    }

    public static void deleteBankaci(int bankaciId) {
        Iterator<Bankaci> iterator = bankacilar.iterator();
        while (iterator.hasNext()) {
            Bankaci bankaci = iterator.next();
            if (bankaci.getId() == bankaciId) {
                iterator.remove();
                break;
            }
        }
    }

    public static ArrayList<Musteri> getMusteriler() {
        return musteriler;
    }

    public static void addMusteri(Musteri musteri) {
        musteriler.add(musteri);
    }

   
    public static Musteri getMusteriById(int id) {
    	String sql = "SELECT * FROM musteriler WHERE id = ?";
    	try (Connection connection = BankaVeritabani.baglan();
    	     PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

    	    preparedStatement.setInt(1, id);

    	    try (ResultSet resultSet = preparedStatement.executeQuery()) {
    	        if (resultSet.next()) {
    	            int musteriID = resultSet.getInt("id");
    	            String ad = resultSet.getString("ad");
    	            String soyad = resultSet.getString("soyad");
    	            String kullaniciAdi = resultSet.getString("kullaniciAdi"); // Değişiklik burada
    	            String sifre = resultSet.getString("sifre");
    	            String tcNo = resultSet.getString("tcNo");

    	            return new Musteri(musteriID, ad, soyad, kullaniciAdi, sifre, tcNo);
    	        }
    	    }
    	} catch (SQLException e) {
    	    e.printStackTrace();
    	}

        

        return null;
    }

    public static void deleteMusteri(int musteriId) {
        Iterator<Musteri> iterator = musteriler.iterator();
        while (iterator.hasNext()) {
            Musteri musteri = iterator.next();
            if (musteri.getId() == musteriId) {
                iterator.remove();
                break;
            }
        }
    }
    public static boolean musterisil(int musteriId) {
        Musteri silinecekMusteri = null;
        for (Musteri musteri : musteriler) {
            if (musteri.getId() == musteriId) {
                silinecekMusteri = musteri;
                break;
            }
        }

        if (silinecekMusteri != null) {
            musteriler.remove(silinecekMusteri);
            return BankaVeritabani.musterisil(musteriId);
        }

        return false;
    }

    public static Musteri musteriGiris(String kullaniciAdi, String sifre) {
        for (Musteri musteri : musteriler) {
            if (musteri.getKullaniciAdi().equals(kullaniciAdi) && musteri.getSifre().equals(sifre)) {
                return musteri;
            }
        }
        return null; 
    }
    public static boolean paraYatir(int musteriID, double miktar) {
        try (Connection connection = BankaVeritabani.baglan()) {
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

    public static boolean paraCek(int musteriID, double miktar) {
        try (Connection connection = BankaVeritabani.baglan()) {
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

}