package com.parse.starter;

public class Item {

	public int Id;
    public String Tytul;
    public int DataRozpoczecia;
    public int DataZakonczenia;
    public String Obraz;
    public String Opis;

    public Item(int id, String tytul, int dataRozpoczecia, int dataZakonczenia, String obraz, String opis) {

        Id = id;
        Tytul = tytul;
        DataRozpoczecia = dataRozpoczecia;
        DataZakonczenia = dataZakonczenia;
        Obraz = obraz;
        Opis = opis;

    }

}