package com.electsofte.parser.users.get_delete_update;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.electsofte.parser.R;
import com.electsofte.parser.users.MainActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author: Azimjon Hakimov
 * @CreateDate: 17.10.2022 5:06
 */
public class UsersListGET extends AppCompatActivity implements UserAdapter.ClickedItem{

    Toolbar toolbar;
    RecyclerView recyclerView;
    UserAdapter usersAdapter;
    ArrayList<Data> contactsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_get);
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        usersAdapter = new UserAdapter(this);
        getAllUsers();
    }
    public void getAllUsers(){
        Call<UserResponseGET> usersData = ApiClient.getUserService().getAllUsers();
        usersData.enqueue(new Callback<UserResponseGET>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<UserResponseGET> call, Response<UserResponseGET> response) {
                    assert response.body() != null;
                    contactsList = response.body().getData();
                    usersAdapter.setData(contactsList);
                    recyclerView.setAdapter(usersAdapter);
            }
            @Override
            public void onFailure(Call<UserResponseGET> call, Throwable t) {
                Toast toast = Toast.makeText(UsersListGET.this, "Вы не подключились к локальной сети сервера...", Toast.LENGTH_LONG);
                toast.setGravity(10,10,10);
                toast.show();
            }
        });
    }
    @Override
    public void ClickedUser(Data ContactInfo, int position) {
        Intent intent = new Intent(this,UserDetailsActyvity.class);
        intent.putExtra("position", position);
        startActivity(intent);
        finish();
    }
   
}
