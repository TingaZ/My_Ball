package com.tinga.android.myball;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddTournamentsActivity extends AppCompatActivity {

    private ImageButton mImage;
    private EditText mTournamentName, mTournamentDate, TournamentDescription;

    private static final int GALLERY_REQUEST = 1;

    private StorageReference mStorage;
    private DatabaseReference mDatabase;

    private Uri imageUri = null;

    private ProgressDialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tournaments);

        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Tournaments");

        mImage = (ImageButton) findViewById(R.id.imageButton_select);
        mTournamentName = (EditText) findViewById(R.id.editText_tournamentName);
        mTournamentDate = (EditText) findViewById(R.id.editText_tournament_date);
        TournamentDescription = (EditText) findViewById(R.id.editText_tournament_decription);
        mDialog = new ProgressDialog(this);

        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST && resultCode == RESULT_OK){

            imageUri = data.getData();
            mImage.setImageURI(imageUri);

        }

    }

    public void submitTournament(){

        mDialog.setMessage("Submitting Details ...");

        final String name = mTournamentName.getText().toString().trim();
        final String date = mTournamentDate.getText().toString().trim();
        final String desc = TournamentDescription.getText().toString().trim();

        if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(date) && !TextUtils.isEmpty(desc) && imageUri != null){

            mDialog.show();

            StorageReference filePath = mStorage.child("Tournament_Images").child(imageUri.getLastPathSegment());

            filePath.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Uri downloadUri = taskSnapshot.getDownloadUrl();

                    DatabaseReference newTournament = mDatabase.push();
                    newTournament.child("name").setValue(name);
                    newTournament.child("date").setValue(date);
                    newTournament.child("description").setValue(desc);
                    newTournament.child("img").setValue(downloadUri.toString());

                    startActivity(new Intent(AddTournamentsActivity.this, TournamentActivity.class));

                    mDialog.dismiss();

                }
            });

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_done, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_done){

            submitTournament();

        }

        return super.onOptionsItemSelected(item);
    }
}
