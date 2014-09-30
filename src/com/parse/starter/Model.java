package com.parse.starter;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class Model {

    public static ArrayList<Item> Items;

    public static String[] values;
    
    public static void LoadModel() {
    	
    	Items = new ArrayList<Item>();
    	
    	List<String> listaTytulow = new ArrayList<String>();
        ParseQuery<ParseObject> query = ParseQuery.getQuery("konkursy");
        List<ParseObject> konkursy = null;
        try {
			konkursy = query.find();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			int licznik = 0;
			for(ParseObject konkurs : konkursy) {
				String tytul = konkurs.getString("tytul");
				int dataRozpoczecia = konkurs.getInt("datarozpoczecia");
				int dataZakonczenia = konkurs.getInt("datazakonczenia");
				String obraz = konkurs.getString("obraz");
				String opis = konkurs.getString("opis");
				Items.add(new Item(licznik, tytul, dataRozpoczecia, dataZakonczenia, obraz, opis));
            	Log.i("Tytul", tytul);
            	listaTytulow.add(tytul);
            	licznik++;
            }
		}
        
        String[] values = new String[listaTytulow.size()];
        listaTytulow.toArray(values);
        
        Log.i("Model", Integer.toString(Items.size()));
    }

    public static Item GetbyId(int id){

        for(Item item : Items) {
            if (item.Id == id) {
                return item;
            }
        }
        return null;
    }

}