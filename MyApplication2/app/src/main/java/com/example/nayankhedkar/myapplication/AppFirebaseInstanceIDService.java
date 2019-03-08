package com.example.nayankhedkar.myapplication;

import android.app.VoiceInteractor;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AppFirebaseInstanceIDService extends FirebaseInstanceIdService{
    private  final static String TAG="FCM Token";
    public  String  refreshedToken = "";
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        try{
            sendRegistrationToServer(refreshedToken);
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getRefreshToken() {
        return this.refreshedToken;
    }

    public void sendRegistrationToServer(String token) throws JSONException {
        // TODO: Implement this method to send any registration to app's server.
        String url = "http://192.199.1.207:4747/sendFcmNotification";
        final String tk = token;
        JSONObject postparams = new JSONObject();
        postparams.put("token", token);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST,
                url,
                postparams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("REST-Response",response.toString());
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Error",error.toString());
                    }
                }

        );
        requestQueue.add(jsonObjectRequest);
    }
}
