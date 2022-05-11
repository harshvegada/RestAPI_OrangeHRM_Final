package com.qa.tc.utill;

import com.github.javafaker.Faker;

import java.util.Locale;

public class GenerateData {
    Faker faker;

    public GenerateData() {
        faker = new Faker(new Locale("en-IND"));
    }

    public String getFullName() {
        return faker.name().fullName();
    }

    public String getFirstName() {
        return faker.name().firstName();
    }

    public String getLastName() {
        return faker.name().lastName();
    }

    public String getJobDescritpion() {
        return faker.job().field() + " " + faker.job().keySkills();
    }

    public String getRandomNotes() {
        return faker.job().field();
    }

    public String getJobTitleName() {
        return faker.job().title();
    }

    public String getJobSpecification() {
        return faker.job().position();
    }

}
