package src;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class KoniecGry extends JPanel {
    private JPanel XPanel;
    private JPanel YPanel;
    private JButton wyjdz;

    public KoniecGry(Frame frame, DefaultListModel<Gracz> listaGraczy){
        XPanel = new JPanel();
        YPanel = new JPanel();
        wyjdz = new JButton();
        frame.makeButton(wyjdz, "Wyjdź", getForeground(), getBackground(), 12);

        XPanel.setLayout(new BoxLayout(XPanel, BoxLayout.X_AXIS));
        YPanel.setLayout(new BoxLayout(YPanel, BoxLayout.Y_AXIS));

        leaderboard.sortDefaultListModel(listaGraczy);
        for(int i = 0; i < listaGraczy.size(); i++){
            System.out.println(listaGraczy.get(i).getNazwa() + " " + listaGraczy.get(i).getWynik());
        }

        JLabel listaWynikow = new JLabel();
        String tekst = "<html><font size = '7'>Koniec gry</font><br><br><br><font size = '5'>Wyniki:</font><br><ol>";
        for(int i = 0; i < listaGraczy.size(); i++){
            tekst += "<li><font size = '4'>" + listaGraczy.get(i).getNazwa() + " " + listaGraczy.get(i).getWynik() + "</font></li>";
        }
        tekst += "</ol></html>";
        listaWynikow.setText(tekst);

        XPanel.add(Box.createHorizontalGlue());
        XPanel.add(listaWynikow);
        XPanel.add(Box.createHorizontalGlue());

        YPanel.add(Box.createVerticalGlue());
        YPanel.add(XPanel);
        YPanel.add(Box.createRigidArea(new Dimension(10, 10)));
        YPanel.add(wyjdz);
        YPanel.add(Box.createVerticalGlue());
        this.add(YPanel);
        this.setVisible(true);

        wyjdz.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    listaGraczy.clear();
                    frame.showPanel("menu główne");
                }   
            }
        );
    }
}
