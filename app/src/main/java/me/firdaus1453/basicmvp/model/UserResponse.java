package me.firdaus1453.basicmvp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by firdaus1453 on 2/4/2019.
 */
public class UserResponse {
    @SerializedName("data")
    private List<UserData> data;

    public List<UserData> getUserDataList() {
        return data;
    }

    public void setData(List<UserData> data) {
        this.data = data;
    }
}
