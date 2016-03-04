package com.lft.espressointro.chiuki.list;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.lft.espressointro.R;

/**
 * Created by laaptu on 3/4/16.
 */
public class SimpleListActivity extends AppCompatActivity {
    private static final int COUNT = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simplelist);


        final TextView textView = (TextView) findViewById(R.id.text);
        textView.setBackgroundColor(Color.LTGRAY);
        textView.setVisibility(View.GONE);

        ListView listView = (ListView) findViewById(R.id.list);

        final Item[] items = new Item[COUNT];
        for (int i = 0; i < COUNT; ++i) {
            items[i] = new Item(i);
        }
        ArrayAdapter<Item> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textView.setText(items[position].toString());
                textView.setVisibility(View.VISIBLE);
            }
        });
    }

    public static class Item {
        private final int value;

        public Item(int value) {
            this.value = value;
        }

        public String toString() {
            return String.valueOf(value);
        }
    }
}
