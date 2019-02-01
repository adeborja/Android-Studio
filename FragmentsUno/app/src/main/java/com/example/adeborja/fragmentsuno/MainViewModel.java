package com.example.adeborja.fragmentsuno;

import android.arch.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private boolean tablet;

    public boolean isTablet() {
        return tablet;
    }

    public void setTablet(boolean tablet) {
        this.tablet = tablet;
    }
}
