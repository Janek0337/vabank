package src;
import java.util.*;
import java.awt.*;
import javax.swing.*;

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

    public WidokPytania(Frame frame, Pytanie p, DefaultListModel<Gracz> listaGraczy, BiHashMap mapa){
        JList leaderBoard = new JList<Gracz>(listaGraczy);
        JLabel pytanieLabel = new JLabel("Pytanie");
        JLabel odpowiedzLabel = new JLabel("Odpowiedź");
        odpowiedz.setVisible(false);
        infoGora.setText(p.getKategoria() + " - " + p.getWartosc());
        frame.makeLabel(infoGora, "Arial", Font.BOLD, 24);
        trescPytania.setText(p.getTresc());
        odpowiedz.setText(p.getOdpowiedz());
        frame.makeButton(pokazOdp, "Pokaż odp", getForeground(), getBackground());
        frame.makeButton(ukryjOdp, "Ukryj odp", getForeground(), getBackground());
        frame.makeButton(wroc, "Wróć", getForeground(), getBackground());
        frame.makeButton(zakonczPyt, "Zakończ pytanie", getForeground(), getBackground());
        frame.makeButton(submitOdp, "Odpowiedz", getForeground(), getBackground());
        trescPytania.setText(p.getTresc());
        odpowiedz.setText(p.getOdpowiedz());


        //layout
        this.setLayout(new BorderLayout());
        this.add(infoGora, BorderLayout.NORTH);

        JPanel srodek = new JPanel();
        srodek.setLayout(new BoxLayout(srodek,BoxLayout.Y_AXIS));
        srodek.add(trescPytania);
        srodek.add(Box.createRigidArea(new Dimension(10, 10)));
        srodek.add(odpowiedz);
        srodek.add(Box.createRigidArea(new Dimension(10, 10)));

        this.add(srodek,BorderLayout.CENTER);
        this.setVisible(true);
    }
}
