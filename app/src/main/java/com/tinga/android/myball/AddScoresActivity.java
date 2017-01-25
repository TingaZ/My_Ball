package com.tinga.android.myball;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddScoresActivity extends AppCompatActivity {

    private EditText mAddTeamA, mAddTeamB, mGround, mDate, mTime, mAddTeamAScore, mAddTeamBScore;
    private Button mSubmit;

    private DatabaseReference mDatabase;

    private ProgressDialog mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_scores);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Teams");

        mAddTeamA = (EditText) findViewById(R.id.et_Addteam_a);
        mAddTeamB = (EditText) findViewById(R.id.et_Addteam_b);
        mGround = (EditText) findViewById(R.id.et_Addground);
        mDate = (EditText) findViewById(R.id.et_Adddate);
        mTime = (EditText) findViewById(R.id.et_Addtime);
        mAddTeamAScore = (EditText) findViewById(R.id.et_Addteam_a_score);
        mAddTeamBScore = (EditText) findViewById(R.id.et_Addteam_b_score);
        mSubmit = (Button) findViewById(R.id.btn_submit);
        mProgress = new ProgressDialog(this);

        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                submitTeams();

            }
        });
    }

    private void submitTeams() {

        mProgress.setMessage("Submitting Team Credentials ...");

        final String team_a = mAddTeamA.getText().toString().trim();
        final String team_b = mAddTeamB.getText().toString().trim();
        final String ground = mGround.getText().toString().trim();
        final String date = mDate.getText().toString().trim();
        final String time = mTime.getText().toString().trim();
        final String team_a_score = mAddTeamAScore.getText().toString().trim();
        final String team_b_score= mAddTeamBScore.getText().toString().trim();

        if (!TextUtils.isEmpty(team_a) && !TextUtils.isEmpty(team_b) && !TextUtils.isEmpty(ground) && !TextUtils.isEmpty(date) && !TextUtils.isEmpty(time)) {

            mProgress.show();

            DatabaseReference newTeam = mDatabase.push();
            newTeam.child("team_a").setValue(team_a);
            newTeam.child("team_b").setValue(team_b);
            newTeam.child("ground").setValue(ground);
            newTeam.child("date").setValue(date);
            newTeam.child("time").setValue(time);
            newTeam.child("team_a_score").setValue(team_a_score);
            newTeam.child("team_b_score").setValue(team_b_score);


            mProgress.dismiss();

            Toast.makeText(AddScoresActivity.this, "Successfully Posted..", Toast.LENGTH_SHORT).show();

            startActivity(new Intent(AddScoresActivity.this, MainActivity.class));


        }

    }
}
