package com.tinga.android.myball;

/**
 * Created by Zack on 2017/01/24.
 */
public class Fixture_Scores {

    String team_a, team_b, ground, date, time, team_a_score, team_b_score;

    public Fixture_Scores() {
    }

    public Fixture_Scores(String date, String ground, String team_a, String team_a_score, String team_b, String team_b_score, String time) {
        this.date = date;
        this.ground = ground;
        this.team_a = team_a;
        this.team_a_score = team_a_score;
        this.team_b = team_b;
        this.team_b_score = team_b_score;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getGround() {
        return ground;
    }

    public void setGround(String ground) {
        this.ground = ground;
    }

    public String getTeam_a() {
        return team_a;
    }

    public void setTeam_a(String team_a) {
        this.team_a = team_a;
    }

    public String getTeam_a_score() {
        return team_a_score;
    }

    public void setTeam_a_score(String team_a_score) {
        this.team_a_score = team_a_score;
    }

    public String getTeam_b() {
        return team_b;
    }

    public void setTeam_b(String team_b) {
        this.team_b = team_b;
    }

    public String getTeam_b_score() {
        return team_b_score;
    }

    public void setTeam_b_score(String team_b_score) {
        this.team_b_score = team_b_score;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
