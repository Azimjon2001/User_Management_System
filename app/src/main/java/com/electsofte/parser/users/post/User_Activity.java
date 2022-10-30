package com.electsofte.parser.users.post;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.electsofte.parser.R;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @Author: Azimjon Hakimov
 * @CreateDate: 28.09.2022 10:04
 */
public class User_Activity extends AppCompatActivity {
    private EditText firstname, lastname, birthdate, email, phone;
    private Calendar myCalendar;
    private TextView responsetv;
    private Button saveButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_input_activity);
        myCalendar = Calendar.getInstance();
        firstname = findViewById(R.id.firtstname);
        lastname = findViewById(R.id.lastname);
        birthdate = findViewById(R.id.year_of_birth);
        responsetv = findViewById(R.id.response);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        saveButton = findViewById(R.id.savebtn);
        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
        };
        birthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(User_Activity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (firstname.getText()!=null){
                    saveUser(createUserrequest());
                }
            }
        });
    }
    public UserRequest createUserrequest(){
        UserRequest userRequest=new UserRequest();
        userRequest.setFirstName(firstname.getText().toString());
        userRequest.setLastName(lastname.getText().toString());
        userRequest.setBirthDate(birthdate.getText().toString());
        userRequest.setEmail(email.getText().toString());
        userRequest.setPhone(phone.getText().toString());
        return userRequest;
    }
    public  void saveUser(UserRequest userRequest){
        Call<UserResponse> userResponseCall = ApiClient.getuserService().saveUser(userRequest);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @SuppressLint("ShowToast")
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                String responseString = "Добавлен пользователь: \n"+firstname.getText()+" "+lastname.getText();
                responsetv.setText(responseString);
                firstname.setText(null);
                lastname.setText(null);
                email.setText(null);
                phone.setText(null);
                birthdate.setText(null);
                firstname.setText(null);
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(User_Activity.this, "Произошла ошибка!",Toast.LENGTH_SHORT);
            }
        });
    }
    private void updateLabel() {
        String myFormat = "yyyy-MM-dd"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        birthdate.setText(sdf.format(myCalendar.getTime()));
    }
}
