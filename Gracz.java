public class Gracz {
    private String nazwa;
    private int wynik;
    
    public Gracz(String nazwa){
        this.wynik = 0;
        setNazwa(nazwa);
    }

    public int getWynik(){
        return this.wynik;
    }

    public void setWynik(int nowyWynik){
        this.wynik = nowyWynik;
    }

    public void setNazwa(String nazwa){
        this.nazwa = nazwa;
    }

    public String getNazwa(){
        return this.nazwa;
    }
}
