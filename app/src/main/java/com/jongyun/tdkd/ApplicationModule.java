package com.jongyun.tdkd;

import android.content.Context;
import android.util.Log;

import com.jongyun.tdkd.activity.MainActivity;
import com.jongyun.tdkd.activity.MemberRegisterActivity;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(
        injects = {
                MainActivity.class,
                MemberRegisterActivity.class
        }
)
public class ApplicationModule {
    private static final String TAG = ApplicationModule.class.getSimpleName();
    private Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    public ApplicationModule() {
        Log.w(ApplicationModule.class.getSimpleName(), "TDKD DataBaseHelper and RestAdaptor");
    }

    @Provides
    @Singleton
    MainActivity providesMainActivity() {
        return new MainActivity();
    }

    @Provides
    @Singleton
    MemberRegisterActivity providesMemberRegisterActivity() {
        return new MemberRegisterActivity();
    }

}
