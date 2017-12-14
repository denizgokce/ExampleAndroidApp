package com.example.deniz.exampleandroidapp.view.create;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import com.example.deniz.exampleandroidapp.R;
import com.example.deniz.exampleandroidapp.util.BaseActivity;
import com.example.deniz.exampleandroidapp.view.error.ErrorFragment;

public class CreateActivity extends BaseActivity {
    private static final String CREATE_FRAG = "CREATE_FRAG";
    private static final String ERROR_FRAG = "ERROR_FRAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_create);

        FragmentManager manager = getSupportFragmentManager();
        if (!isNetworkConnected()) {
            ErrorFragment fragment = (ErrorFragment) manager.findFragmentByTag(ERROR_FRAG);
            if (fragment == null) {
                fragment = ErrorFragment.newInstance();
            }
            addFragmentToActivity(manager,
                    fragment,
                    R.id.root_activity_create,
                    ERROR_FRAG
            );
        } else {
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
}
