package com.example.deniz.exampleandroidapp.view.error;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deniz.exampleandroidapp.R;

public class ErrorFragment extends Fragment {
    public static ErrorFragment newInstance() {
        return  new ErrorFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_error, container, false);
    }


}
