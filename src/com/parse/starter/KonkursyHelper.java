package com.parse.starter;

public class KonkursyHelper {
	public static String createData(String data1, String data2)
	{
		//String d11 = data1.substring(0, 4);
		String d12 = data1.substring(4,6);
		String d13 = data1.substring(6,8);
		//String d21 = data2.substring(0, 4);
		String d22 = data2.substring(4,6);
		String d23 = data2.substring(6,8);
		String[] miesiace={"","stycznia", "lutego", "marca", "kwietnia", "maja", "czerwca", "lipca", "sierpnia", "wrzeúnia", "paüdziernika", "listopada", "grudnia"};
		String data;
		if(d12.equals(d22))
		{
			data = "Czas trwania: od "+Integer.parseInt(d13)+" do "+Integer.parseInt(d23)+" "+miesiace[Integer.parseInt(d12)];
		}
		else 
		{
			data = "Czas trwania: od "+Integer.parseInt(d13)+" "+miesiace[Integer.parseInt(d12)]+" do "+Integer.parseInt(d23)+" "+miesiace[Integer.parseInt(d22)];
		}
		return data;
	}
	
	public static boolean checkURL(String imageFile){
    	String[] rozszerzenie = new String[] { ".jpg", 
                ".png",
                ".jpeg",
                ".gif", 
                ".bmp"
               };
    	for(String roz : rozszerzenie) {
    		if(imageFile.toLowerCase().endsWith(roz)) return true;
    	}
    	return false;
    }
}
