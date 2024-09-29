package src;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;

public class WidokPytania extends JPanel{
    JLabel infoGora = new JLabel();
    JLabel trescPytania = new JLabel();
    JLabel odpowiedz = new JLabel();
    JComboBox<Gracz> wyborOdpowiadajacego = new JComboBox<Gracz>();
    JCheckBox dobrze = new JCheckBox("Dobrze?");
    JButton pokazOdp = new JButton();
    JButton ukryjOdp = new JButton();
    JButton wroc = new JButton();
    JButton zakonczPyt = new JButton();
    JLabel odpowiada = new JLabel("Odpowiada:");
    JButton submitOdp = new JButton();
    leaderboard leaderboard;

    public WidokPytania(Frame frame, Pytanie p, DefaultListModel<Gracz> listaGraczy, BiHashMap<Pytanie, JButton> mapa){
        Font foncik = new Font("Arial", Font.ROMAN_BASELINE, 26);
        Font foncik2 = new Font("Arial", Font.PLAIN, 18);
        leaderboard leaderboard = new leaderboard(listaGraczy, true);
        JLabel pytanieLabel = new JLabel("<html><i>Pytanie:</i></html>");
        pytanieLabel.setFont(foncik);
        JLabel odpowiedzLabel = new JLabel("<html><i>Odpowiedź:</i></html>");
        odpowiedzLabel.setFont(foncik);
        odpowiedz.setVisible(false);
        odpowiedzLabel.setVisible(false);
        infoGora.setText(p.getKategoria() + " - " + p.getWartosc());
        frame.makeLabel(infoGora, "Arial", Font.BOLD, 32);
        trescPytania.setText(p.getTresc());
        trescPytania.setFont(foncik2);
        odpowiedz.setText(p.getOdpowiedz());
        odpowiedz.setFont(foncik2);
        frame.makeButton(pokazOdp, "Pokaż odp", getForeground(), getBackground(),14);
        frame.makeButton(ukryjOdp, "Ukryj odp", getForeground(), getBackground(),14);
        ukryjOdp.setEnabled(false);
        frame.makeButton(wroc, "Wróć", getForeground(), getBackground(),14);
        frame.makeButton(zakonczPyt, "Zakończ pytanie", getForeground(), getBackground(),14);
        frame.makeButton(submitOdp, "Odpowiedz", getForeground(), getBackground(),14);
        trescPytania.setText("<html>" + p.getTresc() + "</html>");
        odpowiedz.setText("<html>" + p.getOdpowiedz() + "</html>");
        dobrze.setSelected(true);
        
        for(int i = 0; i < listaGraczy.size(); i++){
            wyborOdpowiadajacego.addItem(listaGraczy.get(i));
        }


        //layout
        this.setLayout(new BorderLayout());

        JPanel gorny = new JPanel();
        gorny.setLayout(new BoxLayout(gorny, BoxLayout.X_AXIS));
        this.add(gorny, BorderLayout.NORTH);
        gorny.add(Box.createHorizontalGlue());
        gorny.add(infoGora);
        gorny.add(Box.createHorizontalGlue());

        JPanel srodek = new JPanel();
        srodek.add(Box.createHorizontalGlue());
        srodek.setLayout(new BoxLayout(srodek,BoxLayout.Y_AXIS));
        srodek.add(pytanieLabel);
        srodek.add(Box.createRigidArea(new Dimension(10, 10)));
        srodek.add(trescPytania);
        srodek.add(Box.createRigidArea(new Dimension(10, 40)));
        srodek.add(odpowiedzLabel);
        srodek.add(Box.createRigidArea(new Dimension(10, 10)));
        srodek.add(odpowiedz);
        srodek.add(Box.createHorizontalGlue());

        JPanel srodeksrodek = new JPanel();
        srodeksrodek.setLayout(new BoxLayout(srodeksrodek, BoxLayout.X_AXIS));
        srodeksrodek.add(Box.createHorizontalGlue());
        srodeksrodek.add(srodek);
        srodeksrodek.add(Box.createHorizontalGlue());
        this.add(srodeksrodek,BorderLayout.CENTER);

        JPanel lewy = new JPanel();
        lewy.setLayout(new BoxLayout(lewy, BoxLayout.Y_AXIS));

        lewy.add(Box.createVerticalGlue());
        lewy.add(pokazOdp);
        lewy.add(Box.createRigidArea(new Dimension(10, 10)));
        lewy.add(ukryjOdp);
        lewy.add(Box.createRigidArea(new Dimension(10, 10)));
        lewy.add(zakonczPyt);
        lewy.add(Box.createRigidArea(new Dimension(10, 10)));
        lewy.add(wroc);
        lewy.add(Box.createVerticalGlue());
        this.add(lewy, BorderLayout.WEST);

        JPanel prawy = new JPanel();
        prawy.setLayout(new BoxLayout(prawy, BoxLayout.Y_AXIS));
        prawy.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        prawy.add(Box.createVerticalGlue());
        prawy.add(leaderboard);
        prawy.add(Box.createVerticalGlue());
        this.add(prawy, BorderLayout.EAST);

        JPanel dolny = new JPanel();
        this.add(dolny, BorderLayout.SOUTH);
        dolny.setLayout(new BoxLayout(dolny, BoxLayout.X_AXIS));
        dolny.add(Box.createHorizontalGlue());
        dolny.add(odpowiada);
        dolny.add(Box.createRigidArea(new Dimension(10, 10)));
        dolny.add(wyborOdpowiadajacego);
        dolny.add(Box.createRigidArea(new Dimension(10, 10)));
        dolny.add(dobrze);
        dolny.add(Box.createRigidArea(new Dimension(10, 10)));
        dolny.add(submitOdp);
        dolny.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        wyborOdpowiadajacego.setMaximumSize(new Dimension(200,40));
        dolny.add(Box.createHorizontalGlue());


        //listenerzy

        pokazOdp.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    odpowiedzLabel.setVisible(true);
                    odpowiedz.setVisible(true);
                    pokazOdp.setEnabled(false);
                    ukryjOdp.setEnabled(true);
                }
            }
        );

        ukryjOdp.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    odpowiedzLabel.setVisible(false);
                    odpowiedz.setVisible(false);
                    pokazOdp.setEnabled(true);
                    ukryjOdp.setEnabled(false);
                }
            }
        );

        wroc.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    JPanel p = frame.getPanel("gra");
                    if (p instanceof Gra){
                        ((Gra)p).refresh();
                    }
                    frame.showPanel("gra");
                }
            }
        );

        zakonczPyt.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    ((JButton)mapa.getValue(p)).setEnabled(false);
                    wroc.doClick();
                }
            }
        );
        
        submitOdp.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    Gracz g = (Gracz)wyborOdpowiadajacego.getSelectedItem();
                    if(dobrze.isSelected()){
                        g.setWynik(g.getWynik() + p.getWartosc());
                    }
                    else{
                        g.setWynik(g.getWynik() - p.getWartosc());
                    }
                    leaderboard.refresh();
                }
            }
        );

        this.setVisible(true);
    }
}
