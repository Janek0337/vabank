package src;
import javax.swing.*;

import java.awt.*;
import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.*;

public class Gra extends JPanel {
    JButton[][] guziki;
    JLabel[] kategoriaNapis;
    ArrayList<ArrayList<Pytanie>> pytania;
    JPanel srodek = new JPanel();

    public Gra(Frame frame, DefaultListModel<Gracz> leaderboard, DBManager db, String FilePath, String nazwaTablicy){
        
        //setup elementów
        ArrayList<ArrayList<Pytanie>> pytania = db.getGridPytan(FilePath, nazwaTablicy);

        JLabel[] kategoriaNapis = new JLabel[pytania.size()];
        for(int i = 0; i < pytania.size(); i++){
            kategoriaNapis[i] = new JLabel(pytania.get(i).get(0).getKategoria());
            kategoriaNapis[i].add(srodek);
        }
        
        //layout
        BorderLayout layout = new BorderLayout();

        //layout środka

        srodek.setLayout(new GridLayout(pytania.get(0).size(),pytania.size()));
        


        this.setVisible(true);
    }
}
