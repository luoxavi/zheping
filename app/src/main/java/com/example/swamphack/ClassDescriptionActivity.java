package com.example.swamphack;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class ClassDescriptionActivity extends AppCompatActivity {

    private TextView mTextView;
    public static final String CLASS_DES = "LocaX";
    private CollectionReference UF = FirebaseFirestore.getInstance().collection("University of Florida");
    private TextView mQuoteTextView1;
    String showdata;
    private DocumentReference mD = FirebaseFirestore.getInstance().document("University of Florida/ABE3000");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_description);
        mQuoteTextView1 = findViewById(R.id.classDescrip_desciption);

        mD.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if (value.exists()){
                    String quoteText = value.getString(CLASS_DES);
                    mQuoteTextView1.setText("\"" + quoteText + "\"----" );
                }
                else if (error != null)
                {
                    mQuoteTextView1.setText("No" );
                }
            }
        });





        mTextView = (TextView) findViewById(R.id.classDescrip_name);
        Intent intent = getIntent();
        showdata = intent.getStringExtra("com.example.swamphack.MESSAGE");
        mTextView.setText(showdata);

        Button backBtn = (Button) findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ClassDescriptionActivity.this,MatchAcitivity.class);
            startActivity(intent);
        }
    });


    }





}