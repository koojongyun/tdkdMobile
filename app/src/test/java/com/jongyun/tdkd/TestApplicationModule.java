package com.jongyun.tdkd;

import com.jongyun.tdkd.activity.MainActivity;
import com.jongyun.tdkd.activity.MainActivityTest;
import com.jongyun.tdkd.activity.MemberRegisterActivity;
import com.jongyun.tdkd.activity.MemberRegisterActivityTest;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static org.mockito.Mockito.mock;

@Module (
        overrides = true,
        includes = ApplicationModule.class,
        injects = {
                MainActivityTest.class,
                MainActivity.class,
                MemberRegisterActivity.class,
                MemberRegisterActivityTest.class
        }
)
public class TestApplicationModule {
    @Provides
    @Singleton
    MainActivity providesMainActivity() {
        return mock(MainActivity.class);
    }

    @Provides
    @Singleton
    MemberRegisterActivity providesMemberActivity() {return mock(MemberRegisterActivity.class); }

}
