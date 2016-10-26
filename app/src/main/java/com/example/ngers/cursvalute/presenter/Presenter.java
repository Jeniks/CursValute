package com.example.ngers.cursvalute.presenter;

import android.content.Context;


/**
 * Created by ngers on 17.10.16.
 */

public interface Presenter {

    void create(Context context);

    void resume();

    void pause();

    void destroy();
}
