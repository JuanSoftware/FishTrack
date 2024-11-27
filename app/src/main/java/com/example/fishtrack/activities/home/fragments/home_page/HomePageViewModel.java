package com.example.fishtrack.activities.home.fragments.home_page;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomePageViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public HomePageViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}