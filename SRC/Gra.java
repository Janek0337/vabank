package src;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;


public class Gra extends JPanel {
    JButton[][] guziki;
    HashMap<JButton, Pytanie> pytanieGuzik;
    ArrayList<ArrayList<Pytanie>> pytania;
    JPanel srodek = new JPanel();
    JButton odblokujPyt = new JButton();
    JButton zakoncz = new JButton();
    leaderboard prawyLeaderboard;

    public Gra(Frame frame, DefaultListModel<Gracz> leaderboard, DBManager db, String FilePath, String nazwaTablicy){
        BiHashMap<Pytanie, JButton> pytanieGuzik = new BiHashMap<Pytanie, JButton>();
        this.prawyLeaderboard = new leaderboard(leaderboard, true);
        this.pytania = db.getGridPytan(FilePath, nazwaTablicy);
        JButton adminButton2 = new JButton("admin2");
        this.add(adminButton2,BorderLayout.SOUTH);
        frame.makeButton(odblokujPyt, "Odblokuj pytania", getForeground(), getBackground(),14);
        frame.makeButton(zakoncz, "Zakończ grę", getForeground(), getBackground(),14);

        //layout
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        //layout środka

        srodek.setLayout(new GridLayout(0,pytania.size()));
        this.add(srodek, BorderLayout.CENTER);

        JLabel[] kategoriaNapis = new JLabel[pytania.size()];
        for(int i = 0; i < pytania.size(); i++){
            JPanel ten = new JPanel();
            JPanel box = new JPanel();
            box.setLayout(new BoxLayout(box, BoxLayout.X_AXIS));
            ten.setLayout(new BoxLayout(ten, BoxLayout.Y_AXIS));
            kategoriaNapis[i] = new JLabel(pytania.get(i).get(0).getKategoria());
            ten.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
            box.add(Box.createHorizontalGlue());
            box.add(kategoriaNapis[i]);
            box.add(Box.createHorizontalGlue());
            ten.add(Box.createVerticalGlue());
            ten.add(box);
            kategoriaNapis[i].setFont(new Font("Arial", Font.BOLD, 12));
            srodek.add(ten);
        }
        
        for(int i = 0; i < pytania.get(0).size(); i++){
            for(int j = 0; j < pytania.size(); j++){
                JButton guzik = new JButton();
                frame.makeButton(guzik, "<html><font size = '3'><b>" + pytania.get(j).get(i).getWartosc() + "</u></font></html>", getForeground(), getBackground(), 12);
                pytanieGuzik.put(pytania.get(j).get(i), guzik);
                guzik.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent e){
                            WidokPytania pyt = new WidokPytania(frame, pytanieGuzik.getKey(guzik), leaderboard, pytanieGuzik);
                            frame.addToFrame(pyt, "pytanie");
                            frame.showPanel("pytanie");
                        }   
                    }
                );
                srodek.add(guzik);
            }
        }

        //layout lewej
        JPanel lewy = new JPanel();
        this.add(lewy,BorderLayout.WEST);
        lewy.setLayout(new BoxLayout(lewy, BoxLayout.Y_AXIS));
        lewy.add(Box.createVerticalGlue());
        lewy.add(odblokujPyt);
        lewy.add(Box.createRigidArea(new Dimension(10, 10)));
        lewy.add(zakoncz);
        lewy.add(Box.createVerticalGlue());
        lewy.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //layout prawej
        JPanel prawy = new JPanel();
        this.add(prawy,BorderLayout.EAST);
        prawy.setLayout(new BoxLayout(prawy, BoxLayout.Y_AXIS));
        prawy.add(Box.createVerticalGlue());
        prawy.add(prawyLeaderboard);
        prawy.add(Box.createVerticalGlue());
        prawy.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        prawy.setSize(lewy.getSize());
        
        JPanel dolny = new JPanel();
        this.add(dolny,BorderLayout.SOUTH);
        dolny.setLayout(new FlowLayout());

        JPanel gorny = new JPanel();
        JLabel img = new JLabel(new ImageIcon("./grafika/vabanek.png"));
        this.add(gorny,BorderLayout.NORTH);
        gorny.add(img);
        this.add(gorny,BorderLayout.NORTH);

        //listenerzy

        odblokujPyt.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    Iterator<Map.Entry<Pytanie,JButton>> iter = pytanieGuzik.getIterator();
                    while(iter.hasNext()){
                        Map.Entry<Pytanie, JButton> elem = iter.next();
                        JButton b = elem.getValue();
                        b.setEnabled(true);
                    }
                }
            }
        );

        zakoncz.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    
                }
            }
        );
        this.setVisible(true);
    }

    public void refresh(){
        this.prawyLeaderboard.refresh();
    }
}
