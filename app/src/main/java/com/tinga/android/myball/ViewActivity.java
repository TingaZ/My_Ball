package com.tinga.android.myball;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ViewActivity extends AppCompatActivity {

    private String mTournamentKey;

    private TextView mGroup;
    private TextView mTeamA, mTeamA_P, mTeamA_W, mTeamA_L, mTeamA_D, mTeamA_Pts;
    private TextView mTeamB, mTeamB_P, mTeamB_W, mTeamB_L, mTeamB_D, mTeamB_Pts;
    private TextView mTeamC, mTeamC_P, mTeamC_W, mTeamC_L, mTeamC_D, mTeamC_Pts;
    private TextView mTeamD, mTeamD_P, mTeamD_W, mTeamD_L, mTeamD_D, mTeamD_Pts;

    private EditText ETteamA, ETteamB, ETteamC, ETteamD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

       /* mGroup = (TextView) findViewById(R.id.tvGroup);
        //Team A
        mTeamA = (TextView) findViewById(R.id.tv_teamA);
        mTeamA_P = (TextView) findViewById(R.id.tv_teamA_p);
        mTeamA_W = (TextView) findViewById(R.id.tv_teamA_w);
        mTeamA_L = (TextView) findViewById(R.id.tv_teamA_l);
        mTeamA_D = (TextView) findViewById(R.id.tv_teamA_d);
        mTeamA_Pts = (TextView) findViewById(R.id.tv_teamA_pts);

        //Team B
        mTeamB = (TextView) findViewById(R.id.tv_teamB);
        mTeamB_P = (TextView) findViewById(R.id.tv_teamB_p);
        mTeamB_W = (TextView) findViewById(R.id.tv_teamB_w);
        mTeamB_L = (TextView) findViewById(R.id.tv_teamB_l);
        mTeamB_D = (TextView) findViewById(R.id.tv_teamB_d);
        mTeamB_Pts = (TextView) findViewById(R.id.tv_teamB_pts);

        //Team C
        mTeamC = (TextView) findViewById(R.id.tv_teamC);
        mTeamC_P = (TextView) findViewById(R.id.tv_teamC_p);
        mTeamC_W = (TextView) findViewById(R.id.tv_teamC_w);
        mTeamC_L = (TextView) findViewById(R.id.tv_teamC_l);
        mTeamC_D = (TextView) findViewById(R.id.tv_teamC_d);
        mTeamC_Pts = (TextView) findViewById(R.id.tv_teamC_pts);

        //Team D
        mTeamD = (TextView) findViewById(R.id.tv_teamD);
        mTeamD_P = (TextView) findViewById(R.id.tv_teamD_p);
        mTeamD_W = (TextView) findViewById(R.id.tv_teamD_w);
        mTeamD_L = (TextView) findViewById(R.id.tv_teamD_l);
        mTeamD_D = (TextView) findViewById(R.id.tv_teamD_d);
        mTeamD_Pts = (TextView) findViewById(R.id.tv_teamD_pts);

        ETteamA = (EditText) findViewById(R.id.editTextTeamA);
        ETteamB = (EditText) findViewById(R.id.editTextTeamB);
        ETteamC = (EditText) findViewById(R.id.editTextTeamC);
        ETteamD = (EditText) findViewById(R.id.editTextTeamD);

        mTournamentKey = getIntent().getExtras().getString("tournament_id");

        int a = Integer.parseInt(ETteamA.getText().toString());
        int b = Integer.parseInt(ETteamB.getText().toString());
        int c = Integer.parseInt(ETteamC.getText().toString());
        int d = Integer.parseInt(ETteamD.getText().toString());

//        int ta = Integer.parseInt(mTeamA_W.getText().toString());
//
//        int ta = 0;
//
//        if (a > b){
//
//            int x = ta + 3;
//            ETteamA.setText(x);
//
//        }*/

    }
}
