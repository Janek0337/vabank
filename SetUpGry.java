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
    JLabel napisInfo = new JLabel();
    JLabel napisNick = new JLabel();
    
    
    public SetUpGry(Frame frame){
        napisInfo.setVisible(false);
        napisInfo.setForeground(Color.RED);
        napisNick.setText("Wpisz nick:");
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
                    if (nick.isEmpty()){
                        napisInfo.setText("Nick nie może być pusty");
                        napisInfo.setVisible(true);
                    }
                    else if(listaGraczy.contains(new Gracz(nick))){
                        napisInfo.setText("Taki gracz już istnieje");
                        napisInfo.setVisible(true);
                    }
                    else{
                        napisInfo.setVisible(false);
                        Gracz g = new Gracz(nick);
                        listaGraczy.addElement(g);
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
                        napisInfo.setVisible(false);
                    }
                    else{
                        napisInfo.setText("Najpierw wybierz gracza z listy");
                        napisInfo.setVisible(true);
                    }
                }
            }
        );

        start.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    if(listaGraczy.getSize() > 1){
                        napisInfo.setVisible(false);
                        Gra gra = new Gra(frame, listaGraczy);
                        frame.addToFrame(gra, "gra");
                        frame.showPanel("gra");
                    }
                    else{
                        napisInfo.setText("Dodaj conajmniej 2 graczy");
                        napisInfo.setVisible(true);
                    }
                }
            }
        );

        anuluj.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    napisInfo.setVisible(false);
                    frame.showPanel("menu główne");
                }
            }
        );

        usunAll.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    listaGraczy.clear();
                    napisInfo.setVisible(false);
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
        lewy.add(napisNick);
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
        prawy.add(napisInfo);
        prawy.add(Box.createRigidArea(new Dimension(0, 10)));
        
        this.add(srodek,BorderLayout.CENTER);
        this.add(napisNaGorze,BorderLayout.NORTH);
        this.add(anuluj,BorderLayout.SOUTH);

        revalidate();
        repaint();
        frame.setVisible(true);
    }
}
