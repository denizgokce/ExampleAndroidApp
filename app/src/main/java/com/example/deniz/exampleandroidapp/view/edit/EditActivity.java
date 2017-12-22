package com.example.deniz.exampleandroidapp.view.edit;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.deniz.exampleandroidapp.R;
import com.example.deniz.exampleandroidapp.util.BaseActivity;
import com.example.deniz.exampleandroidapp.view.error.ErrorFragment;
import com.example.deniz.exampleandroidapp.view.list.ListFragment;

public class EditActivity extends BaseActivity {
    private static final String EXTRA_PERSON_ID = "EXTRA_PERSON_ID";
    private static final String EDIT_FRAG = "EDIT_FRAG";
    private static final String ERROR_FRAG = "ERROR_FRAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        Intent i = getIntent();

        //if extra is null, not worth even bothering to set up the MVVM stuff; Kill it with fire.


        FragmentManager manager = getSupportFragmentManager();
        if (!isNetworkConnected()) {
            ErrorFragment fragment = (ErrorFragment) manager.findFragmentByTag(ERROR_FRAG);
            if (fragment == null) {
                fragment = ErrorFragment.newInstance();
            }
            addFragmentToActivity(manager,
                    fragment,
                    R.id.root_activity_edit,
                    ERROR_FRAG
            );
        } else {
            if (i.hasExtra(EXTRA_PERSON_ID)) {
                String personId = i.getStringExtra(EXTRA_PERSON_ID);
                EditFragment fragment = (EditFragment) manager.findFragmentByTag(EDIT_FRAG);
                if (fragment == null) {
                    fragment = EditFragment.newInstance(personId);
                }
                addFragmentToActivity(manager,
                        fragment,
                        R.id.root_activity_edit,
                        EDIT_FRAG
                );
            }
        }
    }

    public void clearFragment(Bundle savedInstanceState) {
        ErrorFragment fragment = (ErrorFragment) getSupportFragmentManager().getFragment(savedInstanceState, ERROR_FRAG);
        getSupportFragmentManager().beginTransaction().remove(fragment).commit();
    }
}
