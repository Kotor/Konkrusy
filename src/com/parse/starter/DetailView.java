package com.parse.starter;

import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailView extends Activity implements OnGestureListener {
	
	private GestureDetector gDetector;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_view);
		gDetector = new GestureDetector(this);
		Intent intent = getIntent();
		String tytul = intent.getStringExtra("tytul");
		String dataRozpoczecia = intent.getStringExtra("dataRozpoczecia");
		String dataZakonczenia = intent.getStringExtra("dataZakonczenia");
		String obraz = intent.getStringExtra("obraz");
		String opis = intent.getStringExtra("opis");
		String pelnaData = KonkursyHelper.createData(dataRozpoczecia, dataZakonczenia);
		
		TextView tytulTextView = (TextView) this.findViewById(R.id.konkursTytul);
		tytulTextView.setText(tytul);
		
		TextView pelnaDataTextView = (TextView) this.findViewById(R.id.konkursTermin);
		pelnaDataTextView.setText(pelnaData);
		ImageView obrazImageView = (ImageView) this.findViewById(R.id.konkursObraz);
		if (KonkursyHelper.checkURL(obraz)){
        	Log.i("Plik", obraz + "jest ok");
        	DrawableManager obrazDetail = new DrawableManager();
            obrazDetail.fetchDrawableOnThread(obraz, obrazImageView);            
        } else {
        	Log.i("Plik", obraz + "nie jest ok");
        	InputStream ims = null;
            try {
                ims = getBaseContext().getAssets().open("placeholder.png");
            } catch (IOException e) {
                e.printStackTrace();
            }
            // load image as Drawable
            Drawable d = Drawable.createFromStream(ims, null);
            // set image to ImageView
            obrazImageView.setImageDrawable(d);
        }
		TextView opisTextView = (TextView) this.findViewById(R.id.konkursOpis);
		opisTextView.setText(opis);
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detail_view, menu);
		return true;
	}*/
	
	@Override
    public boolean onTouchEvent(MotionEvent me) {
        return gDetector.onTouchEvent(me);
    }
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev){
	    super.dispatchTouchEvent(ev);    
	    return gDetector.onTouchEvent(ev); 
	}
	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent start, MotionEvent finish, float xVelocity, float yVelocity) {
		final int SWIPE_MIN_DISTANCE = 200;
		if (finish.getRawX() - start.getRawX() > SWIPE_MIN_DISTANCE && start.getRawX() < finish.getRawX()) {
			super.onBackPressed();
		} 
		return true;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float arg2,
			float arg3) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
