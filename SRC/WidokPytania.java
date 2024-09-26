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
    JComboBox wyborOdpowiadajacego = new JComboBox<Gracz>();
    JCheckBox dobrze = new JCheckBox("Dobrze?:");
    JButton pokazOdp = new JButton();
    JButton ukryjOdp = new JButton();
    JButton wroc = new JButton();
    JButton zakonczPyt = new JButton();
    JLabel odpowiada = new JLabel("Odpowiada:");
    JButton submitOdp = new JButton();

    public WidokPytania(Frame frame, Pytanie p, DefaultListModel<Gracz> listaGraczy, BiHashMap<Pytanie, JButton> mapa){
        JList leaderBoard = new JList<Gracz>(listaGraczy);
        JLabel pytanieLabel = new JLabel("Pytanie:");
        JLabel odpowiedzLabel = new JLabel("Odpowiedź:");
        odpowiedz.setVisible(false);
        odpowiedzLabel.setVisible(false);
        infoGora.setText(p.getKategoria() + " - " + p.getWartosc());
        frame.makeLabel(infoGora, "Arial", Font.BOLD, 24);
        trescPytania.setText(p.getTresc());
        odpowiedz.setText(p.getOdpowiedz());
        frame.makeButton(pokazOdp, "Pokaż odp", getForeground(), getBackground());
        frame.makeButton(ukryjOdp, "Ukryj odp", getForeground(), getBackground());
        ukryjOdp.setEnabled(false);
        frame.makeButton(wroc, "Wróć", getForeground(), getBackground());
        frame.makeButton(zakonczPyt, "Zakończ pytanie", getForeground(), getBackground());
        frame.makeButton(submitOdp, "Odpowiedz", getForeground(), getBackground());
        trescPytania.setText(p.getTresc());
        odpowiedz.setText(p.getOdpowiedz());


        //layout
        this.setLayout(new BorderLayout());

        JPanel gorny = new JPanel();
        gorny.setLayout(new BoxLayout(gorny, BoxLayout.X_AXIS));
        this.add(gorny, BorderLayout.NORTH);
        gorny.add(Box.createHorizontalGlue());
        gorny.add(infoGora);
        gorny.add(Box.createHorizontalGlue());

        JPanel srodek = new JPanel();
        srodek.add(Box.createRigidArea(new Dimension(10, 10)));
        srodek.setLayout(new BoxLayout(srodek,BoxLayout.Y_AXIS));
        srodek.add(pytanieLabel);
        srodek.add(Box.createRigidArea(new Dimension(10, 10)));
        srodek.add(trescPytania);
        srodek.add(Box.createRigidArea(new Dimension(10, 10)));
        srodek.add(odpowiedzLabel);
        srodek.add(Box.createRigidArea(new Dimension(10, 10)));
        srodek.add(odpowiedz);
        srodek.add(Box.createRigidArea(new Dimension(10, 10)));

        JPanel lewy = new JPanel();
        lewy.setLayout(new BoxLayout(lewy, BoxLayout.Y_AXIS));

        lewy.add(Box.createRigidArea(new Dimension(10, 10)));
        lewy.add(pokazOdp);
        lewy.add(Box.createRigidArea(new Dimension(10, 10)));
        lewy.add(ukryjOdp);
        lewy.add(Box.createRigidArea(new Dimension(10, 10)));
        lewy.add(zakonczPyt);
        lewy.add(Box.createRigidArea(new Dimension(10, 10)));
        lewy.add(wroc);
        lewy.add(Box.createRigidArea(new Dimension(10, 10)));
        this.add(lewy, BorderLayout.WEST);
        this.add(srodek,BorderLayout.CENTER);

        JPanel prawy = new JPanel();
        FlowLayout prawyLayout = new FlowLayout(FlowLayout.CENTER);
        prawy.setLayout(prawyLayout);
        prawy.add(leaderBoard);
        this.add(prawy, BorderLayout.EAST);


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

        this.setVisible(true);
    }
}
