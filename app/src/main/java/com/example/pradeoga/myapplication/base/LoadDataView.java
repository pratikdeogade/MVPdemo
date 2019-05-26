package com.example.pradeoga.myapplication.base;

import android.content.Context;

public interface LoadDataView {

    void showLoading();

    void hideLoading();

    void showError(String errorMessage);

    Context context();

}
