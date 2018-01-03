package com.example.deniz.exampleandroidapp.view.list;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.deniz.exampleandroidapp.R;
import com.example.deniz.exampleandroidapp.model.Person;
import com.example.deniz.exampleandroidapp.view.edit.EditActivity;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by deniz.gokce on 29.12.2017.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.PersonViewHolder> {
    private static final String EXTRA_PERSON_ID = "EXTRA_PERSON_ID";

    public List<Person> getListOfPeople() {
        return listOfPeople;
    }

    public void setListOfPeople(List<Person> listOfPeople) {
        this.listOfPeople = listOfPeople;
    }

    private List<Person> listOfPeople;
    private LayoutInflater layoutInflater;
    private Activity activity;

    public PersonAdapter(LayoutInflater layoutInflater, Activity activity) {
        this.layoutInflater = layoutInflater;
        this.activity = activity;
    }

    @Override
    public PersonAdapter.PersonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = layoutInflater.inflate(R.layout.person_item, parent, false);
        return new PersonAdapter.PersonViewHolder(v);
    }

    @Override
    public void onBindViewHolder(PersonAdapter.PersonViewHolder holder, int position) {
        //11. and now the ViewHolder data
        Person currentItem = listOfPeople.get(position);

        //holder.coloredCircle.setImageResource(currentItem.getColorResource());


        holder.firstname.setText(
                currentItem.getFirstname()
        );

        holder.lastname.setText(
                currentItem.getLastname()
        );
        //your image url
        String url = "http://laoblogger.com/images/default-profile-picture-5.jpg";

        ImageLoader imageLoader = ImageLoader.getInstance();
        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true).build();
        //.showImageForEmptyUri(fallback)
        //.showImageOnFail(fallback)
        //.showImageOnLoading(fallback).build();


        //download and display image from url
        imageLoader.displayImage(url, holder.coloredCircle, options);
        holder.loading.setVisibility(View.INVISIBLE);
    }

    @Override
    public int getItemCount() {
        // 12. Returning 0 here will tell our Adapter not to make any Items. Let's fix that.
        if (listOfPeople == null)
            return 0;
        return listOfPeople.size();
    }

    class PersonViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        //10. now that we've made our layouts, let's bind them
        private CircleImageView coloredCircle;
        private TextView firstname;
        private TextView lastname;
        private ViewGroup container;
        private ProgressBar loading;


        public PersonViewHolder(View itemView) {
            super(itemView);
            this.coloredCircle = (CircleImageView) itemView.findViewById(R.id.imv_list_person_circle);
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

            this.container.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View v) {
            Person person = listOfPeople.get(
                    this.getAdapterPosition()
            );
            startEditActivity(person.getId(), v);
            return true;
        }
    }

    public void startEditActivity(int personId, View viewRoot) {
        Activity container = activity;
        Intent i = new Intent(container, EditActivity.class);
        i.putExtra(EXTRA_PERSON_ID, String.valueOf(personId));
        container.startActivity(i);
        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
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

            //startActivity(i, options.toBundle());

        } else {
            startActivity(i);
        }*/
    }
}
