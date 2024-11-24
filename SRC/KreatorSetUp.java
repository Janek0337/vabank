package src;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

public class KreatorSetUp extends JPanel {
    private JLabel napisNaGorze = new JLabel("<html><font size = '7'>Kreator pytań</font></html>");
    private JButton nowa = new JButton();
    private JButton wybierzIstniejaca = new JButton();
    private File baza;

    public KreatorSetUp(Frame frame){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(napisNaGorze);
        this.add(nowa);
        this.add(wybierzIstniejaca);
        frame.makeButton(nowa, "Nowa baza pytań", getForeground(), getBackground(), 14);
        frame.makeButton(wybierzIstniejaca, "Dodaj do istniejącej bazy", getForeground(), getBackground(), 14);
        this.setVisible(true);

        wybierzIstniejaca.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    JFileChooser jfc = new JFileChooser();
                    int result = jfc.showOpenDialog(null);
                    jfc.setCurrentDirectory(new File("./bazyPytan"));
                    if(result == JFileChooser.APPROVE_OPTION){

                    }
                }
            }
        );
    }
}
