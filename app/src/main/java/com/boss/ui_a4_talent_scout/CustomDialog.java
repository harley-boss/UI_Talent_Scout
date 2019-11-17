package com.boss.ui_a4_talent_scout;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class CustomDialog extends Dialog implements View.OnClickListener{
    private Context context;
    private Button complete;
    private ListView friends;
    private EditText edFriends;
    private ImageView addFriend;
    private ArrayList<String> allFriends = new ArrayList<>();
    ArrayAdapter<String> friendsAdapter;

    public CustomDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        complete = findViewById(R.id.btnFinish);
        edFriends = findViewById(R.id.edAddFriends);
        addFriend = findViewById(R.id.imgNext);
        friends = findViewById(R.id.friendsList);
        complete.setOnClickListener(this);
        edFriends.setOnClickListener(this);
        addFriend.setOnClickListener(this);
        friendsAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, allFriends);
        friends.setAdapter(friendsAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v == complete) {
            this.dismiss();
        } else if (v == addFriend) {
            if (!edFriends.getText().toString().isEmpty()) {
                allFriends.add(edFriends.getText().toString());
                friendsAdapter.notifyDataSetChanged();
            }
            edFriends.setText("");
        }
    }
}
