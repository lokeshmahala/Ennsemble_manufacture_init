package com.example.lenovo.ennsemble2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class HeadFragment1 extends Fragment {

    Button addmore1,submit;
    EditText heading1et,description1et;
    String heading1,description1;

    public static final String KEY_HEADING1 = "AddProductDescription[heading][]";
    public static final String KEY_DESCRIPTION1 = "AddProductDescription[description][]";

    public static String  ADD_PRODUCT_DESC_FINAL_URL = null;


    public String productId;

    public static HeadFragment1 newInstance(String param1, String param2) {
        HeadFragment1 fragment = new HeadFragment1();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_head_fragment1, container, false);

       // addmore1 = (Button) view.findViewById(R.id.AddMoreBtnHeadFragment1);
       //  submit = (Button) view.findViewById(R.id.submitBtnHeadFragment1);
      //   submit.setOnClickListener(this);
        // addmore1.setOnClickListener(this);


            heading1et = (EditText) view.findViewById(R.id.headingEtForHeadFragment1);
           description1et = (EditText) view.findViewById(R.id.DescEtForHeadFragment1);


        return view;

    }








}
