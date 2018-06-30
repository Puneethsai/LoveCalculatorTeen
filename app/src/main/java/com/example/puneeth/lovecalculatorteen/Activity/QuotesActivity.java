package com.example.puneeth.lovecalculatorteen.Activity;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.example.puneeth.lovecalculatorteen.Adapter.QuoteAdapter;
import com.example.puneeth.lovecalculatorteen.Constants.RestAPI;
import com.example.puneeth.lovecalculatorteen.Modal.QuoteModal;
import com.example.puneeth.lovecalculatorteen.R;
import com.example.puneeth.lovecalculatorteen.Utils.OkHttpUtil;
import com.example.puneeth.lovecalculatorteen.Utils.ResponseListenerString;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuotesActivity extends AppCompatActivity implements
        View.OnClickListener, ResponseListenerString, QuoteAdapter.ItemListener {

    public RecyclerView recyclerView;
    public QuoteAdapter quoteAdapter;
    public ArrayList<QuoteModal> quoteModals;
    public ResponseListenerString responseListenerString;
    public QuoteAdapter.ItemListener itemListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes);
        init();
    }

    public void init(){

        recyclerView = findViewById(R.id.quote_recycler);
        quoteModals = new ArrayList<>();
        responseListenerString = this;

        quoteAdapter = new QuoteAdapter(this, quoteModals, itemListener);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(quoteAdapter);
        getQuoteRequest();
    }

    @Override
    public void onClick(View v) {
    }

    public void getQuoteRequest(){

        try {
            OkHttpUtil.postQuoteRequest(this, RestAPI.quoteGeneratorUrl,responseListenerString);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void onResponseReceived(String response) {

        if (response != null){

            try {

                JSONArray jsonArray = new JSONArray(response);

                for (int i=0; i < jsonArray.length(); i++){

                    JSONObject jsonObject = jsonArray.getJSONObject(i);

                    if (jsonObject.length() > 0){

                        if (jsonObject.has("quote") && jsonObject.has("author") && jsonObject.has("category")){

                            QuoteModal quoteModal = new QuoteModal();
                            quoteModal.setQuote(jsonObject.getString("quote"));
                            quoteModal.setAuthor(jsonObject.getString("author"));
                            quoteModal.setCategory(jsonObject.getString("category"));
                            quoteModals.add(quoteModal);
                        }
                    }
                }
                quoteAdapter.notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onItemClick(QuoteModal item) {
        Toast.makeText(getApplicationContext(), item.getQuote() + " is clicked", Toast.LENGTH_SHORT).show();
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = (column + 1) * spacing / spanCount; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
