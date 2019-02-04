package me.firdaus1453.basicmvp.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by firdaus1453 on 2/4/2019.
 */
public class SingleUserResponse {
    @SerializedName("data")
    private UserData data;

    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }
}
