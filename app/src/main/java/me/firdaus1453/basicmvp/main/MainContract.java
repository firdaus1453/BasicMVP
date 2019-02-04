package me.firdaus1453.basicmvp.main;

import java.util.List;

import me.firdaus1453.basicmvp.model.UserData;

/**
 * Created by firdaus1453 on 2/4/2019.
 */
public interface MainContract {
    interface View{
        void showProgress();
        void hideProgress();
        void showDataListUser(List<UserData> userDataList);
        void showFailureMessage(String msg);
    }

    interface Presenter{
        void getDataListUser();
    }
}
