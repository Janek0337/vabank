package src;

import java.sql.*;
import java.util.*;

import javax.naming.spi.DirStateFactory.Result;

public class DBManager {
    public void wczytajTabliceGry(String nazwaDB, String[][] pytania, String[] kateogire){
        Connection con = null;
        String db = "jdbc:sqlite:" + nazwaDB;
        try {
            con = DriverManager.getConnection(db);
            for(int i = 0; i < 6; i++){

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    

    private void setFalse(HashMap<String, Boolean> mapa){
        for(Map.Entry<String, Boolean> entry : mapa.entrySet()){
            entry.setValue(false);
        }
    }

    private boolean setFound(HashMap<String, Boolean> mapa, String colName){
        Boolean wynik = mapa.get(colName);
        if(wynik != null){
            mapa.put(colName, true);
            return true;
        }
        return false;
    }

    private boolean czyWszystkieTrue(HashMap<String, Boolean> mapa){
        boolean flaga = true;
        for(Map.Entry<String, Boolean> entry : mapa.entrySet()){
            if(!entry.getValue()){
                return false;
            }
        }
        return true;
    }

    //metoda wyłuskuje z wszystkich tabel w bazie danych te, które mają odpowiedni format (odpowiednie kolumny)
    public ArrayList<String> getCorrectTableNames(String nazwaDB) throws SQLException {
        ArrayList<String> tabele = new ArrayList<String>();
        ArrayList<String> poprawneTabele = new ArrayList<String>();
        HashMap<String, Boolean> kolumnawTabeli = new HashMap<String, Boolean>();
        kolumnawTabeli.put("id", false);
        kolumnawTabeli.put("wartosc", false);
        kolumnawTabeli.put("pytanie", false);
        kolumnawTabeli.put("odpowiedz", false);
        kolumnawTabeli.put("kolumna", false);
        kolumnawTabeli.put("wiersz", false);
        Connection con = null;
        String db = "jdbc:sqlite:" + nazwaDB;
    
        try {
            con = DriverManager.getConnection(db);
            DatabaseMetaData dmd = con.getMetaData();
            ResultSet resTabeli = dmd.getTables(null, null, "%", new String[]{"TABLE"});
    
            while (resTabeli.next()) {
                tabele.add(resTabeli.getString("TABLE_NAME"));
            }
            resTabeli.close();
    
            for (String tabela : tabele) {
                ResultSet resKolumn = dmd.getColumns(null, null, tabela, "%");
                setFalse(kolumnawTabeli);
                boolean tabelaPoprawna = true;
                while (resKolumn.next()) {
                    String kolumna = resKolumn.getString("COLUMN_NAME");
                    if (!setFound(kolumnawTabeli, kolumna)) {
                        tabelaPoprawna = false;
                        break;
                    }
                }
                resKolumn.close();
                
                if (tabelaPoprawna && czyWszystkieTrue(kolumnawTabeli)) {
                    poprawneTabele.add(tabela);
                }
            }

            //dodać sprawdzenie czy max id = 30 dla każdej pozostałeś tabeli

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
        }
    
        return poprawneTabele;
    }

    public Pytanie getPytanieIndx(Connection con, int wiersz, int kolumna, String nazwaTabeli) throws SQLException {
        Pytanie p = new Pytanie();
        int id = -1;
        String pytanie = "";
        String odpowiedz = "";
        int wartosc = -1; 

        String query = "SELECT * FROM " + nazwaTabeli + " WHERE wiersz = ? AND kolumna = ?";

        try{
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, wiersz);
            pstmt.setInt(2, kolumna);

            ResultSet rs = pstmt.executeQuery(query);
            while(rs.next()){
                pytanie = rs.getString("pytanie");
                odpowiedz = rs.getString("odpowiedz");
                id = rs.getInt("id");
                wartosc = rs.getInt("wartosc");
            }
            rs.close();
            pstmt.close();
            
        } catch (SQLException e){
            System.err.printf("Nie udało się wykonać zapytania: \"%s\"\n", query);
        }

        if (id == -1 || pytanie.equals("") || wartosc == -1){
            p.setTresc("Nie udało się poprawnie wczytać pytania; Spróbuj ponownie");    
        }
        else{
            p.setID(id);
            p.setOdpowiedz(odpowiedz);
            p.setTresc(pytanie);
            p.setWartosc(wartosc);
        }

        return p;
    }
}
