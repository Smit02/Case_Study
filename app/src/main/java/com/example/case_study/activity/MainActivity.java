package com.example.case_study.activity;

import android.app.Activity;
import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.case_study.AppExecuter;
import com.example.case_study.Client.ApiUtils;
import com.example.case_study.R;
import com.example.case_study.adapter.RecyclerAdapter;
import com.example.case_study.database.UserRoomDatabase;
import com.example.case_study.model.User;
import com.example.case_study.viewmodel.MainActivityViewModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.FragmentCommunication {

  private RecyclerView recyclerView;
   MainActivityViewModel mainActivityViewModel;
    UserRoomDatabase db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler_view_id);
        db = UserRoomDatabase.getDatabase(getApplicationContext());

        mainActivityViewModel= ViewModelProviders.of(this).get(MainActivityViewModel.class);

        //LiveData
        mainActivityViewModel.getUserData().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {

                initRecyclerView();

            }
        });

    }

    private void initRecyclerView() {

        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(this,mainActivityViewModel.getUserData().getValue(),this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(recyclerAdapter);

    }

    @Override
    public void delete(final int id ) {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Delete this image?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AppExecuter.getInstance().diskIO().execute(new Runnable() {
                            @Override
                            public void run() {

                                db.userDao().deleteByUserId(id);

                            }
                        });
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

    }
}
