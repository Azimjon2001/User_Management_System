package com.electsofte.parser.users.get_delete_update;

/**
 * @Author: Azimjon Hakimov
 * @CreateDate: 26.10.2022 4:17
 */
public class UserRequestPUT {
    private int id;
    private String FirstName;
    private String LastName;
    private String BirthDate;
    private String Email;
    private String Phone;
    public UserRequestPUT() {

    }
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
