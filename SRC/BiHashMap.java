package src;
import java.util.*;
public class BiHashMap<K, V> {
    private HashMap<K, V> mapaK;
    private HashMap<V, K> mapaV;

    public BiHashMap(){
        mapaK = new HashMap<K, V>();
        mapaV = new HashMap<V, K>();
    }

    public void put(K klucz, V wartosc){
        mapaK.put(klucz, wartosc);
        mapaV.put(wartosc, klucz);
    }
    
    public K getKey(V wartosc){
        return mapaV.get(wartosc);
    }
    
    public V getValue(K klucz){
        return mapaK.get(klucz);
    }

    public void removeK(K klucz){
        mapaV.remove(mapaK.get(klucz));
        mapaK.remove(klucz);
    }

    public void removeV(V wartosc){
        mapaK.remove(mapaV.get(wartosc));
        mapaV.remove(wartosc);
    }

    public void clear(){
        mapaK.clear();
        mapaV.clear();
    }

    public Iterator<Map.Entry<K, V>> getIterator(){
        return mapaK.entrySet().iterator();
    }
}
