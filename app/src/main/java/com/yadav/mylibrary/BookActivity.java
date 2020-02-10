package com.yadav.mylibrary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity {
    private static final String TAG = "BookActivity";

    private TextView bookName, bookAuthor, bookDescription, bookPages;
    private ImageView bookImage;
    private Button btnCurrentReadBook, btnWantToRead,btnAlreadyRead;

    private Book incomingBook;
    private Uti uti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        overridePendingTransition(R.anim.in,R.anim.out);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initWidgets();

        Intent intent= getIntent();
        int id=intent.getIntExtra("BookId ",0);
        uti= new Uti();
        ArrayList<Book> books= uti.getAllBooks();
        for (Book b: books){
            if (b.getId()==id){
                incomingBook= b;
                bookName.setText(b.getName());
                bookAuthor.setText("Author: "+b.getAuthor());
                bookPages.setText("Pages: "+b.getPages());
                bookDescription.setText("Description: \n"+b.getDescription());
                Glide.with(this)
                        .asBitmap()
                        .load(b.getImageUrl())
                        .into(bookImage);
            }
        }

        btnCurrentReadBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnCurrentReadBookTapped();
            }
        });

        btnAlreadyRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnAlreadyReadTapped();
            }
        });

        btnWantToRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnWantToReadTapped();
            }
        });
    }

    private void btnWantToReadTapped() {
        Log.d(TAG, "btnWantToReadTapped: started");
        boolean doesExist= false;

        ArrayList<Book> wantRead= uti.getWantToReadBooks();
        if (wantRead.contains(incomingBook)){
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setMessage("You already added this book to your Want To Read list.");
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setCancelable(true);
            builder.create().show();
        }
        else {
            ArrayList<Book> alreadyReadBooks= uti.getAlreadyReadBooks();
            if (alreadyReadBooks.contains(incomingBook)){
                AlertDialog.Builder builder= new AlertDialog.Builder(this);
                builder.setMessage("You Already read this book.");
                builder.setTitle("Error");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();

            }else {
                ArrayList<Book> currentlyReadingBooks= uti.getCurrentlyReadingBooks();
                if (currentlyReadingBooks.contains(incomingBook)){
                    AlertDialog.Builder builder= new AlertDialog.Builder(this);
                    builder.setMessage("You are Already read this book.");
                    builder.setTitle("Error");
                    builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.create().show();
                }else {
                    uti.addWantToReadBook(incomingBook);
                    Toast.makeText(this,"The Book "+incomingBook.getName()+" Added to your want to read shelf.",Toast.LENGTH_SHORT).show();
                }

            }
        }
    }

    private void btnAlreadyReadTapped() {
        Log.d(TAG, "btnAlreadyReadTapped: started");

        boolean doesExist=false;

        ArrayList<Book> alreadyRead= uti.getAlreadyReadBooks();

        if (alreadyRead.contains(incomingBook)){
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setMessage("You already added this book to your Already Read Shelf.");
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.create().show();
        }else {

            ArrayList<Book> currentlyReadingBooks= uti.getCurrentlyReadingBooks();
            if (currentlyReadingBooks.contains(incomingBook)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Have you Finished reading this book?");
                builder.setTitle("Error");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        uti.removeCurrentlyReadingBook(incomingBook);
                        uti.addAlreadyReadBook(incomingBook);
                        Toast.makeText(BookActivity.this,"The Book "+incomingBook.getName()+" Added to your already read shelf.",Toast.LENGTH_SHORT).show();

                    }
                });
                builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
            }else {
                uti.addAlreadyReadBook(incomingBook);
                Toast.makeText(this,"The Book "+incomingBook.getName()+" Added to your already read shelf.",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void btnCurrentReadBookTapped() {
        Log.d(TAG, "btnCurrentReadBookTapped: started");
        boolean doesExist= false;

        ArrayList<Book> currentlyReadBook = uti.getCurrentlyReadingBooks();

        if (currentlyReadBook.contains(incomingBook)){
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setMessage("You already added this book to your Currently Reading Book shelf.");
            builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            builder.create().show();
        }else {
            ArrayList<Book> wantToReadBooks= uti.getWantToReadBooks();
            if (wantToReadBooks.contains(incomingBook)){
                AlertDialog.Builder builder= new AlertDialog.Builder(this);
                builder.setMessage("Are you going to start reading this book?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        uti.removeWantToReadBook(incomingBook);
                        uti.addCurrentlyReadBook(incomingBook);
                        Toast.makeText(BookActivity.this,"The book "+incomingBook.getName()+" Added to your currently read shelf.",Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.create().show();
            }else {
                ArrayList<Book> alreadyReadBooks= uti.getAlreadyReadBooks();
                if (alreadyReadBooks.contains(incomingBook)){
                    AlertDialog.Builder builder= new AlertDialog.Builder(this);
                    builder.setMessage("Do you want to read this book again?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            uti.addCurrentlyReadBook(incomingBook);
                            Toast.makeText(BookActivity.this,"The book "+incomingBook.getName()+" Added to your currently read shelf.",Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.create().show();
                }
                else {
                    uti.addCurrentlyReadBook(incomingBook);
                    Toast.makeText(this,"The book "+incomingBook.getName()+" Added to your currently read shelf.",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private void initWidgets() {
        bookName=findViewById(R.id.bookName);
        bookAuthor=findViewById(R.id.bookAuthor);
        bookDescription=findViewById(R.id.bookDescription);
        bookImage=findViewById(R.id.bookImage);
        bookPages=findViewById(R.id.bookPages);
        btnCurrentReadBook=findViewById(R.id.btnCurrentReading);
        btnWantToRead=findViewById(R.id.btnWantToRead);
        btnAlreadyRead=findViewById(R.id.btnAlreadyRead);
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
