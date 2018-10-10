package com.lukemi.android.tutorial.dagger2;

import dagger.Module;
import dagger.Provides;

@Module
public class StudentModule {

    @Provides
    StudentBean providerStudent() {
        return new StudentBean();
    }

}
