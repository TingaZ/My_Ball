package com.tinga.android.myball;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

public class TournamentActivity extends AppCompatActivity {

    private RecyclerView mTournamentList;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Tournaments");

        mTournamentList = (RecyclerView) findViewById(R.id.tournament_list);
        mTournamentList.setHasFixedSize(true);
        mTournamentList.setLayoutManager(new LinearLayoutManager(this));


    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Tournaments, TournamentViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Tournaments, TournamentViewHolder>(
                Tournaments.class,
                R.layout.activity_tournament,
                TournamentViewHolder.class,
                mDatabase

        ) {
            @Override
            protected void populateViewHolder(TournamentViewHolder viewHolder, Tournaments model, int position) {

                final String tournament_key = getRef(position).getKey();

                viewHolder.setName(model.getName());
                viewHolder.setDate(model.getDate());
                viewHolder.setDescription(model.getDescription());
                viewHolder.setImg(getApplicationContext(), model.getImg());

                viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent viewIntent = new Intent(TournamentActivity.this, ViewActivity.class);
                        viewIntent.putExtra(tournament_key, "tournament_id");
                        startActivity(viewIntent);

                        Toast.makeText(TournamentActivity.this, tournament_key, Toast.LENGTH_SHORT).show();

                    }
                });

            }
        };

        mTournamentList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class TournamentViewHolder extends RecyclerView.ViewHolder{

        View mView;

        final ImageView imageView;

        public TournamentViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

            imageView = (ImageView) mView.findViewById(R.id.img_resource);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.v("Tournament Acivity", "Some text");

                }
            });
        }

        public void setName(String name){
            TextView tvName = (TextView) mView.findViewById(R.id.tv_tournament_name);
            tvName.setText(name);
        }

        public void setDate(String date){
            TextView tvDate = (TextView) mView.findViewById(R.id.tv_tournament_date);
            tvDate.setText(date);
        }

        public void setDescription(String desc){
            TextView description = (TextView) mView.findViewById(R.id.tv_tournament_desc);
            description.setText(desc);
        }


        public void setImg(final Context c, final String image) {

            Picasso.with(c).load(image).networkPolicy(NetworkPolicy.OFFLINE).into(imageView, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {

                    Picasso.with(c).load(image).into(imageView);

                }
            });

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_add){

            startActivity(new Intent(TournamentActivity.this, AddTournamentsActivity.class));

        }

        return super.onOptionsItemSelected(item);
    }
}
