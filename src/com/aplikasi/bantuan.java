package com.aplikasi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class bantuan extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.bantuan);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.menu_go_back, menu);
		return super.onCreateOptionsMenu(menu);
	}
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case R.id.go:
		go();
		return true;
		case R.id.back:
		back();
		return true;
		default:
		return super.onOptionsItemSelected(item);
		}
		}
	//memanggil form bantuan2
	public void go(){
		Intent i=new Intent(this,bantuan2.class);
		startActivity(i);
			}
	//menutup form bantuan
	public void back(){
		bantuan.this.finish();
			}
}
