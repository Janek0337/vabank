package src;

import javax.swing.*;
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
}
