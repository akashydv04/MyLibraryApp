package com.yadav.mylibrary;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BookRecViewAdapter extends RecyclerView.Adapter<BookRecViewAdapter.ViewHolder> {
    private static final String TAG = "BookRecViewAdapter";

    private ArrayList<Book> books= new ArrayList<>();
    private Context context;
    private String type="";
    private Uti uti;

    public BookRecViewAdapter(Context context) {
        this.context = context;
        uti = new Uti();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.listbook_rec_view,parent,false);
        ViewHolder holder= new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called");
        holder.bookName.setText(books.get(position).getName());

        holder.bookCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context,BookActivity.class);
                intent.putExtra("BookId " +
                        "",books.get(position).getId());
                context.startActivity(intent);
            }
        });

        holder.bookCard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final Book book = books.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Deleting "+book.getName());
                builder.setMessage("Are You sure you want to delete this book?");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                switch (type){
                    case "want to read":
                        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (uti.removeWantToReadBook(books.get(position))){
                                    notifyDataSetChanged();
                                    Toast.makeText(context,book.getName()+" has successfully deleted.",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).create().show();
                        break;
                    case "already read":
                        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (uti.removeAlreadyReadBook(books.get(position))){
                                    notifyDataSetChanged();
                                    Toast.makeText(context,book.getName()+" has successfully deleted.",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).create().show();
                        break;
                    case "currently reading":
                        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (uti.removeCurrentlyReadingBook(books.get(position))){
                                    notifyDataSetChanged();
                                    Toast.makeText(context,book.getName()+" has successfully deleted.",Toast.LENGTH_SHORT).show();
                                }
                            }
                        }).create().show();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
        Glide.with(context)
                .asBitmap()
                .load(books.get(position).getImageUrl())
                .into(holder.bookImageView);

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView bookImageView;
        private TextView bookName;
        private CardView bookCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            bookImageView   = itemView.findViewById(R.id.bookImage);
            bookName    = itemView.findViewById(R.id.bookName);
            bookCard=itemView.findViewById(R.id.bookCard);
        }

    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public void setType(String type) {
        this.type = type;
    }
}
