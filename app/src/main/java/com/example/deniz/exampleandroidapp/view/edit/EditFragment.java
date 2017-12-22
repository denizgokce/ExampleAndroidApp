package com.example.deniz.exampleandroidapp.view.edit;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.example.deniz.exampleandroidapp.viewmodel.EditViewModel;

import java.util.List;

import javax.inject.Inject;

public class EditFragment extends LifecycleFragment {
    private static final String EXTRA_PERSON_ID = "EXTRA_ITEM_ID";

    private List<Person> listOfPeople;

    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private Button editPersonButton;
    private EditText firstName;
    private EditText lastName;

    private String personId;
    private Toolbar toolbar;
    private Person person;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private EditViewModel editViewModel;

    public EditFragment() {
        // Required empty public constructor
    }

    @NonNull
    public static EditFragment newInstance(String itemId) {
        EditFragment fragment = new EditFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_PERSON_ID, itemId);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ExampleAndroidApp) getActivity().getApplication())
                .getApplicationComponent()
                .inject(this);
        Bundle args = getArguments();

        this.personId = args.getString(EXTRA_PERSON_ID);
    }


    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Set up and subscribe (observe) to the ViewModel
        editViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(EditViewModel.class);

        editViewModel.getPerson(Integer.valueOf(personId)).observe(this, person -> {
            if (person != null) {
                this.person = person;
                firstName.setText(person.getFirstname());
                lastName.setText(person.getLastname());
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_edit, container, false);

        //recyclerView = (RecyclerView) v.findViewById(R.id.rec_list_activity);
        layoutInflater = getActivity().getLayoutInflater();
        //toolbar = (Toolbar) v.findViewById(R.id.tlb_create_activity);

        //toolbar.setTitle(R.string.title_toolbar_create);
        //toolbar.setLogo(R.drawable.ic_view_list_white_24dp);
        //toolbar.setTitleMarginStart(72);

        editPersonButton = (Button) v.findViewById(R.id.editPersonButton);
        firstName = (EditText) v.findViewById(R.id.edit_firstname);
        lastName = (EditText) v.findViewById(R.id.edit_lastname);


        editPersonButton.setOnClickListener(v1 -> {

            this.person.setFirstname(firstName.getText().toString());
            this.person.setLastname(lastName.getText().toString());
            editViewModel.editPerson(this.person);

            startListActivity();
        });
        return v;
    }

    private void startListActivity() {
        startActivity(new Intent(getActivity(), ListActivity.class));
    }
}
