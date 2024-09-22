package src;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class MenuGlowne extends JPanel {
    JButton nowaGra = new JButton();
    JButton zamknij = new JButton();
    Color buttonColor = new Color(57,124,210);

    MenuGlowne(Frame frame){
        
        frame.makeButton(nowaGra, "Nowa gra", Color.yellow, buttonColor);
        frame.makeButton(zamknij, "Wyjście", Color.yellow, buttonColor);


        // ustawienie layoutu
        
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        
        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(nowaGra)
                    .addComponent(zamknij))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(nowaGra)
                .addComponent(zamknij)
        );

        // słuchacze przycisków

        nowaGra.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    frame.showPanel("setup");
                }    
            }
        );

        zamknij.addActionListener(
            (e) -> System.exit(0)
        );

        this.setVisible(true);
    }
}
