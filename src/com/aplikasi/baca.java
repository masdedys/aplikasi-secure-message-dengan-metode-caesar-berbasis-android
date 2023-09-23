package com.aplikasi;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class baca extends Activity {
	private Button dekripsi;
	String hasil, cipher, plain, key;
	EditText cipherText, keyText, plainText,noPengirim;
	int keys;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		super.onCreate(savedInstanceState);
		 setContentView(R.layout.bacapesan);
		
		 dekripsi = (Button) findViewById(R.id.dekrip_button);
		 plainText = (EditText) findViewById(R.id.hasil2);
		 cipherText = (EditText) findViewById(R.id.pesan2);
		 keyText = (EditText) findViewById(R.id.kunci2);
		 noPengirim=(EditText)findViewById(R.id.noPengirim);
		 cipherText.setFocusable(false);
		 plainText.setFocusable(false);
		 
		    
		//operasi button dekripsi
		 dekripsi.setOnClickListener(new Button.OnClickListener() {
		    	@Override
		    	public void onClick(View v) {
		    		dekrip();
		    	}
		    });
	}
	protected void onStart() {
		super.onStart();
		Intent i = getIntent();
		noPengirim.setText(i.getStringExtra("no"));
		cipherText.setText(i.getStringExtra("msg"));
	}
	//memanggil menu_bantu sebagai menu dropdown
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater mi = getMenuInflater();
		mi.inflate(R.menu.menu_bantu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	//case menu dropdown
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId()){
		case R.id.back:
		back();
		return true;
		case R.id.bantuan:
		bantuan();
		return true;
		default:
		return super.onOptionsItemSelected(item);
		}
		}
	//menutup form baca
			private void back(){
				reset();
				baca.this.finish();
			}
	//memanggil form bantuan
				public void bantuan(){
					Intent i=new Intent(this,bantuan.class);
					startActivity(i);
						}
	//mereset semua tulisan
				private void reset(){
					plainText.setText("");
					cipherText.setText("");
					keyText.setText("");
                    noPengirim.setText("");
				}			
	//mereset input key saja 
				private void resetkey(){
					keyText.setText("");
				}
				
				
	private void dekrip() {
		hasil = "";
		key = keyText.getText().toString();
		keys = Integer.parseInt(key);
		cipher = cipherText.getText().toString();
		
		if (keys<0){
			 Toast.makeText(getBaseContext(),"Maaf, Kunci Terlalu kecil untuk sistem", Toast.LENGTH_SHORT).show();
			 resetkey();
			 }
		else if (keys>256){
			 Toast.makeText(getBaseContext(),"Maaf, Kunci Terlalu besar untuk sistem", Toast.LENGTH_SHORT).show();
			 resetkey();
			 }
		else if (cipher.length() > 160){
			Toast.makeText(getBaseContext(),"Maaf, Pesan Terlalu panjang untuk sistem", Toast.LENGTH_SHORT).show();
			back();
		 }
		else{
			// proses dekripsi metode caesar
			for (int r = 0; r < cipher.length(); r++) {
				int p = cipher.charAt(r);
				p = (p - keys);
				p =  p % 256;	
				if (p < 0)
					p = p + 256;
				hasil = hasil + (char) p;
			}
		cipherText.setText(cipher);
		plainText.setText(hasil);
	}
}
}
