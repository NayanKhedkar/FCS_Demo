package com.example.nayankhedkar.myapplication;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {
    private  final static String TAG="FCM Token Reseng";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button getRefreshTokenBtn = (Button)findViewById(R.id.token_send);
        getRefreshTokenBtn.setOnClickListener(new View.OnClickListener() {
            AppFirebaseInstanceIDService appFirebaseInstanceIDService = new AppFirebaseInstanceIDService();
            public void onClick(View v) {
                Log.d(TAG,"on Refresh token clicked");
                try{
                    String refreshedToken =  appFirebaseInstanceIDService.getRefreshToken();
                    appFirebaseInstanceIDService.sendRegistrationToServer(refreshedToken);
                }catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        });
    }
}
