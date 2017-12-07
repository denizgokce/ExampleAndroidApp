package com.example.deniz.exampleandroidapp.view.create;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.deniz.exampleandroidapp.ExampleAndroidApp;
import com.example.deniz.exampleandroidapp.R;
import com.example.deniz.exampleandroidapp.model.Person;
import com.example.deniz.exampleandroidapp.view.list.ListFragment;

import java.util.List;

import javax.inject.Inject;

public class CreateFragment extends LifecycleFragment {

    private static final String EXTRA_ITEM_ID = "EXTRA_ITEM_ID";

    private List<Person> listOfPeople;

    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;

    private Toolbar toolbar;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    public CreateFragment() {
        // Required empty public constructor
    }

    @NonNull
    public static CreateFragment newInstance() {
        return new CreateFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ExampleAndroidApp) getActivity().getApplication())
                .getApplicationComponent()
                .inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create, container, false);
    }
}
