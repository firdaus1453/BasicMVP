package me.firdaus1453.basicmvp.ui.login;

import me.firdaus1453.basicmvp.model.login.LoginBody;

/**
 * Created by firdaus1453 on 2/4/2019.
 */
public interface LoginContract {
    interface View{
        void showProgress();
        void hideProgress();
        void loginFailure(String msg);
        void loginSuccess(String token);
    }

    interface Presenter{
        void onLoginClick(String email, String password);
    }
}
