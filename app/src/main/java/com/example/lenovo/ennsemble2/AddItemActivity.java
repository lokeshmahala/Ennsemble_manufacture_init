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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AddItemActivity extends AppCompatActivity  implements View.OnClickListener{


    EditText editText,editText1,editText2,editText3,editText4;
    String ProductName,PinCode,CategoryName,SubCategoryName,SubSubCategoryName;

    public  static  final String ADD_PRODUCT_URL = "http://10.0.2.2:8000/api/add-product";


    public static final String KEY_PRODUCT_NAME = "AddProduct[name]";
    public static final String KEY_PRODUCT_PINCODE = "AddProduct[pincode_targeted]";
    public static final String KEY_PRODUCT_CATEGORY = "AddProduct[category]";
    public static final String KEY_PRODUCT_SUB_CATEGORY = "AddProduct[sub_category]";
    public static final String KEY_PRODUCT_SUB_SUB_CATEGORY  = "AddProduct[sub_sub_category]";

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Button button = (Button) findViewById(R.id.nextButtonForAddItem);

        button.setOnClickListener(this);

        editText = (EditText) findViewById(R.id.productNameEtForAddItem);
        editText1 = (EditText) findViewById(R.id.pincodeEtForAddItem);
        editText2 = (EditText) findViewById(R.id.categoryNameForAddItem);
        editText3 = (EditText) findViewById(R.id.subCategoryForAddItem);
        editText4 = (EditText) findViewById(R.id.subsubCategoryNameForAddItem);





    }

    @Override
    public void onClick(View v)
    {

        switch (v.getId())
        {
            case R.id.nextButtonForAddItem:



                progressDialog = ProgressDialog.show(AddItemActivity.this,"Product Submission","Waiting....");
                progressDialog.show();


                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(AddItemActivity.this);


              //  Toast.makeText(getApplicationContext(),string,Toast.LENGTH_LONG).show();
                boolean IsLogin = pref.getBoolean("IsLogin", false);

                boolean islogin = pref.getBoolean(Config.SHARED_PREF_LOGGED_IN,false);



                if (IsLogin)
                {
                    AddProduct();
                }
                else
                {
                    Intent intent1 = new Intent(getApplicationContext(),LoginActivity.class);
                    startActivity(intent1);

                }


                SharedPreferences sharedPreferences = getSharedPreferences("AddItem", Context.MODE_WORLD_WRITEABLE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                ProductName = editText.getText().toString().trim();
                PinCode = editText1.getText().toString().trim();
                CategoryName = editText2.getText().toString().trim();
                SubCategoryName = editText3.getText().toString().trim();
                SubSubCategoryName = editText4.getText().toString().trim();
                editor.putString("ProductName",ProductName);
                editor.putString("CompanyName",PinCode);
                editor.putString("CategoryName",CategoryName);
                editor.putString("SubCategoryName", SubCategoryName);
                editor.putString("SubSubCategoryName",SubSubCategoryName);
                editor.commit();





                //sharedPreferences.edit().putString("ProductName",editText.getText().toString()).commit();


                break;
            default:
                break;
        }




    }

    private void AddProduct()
    {


        ProductName = editText.getText().toString().trim();
        PinCode = editText1.getText().toString().trim();
        CategoryName = editText2.getText().toString().trim();
        SubCategoryName = editText3.getText().toString().trim();
        SubSubCategoryName = editText4.getText().toString().trim();



        StringRequest stringRequest = new StringRequest(Request.Method.POST, ADD_PRODUCT_URL,
                new Response.Listener<String>()


                {
                    @Override
                    public void onResponse(String response)
                    {


                        progressDialog.dismiss();

                        String keyStatus = null ,keyMsg = null ,keyId=null;
                        String valuesStatus,valuseMsg,valuesId=null;

                        Toast.makeText(getApplicationContext(),response.toString(),Toast.LENGTH_LONG).show();






                          try
                        {


                            JSONObject jsonObject = new JSONObject(response);
                            Iterator iterator = jsonObject.keys();

                            while (iterator.hasNext())
                            {
                                keyStatus = (String) iterator.next();
                                keyMsg = (String) iterator.next();
                                keyId = (String) iterator.next();

                                valuesStatus = jsonObject.getString(keyStatus);
                                valuseMsg = jsonObject.getString(keyMsg);
                                valuesId = jsonObject.getString(keyId);



                            }





                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        Toast.makeText(getApplicationContext(), valuesId, Toast.LENGTH_LONG).show();

                        SharedPreferences sharedPreferencesforProductId = AddItemActivity.this.getSharedPreferences(Config.SHARED_PREF_NAME_FOR_PRODUCT_ID,Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferencesforProductId.edit();

                        editor.putString(Config.SHARED_PREF_PRODUCT_ID,valuesId);
                        editor.commit();



                        Intent intent = new Intent(getApplicationContext(),AddHeadingDesc.class);

                        startActivity(intent);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(AddItemActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                        // Toast.makeText(MainActivity.this,"fuck of",Toast.LENGTH_SHORT).show();
                    }
                })



        {
            protected Map<String,String> getParams() throws AuthFailureError
            {
                Map<String,String> param = new HashMap<>();

                param.put(KEY_PRODUCT_NAME,ProductName);
                param.put(KEY_PRODUCT_PINCODE,PinCode);
                param.put(KEY_PRODUCT_CATEGORY,CategoryName);
                param.put(KEY_PRODUCT_SUB_CATEGORY,SubCategoryName);
                param.put(KEY_PRODUCT_SUB_SUB_CATEGORY,SubSubCategoryName);

                //param.put("Content-Type:", "application/x-www-form-urlencoded");

                return param;
            }

        };









        RequestQueue requestQueue = Volley.newRequestQueue(AddItemActivity.this);
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

