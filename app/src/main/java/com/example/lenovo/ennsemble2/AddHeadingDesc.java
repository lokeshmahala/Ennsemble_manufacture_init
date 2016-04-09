package com.example.lenovo.ennsemble2;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class AddHeadingDesc extends AppCompatActivity implements View.OnClickListener{

    Button addmore,submit1;
    EditText heading0et,description0et;
    String heading;
    String description;


    public static final String KEY_HEADING0 = "AddProductDescription[heading]";
    public  static final String KEY_DESCRIPTION0 = "AddProductDescription[description]";

    public static String  ADD_PRODUCT_DESC_FINAL_URL = null;

    int max_editext = 16,num_edittext = 1;


    public String productId;

    String id,id1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_heading_desc);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        EditText editText = new EditText(this);
        editText.setHint("Heading");
        EditText editText1 = new EditText(this);
        editText1.setHint("Description");

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);

        layoutParams.setMargins(0, 0, 0, 50);
        editText1.setLayoutParams(layoutParams);






        editText.setId(10 + 0);





        editText1.setId(100 + 0);

        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.containerFragment);

        // LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.containerFragment1);
        linearLayout.addView(editText);
        linearLayout.addView(editText1);






        addmore = (Button) findViewById(R.id.AddMoreBtnMain);
        submit1 = (Button) findViewById(R.id.submitBtnMain);

        addmore.setOnClickListener(this);
        submit1.setOnClickListener(this);






    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.AddMoreBtnMain:


                if (max_editext > num_edittext)
                {

                   // AddPRoductDesc0();

                    EditText editText = new EditText(this);
                    EditText editText1 = new EditText(this);

                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);

                    layoutParams.setMargins(0, 0, 0, 50);
                    editText1.setLayoutParams(layoutParams);






                    editText.setId(10 + num_edittext);
                    editText1.setId(100 + num_edittext);

                    id= String.valueOf(editText.getId());
                    id1 = String.valueOf(editText1.getId());

                    LinearLayout linearLayout = (LinearLayout) findViewById(R.id.containerFragment);

                   // LinearLayout linearLayout1 = (LinearLayout) findViewById(R.id.containerFragment1);
                    linearLayout.addView(editText);
                    linearLayout.addView(editText1);
                   // linearLayout1.addView(editText1);

                    Toast.makeText(getApplicationContext(), id + "    " +id1,Toast.LENGTH_LONG ).show();





                    num_edittext +=1;



                }
                else
                {
                    Toast.makeText(getApplicationContext(),"sddfsf",Toast.LENGTH_LONG).show();
                }



                //HeadFragment1 headFragment2 = (HeadFragment1) getSupportFragmentManager().findFragmentById(R.id.headfragment);
                //headFragment2.AddProductDesc1();











                //   Toast.makeText(getApplicationContext(),heading0,Toast.LENGTH_LONG).show();
             /*   android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
               HeadFragment1 headFragment1 = new HeadFragment1();
                fragmentTransaction.add(R.id.containerFragment, headFragment1, "");

                //  headFragment1.AddProductDesc1();







                fragmentTransaction.commit();*/

                //  addmore.setVisibility(View.GONE);
                // submit1.setVisibility(View.GONE);
                break;
            case R.id.submitBtnMain:
                AddPRoductDesc0();

                Intent intent = new Intent(getApplicationContext(),ItemPriceDescActivity.class);
                startActivity(intent);
                break;



        }


    }

    private void AddPRoductDesc0()
    {










        SharedPreferences sharedPreferences = getSharedPreferences(Config.SHARED_PREF_NAME_FOR_PRODUCT_ID, Context.MODE_WORLD_READABLE);
        productId = sharedPreferences.getString(Config.SHARED_PREF_PRODUCT_ID, "");

        ADD_PRODUCT_DESC_FINAL_URL = Config.ADD_PRODUCT_DESC_URL +  productId;



        StringRequest stringRequest = new StringRequest(Request.Method.POST, ADD_PRODUCT_DESC_FINAL_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {

                        Toast.makeText(AddHeadingDesc.this, response, Toast.LENGTH_LONG).show();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddHeadingDesc.this,error.toString(),Toast.LENGTH_LONG).show();
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

                    Log.e("gjhg","msg"+i );
                    int id = 10+i;
                   EditText headingObj =  (EditText) findViewById(id);
                   heading = headingObj.getText().toString();

                    EditText descriptionObj =  (EditText) findViewById(100 + i);
                     description = descriptionObj.getText().toString();



                   param.put(KEY_HEADING0+"["+i+ "]",heading);
                    param.put(KEY_DESCRIPTION0 + "[" + i + "]" , description);




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
