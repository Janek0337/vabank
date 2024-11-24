package src;

import java.sql.*;
import java.util.*;

public class SQLiteDataBaseManager { 

    public ArrayList<ArrayList<Pytanie>> getGridPytan(String DBFilePath, String nazwaTabeli){
        ArrayList<ArrayList<Pytanie>> gridPytan = new ArrayList<ArrayList<Pytanie>>();
        ArrayList<String> listaKategorii = new ArrayList<String>();
        String queryKategorie = "SELECT DISTINCT kategoria FROM " + nazwaTabeli;
        String queryPytania = "SELECT id, wartosc, pytanie, odpowiedz, kategoria FROM " + nazwaTabeli + " WHERE kategoria = ?";
        ResultSet resPytania = null;

        try (Connection con = DriverManager.getConnection("jdbc:sqlite:" + DBFilePath);
            Statement stmt = con.createStatement();
            ResultSet resKategorie = stmt.executeQuery(queryKategorie); 
            PreparedStatement pstmt = con.prepareStatement(queryPytania);      
            ) {


            while (resKategorie.next()){
                listaKategorii.add(resKategorie.getString("kategoria"));
            }

            for(int i = 0; i < listaKategorii.size(); i++){
                pstmt.setString(1, listaKategorii.get(i));
                resPytania = pstmt.executeQuery();
                ArrayList<Pytanie> tePytania = new ArrayList<Pytanie>();
                
                while(resPytania.next()){
                    String taTresc = resPytania.getString("pytanie");
                    int toID = resPytania.getInt("id");
                    String taKategoria = resPytania.getString("kategoria");
                    int taWartosc = resPytania.getInt("wartosc");
                    String taOdp = resPytania.getString("odpowiedz");
                    tePytania.add(new Pytanie(taTresc, taOdp, toID, taKategoria, taWartosc));
                }
                gridPytan.add(tePytania);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try{
                if(resPytania != null){
                    resPytania.close();
                }
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
        return gridPytan;
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
        for(Map.Entry<String, Boolean> entry : mapa.entrySet()){
            if(!entry.getValue()){
                return false;
            }
        }
        return true;
    }

    //metoda wyłuskuje z wszystkich tabel w bazie danych te, które mają odpowiedni format (odpowiednie kolumny)
    public ArrayList<String> getCorrectTableNames(String DBFilePath) throws SQLException {
        ArrayList<String> tabele = new ArrayList<String>();
        ArrayList<String> poprawneTabele = new ArrayList<String>();
        HashMap<String, Boolean> kolumnawTabeli = new HashMap<String, Boolean>();
        kolumnawTabeli.put("id", false);
        kolumnawTabeli.put("wartosc", false);
        kolumnawTabeli.put("pytanie", false);
        kolumnawTabeli.put("odpowiedz", false);
        kolumnawTabeli.put("kategoria", false);

        Connection con = null;
        String db = "jdbc:sqlite:" + DBFilePath;
    
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
}