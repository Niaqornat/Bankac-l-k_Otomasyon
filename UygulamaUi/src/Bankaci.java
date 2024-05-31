public class Bankaci {
    private int id;
    private String ad;
    private String soyad;
    private String kullaniciAdi;
    private String sifre;

    public Bankaci(int id, String ad, String soyad, String kullaniciAdi, String sifre) {
        this.id = id;
        this.ad = ad;
        this.soyad = soyad;
        this.kullaniciAdi = kullaniciAdi;
        this.sifre = sifre;
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
    public class Hesap {
        private double bakiye;

        public Hesap(double bakiye) {
            this.bakiye = bakiye;
        }
        public double getBakiye() {
            return bakiye;
        }
        public void setBakiye(double bakiye) {
            this.bakiye = bakiye;
        }
    }
}
