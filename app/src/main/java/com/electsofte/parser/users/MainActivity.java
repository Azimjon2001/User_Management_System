package com.electsofte.parser.users;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.electsofte.parser.R;
import com.electsofte.parser.users.post.User_Activity;
import com.electsofte.parser.users.get_delete_update.UsersListGET;

public class MainActivity  extends AppCompatActivity {
private Button buttonAdd, buttonShow;

    @SuppressLint("ResourceAsColor")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonAdd=findViewById(R.id.add);
        buttonShow=findViewById(R.id.read);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, User_Activity.class);
                startActivity(intent);
            }
        });
        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, UsersListGET.class);
                startActivity(intent);
            }
        });
    }
}