package com.jongyun.tdkd;


import android.app.Application;

import org.mockito.MockitoAnnotations;
import org.robolectric.TestLifecycleApplication;

import java.lang.reflect.Method;

public class TestTDKDApplication extends Application implements TestLifecycleApplication {

    private Object testSubject;

    @Override
    public void onCreate() {
        super.onCreate();
        ObjectGraphWrapper.createObjectGraph(new TestApplicationModule());
    }

    public static void inject(Object object) {
        ObjectGraphWrapper.inject(object);
    }

    @Override
    public void beforeTest(Method method) {

    }

    @Override
    public void prepareTest(Object test) {
        this.testSubject = test;
        MockitoAnnotations.initMocks(test);
    }

    @Override
    public void afterTest(Method method) {

    }


}
