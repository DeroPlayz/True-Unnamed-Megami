package lib;

import java.util.ArrayList;

public class Marray {
    private ArrayList<Object> List = new ArrayList<>();
    public int length = List.size();
    public Marray(Object[] o){
        for(int i = 0; i < o.length; i++){
            List.add(o[i]);
        }
    }
    public Marray(){
    }

    public Object get(int index){
        return List.get(index);
    }

    public void insert(Object o, int i){
        ArrayList<Object> temp1 = new ArrayList<>();
        ArrayList<Object> temp2 = new ArrayList<>();

        for(int j = 0; j < i; j++){
            temp1.add(List.get(j));
        }
        for(int j = 0; j < List.size(); j++){
            temp2.add(List.get(j));
        }

        List.clear();
        for(int j = 0; j < temp1.size(); j++){
            List.add(temp1.get(j));
        }

        List.add(o);

        for(int j = 0; j < temp2.size(); j++){
            List.add(temp2.get(j));
        }
    }
}
