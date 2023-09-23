package com.aplikasi;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {
	Button TulisPesan,info,Inbox,Tutup;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		TulisPesan=(Button)findViewById(R.id.tulis);
		info=(Button)findViewById(R.id.info);
		Inbox=(Button)findViewById(R.id.inbox);
		Tutup=(Button)findViewById(R.id.close);
		
		//operasi button TulisPesan
		TulisPesan.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				tulis();
			}
		});
		
		//operasi button info
		info.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				info();
			}
		});
		
		//operasi button inbox
		Inbox.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				inbox();
			}
		});
		
		//operasi button Tutup
		Tutup.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v){
				tutup();
			}
		});
	}
	@Override
	//memanggil menu_penjelasan_bantuan sebagai menu dropdown
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.menu_penjelasan_bantuan, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	//case menu dropdown
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case R.id.bantuan:
		bantuan();
		return true;
		case R.id.Penjelasan:
		penjelasan();
		return true;
		default:
		return super.onOptionsItemSelected(item);
		}
		}

	//memanggil form tulis
	public void tulis(){
		Intent i=new Intent(this,tulis.class);
		startActivity(i);
			}
	//memanggil form bantuan
		public void bantuan(){
			Intent i=new Intent(this,bantuan.class);
			startActivity(i);
				}
	//memanggil form penjelasan
		public void penjelasan(){
			Intent i=new Intent(this,penjelasan.class);
			startActivity(i);
				}
	
	//memanggil form info
	public void info(){
		Intent i=new Intent(this,info.class);
		startActivity(i);
			}
	
	//memanggil Inbox
	public void inbox(){
		Intent click = new Intent(MainActivity.this,
				Inbox.class);
		click.putExtra("tipepesan", "inbox");
		startActivity(click);
			}
	
	//menutup aplikasi
	public void tutup(){
		//TODO Auto-generate method stub
				AlertDialog.Builder ad=new Builder(this);
				ad.setTitle("Keluar aplikasi");
				ad.setMessage("anda yakin ingin keluar ?");
				ad.setPositiveButton("Ya", new 
						DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						MainActivity.this.finish();
					}
				});
				ad.setNegativeButton("Tidak", new OnClickListener(){
					@Override
					public void onClick(DialogInterface dialog,int which){
						//TODO Auto-generate method stub 
						dialog.dismiss();
					}
				});
					ad.show();
	}
	}
	

