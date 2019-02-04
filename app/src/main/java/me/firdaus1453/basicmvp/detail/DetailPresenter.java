package me.firdaus1453.basicmvp.detail;

import android.os.Bundle;

import me.firdaus1453.basicmvp.model.SingleUserResponse;
import me.firdaus1453.basicmvp.network.ApiClient;
import me.firdaus1453.basicmvp.network.ApiInterface;
import me.firdaus1453.basicmvp.utils.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by firdaus1453 on 2/4/2019.
 */
public class DetailPresenter implements DetailContract.Presenter {

    private int id;
    private final DetailContract.View view;
    private ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    public DetailPresenter(DetailContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataSingleUser(Bundle bundle) {
        view.showProgress();

        if (bundle != null){
            id = bundle.getInt(Constants.KEY_ID);
        }

        Call<SingleUserResponse> call = apiInterface.getSingleUser(id);
        call.enqueue(new Callback<SingleUserResponse>() {
            @Override
            public void onResponse(Call<SingleUserResponse> call, Response<SingleUserResponse> response) {
                view.hideProgress();

                if (response.body() != null) {
                    SingleUserResponse singleUserResponse = response.body();
                    if (singleUserResponse.getData() != null) {
                        view.showDataSingleUser(singleUserResponse.getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<SingleUserResponse> call, Throwable throwable) {
                view.hideProgress();
                view.showFailureMessage(throwable.getMessage());
            }
        });
    }
}
