package com.yadav.mylibrary;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Button seeAllBooksbtn, currentReadingBookbtn, wantToReadbtn, alreadyReadbtn, aboutbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        overridePendingTransition(R.anim.in,R.anim.out);
        initWidgets();
        setOnClickListner();

    }

    private void setOnClickListner() {

        seeAllBooksbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, AllBooksActivity.class);
                startActivity(intent);
            }
        });

        wantToReadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, WantToReadActivity.class);
                startActivity(intent);
            }
        });

        currentReadingBookbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, CurrentReadActivity.class);
                startActivity(intent);
            }
        });
        alreadyReadbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, AlreadyReadActivity.class);
                startActivity(intent);
            }
        });
        aboutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aboutbtnTapped();
            }
        });
    }

    private void aboutbtnTapped() {
        Log.d(TAG, "aboutbtnTapped: started");
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        builder.setTitle("About My Library");
        builder.setMessage("Build and Published by Akash Yadav\n"+
                "If you want to hire me or \n"+
                "if you want to check my other works\n"+
                "take a look at\n"+
                "github.com/akashydv04");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent= new Intent(MainActivity.this,MyWebViewActivity.class);
                intent.putExtra("url","https://www.github.com/akashydv04");
                startActivity(intent);
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

    private void initWidgets() {

        seeAllBooksbtn  =   findViewById(R.id.seeallbooksbtn);
        currentReadingBookbtn   =   findViewById(R.id.currentreadbookbtn);
        wantToReadbtn   =   findViewById(R.id.wantreadbookbtn);
        alreadyReadbtn  =   findViewById(R.id.alreadyreadbookbtn);
        aboutbtn        =   findViewById(R.id.aboutbtn);
    }

    @Override
    public void finish() {
        overridePendingTransition(R.anim.close_in,R.anim.close_out);
        super.finish();
    }
}
