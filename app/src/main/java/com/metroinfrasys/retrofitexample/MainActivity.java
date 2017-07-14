package com.metroinfrasys.retrofitexample;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import com.metroinfrasys.retrofitexample.adapters.RecycleViewAdapter;
import com.metroinfrasys.retrofitexample.interfaces.ApiInterface;
import com.metroinfrasys.retrofitexample.model.AnswerModel;
import com.metroinfrasys.retrofitexample.model.Item;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private ApiInterface apiService;
    private List<Item> listItems = new ArrayList<>();
    private ProgressDialog mProgressDialog;
    private RecyclerView recyclerView;
    private RecycleViewAdapter historyAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiService = ApiClient.getApiClient().create(ApiInterface.class);
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);

        setApiCall();
    }

    private void setApiCall() {
        showProgressDialog("Please wait...");
        apiService.getAnswerData().enqueue(new Callback<AnswerModel>() {
            @Override
            public void onResponse(Call<AnswerModel> call, Response<AnswerModel> response) {
                if (response.body() == null) {
                    showToast("null");
                    hideProgressDialog();
                } else {
                    showToast("Success");
                    listItems = response.body().getItems();
                    hideProgressDialog();
                    setRecycleViewAdapter();

                }
            }


            @Override
            public void onFailure(Call<AnswerModel> call, Throwable t) {
                showToast("Respone Fail");
                hideProgressDialog();

            }
        });
    }

    private void setRecycleViewAdapter() {
        recyclerView = (RecyclerView) findViewById(R.id.recycleview);
        historyAdapter = new RecycleViewAdapter(MainActivity.this, listItems);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(historyAdapter);
    }

    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

    }

    public void showProgressDialog(String message) {
        hideProgressDialog();
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            mProgressDialog.setCancelable(false);
            if (message != null) {
                mProgressDialog.setMessage(message);
            } else {
                mProgressDialog.setMessage("Loading...");
            }
            mProgressDialog.show();
        } else {
            Log.e("FreeAds", "mProgressDialog is not Null. So Not able to show Progress Dialog.");
        }
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }
}
