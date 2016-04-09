package com.example.lenovo.ennsemble2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Fragment1 extends Fragment implements View.OnClickListener {


    public Fragment1() {
        // Required empty public constructor
    }



    public static Fragment1 newInstance(String param1, String param2) {
        Fragment1 fragment = new Fragment1();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragment1, container, false);

        Button additem = (Button) view.findViewById(R.id.additemBtnatHome);
        Button aboutus = (Button) view.findViewById(R.id.aboutusBtnHome);

        additem.setOnClickListener(this);
        aboutus.setOnClickListener(this);


        return view;
    }


    @Override
    public void onClick(View v)
    {

        switch (v.getId())
        {
            case R.id.additemBtnatHome:
                Intent intent2 = new Intent(getActivity(),AddItemActivity.class);
                startActivity(intent2);
                break;

            case R.id.aboutusBtnHome:
                Intent intent5 = new Intent(getActivity(),AboutUsAcitivity.class);
                startActivity(intent5);

        }



    }
}
