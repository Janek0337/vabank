package src;
import javax.swing.*;
import javax.swing.border.Border;
import java.io.*;
import java.sql.SQLException;
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
    JButton adminButton = new JButton();
    JButton bazaButton = new JButton();
    JFileChooser jfc = new JFileChooser();
    String DBFilePath;
    String tablica;
    JComboBox<String> wyborTabeli = new JComboBox<String>();
    ArrayList<String> tabele;
    
    public SetUpGry(Frame frame){
        DBManager dbmgng = new DBManager();

        napisInfo.setVisible(false);
        napisInfo.setForeground(Color.RED);
        napisNick.setText("Wpisz nick:");
        wyborTabeli.setMaximumSize(new Dimension(200,30));
        wyborTabeli.addItem("Siema eniu");
        wyborTabeli.addItem("Dotykam małe rumuńskie dzieci");
        wyborTabeli.setAlignmentX(Component.LEFT_ALIGNMENT);
        frame.makeButton(bazaButton,"Wybierz pytania" , getForeground(), getBackground());
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
        frame.makeButton(adminButton, "adminButton", getForeground(), getBackground());
        JLabel zestawNapis = new JLabel("Wybierz zestaw pytań:");
        zestawNapis.setVisible(false);
        wyborTabeli.setVisible(false);

        JLabel wybranyPlik = new JLabel("Wybrany plik:\n");
        wybranyPlik.setVisible(false);
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
                        Gra gra = new Gra(frame, listaGraczy, dbmgng, DBFilePath, tablica);
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

        bazaButton.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    int result = jfc.showOpenDialog(null);
                    jfc.setCurrentDirectory(new File("../bazyPytan"));
                    if(result == JFileChooser.APPROVE_OPTION){
                        File bazaDanych = jfc.getSelectedFile();
                        DBFilePath = bazaDanych.getAbsolutePath();
                        wybranyPlik.setVisible(true);
                        wybranyPlik.setText("Wybrany plik: \"" + bazaDanych.getName() + "\"");
                        
                        if(!bazaDanych.getName().endsWith(".db")){
                            napisInfo.setVisible(true);
                            napisInfo.setText("Zły format pliku; oczekiwany \".db\"");
                        }
                        else{
                            zestawNapis.setVisible(true);
                            wyborTabeli.setVisible(true);
                            try{
                            tabele = dbmgng.getCorrectTableNames(DBFilePath);
                            String[] tabelki = new String[tabele.size()];
                            for(int i = 0; i < tabele.size(); i++){
                                tabelki[i] = tabele.get(i);
                            }
                            
                            for(String el : tabelki){
                                wyborTabeli.addItem(el);
                            }

                            } catch (SQLException ex){
                                ex.printStackTrace();
                                frame.showPanel("menu główne");
                            }
                        }
                    }
                }
            }
        );

        adminButton.addActionListener(
            new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    listaGraczy.addElement(new Gracz("Bolek"));
                    listaGraczy.addElement(new Gracz("Lolek"));
                    start.doClick();
                }
            }
        );

        wyborTabeli.addActionListener(
            new ActionListener() {
                public void actionPerformed(ActionEvent e){
                    tablica = (String) wyborTabeli.getSelectedItem();
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
        lewy.add(bazaButton);
        lewy.add(Box.createRigidArea(new Dimension(0, 10)));
        lewy.add(wybranyPlik);
        lewy.add(Box.createRigidArea(new Dimension(0, 10)));
        lewy.add(Box.createRigidArea(new Dimension(0, 10)));
        lewy.add(zestawNapis);
        lewy.add(Box.createRigidArea(new Dimension(0, 10)));
        lewy.add(wyborTabeli);
        lewy.add(Box.createRigidArea(new Dimension(0, 10)));
        
        prawy.add(Box.createRigidArea(new Dimension(10, 10)));
        prawy.add(napisLista);
        napisLista.setPreferredSize(new Dimension(200,50));
        napisLista.setAlignmentX(Component.LEFT_ALIGNMENT);
        prawy.add(Box.createRigidArea(new Dimension(0, 10)));
        prawy.add(listaGraczyWidok);
        listaGraczyWidok.setAlignmentX(Component.LEFT_ALIGNMENT);
        prawy.add(Box.createRigidArea(new Dimension(0, 10)));
        prawy.add(usun);
        prawy.add(Box.createRigidArea(new Dimension(0, 10)));
        prawy.add(usunAll);
        prawy.add(Box.createRigidArea(new Dimension(0, 10)));
        prawy.add(start);
        prawy.add(Box.createRigidArea(new Dimension(0, 10)));
        prawy.add(adminButton);
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
