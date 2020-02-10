package com.yadav.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

public class AlreadyReadActivity extends AppCompatActivity {
    private static final String TAG = "AlreadyReadActivity";

    private RecyclerView recyclerViewAlreadyRead;

    private BookRecViewAdapter bookRecViewAdapter;
    private Uti uti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_read);

        overridePendingTransition(R.anim.in,R.anim.out);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerViewAlreadyRead=findViewById(R.id.recycViewAlreadyRead);

        bookRecViewAdapter= new BookRecViewAdapter(this);
        bookRecViewAdapter.setType("already read");
        uti=new Uti();
        recyclerViewAlreadyRead.setAdapter(bookRecViewAdapter);
        recyclerViewAlreadyRead.setLayoutManager(new GridLayoutManager(this,2));

        bookRecViewAdapter.setBooks(uti.getAlreadyReadBooks());


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                super.onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
