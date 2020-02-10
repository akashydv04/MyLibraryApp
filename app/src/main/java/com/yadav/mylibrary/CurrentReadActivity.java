package com.yadav.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

public class CurrentReadActivity extends AppCompatActivity {
    private static final String TAG = "CurrentReadActivity";

    private RecyclerView recyclerViewCurrentRead;

    private BookRecViewAdapter bookRecViewAdapter;
    private Uti uti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_read);

        overridePendingTransition(R.anim.in,R.anim.out);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerViewCurrentRead=findViewById(R.id.recycCurrentRead);

        bookRecViewAdapter= new BookRecViewAdapter(this);
        bookRecViewAdapter.setType("currently reading");
        uti=new Uti();
        recyclerViewCurrentRead.setAdapter(bookRecViewAdapter);
        recyclerViewCurrentRead.setLayoutManager(new GridLayoutManager(this,2));

        bookRecViewAdapter.setBooks(uti.getCurrentlyReadingBooks());


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
    public void finish() {
        overridePendingTransition(R.anim.close_in,R.anim.close_out);
        super.finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
