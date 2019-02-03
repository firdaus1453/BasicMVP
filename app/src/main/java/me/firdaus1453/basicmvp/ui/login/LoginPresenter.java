package me.firdaus1453.basicmvp.ui.login;

import me.firdaus1453.basicmvp.model.login.LoginBody;
import me.firdaus1453.basicmvp.model.login.LoginResponse;
import me.firdaus1453.basicmvp.network.ApiClient;
import me.firdaus1453.basicmvp.network.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by firdaus1453 on 2/4/2019.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public LoginPresenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void onLoginClick(String email, String password) {

        //validate email and password
        if (email == null || email.isEmpty()) {
            view.loginFailure("Email kosong");
            return;
        }

        if (password == null || password.isEmpty()) {
            view.loginFailure("Password kosong");
            return;
        }

        // Buat object LoginBody
        LoginBody loginBody = new LoginBody();
        // Mengisi loginbody
        loginBody.setEmail(email);
        loginBody.setPassword(password);

        view.showProgress();

        Call<LoginResponse> call = apiInterface.postLogin(loginBody);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                view.hideProgress();
                if (response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    if (loginResponse.getToken() != null) {
                        view.loginSuccess(loginResponse.getToken());
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable throwable) {
                view.hideProgress();
                view.loginFailure(throwable.getMessage());
            }
        });

    }


}
