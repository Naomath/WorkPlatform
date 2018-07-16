package com.dennnoukishidann.workplatform.firebase;

import android.support.annotation.NonNull;

import com.dennnoukishidann.workplatform.enums.UserExists;
import com.dennnoukishidann.workplatform.instanceClass.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by gotounaoto on 2018/07/13.
 */


public class ReadingInFireBase {

    //FireBaseから読み込む処理
    //実際に値を返したりするのは

    //戻り値がUser.class

    public static void searchUserByMailAddress(String mailAddress, final OnReturnUserListener listener) {
        //userIdによりuserをsearchする
        KeyInFireBase.returnUsersDatabase().orderByChild("mailAddress").equalTo(mailAddress).addValueEventListener(new ValueEventListener() {
            //TODO:write
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User item = snapshot.getValue(User.class);
                    listener.returnUser(item);

                    //ここでリスナーをremove
                    KeyInFireBase.returnUsersDatabase().removeEventListener(this);
                    return;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    //戻り値がboolean

    public static void userWithMailAddressExists(String mailAddress, final OnReturnUserExistsListener listener) {
        //引数のメールアドレスのUserが存在しているかを返す

        KeyInFireBase.returnUsersDatabase().orderByChild("mailAddress").equalTo(mailAddress).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    User item = dataSnapshot.getValue(User.class);

                    if (item != null) {
                        listener.returnUserExists(UserExists.EXIST);

                        //ここでリスナーをremove
                        KeyInFireBase.returnUsersDatabase().removeEventListener(this);
                    } else {
                        listener.returnUserExists(UserExists.NON_EXISTS);

                        //ここでリスナーをremove
                        KeyInFireBase.returnUsersDatabase().removeEventListener(this);
                        return;
                    }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    //interfaceたち

    public interface OnReturnUserExistsListener {
        void returnUserExists(UserExists result);
    }

    public interface OnReturnUserListener {
        void returnUser(User user);
    }


}