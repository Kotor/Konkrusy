package com.parse.starter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class ParseStarterProjectActivity extends Activity implements Runnable {
    ListView listView ;
    
	//A ProgressDialog View
  	private ProgressDialog progressDialog;
  	//A thread, that will be used to execute code in parallel with the UI thread
  	private Thread thread;
  	//Create a Thread handler to queue code execution on a thread
  	private Handler handler;
        
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        progressDialog = ProgressDialog.show(ParseStarterProjectActivity.this,"£adujê...",  
			    "Trwa ³adowanie konkursów", false, false); 
		
		//Initialize the handler
        handler = new Handler();
        //Initialize the thread
        thread = new Thread(this, "ProgressDialogThread");
        //start the thread
        thread.start();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ((ListView) findViewById(R.id.list)).setBackgroundColor(0);
        
        
    }
        
    int counter = 0;
    
	@Override
	public void run() 
	{
		try 
		{
			//Obtain the thread's token
			synchronized (thread) 
			{
				//While the counter is smaller than four
				while(counter <= 4)
				{
					//Wait 850 milliseconds
					thread.wait(850);
					//Increment the counter 
					counter++;
					
					//update the changes to the UI thread
					handler.post(new Runnable() 
					{
						@Override
						public void run() 
						{
							//Set the current progress. 
							progressDialog.setProgress(counter*25);
						}
					});
				}
			}
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
		
		//This works just like the onPostExecute method from the AsyncTask class
		handler.post(new Runnable() 
		{
			@Override
			public void run() 
			{
				//Close the progress dialog
				progressDialog.dismiss();
				Model.LoadModel();
		        
		        listView = (ListView) findViewById(R.id.list);
		        
		        String[] ids = new String[Model.Items.size()];
		        for (int i= 0; i < ids.length; i++){

		            ids[i] = Integer.toString(i);
		        }
		        Log.i("Activity", Integer.toString(ids.length));
		        
		        ItemAdapter adapter = new ItemAdapter(getBaseContext(),R.layout.row, ids);
		        listView.setAdapter(adapter);
		        listView.setOnItemClickListener(new OnItemClickListener() {

		              @Override
		              public void onItemClick(AdapterView<?> parent, View view,
		                 int position, long id) {
		              
		            	  Item item = Model.GetbyId(position);
		            	  Intent myIntent = new Intent(ParseStarterProjectActivity.this, DetailView.class);
		            	  myIntent.putExtra("tytul", item.Tytul); //Optional parameters
		            	  myIntent.putExtra("dataRozpoczecia", Integer.toString(item.DataRozpoczecia));
		            	  myIntent.putExtra("dataZakonczenia", Integer.toString(item.DataZakonczenia));
		            	  myIntent.putExtra("obraz", item.Obraz);
		            	  myIntent.putExtra("opis", item.Opis);
		            	  ParseStarterProjectActivity.this.startActivity(myIntent);
		              }
		         }); 
				
			}
		});
		
		//Try to "kill" the thread, by interrupting its execution
		synchronized (thread) 
		{
			thread.interrupt();
		}
	}
}