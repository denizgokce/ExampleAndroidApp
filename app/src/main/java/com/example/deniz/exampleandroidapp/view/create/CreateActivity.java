package com.example.deniz.exampleandroidapp.view.create;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import com.example.deniz.exampleandroidapp.R;
import com.example.deniz.exampleandroidapp.util.BaseActivity;
import com.example.deniz.exampleandroidapp.view.list.ListFragment;

public class CreateActivity extends BaseActivity {
    private static final String CREATE_FRAG = "CREATE_FRAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        FragmentManager manager = getSupportFragmentManager();

        CreateFragment fragment = (CreateFragment) manager.findFragmentByTag(CREATE_FRAG);

        if (fragment == null) {
            fragment = CreateFragment.newInstance();
        }

        addFragmentToActivity(manager,
                fragment,
                R.id.root_activity_create,
                CREATE_FRAG
        );
    }
}
