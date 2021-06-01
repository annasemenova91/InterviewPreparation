package lesson1;

public class PersonBuilder {
    private final Person person;

    public PersonBuilder() {
        this.person = new Person();
    }

    public PersonBuilder firstName(String firstName) {
        person.setFirstName(firstName);
        return this;
    }

    public PersonBuilder lastName(String lastName) {
        person.setLastName(lastName);
        return this;
    }

    public PersonBuilder middleName(String middleName) {
        person.setMiddleName(middleName);
        return this;
    }

    public PersonBuilder country(String country) {
        person.setCountry(country);
        return this;
    }

    public PersonBuilder address(String address) {
        person.setAddress(address);
        return this;
    }

    public PersonBuilder phone(String phone) {
        person.setPhone(phone);
        return this;
    }

    public PersonBuilder age(int age) {
        person.setAge(age);
        return this;
    }

    public PersonBuilder gender(String gender) {
        person.setGender(gender);
        return this;
    }

    public Person build() {
        return person;
    }
}
