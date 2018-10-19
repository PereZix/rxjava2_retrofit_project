package com.aprezware.rxjava2_project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.aprezware.rxjava2_project.adapter.PostAdapter;
import com.aprezware.rxjava2_project.model.Post;
import com.aprezware.rxjava2_project.retrofit.IMyApi;
import com.aprezware.rxjava2_project.retrofit.RetrofitClient;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    IMyApi iMyApi;
    RecyclerView rcvPosts;
    CompositeDisposable compositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init(){
        compositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RetrofitClient.getInstance();
        iMyApi = retrofit.create(IMyApi.class);

        rcvPosts = findViewById(R.id.rcvPosts);
        rcvPosts.setHasFixedSize(true);
        rcvPosts.setLayoutManager(new LinearLayoutManager(this));

        fetchData();
    }

    //Recover datas
    private void fetchData(){
        compositeDisposable.add(iMyApi.getPosts()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Consumer<List<Post>>() {
            @Override
            public void accept(List<Post> posts) throws Exception {
                //Show datas
                displayData(posts);
            }
        }));
    }

    private void displayData(List<Post> posts) {
        PostAdapter adapter = new PostAdapter(this,posts);
        rcvPosts.setAdapter(adapter);
    }

    @Override
    protected void onStop() {
        compositeDisposable.clear();
        super.onStop();
    }
}
