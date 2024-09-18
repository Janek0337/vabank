package SRC;
import javax.swing.*;

import java.awt.*;
import java.util.*;

public class Gra extends JPanel {
    JButton[][] guziki = new JButton[6][5];
    String[] kategorie = new String[6];
    HashMap guzikPytanie = new HashMap<JButton,Pytanie>();
    JPanel srodek = new JPanel();

    public Gra(Frame frame, DefaultListModel leaderboard){
        
        //setup elementów

        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 5; j++){
                guziki[i][j] = new JButton("" + 100*(j+1));
            }
        }

        for(int i = 0; i < 6; i++){
            
        }
        
        //layout
        BorderLayout layout = new BorderLayout();

        //layout środka

        srodek.setLayout(new GridLayout(6,6));
        


        this.setVisible(true);
    }
}
