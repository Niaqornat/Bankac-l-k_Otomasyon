public class KrediBasvuru {
    private Musteri musteri;
    private double miktar;
    private boolean onayDurumu;

    public KrediBasvuru(Musteri musteri, double miktar) {
        this.musteri = musteri;
        this.miktar = miktar;
        this.onayDurumu = false;
    }

    public Musteri getMusteri() {
        return musteri;
    }

    public void setMusteri(Musteri musteri) {
        this.musteri = musteri;
    }

    public double getMiktar() {
        return miktar;
    }

    public void setMiktar(double miktar) {
        this.miktar = miktar;
    }

    public boolean isOnayDurumu() {
        return onayDurumu;
    }

    public void setOnayDurumu(boolean onayDurumu) {
        this.onayDurumu = onayDurumu;
        if (onayDurumu) {
            
            double yeniBakiye = musteri.getBakiye() + miktar;
            musteri.setBakiye(yeniBakiye);
        }
    }
}
