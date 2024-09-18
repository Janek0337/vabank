package SRC;
import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame{
    private CardLayout cardLayout;
    private JPanel cards;

    Frame(){
        int szerokosc = Toolkit.getDefaultToolkit().getScreenSize().width;
        int wysokosc = Toolkit.getDefaultToolkit().getScreenSize().height;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(szerokosc/2, wysokosc/2);
        this.setLocationRelativeTo(null);
        this.setTitle("Vabank");

        this.cardLayout = new CardLayout();
        this.cards = new JPanel(cardLayout);
        this.add(cards);
        this.setVisible(true);
    }

    public void addToFrame(JPanel panel, String nazwa){
        this.cards.add(panel, nazwa);
        this.revalidate();
        this.repaint();
    }

    public void removeFromPanel(JPanel panel){
        this.cards.remove(panel);
    }

    public void showPanel(String nazwa){
        cardLayout.show(cards, nazwa);
        cards.revalidate();
        cards.repaint();
    }

    public void makeButton (JButton button, String text, Color kolorTekstu, Color kolorTla){
        button.setForeground(kolorTekstu);
        button.setBackground(kolorTla);
        button.setText(text);
        button.setVisible(true);
    }

    public void makeLabel(JLabel label, String nazwaCzcionki, int font, int size){
        label.setFont(new Font(nazwaCzcionki, font, size));
    }
}
