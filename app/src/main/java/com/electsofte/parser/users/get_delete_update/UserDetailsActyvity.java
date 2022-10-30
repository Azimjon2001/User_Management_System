package com.electsofte.parser.users.get_delete_update;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.electsofte.parser.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author: Azimjon Hakimov
 * @CreateDate: 17.10.2022 5:08
 */
public class UserDetailsActyvity extends AppCompatActivity {
    public TextView username,email,userphone, birth_date;
    String usernamedata, useremail,userphone_string,birthdate;
    private Button changebtn, deletebtn;
    private int id, user_id;
    private ArrayList<Data> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info_activity);

        username  = (TextView) findViewById(R.id.username);
        email = (TextView)findViewById(R.id.email);
        userphone = (TextView)findViewById(R.id.phone);
        birth_date = (TextView)findViewById(R.id.birthdate);
        changebtn=findViewById(R.id.changebtn);
        deletebtn=findViewById(R.id.delbtn);

        changebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(UserDetailsActyvity.this, User_activity_PUT.class);
                intent.putExtra("id", user_id);
                startActivity(intent);
                finish();
            }
        });
        getUser_detail();
        deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(UserDetailsActyvity.this, "Пользователь "+contactsList.get(user_id).getFirstName()+" "+contactsList.get(user_id).getLastName()+" успешно удалён!", Toast.LENGTH_LONG);
                toast.setGravity(10,10,10);
                toast.show();
                deleteUser();
                username.setText(null);
                email.setText(null);
                birth_date.setText(null);
                userphone.setText(null);
                Intent intent = new Intent(UserDetailsActyvity.this, UsersListGET.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void getUser_detail(){
        Call<UserResponseGET> usersData = ApiClient.getUserService().getAllUsers();
        usersData.enqueue(new Callback<UserResponseGET>(){
            @Override
            public void onResponse(Call<UserResponseGET> call, Response<UserResponseGET> response) {
             contactsList = response.body().getData();
                Intent intent = getIntent();
                if(intent.getExtras() !=null){
                    user_id= intent.getIntExtra("position",0);
                        id=contactsList.get(user_id).getId();
                        usernamedata = contactsList.get(user_id).getFirstName() + " " + contactsList.get(user_id).getLastName();
                        useremail = contactsList.get(user_id).getEmail();
                        userphone_string = contactsList.get(user_id).getPhone();
                        birthdate = contactsList.get(user_id).getBirthDate();

                        username.setText(usernamedata);
                        email.setText(useremail);
                        birth_date.setText(birthdate);
                        userphone.setText(userphone_string);
                    }
                }
            @Override
            public void onFailure(Call<UserResponseGET> call, Throwable t) {

            }
        });
    }
    public void deleteUser(){
        Call<UserResponseGET> usersData = ApiClient.deleteUserService().deleteUser(id);
        usersData.enqueue(new Callback<UserResponseGET>() {
            //@RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(Call<UserResponseGET> call, Response<UserResponseGET> response) {
            }
            @Override
            public void onFailure(Call<UserResponseGET> call, Throwable t) {
                Toast toast = Toast.makeText(UserDetailsActyvity.this, "Произошла ошибка при удалении..", Toast.LENGTH_LONG);
                toast.setGravity(10,10,10);
                toast.show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(UserDetailsActyvity.this, UsersListGET.class);
        startActivity(intent);
        finish();
    }
}
