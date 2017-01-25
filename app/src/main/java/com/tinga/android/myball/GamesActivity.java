package com.tinga.android.myball;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GamesActivity extends AppCompatActivity {

    private RecyclerView mTeamsList;

    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fixture_scores);


        mDatabase = FirebaseDatabase.getInstance().getReference().child("Teams");
        mDatabase.keepSynced(true);

        mTeamsList = (RecyclerView) findViewById(R.id.teamslist);
        mTeamsList.setHasFixedSize(true);
        mTeamsList.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Fixture_Scores, TeamViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Fixture_Scores, TeamViewHolder>(
                Fixture_Scores.class,
                R.layout.scores_list,
                TeamViewHolder.class,
                mDatabase

        ) {
            @Override
            protected void populateViewHolder(TeamViewHolder viewHolder, Fixture_Scores model, int position) {

                viewHolder.setTeam_a(model.getTeam_a());
                viewHolder.setTeam_b(model.getTeam_b());
                viewHolder.setGround(model.getGround());
                viewHolder.setDate(model.getDate());
                viewHolder.setTeam_a_score(model.getTeam_a_score());
                viewHolder.setTeam_b_score(model.getTeam_b_score());

            }
        };

        mTeamsList.setAdapter(firebaseRecyclerAdapter);

    }

    public static class TeamViewHolder extends RecyclerView.ViewHolder{

        View mView;

        public TeamViewHolder(View itemView) {
            super(itemView);

            mView = itemView;

        }

        public void setTeam_a(String teamA){
            TextView team_a = (TextView) mView.findViewById(R.id.tv_team_a);
            team_a.setText(teamA);

        }

        public void setTeam_b(String teamB){
            TextView team_b = (TextView) mView.findViewById(R.id.tv_team_b);
            team_b.setText(teamB);

        }

        public void setGround(String ground){
            TextView stad = (TextView) mView.findViewById(R.id.tv_ground);
            stad.setText(ground);

        }

        public void setDate(String date){
            TextView dat = (TextView) mView.findViewById(R.id.tv_date_time);
            dat.setText(date);

        }

        public void setTime(String time){
            TextView tim = (TextView) mView.findViewById(R.id.tv_date_time);
            tim.setText(time);

        }

        public void setTeam_a_score(String teamAScore){
            TextView team_a_score = (TextView) mView.findViewById(R.id.tv_team_a_score);
            team_a_score.setText(teamAScore);

        }

        public void setTeam_b_score(String teamBScore){
            TextView team_b_score = (TextView) mView.findViewById(R.id.tv_team_b_score);
            team_b_score.setText(teamBScore);

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

            startActivity(new Intent(GamesActivity.this, AddScoresActivity.class));

        }

        return super.onOptionsItemSelected(item);
    }
}
