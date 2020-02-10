package com.yadav.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

public class WantToReadActivity extends AppCompatActivity {
    private static final String TAG = "WantToReadActivity";

    private RecyclerView wantReadRecyc;

    private BookRecViewAdapter bookRecViewAdapter;
    private Uti uti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_to_read);

        overridePendingTransition(R.anim.in,R.anim.out);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initWidgets();
    }

    private void initWidgets() {

        bookRecViewAdapter= new BookRecViewAdapter(this);
        bookRecViewAdapter.setType("want to read");
        uti=new Uti();
        wantReadRecyc=findViewById(R.id.recycViewWantRead);
        wantReadRecyc.setAdapter(bookRecViewAdapter);
        wantReadRecyc.setLayoutManager(new GridLayoutManager(this,2));

        bookRecViewAdapter.setBooks(uti.getWantToReadBooks());
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
