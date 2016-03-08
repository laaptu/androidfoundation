package com.lft.espressointro.chiuki.recylerview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lft.espressointro.R;

import timber.log.Timber;

/**
 * Here this activity can only be launched with some bundle params,
 * so when we start this activity through test i.e. RecyclerViewActivityTest
 * then this will crash, as it won't contain any extra params while launching
 * the activity. So for that we need, intent extras with Espresso rules*/
public class RecyclerViewActivity extends AppCompatActivity {

    private int selectedItemPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        Bundle params = getIntent().getExtras();
        selectedItemPosition = params.getInt(ITEM_POSITION, selectedItemPosition);
        Timber.d("SelectedItem Position =%d",selectedItemPosition);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        final TextView textView = (TextView) findViewById(R.id.text);
        textView.setBackgroundColor(Color.LTGRAY);
        textView.setVisibility(View.GONE);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new NumberedAdapter(30, new NumberedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                textView.setText(String.valueOf(position));
                textView.setVisibility(View.VISIBLE);
            }
        }));
    }

    public static final String ITEM_POSITION = "itemPosition";

    public static Intent launchActivity(Context context, int selectedItem) {
        Intent intent = new Intent(context, RecyclerViewActivity.class);
        Bundle params = new Bundle();
        params.putInt(ITEM_POSITION, selectedItem);
        intent.putExtras(params);
        return intent;
    }
}