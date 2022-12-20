package com.example.thomedss.data;

public class UserModel {

    private static String id;
    private static String password;
    private static String name;
    private String photo;
    private String sex;
    private String age;
    private String type;
    private String college;
    private String program;
    private String civil;
    private String citizenship;
    private String religion;
    private String mobile;
    private String other_mobile;
    private String email;
    private String residence;
    private String facebook;
    private String c_person;
    private String c_relationship;
    private String c_primary;
    private String c_email;
    private String c_address;
    private String c_zipcode;

//CONSTRUCTORS

    public UserModel
            (String id,
             String password) {

        this.id = id;
        this.password = password;
    }

    public UserModel
            (String id,
             String name,
             String sex,
             String age,
             String type,
             String college,
             String program,
             String civil,
             String citizenship,
             String religion) {

        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.type = type;
        this.college = college;
        this.program = program;
        this.civil = civil;
        this.citizenship = citizenship;
        this.religion = religion;

    }

    public UserModel
        (String id,
         String mobile,
         String other_mobile,
         String email,
         String residence,
         String facebook,
         String c_person,
         String c_relationship,
         String c_primary,
         String c_email,
         String c_address,
         String c_zipcode) {

            this.id = id;
            this.mobile = mobile;
            this.other_mobile = other_mobile;
            this.email = email;
            this.residence = residence;
            this.facebook = facebook;
            this.c_person = c_person;
            this.c_relationship = c_relationship;
            this.c_primary = c_primary;
            this.c_email = c_email;
            this.c_address = c_address;
            this.c_zipcode = c_zipcode;
    }

    public UserModel() {
    }

//GETTERS & SETTERS

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getCivil() {
        return civil;
    }

    public void setCivil(String civil) {
        this.civil = civil;
    }

    public String getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOther_mobile() {
        return other_mobile;
    }

    public void setOther_mobile(String other_mobile) {
        this.other_mobile = other_mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getC_person() {
        return c_person;
    }

    public void setC_person(String c_person) {
        this.c_person = c_person;
    }

    public String getC_relationship() {
        return c_relationship;
    }

    public void setC_relationship(String c_relationship) {
        this.c_relationship = c_relationship;
    }

    public String getC_primary() {
        return c_primary;
    }

    public void setC_primary(String c_primary) {
        this.c_primary = c_primary;
    }

    public String getC_email() {
        return c_email;
    }

    public void setC_email(String c_email) {
        this.c_email = c_email;
    }

    public String getC_address() {
        return c_address;
    }

    public void setC_address(String c_address) {
        this.c_address = c_address;
    }

    public String getC_zipcode() {
        return c_zipcode;
    }

    public void setC_zipcode(String c_zipcode) {
        this.c_zipcode = c_zipcode;
    }
}
