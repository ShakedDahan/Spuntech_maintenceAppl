package com.example.spuntech.ui.faults;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class faultsviewmodel extends ViewModel {

    private MutableLiveData<String> mText;

    public faultsviewmodel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}