package com.immersion.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.immersion.databinding.databinders.MeroImageCallback;
import com.immersion.databinding.databinders.MeroListItem;
import com.immersion.databinding.databinding.ViewHolderLayoutBinding;

import java.util.ArrayList;

/**
 * Created by laaptu on 1/5/16.
 */
public class ListBindingTest extends AppCompatActivity {

    RecyclerView recyclerView;

    public ArrayList<MeroListItem> listItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listbinding);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        listItems = new ArrayList<>();
        listItems.add(
                new MeroListItem(
                        "MeroDog", "http://dogtowndogtraining.com/wp-content/uploads/2012/06/300x300-061-e1340955308953.jpg"
                )
        );
        listItems.add(
                new MeroListItem("MeroAndroid",
                        "http://socialmediaseo.net/wp-content/uploads/2010/05/500px-android-logosvg-300x300.png")
        );
        listItems.add(
                new MeroListItem("MeroLat",
                        "http://www.bonus-level.com/Bonus_Level_Uploads/2012/07/72012-wallpaper-f-300x300.png")
        );

        recyclerView.setAdapter(new OurAdapter());

    }

    public class OurAdapter extends RecyclerView.Adapter<OurViewHolder> implements MeroImageCallback {

        @Override
        public OurViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            final ViewHolderLayoutBinding binding =
                    DataBindingUtil.inflate(
                            LayoutInflater.from(ListBindingTest.this), R.layout.view_holder_layout, parent, false);
            return new OurViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(OurViewHolder holder, int position) {
            holder.ourBinding.setMeroListItem(listItems.get(position));
            holder.ourBinding.setSomeCallback(this);
        }

        @Override
        public int getItemCount() {
            return listItems.size();
        }

        @Override
        public void onImageLoading(String imageUrl) {
            System.out.println("Image Load started for :" + imageUrl);
        }

        @Override
        public void onImageLoaded(String imageUrl) {
            System.out.println("Image Load completed for :" + imageUrl);
        }

        @Override
        public void onImageLoadError(String imageUrl) {
            System.out.println("Image Load error for :" + imageUrl);

        }
    }

    static class OurViewHolder extends RecyclerView.ViewHolder {
        public final ViewHolderLayoutBinding ourBinding;

        public OurViewHolder(ViewHolderLayoutBinding ourBinding) {
            super(ourBinding.getRoot());
            this.ourBinding = ourBinding;
        }
    }
}
