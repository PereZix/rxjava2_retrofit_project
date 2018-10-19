package com.aprezware.rxjava2_project.retrofit;

import android.database.Observable;

import com.aprezware.rxjava2_project.model.Post;

import java.util.List;
import retrofit2.http.GET;

/**
 * Created by alvaro.perez on 17/10/2018.
 */

public interface IMyApi {
    @GET("posts")
    io.reactivex.Observable<List<Post>> getPosts();
}
