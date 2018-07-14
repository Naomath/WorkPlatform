package com.dennnoukishidann.workplatform.firebase;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.dennnoukishidann.workplatform.instanceClass.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by gotounaoto on 2018/07/13.
 */


public class ReadingInFireBase {

    //FireBaseから読み込む処理

    //searchする処理

    public static void searchUserByMailAddress(String mailAddress) {
        //mailAddressによりuserをsearchする
        KeyInFireBase.returnUsersDatabase().orderByChild("mailAddress").equalTo(mailAddress).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public static void returnUser(User user) {
        Log.d("check", user.getMailAddress());
    }
}