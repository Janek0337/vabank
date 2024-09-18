public class Gracz {
    private String nazwa;
    private int wynik;
    
    public Gracz(String nazwa){
        this.wynik = 0;
        setNazwa(nazwa);
    }

    public boolean equals(Object o){
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Gracz g = (Gracz)o;
        return this.nazwa.equals(g.nazwa);
    }

    public int hashCode(){
        return nazwa.hashCode();
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

    public String toString(){
        return this.nazwa;
    }
}
