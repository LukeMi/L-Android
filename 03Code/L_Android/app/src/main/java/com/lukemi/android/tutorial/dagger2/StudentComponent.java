package com.lukemi.android.tutorial.dagger2;

import dagger.Component;

@Component(modules = StudentModule.class)
public interface StudentComponent {
    void inject(StudentActivity studentActivity );
}
