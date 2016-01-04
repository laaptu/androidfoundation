package com.immersion.databinding.databinders;

import android.view.View;
import android.widget.Toast;

/**
 * Created by laaptu on 1/4/16.
 */
public class MyHandlers {

    public MyHandlers() {

    }

    public void onClickFriend(View view) {
        System.out.println("Friend Clicked");
        Toast.makeText(view.getContext(), "You are my friend", Toast.LENGTH_SHORT).show();
    }

    public void onClickEnemy(View view) {
        Toast.makeText(view.getContext(), "You are my enemy", Toast.LENGTH_SHORT).show();
    }
}
