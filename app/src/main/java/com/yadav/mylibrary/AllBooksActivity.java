package com.yadav.mylibrary;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AllBooksActivity extends AppCompatActivity {
    private static final String TAG = "AllBooksActivity";

    private RecyclerView bookRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_books);
        Log.d(TAG, "onCreate: Started");

        overridePendingTransition(R.anim.in, R.anim.out);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        bookRecyclerView = findViewById(R.id.booksRecyclerView);

        BookRecViewAdapter adapter = new BookRecViewAdapter(this);
        bookRecyclerView.setAdapter(adapter);
        bookRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        /*ArrayList<Book> books= new ArrayList<>();
        books.add(new Book("Math III","NP Bali",400,
                "data:image/png;base64,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",
                "A Very Dangerous material of Engineering"));
        books.add(new Book("Math II","NP Bali",400,
                "data:image/png;base64,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",
                "A Very Dangerous material of Engineering"));
        books.add(new Book("Math 1","NP Bali",400,
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAH0AAAB9CAMAAAC4XpwXAAAA1VBMVEX///+jo81maq2pqasABRioqNT///f///Tc29BoaW0WGBhqbrQBCA4AAAAAAA9FSHVFRlcNEReOjrNoaGheXl+lpJ4MEBuFhaghJC4WGSEMFSP/wIPd3d4SFRxvcI0pKzd4eJhnUUJaXpiXl75dRTXPz9FjZH46PEwzNURjZZN2ebaGh4twcXXm5+hSU1q+v8FAQUbn59uBgoDNzcOPkIzz8vOzs6wpLD4+QWhYWXFPUGVUWIs6PV2UlJgvMkm0tLgvKSzNmGu6i2ORblJRQDgxJCEnHR+e4oSJAAADJUlEQVRoge2abVPaQBCAE8iBlQSTE81uyl1SU5JUMUKr1GBb+mL7/39SLxcQdaZTuTRlxHv84nLBh73b25GZNQyNRqPRaDQvjtHZ+FUdxuc13Llj1SAIAqetLL8Q758MDpXJESfKuQ/DwDqadjuqGO8DHCmnnh/Dh05X3T4NrTNl+RCsy66QHw4UyaF/oWxfWP09Ye8eqTIOnCtle9tyunXozCxHWS7sVe53f23Toqtpty47a/nM2RiwhzXsOJmu7dO9TZmBpd5r2hYEk2mnq87kOFcuO2EHhMFs45xXTC8D60xVL+whYmD1lQkA+qp737b2ewcf9wWIniLqJ1/aWycl1ylVI/bq2Ut6By4xlSC2tmu7tmu7tj9nO/nLp2rUHrMw3p7d98DXdm3feTspWdvvhQ8XG7HTxE3Tgq7sNE7uwmrRnUdN2Ukk/7VnmBBpD6PM9hgDLh+IgDGx6BWN2aGI/MhFxqvcIUx4Ir7yRDJEEfE53HXAf77zsUnET4jZ0k7FQVOQoYthWQYmpQ3lfu8AYpms3HLC0aOEhix5/FwDdpmt59+7cTT0ouVrzdqJ6UecF8Ae2llE/Obt8dxxbBBswx6nLItoHPuPd/5/2Mtqo+L39blX5V2GRH6ERu0c7WWRVzuPhQwLxNiMsyoySXN2r7zSPlbnzsDj4gpwhnOxWpQ9T9x3XjTUbWJA0excxw2rnQ9TG1JgmJU+kTyDNGOeTZuxEw7o2ZCI6hMGyjI6F70dXSofoCmgiIA3deNIzHkUi3Yjy5uKPks5X5e6CMrlpuyy0z0qbGL+aXWXv8tou7Zru7Zru7Zru7Zru7Y/D3vr5N027a1TNfku2F9w1Wn787PXm/po1bOTiNWw403NXlcgqI42jhA/1eo2xEfMFeWGMUG86anbCQ3RUR9tHAWI1yetXq93SjbG9BMb+5+V5fLkEb6UuBuTAqI1riE3jEXWP34qGEgQVy/0YVFLbhhXi6eOUQ+CN5Kvk9UEtfqRb86581ryVn2YsQbDF27/9n2L9h8Q3m7NfhtcjPKfv7Zkl9Pq7WArdmM5tHyuPjau0Wh2mN+s26xTTeiDLAAAAABJRU5ErkJggg==",
                "A Very Dangerous material of Engineering"));*/

        Uti uti = new Uti();
        ArrayList<Book> books = new ArrayList<>();
        books = Uti.getAllBooks();
        adapter.setBooks(books);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
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
        overridePendingTransition(R.anim.close_in, R.anim.close_out);
        super.finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
