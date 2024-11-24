package src;

import javax.swing.*;
import java.io.*;
import java.sql.SQLException;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.event.ActionEvent;

public class SetUpGry extends JPanel {
    private JButton start = new JButton();
    private JButton dodaj = new JButton();
    private JButton usun = new JButton();
    private JTextField nazwa = new JTextField(30);
    private DefaultListModel<Gracz> listaGraczy = new DefaultListModel<Gracz>();
    private JList<Gracz> listaGraczyWidok = new JList<Gracz>(listaGraczy);
    private JLabel napisNaGorze = new JLabel("<html><font size = '7'>Nowa Gra</font></html>");
    private JLabel napisLista = new JLabel("<html><font size = '4'>Lista graczy:</font></html>");
    private JButton anuluj = new JButton();
    private JButton usunAll = new JButton();
    private JLabel napisInfo = new JLabel();
    private JLabel napisNick = new JLabel();
    private JButton adminButton = new JButton();
    private JButton bazaButton = new JButton();
    private JFileChooser jfc = new JFileChooser();
    private String DBFilePath;
    private String tablica;
    private JComboBox<String> wyborTabeli = new JComboBox<String>();
    private ArrayList<String> tabele;
    private File bazaDanych = null;
    private SQLiteDataBaseManager dbmgng = new SQLiteDataBaseManager();
    private JLabel zestawNapis = new JLabel("Wybierz zestaw pytań:");
    private JLabel wybranyPlik = new JLabel("Wybrany plik: ");
    private ArrayList<ArrayList<Pytanie>> listaPytan;
    private String fileFormat = "";
    
    public SetUpGry(Frame frame){
        napisInfo.setVisible(false);
        napisInfo.setForeground(Color.RED);
        napisNick.setText("<html><font size = '4'>Wpisz nick:</font></html>");
        wyborTabeli.setMaximumSize(new Dimension(200,30));
        wyborTabeli.setAlignmentX(Component.LEFT_ALIGNMENT);
        frame.makeButton(bazaButton,"Wybierz pytania" , getForeground(), getBackground(),14);
        frame.makeButton(dodaj, "Dodaj gracza", null, null,14);
        frame.makeButton(start, "Rozpocznij grę", getForeground(), getBackground(),14);
        frame.makeButton(usun,  "Usuń zaznaczony", getForeground(), getBackground(),14);
        frame.makeButton(usunAll, "Wyczyść listę", getForeground(), getBackground(),14);
        listaGraczyWidok.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        nazwa.setColumns(40);
        nazwa.setMaximumSize(new Dimension(400,30));
        frame.makeButton(anuluj, "Anuluj", getForeground(), getBackground(),14);
        listaGraczyWidok.setVisibleRowCount(10);
        napisNaGorze.setHorizontalAlignment(SwingConstants.CENTER);
        frame.makeButton(adminButton, "adminButton", getForeground(), getBackground(),14);
        zestawNapis.setVisible(false);
        wyborTabeli.setVisible(false);
        wybranyPlik.setText("Brak wybranej bazy pytań");

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
                    if (listaGraczy.getSize() < 2){
                        napisInfo.setText("Dodaj conajmniej 2 graczy");
                        napisInfo.setVisible(true);
                    }
                    else if (bazaDanych == null){
                        napisInfo.setText("Wybierz bazę danych z pytaniami");
                        napisInfo.setVisible(true);
                    }
                    else if (fileFormat.endsWith(".db") && wyborTabeli.getSelectedIndex() == -1){
                        napisInfo.setText("Brak odpowiednich zestawów w danej bazie");
                        napisInfo.setVisible(true);
                    }
                    else{
                        napisInfo.setVisible(false);
                        bazaDanych = null;
                        
                        if(fileFormat.endsWith(".db")){
                            listaPytan = dbmgng.getGridPytan(DBFilePath, tablica);
                        }
                        else if(fileFormat.endsWith(".csv")){
                            CsvManager cm = new CsvManager();
                            listaPytan = cm.getGridPytan(DBFilePath, 6, 6);
                        }

                        wybranyPlik.setText("Brak wybranej bazy pytań: ");
                        wyborTabeli.setVisible(false);
                        zestawNapis.setVisible(false);
                        Gra gra = new Gra(frame, listaGraczy, listaPytan, DBFilePath, tablica);
                        frame.addToFrame(gra, "gra");
                        frame.showPanel("gra");
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
                        wyborTabeli.removeAllItems();
                        bazaDanych = jfc.getSelectedFile();
                        DBFilePath = bazaDanych.getAbsolutePath();
                        wybranyPlik.setVisible(true);
                        wybranyPlik.setText("Wybrany plik: \"" + bazaDanych.getName() + "\"");
                        fileFormat = bazaDanych.getName();
                        if(!(fileFormat.endsWith(".db") || fileFormat.endsWith(".csv") )){
                            napisInfo.setVisible(true);
                            napisInfo.setText("Zły format pliku; oczekiwany \".db\" lub \".csv\"");
                        }
                        else{
                            if(fileFormat.endsWith(".db")){
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
                            else if (fileFormat.endsWith(".csv")){
                                zestawNapis.setVisible(false);
                                wyborTabeli.setVisible(false);
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
