package com.wildnettechnologies.cutepuppyapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wildnettechnologies.cutepuppyapp.Adapters.AdapterRecepie;
import com.wildnettechnologies.cutepuppyapp.models.RecepieModel;
import com.wildnettechnologies.cutepuppyapp.webservices.NetworkDataManager;
import com.wildnettechnologies.cutepuppyapp.webservices.PuppyApiClient;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NetworkDataManager.DataManagerCallbacks, NetworkDataManager.genericCallbacks {

    private RecyclerView mRecyclerView;
    private ArrayList<RecepieModel.Result> mRecepieResult = new ArrayList<>();
    AdapterRecepie mAdapterRecepie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMembers();
    }

    private void initMembers() {
        LinearLayoutManager llm = new LinearLayoutManager(this);
        mRecyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(llm);
        mAdapterRecepie = new AdapterRecepie(this, mRecepieResult);
        mRecyclerView.setAdapter(mAdapterRecepie);
        hitInitialService();
    }

    private void hitInitialService() {
        NetworkDataManager.getInstance(this).getRecepies(this, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onError(Throwable error) {

    }

    @Override
    public void onNetworkCallSuccess(RecepieModel response) {
        mRecepieResult.clear();
        mRecepieResult.addAll(response.getResults());
//        mAdapterRecepie = new AdapterRecepie(this, mRecepieResult);
//        mRecyclerView.setAdapter(mAdapterRecepie);
        mAdapterRecepie.notifyDataSetChanged();
    }

    @Override
    public void onNetworkCallFailed(String loginError, String message) {

    }
}
