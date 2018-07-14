package com.dennnoukishidann.workplatform.instanceClass;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by gotounaoto on 2018/07/14.
 */

public class User {

    //userの情報の受け渡しとかに使うためのクラス

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String mailAddress;

    @Getter
    @Setter
    private String password;

    @Getter
    @Setter
    private String userId;

    public User() {
        //empty constructor
    }

    public User(String name, String mailAddress, String password) {
        this.name = name;
        this.mailAddress = mailAddress;
        this.password = password;
    }

}
