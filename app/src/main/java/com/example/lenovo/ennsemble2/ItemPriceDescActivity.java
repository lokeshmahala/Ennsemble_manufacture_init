package com.example.lenovo.ennsemble2;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemPriceDescActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener{


    EditText heading0et,description0et;
    String price;
    String quantity;
    String quantityMode;


    public static final String KEY_PRODUCT_PRICE ="AddProductPricing[price]";
    public static final String KEY_PRODUCT_QUANTITY = "AddProductPricing[quantity]";
    public static final String KEY_PRODUCT_QUANTITY_MODE = "AddProductPricing[quantity_type]";



    int max_editext = 4,num_edittext = 1;


    public String productId,SpinnerValue;


    String  ADD_PRODUCT_PRICE_QUANTITY_FINAL_URL;



    Button addmore,submit;

    Spinner spinner;



    List<String> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_price_desc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        EditText editText = new EditText(this);
        editText.setHint("Price");
        editText.setHeight(100);
        EditText editText1 = new EditText(this);
        editText1.setHint("Quantity");
        editText1.setHeight(100);


        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(0, 0, 0, 60);
        editText1.setLayoutParams(layoutParams);
        editText.setLayoutParams(layoutParams);



        list.add("Kilogram");
        list.add("Litre");
        list.add("Metre");
        list.add("Piece");






        editText.setId(10 + 0);
        editText1.setId(100 + 0);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layoutContainer);

        LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.layoutContainer1);

        // LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.containerFragment1);
        linearLayout.addView(editText);
        linearLayout1.addView(editText1);





        LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.layoutContainer2);

        spinner = new Spinner(this,Spinner.MODE_DIALOG);
        spinner.setPrompt("Quantity Mode");
        spinner.setId(1000+0);

        spinner.setOnItemSelectedListener(this);
        linearLayout2.addView(spinner);



        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list);
        spinner.setAdapter(arrayAdapter);
        spinner.setLayoutParams(layoutParams);


        submit = (Button) findViewById(R.id.submitBtbForItem_price_desc_activity);
        addmore = (Button) findViewById(R.id.addmoreBtbForItem_price_desc_activity);
        addmore.setOnClickListener(this);


        submit.setOnClickListener(this);

    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.addmoreBtbForItem_price_desc_activity:



                if (max_editext > num_edittext)
                {

                    // AddPRoductDesc0();

                    EditText editText = new EditText(this);
                    EditText editText1 = new EditText(this);

                    editText.setHint("Price");
                    editText1.setHint("Quantity");

                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);

                    layoutParams.setMargins(0, 0, 0, 60);
                    editText1.setLayoutParams(layoutParams);
                    editText.setLayoutParams(layoutParams);

                    editText.setId(10 + num_edittext);
                    editText1.setId(100 + num_edittext);



                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.layoutContainer);
                    LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.layoutContainer1);
                    LinearLayout linearLayout2 = (LinearLayout) findViewById(R.id.layoutContainer2);

                     spinner  = new Spinner(this,Spinner.MODE_DIALOG);
                    spinner.setPrompt("Quantity Mode");

                    spinner.setOnItemSelectedListener(this);
                    spinner.setLayoutParams(layoutParams);
                    spinner.setId(1000+num_edittext);



                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,list);
                    spinner.setAdapter(arrayAdapter);



                    // LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.containerFragment1);
                    linearLayout.addView(editText);
                    linearLayout1.addView(editText1);
                    linearLayout2.addView(spinner);
                    // linearLayout1.addView(editText1);







                    num_edittext +=1;



                }
                else
                {
                    Toast.makeText(getApplicationContext(),"sddfsf",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.submitBtbForItem_price_desc_activity:
                AddProductPriceQuantityDesc();
        }



    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
    {
        SpinnerValue = spinner.getSelectedItem().toString();
        switch (view.getId())
        {

        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void AddProductPriceQuantityDesc()
    {

        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME_FOR_PRODUCT_ID, Context.MODE_WORLD_READABLE);
        productId = sharedPreferences.getString(Config.SHARED_PREF_PRODUCT_ID, "");

        ADD_PRODUCT_PRICE_QUANTITY_FINAL_URL = Config.ADD_PRODUCT_PRICE_QUANTITY_URL + productId;

        Toast.makeText(getApplicationContext(),ADD_PRODUCT_PRICE_QUANTITY_FINAL_URL,Toast.LENGTH_LONG).show();



        StringRequest stringRequest = new StringRequest(Request.Method.POST, ADD_PRODUCT_PRICE_QUANTITY_FINAL_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {

                        Toast.makeText(ItemPriceDescActivity.this, response, Toast.LENGTH_LONG).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ItemPriceDescActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                        // Toast.makeText(MainActivity.this,"fuck of",Toast.LENGTH_SHORT).show();
                    }
                })

        {
            protected Map<String,String > getParams()
            {


                Map<String,String> param = new HashMap<>();

                ;

                for (int i=0;i<num_edittext;i++)
                {

                    Log.e("gjhg", "msg" + i);
                    int id = 10+i;
                    EditText headingObj =  (EditText) findViewById(id);
                    price = headingObj.getText().toString();

                    EditText descriptionObj =  (EditText) findViewById(100 + i);
                    quantity = descriptionObj.getText().toString();



                    param.put(KEY_PRODUCT_PRICE+"[" + i + "]",price);
                    param.put(KEY_PRODUCT_QUANTITY + "[" + i + "]" , quantity);
                    param.put(KEY_PRODUCT_QUANTITY_MODE + "[" + i + "]" ,SpinnerValue);




                }

                return  param;

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
