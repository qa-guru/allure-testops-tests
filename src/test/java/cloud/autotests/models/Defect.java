package cloud.autotests.models;

import com.github.javafaker.Faker;

public class Defect {

    private String name;
    private String description;

    public Defect(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Defect(){
        Faker faker = new Faker();
        this.name = faker.book().author();
        this.description = faker.book().title();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public String generateNewDescription() {
        Faker faker = new Faker();
        this.description = faker.book().title();
        return this.description;
    }

    public String generateNewName() {
        Faker faker = new Faker();
        this.name = faker.book().author();
        return this.name;
    }
}
