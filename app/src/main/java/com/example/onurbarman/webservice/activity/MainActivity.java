package com.example.onurbarman.webservice.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.onurbarman.webservice.R;
import com.example.onurbarman.webservice.adapter.UserAdapter;
import com.example.onurbarman.webservice.model.UserModel;
import com.example.onurbarman.webservice.network.Service;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class MainActivity extends AppCompatActivity {

    List<UserModel> usersList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUsersList();

    }


    void  getUsersList()
    {

        new Service().getServiceApi().getUsers().
                subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<UserModel>>() {


                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<UserModel> userModels) {
                        usersList=userModels;
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete()
                    {
                        if(usersList.size()>0)
                        {
                            generateUserList(usersList);
                        }

                    }
                });
    }



    void generateUserList(List<UserModel> empDataList)
    {
        RecyclerView   recyclerView = (RecyclerView) findViewById(R.id.recycler_view_user_list);
        UserAdapter  adapter = new UserAdapter(empDataList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
