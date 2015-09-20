package com.geekband.tingyou.model;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 *
 */
public class UserInfo implements java.io.Externalizable{
    private String userPhoneNumber;
    private String userPassword;
    private String userNickname;
    private String userImageUrl;



    @Override
    public void readExternal(ObjectInput input) throws IOException, ClassNotFoundException {

    }

    @Override
    public void writeExternal(ObjectOutput output) throws IOException {

    }
}
