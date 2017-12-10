package com.example.deniz.exampleandroidapp.view.create;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.deniz.exampleandroidapp.ExampleAndroidApp;
import com.example.deniz.exampleandroidapp.R;
import com.example.deniz.exampleandroidapp.model.Person;
import com.example.deniz.exampleandroidapp.view.list.ListActivity;
import com.example.deniz.exampleandroidapp.view.list.ListFragment;
import com.example.deniz.exampleandroidapp.viewmodel.NewPersonViewModel;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

public class CreateFragment extends LifecycleFragment {

    private static final String EXTRA_ITEM_ID = "EXTRA_ITEM_ID";

    private List<Person> listOfPeople;

    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private Button addPersonButton;
    private EditText firstName;
    private EditText lastName;

    private Toolbar toolbar;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private NewPersonViewModel newPersonViewModel;

    public CreateFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ExampleAndroidApp) getActivity().getApplication())
                .getApplicationComponent()
                .inject(this);
    }

    @NonNull
    public static CreateFragment newInstance() {
        return new CreateFragment();
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Set up and subscribe (observe) to the ViewModel
        newPersonViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(NewPersonViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_create, container, false);

        //recyclerView = (RecyclerView) v.findViewById(R.id.rec_list_activity);
        layoutInflater = getActivity().getLayoutInflater();
        toolbar = (Toolbar) v.findViewById(R.id.tlb_create_activity);

        toolbar.setTitle(R.string.title_toolbar_create);
        //toolbar.setLogo(R.drawable.ic_view_list_white_24dp);
        toolbar.setTitleMarginStart(72);

        addPersonButton = (Button) v.findViewById(R.id.addPersonButton);
        firstName = (EditText) v.findViewById(R.id.edit_firstname);
        lastName = (EditText) v.findViewById(R.id.edit_lastname);

        addPersonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Person person = new Person(
                        firstName.getText().toString(),
                        lastName.getText().toString()
                );
                newPersonViewModel.addNewPersonToDatabase(person);

                startListActivity();
            }
        });
        return v;
    }

    private void startListActivity() {
        startActivity(new Intent(getActivity(), ListActivity.class));
    }
}
