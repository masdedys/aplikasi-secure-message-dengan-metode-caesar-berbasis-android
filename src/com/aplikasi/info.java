package com.aplikasi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import com.aplikasi.R;

public class info extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//set info.xml sebagai display
		 setContentView(R.layout.info);
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
	//menutup form tentang
			public void back(){
				info.this.finish();
					}
	//memanggil form penjelasan
		public void penjelasan(){
			Intent i=new Intent(this,penjelasan.class);
			startActivity(i);
				}
	//memanggil form bantuan
		public void bantuan(){
			Intent i=new Intent(this,bantuan.class);
			startActivity(i);
				}

}

