package com.example.onurbarman.webservice.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.onurbarman.webservice.R;
import com.example.onurbarman.webservice.adapter.UserAdapter;
import com.example.onurbarman.webservice.model.User;
import com.example.onurbarman.webservice.model.UserList;
import com.example.onurbarman.webservice.network.GetUserDataService;
import com.example.onurbarman.webservice.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private UserAdapter adapter;

    private RecyclerView recyclerView;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        /*Create handle for the RetrofitInstance interface*/

        GetUserDataService service = RetrofitInstance.getRetrofitInstance().create(GetUserDataService.class);



        /*Call the method with parameter in the interface to get the employee data*/

        Call<UserList> call = service.getUserData();



        /*Log the URL called*/

        Log.wtf("URL Called", call.request().url() + "");



        call.enqueue(new Callback<UserList>() {

            @Override

            public void onResponse(Call<UserList> call, Response<UserList> response) {

                generateUserList(response.body().getUserArrayList());

            }



            @Override

            public void onFailure(Call<UserList> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();

            }

        });

    }



    /*Method to generate List of employees using RecyclerView with custom adapter*/

    private void generateUserList(ArrayList<User> empDataList) {

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_user_list);



        adapter = new UserAdapter(empDataList);



        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);



        recyclerView.setLayoutManager(layoutManager);



        recyclerView.setAdapter(adapter);

    }
}
