package src;
import java.io.*;
import java.util.*;
import java.util.Scanner;

public class CsvManager {
    public void writeQuestions(File f, ArrayList<ArrayList<Pytanie>> pytania){
        try{
            FileWriter fw = new FileWriter(f);
            for(int i = 0; i < pytania.size(); i++){
                for(int j = 0; j < pytania.get(i).size(); pytania.get(i).get(j)){
                    Pytanie p = pytania.get(i).get(j);
                    String toWrite = p.getID() + ";" + p.getKategoria() + ";" + addQuotation(p.getTresc()) + ";" + addQuotation(p.getOdpowiedz()) + ";" + p.getWartosc() + "\n";
                    fw.write(toWrite);
                    fw.close();
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public ArrayList<ArrayList<Pytanie>> getGridPytan(String filePath, int nKategorii, int nPytanNaKategorie){
        ArrayList<ArrayList<Pytanie>> pytania = new ArrayList<ArrayList<Pytanie>>();
        try{
            File f = new File(filePath);
            Scanner scanner = new Scanner(f);
            for(int j = 0; j < nKategorii; j++){
                pytania.add(new ArrayList<Pytanie>());
                for(int i = 0; i < nPytanNaKategorie; i++){
                    String[] line = scanner.nextLine().split(";(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
                    Pytanie p = new Pytanie(line[2], line[3], Integer.parseInt(line[0]), line[1], Integer.parseInt(line[4]));
                    pytania.get(j).add(p);
                }
            }
            scanner.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        return pytania;
    }

    private String addQuotation(String s){
        return "\"" + s + "\"";
    }
}
