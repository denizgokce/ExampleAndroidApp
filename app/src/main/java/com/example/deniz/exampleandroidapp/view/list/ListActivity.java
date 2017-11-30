package com.example.deniz.exampleandroidapp.view.list;

import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import com.example.deniz.exampleandroidapp.R;
import com.example.deniz.exampleandroidapp.util.BaseActivity;

public class ListActivity extends BaseActivity {

    private static final String LIST_FRAG = "LIST_FRAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        FragmentManager manager = getSupportFragmentManager();

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
