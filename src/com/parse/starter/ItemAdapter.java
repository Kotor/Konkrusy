package com.parse.starter;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemAdapter extends ArrayAdapter<String> {

    private final Context context;
    private final String[] Ids;
    private final int rowResourceId;

    public ItemAdapter(Context context, int textViewResourceId, String[] objects) {

        super(context, textViewResourceId, objects);

        this.context = context;
        this.Ids = objects;
        this.rowResourceId = textViewResourceId;

    }

    
    
    @Override
    
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(rowResourceId, parent, false);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.imageView);
        TextView textView = (TextView) rowView.findViewById(R.id.textView);
        rowView.setBackgroundResource(R.drawable.selector);
        
        int id = Integer.parseInt(Ids[position]);
        String imageFile = Model.GetbyId(id).Obraz;

        textView.setText(Model.GetbyId(id).Tytul);
              
        if (KonkursyHelper.checkURL(imageFile)){
        	Log.i("Plik", imageFile + "jest ok");
        	DrawableManager obraz = new DrawableManager();
            obraz.fetchDrawableOnThread(imageFile, imageView);            
        } else {
        	Log.i("Plik", imageFile + "nie jest ok");
        	InputStream ims = null;
            try {
                ims = context.getAssets().open("placeholder.png");
            } catch (IOException e) {
                e.printStackTrace();
            }
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            imageView.setImageDrawable(d);
        }
        return rowView;

    }  
    

}