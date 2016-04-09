package com.example.lenovo.ennsemble2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LoginActivity extends AppCompatActivity  implements View.OnClickListener{

    public static final String companyLoginURL = "http://10.0.2.2:8000/login_check";
    EditText useridEt,passwordEt;
    String username,password;
    public static final String KEY_USERID ="_username";
    public static  final String KEY_PASSWORD = "_password";

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button button = (Button) findViewById(R.id.loginBtnforLogin);
        button.setOnClickListener(this);

        useridEt = (EditText) findViewById(R.id.userIdEtforLogin);
        passwordEt = (EditText) findViewById(R.id.PasswordEtforlogin);

        CookieHandler.setDefault(new CookieManager());





    }

    @Override
    public void onClick(View v)
    {

        switch (v.getId())
        {
            case R.id.loginBtnforLogin:

                companyLogin();

              //  Intent intent = new Intent(getApplicationContext(),MainPage1.class);
               // startActivity(intent);

                progressDialog = ProgressDialog.show(LoginActivity.this,"Company Login","Loadin...");
              progressDialog.show();






                break;
            default:
                break;

        }


    }

    private void companyLogin()
    {
        username = useridEt.getText().toString().trim().toLowerCase();
        password = passwordEt.getText().toString().trim().toLowerCase();


        StringRequest stringRequest = new StringRequest(Request.Method.POST, companyLoginURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                String key = null,key1 = null,values = null,values1 = null;

                try
                {
                    JSONObject jsonObject = new JSONObject(response);
                    Iterator iterator = jsonObject.keys();



                    while (iterator.hasNext())
                    {
                        key = (String) iterator.next();
                        key1 = (String) iterator.next();
                        values = jsonObject.getString(key);
                        values1 = jsonObject.getString(key1);
                    }
                }
                catch (JSONException e)
                {

                    e.printStackTrace();
                }



                if (values.equals("200")& values1.equals("success") )
                {
                    progressDialog.dismiss();

                    SharedPreferences sharedPreferences = LoginActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();

                    editor.putBoolean(Config.SHARED_PREF_LOGGED_IN, true);
                    editor.putString(Config.SHARED_PREF_USERNAME, username);
                    editor.commit();



                    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                    pref.edit().putBoolean("IsLogin",true).commit();














                    Intent intent = new Intent(getApplicationContext(),MainPage1.class);
                    startActivity(intent);

                }








            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();


            }
        })
        {

            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<>();



                // Toast.makeText(getApplicationContext(),username+password,Toast.LENGTH_LONG).show();

                map.put(KEY_USERID,username);
                map.put(KEY_PASSWORD,password);
                //map.put("Content-Type","application/x-www-form-urlencoded");
                return map;

            }



          /*  @Override
            public String getBodyContentType()
            {
                return "application/x-www-form-urlencoded";
            }
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError
            {
                Map<String,String> headers = new HashMap<>();



                headers.put("Content-Type","application/x-www-form-urlencoded");
                return headers;
            }*/



        };






        RequestQueue requestQueue = Volley.newRequestQueue(this);
        //    requestQueue.add(stringRequest);
        requestQueue.add(stringRequest);

        stringRequest.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 50000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 50000;
            }

            @Override
            public void retry(VolleyError error) throws VolleyError {

            }
        });


    }


    }

