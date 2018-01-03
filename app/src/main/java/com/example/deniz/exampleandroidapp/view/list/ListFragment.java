package com.example.deniz.exampleandroidapp.view.list;


import android.app.Activity;
import android.app.AlertDialog;
import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.deniz.exampleandroidapp.ExampleAndroidApp;
import com.example.deniz.exampleandroidapp.R;
import com.example.deniz.exampleandroidapp.model.Person;
import com.example.deniz.exampleandroidapp.view.create.CreateActivity;
import com.example.deniz.exampleandroidapp.view.edit.EditActivity;
import com.example.deniz.exampleandroidapp.viewmodel.ListViewModel;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.List;

import javax.inject.Inject;

import de.hdodenhof.circleimageview.CircleImageView;


public class ListFragment extends LifecycleFragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String EXTRA_PERSON_ID = "EXTRA_PERSON_ID";
    private static final String LOGTAG = "GESTURE";
    private List<Person> listOfPeople;

    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private PersonAdapter adapter;
    private Toolbar toolbar;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    ListViewModel listViewModel;

    public ListFragment() {
    }

    @NonNull
    public static ListFragment newInstance() {
        return new ListFragment();
    }

    /*------------------------------- Lifecycle -------------------------------*/

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
        View v = inflater.inflate(R.layout.fragment_list, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.rec_list_activity);
        layoutInflater = getActivity().getLayoutInflater();
        toolbar = (Toolbar) v.findViewById(R.id.tlb_list_activity);


        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(() -> swipeRefreshLayout.setRefreshing(true));


        //toolbar.setTitle(R.string.title_toolbar_list);
        //toolbar.setLogo(R.drawable.ic_view_list_white_24dp);
        //toolbar.setTitleMarginStart(72);


        // UNIVERSAL IMAGE LOADER SETUP
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisc(true)
                .cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
                getActivity())
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache()).build();
        //.discCacheSize(100 * 1024 * 1024).build();

        ImageLoader.getInstance().init(config);
        // END - UNIVERSAL IMAGE LOADER SETUP


        FloatingActionButton fabulous = (FloatingActionButton) v.findViewById(R.id.fab_create_new_item);

        fabulous.setOnClickListener(v1 -> startCreateActivity());

        return v;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Set up and subscribe (observe) to the ViewModel
        listViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ListViewModel.class);

        listViewModel.getPeople().observe(this, people -> {
            swipeRefreshLayout.setRefreshing(false);
            setListOfPeople(people);
        });
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public void startCreateActivity() {
        startActivity(new Intent(getActivity(), CreateActivity.class));
    }


    public void setListOfPeople(List<Person> listOfPeople) {
        this.listOfPeople = listOfPeople;


        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        if (adapter == null)
            adapter = new PersonAdapter(this.layoutInflater, getActivity());
        recyclerView.setAdapter(adapter);
        adapter.setListOfPeople(listOfPeople);

        DividerItemDecoration itemDecoration = new DividerItemDecoration(
                recyclerView.getContext(),
                layoutManager.getOrientation()
        );

        itemDecoration.setDrawable(
                ContextCompat.getDrawable(
                        getActivity(),
                        R.drawable.divider_white
                )
        );

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    @Override
    public void onRefresh() {
        listViewModel.getPeople().observe(this, people -> {
            swipeRefreshLayout.setRefreshing(false);
            setListOfPeople(people);
        });
    }
    /*-------------------- RecyclerView Boilerplate ----------------------*/


    private ItemTouchHelper.Callback createHelperCallback() {


        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {


            public boolean isLongPressDragEnabled() {
                return true;
            }

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position = viewHolder.getAdapterPosition();

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                // Add the buttons
                builder.setPositiveButton(R.string.dialog_button_ok, (dialog, id) -> {
                    listViewModel.deletePerson(
                            listOfPeople.get(position)
                    );

                    //ensure View is consistent with underlying data
                    listOfPeople.remove(position);
                    adapter.notifyItemRemoved(position);
                });
                builder.setNegativeButton(R.string.dialog_button_cancel, (dialog, id) -> {
                    setListOfPeople(listOfPeople);
                });
                AlertDialog dialog = builder.create();
                dialog.setTitle("Alert!");
                dialog.setMessage("Are you sure?");
                dialog.show();
            }
        };
        return simpleItemTouchCallback;
    }


}

