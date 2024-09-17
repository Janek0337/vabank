import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.event.ActionEvent;

public class SetUpGry extends JPanel {
    JButton start = new JButton();
    JButton dodaj = new JButton();
    JButton usun = new JButton();
    JTextField nazwa = new JTextField(30);
    DefaultListModel<Gracz> listaGraczy = new DefaultListModel<Gracz>();
    JList<Gracz> listaGraczyWidok = new JList<Gracz>(listaGraczy);
    JLabel napisNaGorze = new JLabel("Nowa Gra");
    JLabel napisLista = new JLabel("Lista graczy:");
    JButton anuluj = new JButton();
    JButton usunAll = new JButton();
    
    
    public SetUpGry(Frame frame){
        frame.makeButton(dodaj, "Dodaj gracza", null, null);
        frame.makeButton(start, "Rozpocznij grę", getForeground(), getBackground());
        frame.makeButton(usun,  "Usuń zaznaczony", getForeground(), getBackground());
        frame.makeButton(usunAll, "Wyczyść listę", getForeground(), getBackground());
        listaGraczyWidok.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        nazwa.setColumns(40);
        nazwa.setMaximumSize(new Dimension(400,30));
        frame.makeLabel(napisNaGorze, "Arial", Font.BOLD, 24);
        frame.makeButton(anuluj, "Anuluj", getForeground(), getBackground());
        listaGraczyWidok.setVisibleRowCount(10);
        napisNaGorze.setHorizontalAlignment(SwingConstants.CENTER);

        // listenerzy

        dodaj.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    String nick = nazwa.getText();
                    if (!nick.isEmpty()){
                        listaGraczy.addElement(new Gracz(nick));
                        nazwa.setText("");
                    }
                }
                
            }
        );

        usun.addActionListener(
            new ActionListener(){
                public void actionPerformed (ActionEvent e){
                    if(listaGraczyWidok.getSelectedIndex() != -1){
                        listaGraczy.remove(listaGraczyWidok.getSelectedIndex());
                    }
                }
            }
        );

        start.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    System.out.println("Gramy");
                }
            }
        );

        anuluj.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    frame.showPanel("menu główne");
                }
            }
        );

        usunAll.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    listaGraczy.clear();
                }
            }
        );

        //layout
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);
        JPanel lewy = new JPanel();
        JPanel prawy = new JPanel();
        JPanel srodek = new JPanel();
        lewy.setLayout(new BoxLayout(lewy,BoxLayout.Y_AXIS));
        prawy.setLayout(new BoxLayout(prawy,BoxLayout.Y_AXIS));
        srodek.setLayout(new GridLayout(1,2));
        srodek.add(lewy);
        srodek.add(prawy);
        //lewy.setBackground(Color.yellow);
        //prawy.setBackground(Color.blue);

        lewy.add(Box.createRigidArea(new Dimension(10, 10)));
        lewy.add(nazwa);
        lewy.add(Box.createRigidArea(new Dimension(10, 10)));
        lewy.add(dodaj);
        lewy.add(Box.createRigidArea(new Dimension(0, 10)));
        lewy.add(usun);
        lewy.add(Box.createRigidArea(new Dimension(0, 10)));
        lewy.add(usunAll);
        lewy.add(Box.createRigidArea(new Dimension(0, 10)));
        
        prawy.add(Box.createRigidArea(new Dimension(10, 10)));
        prawy.add(napisLista);
        napisLista.setPreferredSize(new Dimension(200,50));
        napisLista.setAlignmentX(Component.LEFT_ALIGNMENT);
        prawy.add(Box.createRigidArea(new Dimension(0, 10)));
        prawy.add(listaGraczyWidok);
        listaGraczyWidok.setAlignmentX(Component.LEFT_ALIGNMENT);
        prawy.add(Box.createRigidArea(new Dimension(0, 10)));
        prawy.add(start);
        prawy.add(Box.createRigidArea(new Dimension(0, 10)));
        
        this.add(srodek,BorderLayout.CENTER);
        this.add(napisNaGorze,BorderLayout.NORTH);
        //this.add(start,BorderLayout.NORTH);
        this.add(anuluj,BorderLayout.SOUTH);
        
        /*srodekLayout.setHorizontalGroup(
            srodekLayout.createSequentialGroup()
                .addContainerGap(10,10)
                .addGroup(srodekLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                    .addComponent(lewy)
                )
                .addContainerGap(10,10)
                .addComponent(prawy)
                .addContainerGap(10,10)
        );


        srodekLayout.setVerticalGroup(
            srodekLayout.createSequentialGroup()
                .addGroup(srodekLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(lewy)
                    .addComponent(prawy)
                )
        );*/ 
        
            
        revalidate();
        repaint();
        frame.setVisible(true);
    }
}
