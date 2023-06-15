package com.example.ezobook2.retrofit;

import com.example.ezobook2.domain.modal.AuthorModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface AuthorApi {

    @GET("list?limit=10")
    Call<List<AuthorModel>> getAuthors();

}
