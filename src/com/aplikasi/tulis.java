package com.aplikasi;
import android.app.Activity;
import android.app.PendingIntent;
import com.aplikasi.R;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
//import android.provider.Contacts;
//import android.provider.Contacts.People;
//import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class tulis extends Activity {
	
	public Button Enkrip, Dekrip, sendsms;
	String hasil, cipher, plain, key,no,pesanEnkrip;
	EditText cipherText, keyText, plainText,NoTujuan;
	int keys;
	final static int RQS_PICK_CONTACT = 1;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tulispesan); 
		plainText = (EditText) findViewById(R.id.pesan);
		cipherText = (EditText) findViewById(R.id.sandi);
		keyText = (EditText) findViewById(R.id.kunci);
		NoTujuan=(EditText)findViewById(R.id.NoTujuan);
		Button Enkrip = (Button) findViewById(R.id.enkrip_button);
		Button kirim = (Button) findViewById(R.id.kirim);
		Button contact=(Button) findViewById(R.id.contact);
		Button reset=(Button) findViewById(R.id.reset);
		cipherText.setFocusable(false);
		
		 //operasi button kontak
		contact.setOnClickListener(new View.OnClickListener(){
        	@Override
        	public void onClick(View v) {
        		kontak();
        	}
        });
		//operasi button enkrip
		Enkrip.setOnClickListener(new Button.OnClickListener() {
        	@Override
			public void onClick(View v) { 
				enkrip();
        	}    
        });
		//operasi button kirim
		 kirim.setOnClickListener(new Button.OnClickListener() {
	        	@Override
				public void onClick(View v) {
	        		send();
	        	}
	        });	
		 //operasi button reset
		 reset.setOnClickListener(new Button.OnClickListener() {
	        	@Override
				public void onClick(View v) {
	        		reset();
	        	}
	        });	
			}
	@Override
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
	//menutup form tulis
			public void back(){
				reset();
				tulis.this.finish();
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
		}
	//operasi enkripsi
	 private void enkrip() {
		 	hasil = "";
			key = keyText.getText().toString();
			keys = Integer.parseInt(key);
			plain = plainText.getText().toString();
		 if (plainText.length()>160){
			 Toast.makeText(getBaseContext(),"Maaf, Pesan Terlalu panjang untuk sistem", Toast.LENGTH_SHORT).show();
			 reset();
			 }
		else if (keys<0){
			 Toast.makeText(getBaseContext(),"Maaf, Kunci Terlalu kecil untuk sistem", Toast.LENGTH_SHORT).show();
			 reset();
			 }
		else if (keys>256){
			 Toast.makeText(getBaseContext(),"Maaf, Kunci Terlalu besar untuk sistem", Toast.LENGTH_SHORT).show();
			 reset();
			 }
		else {
			// proses enkripsi metode caesar
			for (int t = 0; t < plain.length(); t++) {
				int p = plain.charAt(t);
				p = (p + keys);
				p = p % 256;
				if (p > 256)
					p = p - 256;
				hasil = hasil + (char) p;
			}
			cipherText.setText(hasil);
			plainText.setText(plain);
		}
 }
		 
	
	 //mengeload kontak
    private void kontak() {
    	//ambil kontak dari daftar kontak dengan intent
		Intent intent = new Intent(Intent.ACTION_PICK,
        ContactsContract.Contacts.CONTENT_URI);
		//memulai activity dari intent baru
        startActivityForResult(intent, RQS_PICK_CONTACT);
	}

  //mengambil nomor dari kontak hp	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	super.onActivityResult(requestCode, resultCode, data);
    	if (requestCode == RQS_PICK_CONTACT && resultCode==Activity.RESULT_OK) {
    		Uri contactData = data.getData();
    		ContentResolver cr = getContentResolver();
    		@SuppressWarnings("deprecation")
			Cursor cur = managedQuery(contactData, null, null, null, null);
    		if (cur.getCount() > 0) {
    			while (cur.moveToNext()) {
    				//ambil id contact
    				String id = cur.getString(cur
    				.getColumnIndex(ContactsContract.Contacts._ID));
    				//cek jumlah nomor pada contact yg dipilih
    				if (Integer
    					.parseInt(cur.getString(cur
    					.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) > 0) {
    						Cursor pCur = cr
    						//query ke SQLite Contact
    						.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
    						null,
    						ContactsContract.CommonDataKinds.Phone.CONTACT_ID
    						+ " = ?", new String[] { id },
    						null);
    			
    						while (pCur.moveToNext()) {
    							//ambil nomor berdasarkan id yang dipilih
    							String nomorHp = pCur
    							.getString(pCur
    							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DATA));
    							NoTujuan.setText(nomorHp);
    						}
    						pCur.close();
    				}
    			}
    		}
    	}
    }
    
    
	//operasi kirim pesan
			private void send() {
				no=NoTujuan.getText().toString();
	    		pesanEnkrip=cipherText.getText().toString();
	    		if(no.length()>0 && pesanEnkrip.length()>0) {
	    			sendSMS(no,pesanEnkrip);
					back();
	    		}
	    		else if (no.length()>0 && hasil.length()==0) {
	    			Toast.makeText(getBaseContext(),"Maaf. Pesan Kosong Isi Terlebih Dahulu", Toast.LENGTH_SHORT).show();
	    		}
	    		else {
	    			Toast.makeText(getBaseContext(),"Maaf. No. Tujuan Kosong Isi Terlebih Dahulu", Toast.LENGTH_SHORT).show();
	    		}
			}
			//metod pengiriman SMS dan Laporan pengiriman
			private void sendSMS(String phoneNumber, String message)
		    {        
				String SENT="SMS_SENT";
				 String DELIVERED = "SMS_DELIVERED";
		    	PendingIntent sentPI=PendingIntent.getBroadcast(this,0,new Intent(SENT),0);
		    	PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,new Intent(DELIVERED), 0);
		    	registerReceiver(new BroadcastReceiver() {
					@Override
					public void onReceive(Context arg0,Intent arg1) {
		    			switch(getResultCode()) {
							case Activity.RESULT_OK:
								Toast.makeText(getBaseContext(),"SMS TERKIRIM", Toast.LENGTH_SHORT).show();
								break;
							case Activity.RESULT_CANCELED:
								Toast.makeText(getBaseContext(),"SMS TIDAK TERKIRIM", Toast.LENGTH_SHORT).show();
			    				break;
							case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
								Toast.makeText(getBaseContext(),"Generic failure", Toast.LENGTH_SHORT).show();
								break;
							case SmsManager.RESULT_ERROR_NO_SERVICE:
								Toast.makeText(getBaseContext(),"No Service", Toast.LENGTH_SHORT).show();
								break;
							case SmsManager.RESULT_ERROR_NULL_PDU:
								Toast.makeText(getBaseContext(),"Null PDU", Toast.LENGTH_SHORT).show();
								break;
							case SmsManager.RESULT_ERROR_RADIO_OFF:
								Toast.makeText(getBaseContext(),"Radio Off", Toast.LENGTH_SHORT).show();
								break;
		    			}
		    		}
		    	}, new IntentFilter(SENT));
		    	
		    	//---when the SMS has been delivered---
		        registerReceiver(new BroadcastReceiver(){
		            @Override
		            public void onReceive(Context arg0,Intent arg1) {
		                switch (getResultCode())
		                {
		                    case Activity.RESULT_OK:
		                        Toast.makeText(getBaseContext(), "SMS delivered", 
		                                Toast.LENGTH_SHORT).show();
		                        break;
		                    case Activity.RESULT_CANCELED:
		                        Toast.makeText(getBaseContext(), "SMS not delivered", 
		                                Toast.LENGTH_SHORT).show();
		                        break;                        
		                }
		            }
		        }, new IntentFilter(DELIVERED));  
		    	
		    	SmsManager sms=SmsManager.getDefault();
		    	sms.sendTextMessage(phoneNumber,null, message, sentPI, deliveredPI);
		    	//ArrayList<String> parts = sms.divideMessage(message);
		    	//sms.sendMultipartTextMessage(phoneNumber, null, parts, null, null);  
		    }
	} 	        	    

