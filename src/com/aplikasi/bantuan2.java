package com.aplikasi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class bantuan2 extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.bantuan2);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.menu_back, menu);
		return super.onCreateOptionsMenu(menu);
	}
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case R.id.back:
		back();
		return true;
		default:
		return super.onOptionsItemSelected(item);
		}
		}
	//menutup form bantuan2
	public void back(){
		bantuan2.this.finish();
			}
	
	//memanggil form penjelasan
		public void penjelasan(){
			Intent i=new Intent(this,penjelasan.class);
			startActivity(i);
				}
}
