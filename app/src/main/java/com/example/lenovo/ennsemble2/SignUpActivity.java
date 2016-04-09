package com.example.lenovo.ennsemble2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener
{
    public static  final String COMAPANY_REGISTER_URL = "http://10.0.2.2:8000/api/register/mf";

    EditText firstnameEt,lastnameEt,usernameEt,companynameEt,emailEt,passwordEt,conpasswordEt,pincodeEt,addressEt;

    String firstname,username,companyname,email,password,pincode,mobilenumber,lastname,password2;
    ProgressDialog progressDialog;








    Button signupButton;

    public static final String KEY_NAME = " mf_registration[firstName]";
    public static final String KEY_USERNAME = "mf_registration[username]";
    public static final String KEY_COMPANYNAME = "mf_registration[companyName]";
    public  static final String KEY_EMAIL = "mf_registration[email]";
    public static final String KEY_PASSWORD = "mf_registration[plainPassword][first]";
    public  static final String KEY_PINCODE = " mf_registration[pincode]";
    public static final String KEY_MOBILENUMBER = "mf_registration[address]";
    public static final String KEY_LASTNAME = "mf_registration[lastName]";
    public static final String KEY_PASSWORD2 = "mf_registration[plainPassword][second]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        firstnameEt = (EditText) findViewById(R.id.signupFirstNameEt);
        lastnameEt = (EditText) findViewById(R.id.signupLastNameEt);

        usernameEt = (EditText) findViewById(R.id.signupUserNameEt);
        companynameEt = (EditText) findViewById(R.id.signupcompanyNameEt);
        emailEt = (EditText) findViewById(R.id.signupEmailEt);
        passwordEt = (EditText) findViewById(R.id.signupPasswordEt);
        conpasswordEt = (EditText) findViewById(R.id.signupConPasswordEt);
        pincodeEt = (EditText) findViewById(R.id.signupPinCodeEt);
        addressEt = (EditText) findViewById(R.id.signupAddressEt);




        signupButton = (Button) findViewById(R.id.signupBtnForSignup);
        signupButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.signupBtnForSignup:
                registerCompany();
                progressDialog = ProgressDialog.show(SignUpActivity.this,"User Registration","Loading .....");
                progressDialog.show();

                break;
            default:
                break;
        }

    }

    private void registerCompany()
    {


        firstname = firstnameEt.getText().toString().trim();
        lastname = lastnameEt.getText().toString().trim();
        username = usernameEt.getText().toString().trim();
        companyname = companynameEt.getText().toString().trim();
        email = emailEt.getText().toString().trim();
        password = passwordEt.getText().toString().trim();
        pincode = pincodeEt.getText().toString().trim();
        mobilenumber = addressEt.getText().toString().trim();
        password2 = conpasswordEt.getText().toString().trim();



        StringRequest stringRequest = new StringRequest(Request.Method.POST, COMAPANY_REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(SignUpActivity.this, response, Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SignUpActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        // Toast.makeText(MainActivity.this,"fuck of",Toast.LENGTH_SHORT).show();
                    }
                })

        {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put(KEY_NAME,firstname);
                params.put(KEY_USERNAME, username);
                params.put(KEY_COMPANYNAME,companyname);
                params.put(KEY_EMAIL, email);
                params.put(KEY_PASSWORD, password);
                params.put(KEY_PINCODE,pincode);
                params.put(KEY_MOBILENUMBER,mobilenumber);
                params.put(KEY_LASTNAME,lastname);
                params.put(KEY_PASSWORD2,password2);


                return params;


            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
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
