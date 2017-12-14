package com.example.deniz.exampleandroidapp.view.list;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.example.deniz.exampleandroidapp.R;
import com.example.deniz.exampleandroidapp.util.BaseActivity;
import com.example.deniz.exampleandroidapp.view.error.ErrorFragment;


public class ListActivity extends BaseActivity {

    private static final String LIST_FRAG = "LIST_FRAG";
    private static final String ERROR_FRAG = "ERROR_FRAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        FragmentManager manager = getSupportFragmentManager();
        if (!isNetworkConnected()) {
            ErrorFragment fragment = (ErrorFragment) manager.findFragmentByTag(ERROR_FRAG);
            if (fragment == null) {
                fragment = ErrorFragment.newInstance();
            }
            addFragmentToActivity(manager,
                    fragment,
                    R.id.root_activity_list,
                    ERROR_FRAG
            );
        } else {
            ListFragment fragment = (ListFragment) manager.findFragmentByTag(LIST_FRAG);
            if (fragment == null) {
                fragment = ListFragment.newInstance();
            }
            addFragmentToActivity(manager,
                    fragment,
                    R.id.root_activity_list,
                    LIST_FRAG
            );
        }

    }
}
