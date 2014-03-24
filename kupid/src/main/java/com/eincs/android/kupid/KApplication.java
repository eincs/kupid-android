package com.eincs.android.kupid;

import android.app.Application;

import com.eincs.android.kupid.controller.Controller;
import com.eincs.android.kupid.controller.DummyController;
import com.eincs.android.kupid.database.DummyRepository;
import com.eincs.android.kupid.database.Repository;
import com.google.common.base.Preconditions;

/**
 * Application implementation of the application.
 */
public class KApplication extends Application {

    private static KApplication INSTANCE;
    private static Repository REPOSITORY;
    private static Controller CONTROLLER;

    public static KApplication getInstance() {
        Preconditions.checkState(INSTANCE != null, "KApplication is not created yet.");
        return INSTANCE;
    }

    public static Repository getRepositoy() {
        return REPOSITORY;
    }

    public static Controller getController() {
        return CONTROLLER;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        REPOSITORY = new DummyRepository();
        CONTROLLER = new DummyController();
    }
}
