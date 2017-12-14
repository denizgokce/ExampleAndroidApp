package com.example.deniz.exampleandroidapp.view.list;


import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;

import com.example.deniz.exampleandroidapp.R;
import com.example.deniz.exampleandroidapp.util.BaseActivity;
import com.example.deniz.exampleandroidapp.view.error.ErrorActivity;


public class ListActivity extends BaseActivity {

    private static final String LIST_FRAG = "LIST_FRAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        if (!isNetworkConnected()) {
            Intent intent = new Intent(this, ErrorActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
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
}
