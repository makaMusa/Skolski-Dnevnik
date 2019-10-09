package com.example.sviostali.sk_dnevnik;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.sviostali.sk_dnevnik.sugarclasses.usersugar;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetUsersFromJSON extends AppCompatActivity {

    public String url = "https://api.github.com/users";
    public RequestQueue requestQueue;
    public Context context;
    public GetUsersFromJSON(Context context) {
        this.context = context;
    }

    public void getData(){
        requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    String login;
                    String avatar;
                    usersugar user;
                    for(int i = 0;i<jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        login = jsonObject.getString("login");
                        avatar = jsonObject.getString("avatar_url");
                        user = new usersugar(login,"1234", avatar,"firstname","lastname","01.01.2000.",0);
                        user.save();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(stringRequest);
    }
}
