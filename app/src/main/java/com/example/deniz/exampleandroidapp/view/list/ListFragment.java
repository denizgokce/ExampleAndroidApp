package com.example.deniz.exampleandroidapp.view.list;


import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.deniz.exampleandroidapp.ExampleAndroidApp;
import com.example.deniz.exampleandroidapp.R;
import com.example.deniz.exampleandroidapp.model.Person;
import com.example.deniz.exampleandroidapp.view.create.CreateActivity;
import com.example.deniz.exampleandroidapp.viewmodel.ListViewModel;

import java.util.List;

import javax.inject.Inject;


public class ListFragment extends LifecycleFragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String EXTRA_ITEM_ID = "EXTRA_ITEM_ID";
    private static final String LOGTAG = "GESTURE";
    private List<Person> listOfPeople;

    private LayoutInflater layoutInflater;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;
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
        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);
                                    }
                                }
        );

        //toolbar.setTitle(R.string.title_toolbar_list);
        //toolbar.setLogo(R.drawable.ic_view_list_white_24dp);
        //toolbar.setTitleMarginStart(72);


        FloatingActionButton fabulous = (FloatingActionButton) v.findViewById(R.id.fab_create_new_item);

        fabulous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCreateActivity();
            }
        });

        return v;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Set up and subscribe (observe) to the ViewModel
        listViewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ListViewModel.class);

        listViewModel.getPeople().observe(this, people -> {
            swipeRefreshLayout.setRefreshing(false);
            setListData(people);
        });
        /*listViewModel.people.observe(this, new Observer<List<Person>>() {
            @Override
            public void onChanged(@Nullable List<Person> listPeople) {
                if (ListFragment.this.listOfPeople == null) {
                    swipeRefreshLayout.setRefreshing(false);
                    setListData(listPeople);
                }
            }
        });*/

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

    /*public void startDetailActivity(String itemId, View viewRoot) {
        Activity container = getActivity();
        Intent i = new Intent(container, DetailActivity.class);
        i.putExtra(EXTRA_ITEM_ID, itemId);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            container.getWindow().setEnterTransition(new Fade(Fade.IN));
            container.getWindow().setEnterTransition(new Fade(Fade.OUT));

            ActivityOptions options = ActivityOptions
                    .makeSceneTransitionAnimation(container,
                            new Pair<>(viewRoot.findViewById(R.id.imv_list_item_circle),
                                    getString(R.string.transition_drawable)),
                            new Pair<>(viewRoot.findViewById(R.id.lbl_message),
                                    getString(R.string.transition_message)),
                            new Pair<>(viewRoot.findViewById(R.id.lbl_date_and_time),
                                    getString(R.string.transition_time_and_date)));

            startActivity(i, options.toBundle());

        } else {
            startActivity(i);
        }
    }*/

    public void startCreateActivity() {
        startActivity(new Intent(getActivity(), CreateActivity.class));
    }


    public void setListData(List<Person> listOfPeople) {
        this.listOfPeople = listOfPeople;

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);


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

        recyclerView.addItemDecoration(
                itemDecoration
        );


        /*ItemTouchHelper itemTouchHelper = new ItemTouchHelper(createHelperCallback());
        itemTouchHelper.attachToRecyclerView(recyclerView);*/
    }

    @Override
    public void onRefresh() {
        listViewModel.getPeople().observe(this, people -> {
            swipeRefreshLayout.setRefreshing(false);
            setListData(listOfPeople);
        });
    }
    /*-------------------- RecyclerView Boilerplate ----------------------*/

    private class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {

        @Override
        public CustomAdapter.CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = layoutInflater.inflate(R.layout.person_item, parent, false);
            return new CustomViewHolder(v);
        }

        @Override
        public void onBindViewHolder(CustomAdapter.CustomViewHolder holder, int position) {
            //11. and now the ViewHolder data
            Person currentItem = listOfPeople.get(position);

            //holder.coloredCircle.setImageResource(currentItem.getColorResource());


            holder.firstname.setText(
                    currentItem.getFirstname()
            );

            holder.lastname.setText(
                    currentItem.getLastname()
            );

            holder.loading.setVisibility(View.INVISIBLE);
        }

        @Override
        public int getItemCount() {
            // 12. Returning 0 here will tell our Adapter not to make any Items. Let's fix that.
            if (listOfPeople == null)
                return 0;
            return listOfPeople.size();
        }

        class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            //10. now that we've made our layouts, let's bind them
            //private CircleImageView coloredCircle;
            private TextView firstname;
            private TextView lastname;
            private ViewGroup container;
            private ProgressBar loading;

            public CustomViewHolder(View itemView) {
                super(itemView);
                //this.coloredCircle = (CircleImageView) itemView.findViewById(R.id.imv_list_item_circle);
                this.firstname = (TextView) itemView.findViewById(R.id.lbl_firstname);
                this.lastname = (TextView) itemView.findViewById(R.id.lbl_lastname);
                this.loading = (ProgressBar) itemView.findViewById(R.id.pro_item_data);

                this.container = (ViewGroup) itemView.findViewById(R.id.root_list_item);
                /*
                We can pass "this" as an Argument, because "this", which refers to the Current
                Instance of type CustomViewHolder currently conforms to (implements) the
                View.OnClickListener interface. I have a Video on my channel which goes into
                Interfaces with Detailed Examples.

                Search "Android WTF: Java Interfaces by Example"
                 */
                this.container.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                //getAdapterPosition() get's an Integer based on which the position of the current
                //ViewHolder (this) in the Adapter. This is how we get the correct Data.
                Person listItem = listOfPeople.get(
                        this.getAdapterPosition()
                );

                //startDetailActivity(listItem.getItemId(), v);

            }
        }

    }


    /*private ItemTouchHelper.Callback createHelperCallback() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            //not used, as the first parameter above is 0
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position = viewHolder.getAdapterPosition();
                listItemCollectionViewModel.deleteListItem(
                        listOfData.get(position)
                );

                //ensure View is consistent with underlying data
                listOfData.remove(position);
                adapter.notifyItemRemoved(position);


            }
        };
        return simpleItemTouchCallback;
    }*/
}

