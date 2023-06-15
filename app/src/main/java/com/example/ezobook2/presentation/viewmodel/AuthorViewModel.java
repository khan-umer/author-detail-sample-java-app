package com.example.ezobook2.presentation.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.ezobook2.domain.modal.AuthorModel;
import com.example.ezobook2.domain.repository.AuthorRepository;

public class AuthorViewModel extends ViewModel {
    private static final String TAG = "TAG:AuthorViewModel";
    private AuthorRepository authorRepository;
    private MutableLiveData<AuthorModel> authorMutableLiveData;

    public AuthorViewModel() {
        authorRepository = new AuthorRepository();
        authorMutableLiveData = new MutableLiveData<>();
    }

    public LiveData<AuthorModel> getAuthorLiveData() {
        return authorMutableLiveData;
    }

    public void getAuthorDetails() {
        authorRepository.getAuthorDetails(new AuthorRepository.AuthorRepositoryCallback() {
            @Override
            public void onSuccess(AuthorModel data) {
                authorMutableLiveData.postValue(data);
            }

            @Override
            public void onFailure(String errorMessage) {
                // Handle error case
                Log.d(TAG, "onFailure: "+ errorMessage);
            }
        });
    }




}
