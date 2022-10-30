package com.electsofte.parser.users.get_delete_update;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author: Azimjon Hakimov
 * @CreateDate: 26.10.2022 3:57
 */
public class User_activity_PUT extends AppCompatActivity {
    private Button changebtn;
    private EditText firstname, lastname, birthdate, email, phone;
    private Calendar myCalendar;
    private int id, user_id;
    private ArrayList<Data> contactsList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.electsofte.parser.R.layout.change_activity);
        myCalendar = Calendar.getInstance();
        changebtn = findViewById(com.electsofte.parser.R.id.changebutton);
        firstname = findViewById(com.electsofte.parser.R.id.firtstname1);
        lastname = findViewById(com.electsofte.parser.R.id.lastname1);
        birthdate = findViewById(com.electsofte.parser.R.id.year_of_birth1);
        email = findViewById(com.electsofte.parser.R.id.email1);
        phone = findViewById(com.electsofte.parser.R.id.phone1);

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };

        birthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(User_activity_PUT.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        getUser_detail();
        changebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(User_activity_PUT.this, "Пользователь "+contactsList.get(user_id).getFirstName()+" "+contactsList.get(user_id).getLastName()+" успешно изменен!", Toast.LENGTH_LONG);
                toast.setGravity(10,10,10);
                toast.show();
                updateUser(createUserrequest());
                Intent intent = new Intent(User_activity_PUT.this, UserDetailsActyvity.class);
                intent.putExtra("position", user_id);
                startActivity(intent);
                finish();
            }
        });

    }
    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //date format
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        birthdate.setText(sdf.format(myCalendar.getTime()));
    }

    public void getUser_detail(){
        retrofit2.Call<UserResponseGET> usersData = ApiClient.getUserService().getAllUsers();
        usersData.enqueue(new Callback<UserResponseGET>(){
            @Override
            public void onResponse(retrofit2.Call<UserResponseGET> call, Response<UserResponseGET> response) {
                contactsList = response.body().getData();
                Intent intent = getIntent();
                if(intent.getExtras() !=null){
                    user_id = intent.getIntExtra("id",0);
                    id = contactsList.get(user_id).getId();
                   String userfirstnamedata = contactsList.get(user_id).getFirstName();
                   String userlastnamedata = contactsList.get(user_id).getLastName();
                   String useremail = contactsList.get(user_id).getEmail();
                   String userphone_string = contactsList.get(user_id).getPhone();
                   String birthdatedata = contactsList.get(user_id).getBirthDate();

                   firstname.setText(userfirstnamedata);
                   lastname.setText(userlastnamedata);
                   email.setText(useremail);
                   phone.setText(userphone_string);
                   birthdate.setText(birthdatedata);
                }
            }
            @Override
            public void onFailure(retrofit2.Call<UserResponseGET> call, Throwable t) {
            }
        });
    }

    public UserRequestPUT createUserrequest(){
        UserRequestPUT userRequestPUT=new UserRequestPUT();
        userRequestPUT.setFirstName(firstname.getText().toString());
        userRequestPUT.setLastName(lastname.getText().toString());
        userRequestPUT.setBirthDate(birthdate.getText().toString());
        userRequestPUT.setEmail(email.getText().toString());
        userRequestPUT.setPhone(phone.getText().toString());
        return userRequestPUT;
    }

    public void updateUser(UserRequestPUT userRequestPUT){
        retrofit2.Call<UserResponseGET> usersData = ApiClient.updateUserService().updateUser(userRequestPUT, id);
        usersData.enqueue(new Callback<UserResponseGET>() {
            @Override
            public void onResponse(retrofit2.Call<UserResponseGET> call, Response<UserResponseGET> response) {
            }
            @Override
            public void onFailure(Call<UserResponseGET> call, Throwable t) {
                Toast toast = Toast.makeText(User_activity_PUT.this, "Произошла ошибка при изменении данных...", Toast.LENGTH_LONG);
                toast.setGravity(10,10,10);
                toast.show();
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(User_activity_PUT.this, UserDetailsActyvity.class);
        intent.putExtra("position", user_id);
        startActivity(intent);
        finish();
    }
}
