package com.dennnoukishidann.workplatform.firebase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.dennnoukishidann.workplatform.R;
import com.dennnoukishidann.workplatform.instanceClass.User;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import org.apache.commons.text.CharacterPredicates;
import org.apache.commons.text.RandomStringGenerator;

/**
 * Created by gotounaoto on 2018/07/13.
 */

public class WritingInFireBase {

    //FireBaseに書き込む処理


    public static void addUser(User user, final OnCompleteListener completeListener) {
        //ユーザーを追加する
        //メールアドレスが重複してないことを確認してから呼び出される

        //Randomに生成するuserID
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('0', 'z')
                .filteredBy(CharacterPredicates.LETTERS, CharacterPredicates.DIGITS)
                .build();
        //.filteredByでLETTERつまり文字と,DIGITSつまり数字を指定している
        final String userId = generator.generate(12);

        //userにuserIdを追加する
        user.setUserId(userId);

        KeyInFireBase.returnUsersDatabase().child(userId).setValue(user, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError databaseError, @NonNull DatabaseReference databaseReference) {
                Bundle bundle = new Bundle();
                bundle.putString(String.valueOf(R.string.BundleUserIdKey), userId);
                completeListener.completeFirebaseProcessing(bundle);

                //ここでリスナーをremove
                KeyInFireBase.returnUsersDatabase().removeValue(this);
                return;
            }
        });
    }


    //リスナーたち

    //完了(completeFirebaseProcessing)したことを知らせるリスナー

    public interface OnCompleteListener {
        void completeFirebaseProcessing(Bundle bundle);
    }

}