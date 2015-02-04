package com.example.o.fragmentdemo;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

/**
 * Created by o on 1/30/2015.
 */
public class MyFragment3 extends Fragment implements View.OnClickListener{
    private View convertView;
    RatingBar ratingBar;
    SharedPreferences sPref;
    Button btnSubmit;
    EditText edtComment;
    boolean isDataChanged = false;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        convertView = inflater.inflate(R.layout.fragment3, container, false);
        sPref = getActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        ratingBar = (RatingBar) convertView.findViewById(R.id.rating);
        btnSubmit = (Button) convertView.findViewById(R.id.btnSubmit);
        edtComment = (EditText) convertView.findViewById(R.id.edtComment);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                isDataChanged = true;
            }
        });

        edtComment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                isDataChanged = true;
            }
        });

        btnSubmit.setOnClickListener(this);

        if(!isDataChanged) {
            if (sPref.contains("rating")) {
                float ratingValue = sPref.getFloat("rating", 0);
                ratingBar.setRating(ratingValue);
            }
            if (sPref.contains("comment")) {
                String comment = sPref.getString("comment", "");
                edtComment.setText(comment);
            }
        }
        return convertView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        this.setRetainInstance(true);  //this will retain fragment instance's state on orientation change
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSubmit:
                SharedPreferences.Editor spEditor = sPref.edit();
                spEditor.putFloat("rating", ratingBar.getRating());
                spEditor.putString("comment", edtComment.getText().toString().trim());
                spEditor.commit();
                isDataChanged = false;
                Toast.makeText(getActivity().getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
