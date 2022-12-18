package com.example.thomedss.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;

import kotlinx.coroutines.internal.LockFreeLinkedListNode;

public class DatabaseHelper extends SQLiteOpenHelper {

//USER_TABLE VARIABLES
    public static final String USER_TABLE = "USER_TABLE";

    //LOGIN INFO
    public static final String USER_ID = "USER_ID";
    public static final String USER_PASS = "USER_PASS";
    public static final String USER_REMEMBER = "USER_REMEMBER";

    //OVERVIEW
    public static final String USER_NAME = "USER_NAME";
    public static final String USER_PHOTO = "USER_PHOTO";
    public static final String USER_SEX = "USER_SEX";
    public static final String USER_AGE = "USER_AGE";
    public static final String USER_TYPE = "USER_TYPE";
    public static final String USER_COLLEGE = "USER_COLLEGE";
    public static final String USER_PROGRAM = "USER_PROGRAM";
    public static final String USER_CIVIL = "USER_CIVIL";
    public static final String USER_CITIZENSHIP = "USER_CITIZENSHIP";
    public static final String USER_RELIGION = "USER_RELIGION";

    //PERSONAL INFO
    public static final String PRIMARY_MOBILE = "PRIMARY_MOBILE";
    public static final String OTHER_MOBILE = "OTHER_MOBILE";
    public static final String EMAIL = "EMAIL";
    public static final String RESIDENCE = "RESIDENCE";
    public static final String FACEBOOK_NAME = "FACEBOOK_NAME";

    //EMERGENCY INFO
    public static final String CONTACT_PERSON = "CONTACT_PERSON";
    public static final String RELATIONSHIP = "RELATIONSHIP";
    public static final String PRIMARY_CONTACT = "PRIMARY_CONTACT";
    public static final String CONTACT_EMAIL = "CONTACT_EMAIL";
    public static final String CONTACT_ADDRESS = "CONTACT_ADDRESS";
    public static final String ZIP_CODE = "ZIP_CODE";

//HEALTH_TABLE VARIABLES
    public static final String HEALTH_TABLE = "HEALTH_TABLE";

    //MEDICAL
    public static final String ILLNESSES = "ILLNESSES";

    //SOCIAL
    public static final String SMOKER = "SMOKER";
    public static final String DRINKER = "DRINKER";

    //OBSTETRIC
    public static final String REGULAR_CYCLE = "REGULAR_CYCLE";
    public static final String CONTRACEPTIVES = "CONTRACEPTIVES";
    public static final String PREGNANT = "PREGNANT";
    public static final String MISCARRIAGE = "MISCARRIAGE";


//DECLARATION_TABLE VARIABLES
    public static final String DECLARATION_TABLE = "DECLARATION_TABLE";


//    public static final String DECLARATION_ID = "DECLARATION_ID";
    public static final String PURPOSE = "PURPOSE";
    public static final String PLACE_OF_VISIT = "PLACE_OF_VISIT";
    public static final String TEMPERATURE = "TEMPERATURE";
    public static final String DECLARATION_DATE = "DECLARATION_DATE";

    //SECTION 1
    public static final String FEVER = "SYMPTOMS";
    public static final String FEVER_DATE = "SYMPTOMS_DATE";
    public static final String COUGH = "COUGH";
    public static final String COUGH_DATE = "COUGH_DATE";
    public static final String COLDS = "COLDS";
    public static final String COLDS_DATE = "COLDS_DATE";
    public static final String THROAT = "THROAT";
    public static final String THROAT_DATE = "THROAT_DATE";
    public static final String SHORTNESS = "SHORTNESS";
    public static final String SHORTNESS_DATE = "SHORTNESS_DATE";
    public static final String DIFFICULTY = "DIFFICULTY";
    public static final String DIFFICULTY_DATE = "DIFFICULTY_DATE";
    public static final String DIARRHEA = "DIARRHEA";
    public static final String DIARRHEA_DATE = "DIARRHEA_DATE";
    public static final String RASH = "RASH";
    public static final String RASH_DATE = "RASH_DATE";
    public static final String TASTE = "TASTE";
    public static final String TASTE_DATE = "TASTE_DATE";
    public static final String SMELL = "SMELL";
    public static final String SMELL_DATE = "SMELL_DATE";

    //SECTION 2
    public static final String ENVIRONMENT = "ENVIRONMENT";
    public static final String DATE_OF_CONTACT = "DATE_OF_CONTACT";
    public static final String COHABITING = "COHABITING";
    public static final String DINING = "DINING";

    //SECTION 3
    public static final String DIAGNOSED = "DIAGNOSED";
    public static final String DIAGNOSED_DATE = "DIAGNOSED_DATE";

    //SECTION 4
    public static final String TRAVEL = "TRAVEL";
    public static final String TRAVEL_DATE = "TRAVEL_DATE";
    public static final String ARRIVAL_DATE = "ARRIVAL_DATE";
    public static final String TRAVEL_COUNTRY = "TRAVEL_COUNTRY";

    //AUTHORIZATION
    public static final String CONSENT = "CONSENT";

//CONSULTATION_TABLE VARIABLES
    public static final String CONSULTATION_TABLE = "CONSULTATION_TABLE";

    //SECTION 1
//    public static final String CONSULTATION_ID = "CONSULTATION_ID";
    public static final String CASE_TYPE = "CASE_TYPE";
    public static final String DATE_CREATED = "DATE_CREATED";
    public static final String CASE_DETAILS = "CASE_DETAILS";
    public static final String SCHEDULE = "SCHEDULE";
    public static final String ATTENDING_DOCTOR = "ATTENDING_DOCTOR";
    public static final String STATUS = "STATUS";


    public DatabaseHelper(@Nullable Context context) {
        super(context, "users.db", null, 1);
    }

    //create a new database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable
                = "CREATE TABLE " + USER_TABLE +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                USER_ID + " TEXT," +
                USER_PASS + " TEXT," +
                USER_REMEMBER + " BOOL," +

                USER_NAME + " TEXT," +
                USER_PHOTO + " TEXT," +
                USER_SEX + " TEXT," +
                USER_AGE + " INTEGER," +
                USER_TYPE + " TEXT," +
                USER_COLLEGE + " TEXT," +
                USER_PROGRAM + " TEXT," +
                USER_CIVIL + " TEXT," +
                USER_CITIZENSHIP + " TEXT," +
                USER_RELIGION + " TEXT," +

                PRIMARY_MOBILE + " TEXT," +
                OTHER_MOBILE + "  TEXT," +
                EMAIL + " TEXT," +
                RESIDENCE + " TEXT," +
                FACEBOOK_NAME + " TEXT," +

                CONTACT_PERSON + " TEXT," +
                RELATIONSHIP + " TEXT," +
                PRIMARY_CONTACT + " TEXT," +
                CONTACT_EMAIL + " TEXT," +
                CONTACT_ADDRESS + " TEXT," +
                ZIP_CODE + " TEXT)";

        String createHealthTable
                = "CREATE TABLE " + HEALTH_TABLE +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                USER_ID + " TEXT," +

                ILLNESSES + " TEXT," +
                SMOKER + "  BOOL," +
                DRINKER + " BOOL," +
                REGULAR_CYCLE + " BOOL," +
                CONTRACEPTIVES + "  BOOL," +
                PREGNANT + " BOOL," +
                MISCARRIAGE + " BOOL)";

        String createDeclarationTable
                = "CREATE TABLE " + DECLARATION_TABLE +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                USER_ID + " TEXT," +

                DECLARATION_DATE + " TEXT," +
                PURPOSE + " TEXT," +
                PLACE_OF_VISIT + "  TEXT," +
                TEMPERATURE + " TEXT," +
                FEVER + " TEXT," +
                FEVER_DATE + "  TEXT," +
                COUGH + " TEXT," +
                COUGH_DATE + "  TEXT," +
                COLDS + " TEXT," +
                COLDS_DATE + "  TEXT," +
                THROAT + " TEXT," +
                THROAT_DATE + "  TEXT," +
                SHORTNESS + " TEXT," +
                SHORTNESS_DATE + "  TEXT," +
                DIFFICULTY + " TEXT," +
                DIFFICULTY_DATE + "  TEXT," +
                DIARRHEA + " TEXT," +
                DIARRHEA_DATE + "  TEXT," +
                RASH + " TEXT," +
                RASH_DATE + "  TEXT," +
                TASTE + " TEXT," +
                TASTE_DATE + "  TEXT," +
                SMELL + " TEXT," +
                SMELL_DATE + "  TEXT," +

                ENVIRONMENT + " TEXT," +
                DATE_OF_CONTACT + " TEXT," +
                COHABITING + "  TEXT," +
                DINING + " TEXT," +

                DIAGNOSED + " TEXT," +
                DIAGNOSED_DATE + "  TEXT," +

                TRAVEL + " TEXT," +
                TRAVEL_DATE + " TEXT," +
                ARRIVAL_DATE + " TEXT," +
                TRAVEL_COUNTRY + " TEXT)";

        String createConsultationTable
                = "CREATE TABLE " + CONSULTATION_TABLE +
                "(ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                USER_ID + " TEXT," +

                CASE_TYPE + " TEXT," +
                DATE_CREATED + "  NUMERIC," +
                CASE_DETAILS + " TEXT," +
                SCHEDULE + " NUMERIC," +
                ATTENDING_DOCTOR + "  TEXT," +
                STATUS + " BOOL)";


        String populate
                = "INSERT INTO " + USER_TABLE +
                "(USER_ID, USER_PASS, USER_REMEMBER, USER_NAME, USER_SEX, USER_AGE, USER_TYPE, USER_COLLEGE, USER_PROGRAM, USER_CIVIL, USER_CITIZENSHIP, USER_RELIGION) " +
                "VALUES ('2020123456', 'pass_one', 'true', 'Keon Aquilino Co Pérez', 'Male', 20, 'Student', 'College of Fine Arts and Design', 'Bachelor of Fine Arts, major in Industrial Design', 'Single', 'Filipino', 'Roman Catholic')," +
                "('2020123456', 'pass_one', 'true', 'Dillon Jaren Diongon Flores', 'Male', 52, 'Employee', 'Conservatory of Music', 'N/A', 'Married', 'Filipino', 'Roman Catholic')," +
                "('2021176201', 'pass_two', 'true', 'Michelle Yanely Azis Pasa', 'Female', 18, 'Student', 'Faculty of Pharmacy', 'Bachelor of Science in Medical Technology', 'Single', 'Filipino', 'Iglesia ni Cristo')," +
                "('2021177362', 'pass_three', 'true', 'Xavier Joel Francisco', 'Male', 20, 'Student', 'College of Tourism and Hospitality Management', 'Bachelor of Science in Hotel and Restaurant Management', 'Single', 'Filipino', 'Roman Catholic')," +
                "('2019315478', 'pass_four', 'true', 'Cassandra Ruby Duarte', 'Female', 21, 'Student', 'Senior High School', 'Physical Education and Sports Track', 'Single', 'Filipino', 'Aglipayan')," +
                "('2019645782', 'pass_five', 'true', 'Ellen Jenny Lacan', 'Female', 19, 'Student', 'College of Information and Computing Sciences', 'Bachelor of Science in Information Technology', 'Married', 'Filipino', 'Roman Catholic')," +
                "('2018302147', 'pass_six', 'true', 'Reid Cortez Vitug', 'Male', 44, 'Employee', 'Faculty of Sacred Theology', 'N/A', 'Married', 'Filipino', 'Roman Catholic')";

        db.execSQL(createUserTable);
        db.execSQL(createHealthTable);
        db.execSQL(createDeclarationTable);
        db.execSQL(createConsultationTable);
        db.execSQL(populate);

    }

    //update the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + HEALTH_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + DECLARATION_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + CONSULTATION_TABLE);
        onCreate(db);
    }

    public UserModel getUser (String id)
    {
        UserModel userInfo = new UserModel();

        String[] columns = new String[] {
                USER_NAME,
                USER_PHOTO,
                USER_SEX,
                USER_AGE,
                USER_TYPE,
                USER_COLLEGE,
                USER_PROGRAM,
                USER_CIVIL,
                USER_CITIZENSHIP,
                USER_RELIGION };

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(USER_TABLE, columns,USER_ID + "=?", new String[] {id}, null, null, null, "1");

        if (c != null)
        {
            c.moveToFirst();
            userInfo.setId(id);
            userInfo.setName(c.getString(0));
            userInfo.setSex(c.getString(2));
            userInfo.setAge(c.getString(3));
            userInfo.setType(c.getString(4));
            userInfo.setCollege(c.getString(5));
            userInfo.setProgram(c.getString(6));
            userInfo.setCivil(c.getString(7));
            userInfo.setCitizenship(c.getString(8));
            userInfo.setReligion(c.getString(9));
        }

        c.close();
        db.close();

        return userInfo;
    }


    public HealthModel getHealth (String id)
    {
        HealthModel healthInfo = new HealthModel();

        String[] columns = new String[] {
                ILLNESSES,
                SMOKER,
                DRINKER,
                REGULAR_CYCLE,
                CONTRACEPTIVES,
                PREGNANT,
                MISCARRIAGE };

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(HEALTH_TABLE, columns,USER_ID + "=?", new String[] {id}, null, null, null, "1");

        if (c != null)
        {
            c.moveToFirst();
            healthInfo.setId(id);
            healthInfo.setIllnesses(c.getString(0));
            healthInfo.setSmoker(c.getInt(1) > 0);
            healthInfo.setDrinker(c.getInt(2) > 0);
            healthInfo.setRegular_cycle(c.getInt(3) > 0);
            healthInfo.setContraceptives(c.getInt(4) > 0);
            healthInfo.setPregnant(c.getInt(5) > 0);
            healthInfo.setMiscarriage(c.getInt(6) > 0);
        }

        c.close();
        db.close();

        return healthInfo;
    }

    public boolean updateUser(UserModel user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        //IDENTIFIER
        String id = user.getId();

        //PERSONAL INFO
        cv.put(PRIMARY_MOBILE, user.getMobile());
        cv.put(OTHER_MOBILE, user.getOther_mobile());
        cv.put(EMAIL, user.getEmail());
        cv.put(RESIDENCE, user.getResidence());
        cv.put(FACEBOOK_NAME, user.getFacebook());

        //EMERGENCY INFO
        cv.put(CONTACT_PERSON, user.getC_person());
        cv.put(RELATIONSHIP, user.getC_relationship());
        cv.put(PRIMARY_CONTACT, user.getC_primary());
        cv.put(CONTACT_EMAIL, user.getC_email());
        cv.put(CONTACT_ADDRESS, user.getC_address());
        cv.put(ZIP_CODE, user.getC_zipcode());

        long updateSuccess = db.update(USER_TABLE, cv, "USER_ID=?", new String[] {id});

        db.close();

        if ( updateSuccess == -1)
        {
            return false;
        } else
        {
            return true;
        }
    }

    public boolean updateMedical(HealthModel health)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        //IDENTIFIER
        String id = health.getId();

        //MEDICAL
        cv.put(USER_ID, health.getId());
        cv.put(ILLNESSES, health.getIllnesses());

        Cursor c = db.query(HEALTH_TABLE, null,USER_ID + "=?", new String[] {id}, null, null, null, "1");

        long updateSuccess;

        if (c != null && c.moveToFirst())
        {
            updateSuccess = db.update(HEALTH_TABLE, cv, "USER_ID=?", new String[] {id});

        } else {
            updateSuccess = db.insert(HEALTH_TABLE, null, cv);
        }

        db.close();

        if ( updateSuccess == -1)
        {
            return false;
        } else
        {
            return true;
        }
    }

    public boolean updateSocial(HealthModel health)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        //IDENTIFIER
        String id = health.getId();

        //SOCIAL
        cv.put(USER_ID, health.getId());
        cv.put(SMOKER, health.isSmoker());
        cv.put(DRINKER, health.isDrinker());


        Cursor c = db.query(HEALTH_TABLE, null,USER_ID + "=?", new String[] {id}, null, null, null, "1");

        long updateSuccess;

        if (c != null && c.moveToFirst())
        {
            updateSuccess = db.update(HEALTH_TABLE, cv, "USER_ID=?", new String[] {id});

        } else {
            updateSuccess = db.insert(HEALTH_TABLE, null, cv);
        }

        db.close();

        if ( updateSuccess == -1)
        {
            return false;
        } else
        {
            return true;
        }
    }

    public boolean updateObstetric(HealthModel health)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        //IDENTIFIER
        String id = health.getId();

        //OBSTETRIC
        cv.put(USER_ID, health.getId());
        cv.put(REGULAR_CYCLE, health.isRegular_cycle());
        cv.put(CONTRACEPTIVES, health.isContraceptives());
        cv.put(PREGNANT, health.isPregnant());
        cv.put(MISCARRIAGE, health.isMiscarriage());

        Cursor c = db.query(HEALTH_TABLE, null,USER_ID + "=?", new String[] {id}, null, null, null, "1");

        long updateSuccess;

        if (c != null && c.moveToFirst())
        {
            updateSuccess = db.update(HEALTH_TABLE, cv, "USER_ID=?", new String[] {id});

        } else {
            updateSuccess = db.insert(HEALTH_TABLE, null, cv);
        }

        db.close();

        if ( updateSuccess == -1)
        {
            return false;
        } else
        {
            return true;
        }
    }

    public boolean createDeclaration(DeclarationModel declaration)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        //IDENTIFIER
        String id = declaration.getId();

        //OVERVIEW
        cv.put(USER_ID, declaration.getId());
        cv.put(DECLARATION_DATE, declaration.getDeclarationDate());
        cv.put(PURPOSE, declaration.getPurpose());
        cv.put(PLACE_OF_VISIT, declaration.getPlaceOfVisit());
        cv.put(TEMPERATURE, declaration.getTemperature());

        //SECTION 1
        cv.put(FEVER, declaration.isFever());
        cv.put(FEVER_DATE, declaration.getFeverDate());
        cv.put(COUGH, declaration.isCough());
        cv.put(COUGH_DATE, declaration.getCoughDate());
        cv.put(COLDS, declaration.isColds());
        cv.put(COLDS_DATE, declaration.getColdsDate());
        cv.put(THROAT, declaration.isThroat());
        cv.put(THROAT_DATE, declaration.getThroatDate());
        cv.put(SHORTNESS, declaration.isShortnessBreath());
        cv.put(SHORTNESS_DATE, declaration.getShortnessBreathDate());
        cv.put(DIFFICULTY, declaration.isDifficultyBreath());
        cv.put(DIFFICULTY_DATE, declaration.getDifficultyBreathDate());
        cv.put(DIARRHEA, declaration.isDiarrhea());
        cv.put(DIARRHEA_DATE, declaration.getDiarrheaDate());
        cv.put(RASH, declaration.isRash());
        cv.put(RASH_DATE, declaration.getRashDate());
        cv.put(TASTE, declaration.isLossTaste());
        cv.put(TASTE_DATE, declaration.getLossTasteDate());
        cv.put(SMELL, declaration.isLossSmell());
        cv.put(SMELL_DATE, declaration.getLossSmellDate());

        //SECTION 2
        cv.put(ENVIRONMENT, declaration.isClosedEnvironment());
        cv.put(DATE_OF_CONTACT, declaration.getContactDate());
        cv.put(COHABITING, declaration.isSameHouse());
        cv.put(DINING, declaration.isSameDining());

        //SECTION 3
        cv.put(DIAGNOSED, declaration.isDiagnosed());
        cv.put(DIAGNOSED_DATE, declaration.getDiagnosedDate());


        //SECTION 4
        cv.put(TRAVEL, declaration.isTravelOutside());
        cv.put(TRAVEL_DATE, declaration.getTravelDate());
        cv.put(ARRIVAL_DATE, declaration.getArrivalDate());
        cv.put(TRAVEL_COUNTRY, declaration.getTravelCountry());

        Cursor c = db.query(DECLARATION_TABLE, null,USER_ID + "=?", new String[] {id}, null, null, null, "1");

        long createSuccess = 0;

        if (c != null && c.moveToFirst())
        {
            createSuccess = db.update(DECLARATION_TABLE, cv, "USER_ID=?", new String[] {id});

        } else {
            createSuccess = db.insert(DECLARATION_TABLE, null, cv);
        }

        db.close();

        if ( createSuccess == -1)
        {
            return false;
        } else
        {
            return true;
        }
    }
}
