package src;

import javax.swing.*;

import org.sqlite.date.DateFormatUtils;

import java.util.*;
public class leaderboard extends JList<String> {
    private DefaultListModel<String> lista;
    private DefaultListModel<Gracz> listaG;
    private boolean czyzWynikiem;
    
    public leaderboard(DefaultListModel<Gracz> listaGraczy, boolean czyzWynikiem){
        listaG = listaGraczy;
        this.czyzWynikiem = czyzWynikiem;
        this.lista = new DefaultListModel<String>();
        for(int i = 0; i < listaG.size(); i++){
            String wstaw = listaG.get(i).toString();
            if(czyzWynikiem){
                wstaw += " " + listaG.get(i).getWynik();
            }
            lista.addElement(wstaw);
        }
        this.setModel(lista);
        if(czyzWynikiem){
            this.setSelectionModel(new DefaultListSelectionModel(){
                public void setSelectionInterval(int i, int j){
                    //tu ma nic nie byc, nie pozwala na wybieranie elementow z listy;
                }
            });
        }
    }

    public Gracz getGracz(int index){
        return listaG.get(index);
    }

    public void refresh(){
        lista.clear();
        for(int i = 0; i < listaG.size(); i++){
            String wstaw = listaG.get(i).toString();
            if(czyzWynikiem){
                wstaw += " " + listaG.get(i).getWynik();
            }
            lista.addElement(wstaw);
        }
    }

    public static void sortDefaultListModel(DefaultListModel<Gracz> l){
        ArrayList<Gracz> lg = new ArrayList<>();
        for(int i = 0; i < l.size(); i++){
            lg.add(l.get(i));
        }

        Collections.sort(lg);
        l.clear();

        for(int i = 0; i < lg.size(); i++){
            l.addElement(lg.get(i));
        }
    }
}
