package br.com.jardel.converter.mocks;

import br.com.jardel.data.entity.Person;
import br.com.jardel.data.vo.PersonVO;

import java.util.ArrayList;
import java.util.List;

public class MockPerson {

    public List<Person> mockPersonList() {
        List<Person> list = new ArrayList<>();

        for (int i = 0; i < 14; i++) {
            list.add(mockPerson(i));
        }

        return list;
    }

    public List<PersonVO> mockPersonVOList() {
        List<PersonVO> list = new ArrayList<>();

        for (int i = 0; i < 14; i++) {
            list.add(mockPersonVO(i));
        }

        return list;
    }

    public Person mockPerson(Integer number) {
        Person person = new Person();
        person.setId(number.longValue());
        person.setFirstName("First Name is " + number);
        person.setLastName("Last Name is " + number);
        person.setAddress("Address is " + number);
        person.setGender(number % 2 == 0 ? "Male" : "Female");

        return person;
    }

    public PersonVO mockPersonVO(Integer number) {
        PersonVO person = new PersonVO();
        person.setId(number.longValue());
        person.setFirstName("First Name is " + number);
        person.setLastName("Last Name is " + number);
        person.setAddress("Address is " + number);
        person.setGender(number % 2 == 0 ? "Male" : "Female");

        return person;
    }
}
