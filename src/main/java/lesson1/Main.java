package lesson1;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Person person = new PersonBuilder()
                .address("Address")
                .age(0)
                .country("Country")
                .gender("Gender")
                .firstName("FirstName")
                .middleName("MiddleName")
                .lastName("LastName")
                .phone("Phone")
                .build();
        System.out.println(person);

        ArrayList<Shape> shapes = new ArrayList<>();
        shapes.add(new Square());
        shapes.add(new Triangle());
        shapes.add(new Circle());
        for (Shape shape : shapes) {
            shape.draw();
        }
    }
}
