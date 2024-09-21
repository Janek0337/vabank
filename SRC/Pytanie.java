package src;
public class Pytanie{
    private int ID;
    private int wartosc;
    private String tresc;
    private String odpowiedz;
    private String kategoria;

    public Pytanie(){
        
    }

    public Pytanie(String tresc, String odpowiedz, int ID, String kategoria, int wartosc){
        this.setID(ID);
        this.setWartosc(wartosc);
        this.setKategoria(kategoria);
        this.setOdpowiedz(odpowiedz);
        this.setTresc(tresc);
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

}