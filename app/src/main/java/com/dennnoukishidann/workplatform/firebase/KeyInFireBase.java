package com.dennnoukishidann.workplatform.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by gotounaoto on 2018/07/13.
 */

public class KeyInFireBase {

    //FireBaseの基幹となる機能を扱う（WritingとReadingのやつ両方で使うもの）

    public static String USERS_DATA_BASE_KEY = "users";

    public static String WORKS_DATA_BASE_KEY = "works";

    public static FirebaseDatabase returnDataBase() {
        //FireBaseにアクセスする大元
        return FirebaseDatabase.getInstance();
    }

    public static DatabaseReference returnUsersDatabase() {
        //userの枝以下にアクセスするための元
        return returnDataBase().getReference(USERS_DATA_BASE_KEY);
    }

    public static DatabaseReference returnWorksDatabase() {
        //worksの枝以下にアクセスするための元
        return returnDataBase().getReference(WORKS_DATA_BASE_KEY);
    }
}
