package src;
import javax.swing.*;

import java.awt.*;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.*;

public class Gra extends JPanel {
    JButton[][] guziki;
    HashMap<JButton, Pytanie> pytanieGuzik;
    ArrayList<ArrayList<Pytanie>> pytania;
    JPanel srodek = new JPanel();

    public Gra(Frame frame, DefaultListModel<Gracz> leaderboard, DBManager db, String FilePath, String nazwaTablicy){
        
        ArrayList<ArrayList<Pytanie>> pytania = db.getGridPytan(FilePath, nazwaTablicy);
        HashMap<JButton, Pytanie> pytanieGuzik = new HashMap<JButton, Pytanie>();
        //layout
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        //layout środka

        srodek.setLayout(new GridLayout(0,pytania.size()));
        this.add(srodek, BorderLayout.CENTER);

        JLabel[] kategoriaNapis = new JLabel[pytania.size()];
        for(int i = 0; i < pytania.size(); i++){
            kategoriaNapis[i] = new JLabel(pytania.get(i).get(0).getKategoria());
            srodek.add(kategoriaNapis[i]);
        }
        
        for(int i = 0; i < pytania.get(0).size(); i++){
            for(int j = 0; j < pytania.size(); j++){
                JButton guzik = new JButton("" + pytania.get(j).get(i).getWartosc());
                pytanieGuzik.put(guzik, pytania.get(j).get(i));
                srodek.add(guzik);
            }
        }


        this.setVisible(true);
    }
}
