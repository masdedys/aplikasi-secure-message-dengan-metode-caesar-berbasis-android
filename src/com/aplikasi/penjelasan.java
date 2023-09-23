package com.aplikasi;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class penjelasan extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.penjelasan);
	}
	
	@Override
	//memanggil menu_back sebagai menu dropdown
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.menu_back, menu);
		return super.onCreateOptionsMenu(menu);
	}
	//case menu dropdown
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case R.id.back:
		back();
		return true;
		default:
		return super.onOptionsItemSelected(item);
		}
		}
	//menutup form penjelasan
			public void back(){
				penjelasan.this.finish();
					}
}
