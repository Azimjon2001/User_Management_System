package com.electsofte.parser.users.get_delete_update;

import java.util.ArrayList;

/**
 * @Author: Azimjon Hakimov
 * @CreateDate: 17.10.20225 :07
 */
public class UserResponseGET  {
    private String message;
    private String errorcode;
    private ArrayList<Data> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorcode() {
        return errorcode;
    }

    public void setErrorcode(String errorcode) {
        this.errorcode = errorcode;
    }

    public ArrayList<Data> getData() {
        return data;
    }

    public void setData(ArrayList<Data> data) {
        this.data = data;
    }
}
class Data {
    private int id;
    private String FirstName;
    private String LastName;
    private String BirthDate;
    private String Email;
    private String Phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(String birthDate) {
        BirthDate = birthDate;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }
}
