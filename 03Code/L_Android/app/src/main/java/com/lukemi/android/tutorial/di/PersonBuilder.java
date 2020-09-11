package com.lukemi.android.tutorial.di;

public
class PersonBuilder {
    private Person object = new Person();

    public Person build() {
        return object;
    }

    public PersonBuilder setName(java.lang.String value) {
        object.setName(value);
        return this;
    }

    public PersonBuilder setAge(int value) {
        object.setAge(value);
        return this;
    }
}
