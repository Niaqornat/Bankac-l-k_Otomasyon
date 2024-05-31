import java.util.ArrayList;
import java.util.List;

public class Musteri {
    private int id;
    private String ad;
    private String soyad;
    private String kullaniciAdi;
    private String sifre;
    private String tcNo;
    private double bakiye;
    private List<KrediBasvuru> krediBasvurulari;

    public Musteri(int id, String ad, String soyad, String kullaniciAdi, String sifre, String tcNo) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
        this.tcNo = tcNo;
        this.bakiye = 0.0;
        this.krediBasvurulari = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getKullaniciAdi() {
        return kullaniciAdi;
    }

    public void setKullaniciAdi(String kullaniciAdi) {
        this.kullaniciAdi = kullaniciAdi;
    }

    public String getSifre() {
        return sifre;
    }

    public void setSifre(String sifre) {
        this.sifre = sifre;
    }

    public String getTcNo() {
        return tcNo;
    }

    public void setTcNo(String tcNo) {
        this.tcNo = tcNo;
    }

    public double getBakiye() {
        return bakiye;
    }

    public void setBakiye(double bakiye) {
        this.bakiye = bakiye;
    }

    
    

    public void setKrediBasvurulari(List<KrediBasvuru> krediBasvurulari) {
        this.krediBasvurulari = krediBasvurulari;
    }
    
    public void krediBasvuruEkle(KrediBasvuru krediBasvuru) {
        this.krediBasvurulari.add(krediBasvuru);
    }
    
}
