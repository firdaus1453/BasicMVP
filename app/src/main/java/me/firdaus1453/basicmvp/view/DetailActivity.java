package me.firdaus1453.basicmvp.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.firdaus1453.basicmvp.R;
import me.firdaus1453.basicmvp.detail.DetailContract;
import me.firdaus1453.basicmvp.detail.DetailPresenter;
import me.firdaus1453.basicmvp.model.UserData;
import me.firdaus1453.basicmvp.utils.Constants;

public class DetailActivity extends AppCompatActivity implements DetailContract.View {

    @BindView(R.id.imgAvatar)
    ImageView imgAvatar;
    @BindView(R.id.txtFirst)
    TextView txtFirst;
    @BindView(R.id.txtLast)
    TextView txtLast;

    private DetailPresenter detailPresenter = new DetailPresenter(this);
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        detailPresenter.getDataSingleUser(bundle);
    }

    @Override
    public void showProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading ...");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showDataSingleUser(UserData userData) {
        txtFirst.setText(userData.getFirstName());
        txtLast.setText(userData.getLastName());

        RequestOptions options = new RequestOptions().error(R.drawable.ic_broken_image).placeholder(R.drawable.ic_broken_image);

        Glide.with(this).load(userData.getAvatar()).apply(options).into(imgAvatar);
    }

    @Override
    public void showFailureMessage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
