package src;
public class Pytanie{
    private int ID;
    private int wartosc;
    private String tresc;
    private String odpowiedz;
    private String kategoria;
    private boolean odpowiedziane;

    public Pytanie(){
        
    }

    public Pytanie(String tresc, String odpowiedz, int ID, String kategoria, int wartosc){
        this.setID(ID);
        this.setWartosc(wartosc);
        this.setKategoria(kategoria);
        this.setOdpowiedz(odpowiedz);
        this.setTresc(tresc);
        this.setOdpowiedziane(false);
    }

    public void setOdpowiedziane(boolean czy){
        this.odpowiedziane = czy;
    }

    public boolean getOdpowiedziane(){
        return this.odpowiedziane;
    }

    public void setWartosc(int wartosc){
        this.wartosc = wartosc;
    }

    public int getWartosc(){
        return this.wartosc;
    }

    public String getTresc(){
        return this.tresc;
    }

    public void setTresc(String tresc){
        this.tresc = tresc;
    }

    public String getOdpowiedz(){
        return this.odpowiedz;
    }

    public void setOdpowiedz(String odpowiedz){
        this.odpowiedz = odpowiedz;
    }

    public void setID(int ID){
        this.ID = ID;
    }

    public int getID(){
        return this.ID;
    }

    public void setKategoria(String kategoria){
        this.kategoria = kategoria;
    }

    public String getKategoria(){
        return this.kategoria;
    }

    public int hashCode(){
        int hash = this.ID;
        hash *= this.wartosc;
        hash *= this.kategoria.hashCode();
        hash *= this.tresc.hashCode();
        hash *= this.odpowiedz.hashCode();
        return hash;
    }

}