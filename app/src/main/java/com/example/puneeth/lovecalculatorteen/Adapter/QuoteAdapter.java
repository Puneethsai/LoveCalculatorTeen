package com.example.puneeth.lovecalculatorteen.Adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.example.puneeth.lovecalculatorteen.Modal.QuoteModal;
import com.example.puneeth.lovecalculatorteen.R;
import com.example.puneeth.lovecalculatorteen.Utils.SqliteHelper;

import java.util.ArrayList;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.MyViewHolder> {

    private ArrayList<QuoteModal> quoteModals;
    private Context context;
    private ItemListener itemListener;
    public SqliteHelper sqliteHelper;

    public QuoteAdapter(Context context, ArrayList<QuoteModal> quoteModals, ItemListener itemListener) {
        this.quoteModals = quoteModals;
        this.context = context;
        this.itemListener = itemListener;
        sqliteHelper = new SqliteHelper(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.quote_list_row_test, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        int lastPosition = -1;
        final QuoteModal quoteModal = quoteModals.get(position);
        holder.author.setText(quoteModal.getAuthor());
        holder.category.setText(quoteModal.getCategory());
        holder.quote.setText(quoteModal.getQuote());

       /* holder.favourite.setChecked(false);
        holder.favourite.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.not_favourite));

        holder.favourite.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    holder.favourite.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.favourite_active));
                    sqliteHelper.addTable(new QuoteModal(quoteModal.getQuote(), quoteModal.getAuthor(), quoteModal.getCategory()));
                    Log.d("Inseterd into:", "Table");
                    Toast.makeText(context, "Saved to Favourites", Toast.LENGTH_SHORT).show();
                } else
                    holder.favourite.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.not_favourite));                    Toast.makeText(context, "Saved to Favourites", Toast.LENGTH_SHORT).show();

                     Toast.makeText(context, "Removed from Favourites", Toast.LENGTH_SHORT).show();
            }
        });*/

        holder.favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sqliteHelper.addTable(new QuoteModal(quoteModal.getQuote(), quoteModal.getAuthor(), quoteModal.getCategory()));
                Log.d("Inseterd into:", "Table");
                Toast.makeText(context, "Saved to Favourites", Toast.LENGTH_SHORT).show();
            }
        });

        if (position > lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_in_right);
            holder.cardView.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return quoteModals.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView quote, author, category;
        public Button favourite;
        CardView cardView;
        QuoteModal quoteModal;

        MyViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            cardView = itemView.findViewById(R.id.cardview_quote);
            quote = itemView.findViewById(R.id.quote);
            author = itemView.findViewById(R.id.author);
            category = itemView.findViewById(R.id.category);
            favourite = itemView.findViewById(R.id.favourite);
        }

        @Override
        public void onClick(View v) {

            if (itemListener != null) {
                itemListener.onItemClick(quoteModal);
            }
        }
    }

    public interface ItemListener {
        void onItemClick(QuoteModal item);
    }
}
