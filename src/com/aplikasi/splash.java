package com.aplikasi;

import com.aplikasi.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;

public class splash extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.splashscreen);
        //menjalankan splash screen dan menu menggunakan delayed thread
        new Handler().postDelayed(new Thread() {
            @Override
            public void run() {
                   Intent mainMenu= new Intent(splash.this,MainActivity.class);
                   splash.this.startActivity(mainMenu);
                   splash.this.finish();
            }
    }, 3000);
    }
}
