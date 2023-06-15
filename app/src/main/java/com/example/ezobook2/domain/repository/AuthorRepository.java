package com.example.ezobook2.domain.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.ezobook2.domain.modal.AuthorModel;
import com.example.ezobook2.retrofit.AuthorApi;
import com.example.ezobook2.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthorRepository {
    private static final String TAG = "AuthorRepository";

    MutableLiveData<List<AuthorModel>> authorResponseLiveData;

    public MutableLiveData<List<AuthorModel>> getAuthorDetails() {

        authorApiService.getAuthors().enqueue(new Callback<List<AuthorModel>>() {
            @Override
            public void onResponse(Call<List<AuthorModel>> call, Response<List<AuthorModel>> response) {
                if (response.isSuccessful()) {
                    authorResponseLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<AuthorModel>> call, Throwable t) {
                t.printStackTrace();
                Log.d(TAG, "" + t.getLocalizedMessage());
                authorResponseLiveData.setValue(null);
            }
        });
        return authorResponseLiveData;

    }


    private AuthorApi authorApiService;

    public AuthorRepository() {
        authorApiService = RetrofitClient.getInstance().create(AuthorApi.class);
    }

    public void getAuthorDetails(final AuthorRepositoryCallback callback) {
        Call<List<AuthorModel>> call = authorApiService.getAuthors();
        call.enqueue(new Callback<List<AuthorModel>>() {
            @Override
            public void onResponse(Call<List<AuthorModel>> call, Response<List<AuthorModel>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null && !response.body().isEmpty()) {
                        callback.onSuccess(response.body().get(0));
                    }
                } else {
                    callback.onFailure("Failed to fetch author details");
                }
            }

            @Override
            public void onFailure(Call<List<AuthorModel>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    public interface AuthorRepositoryCallback {
        void onSuccess(AuthorModel data);

        void onFailure(String errorMessage);
    }
}
