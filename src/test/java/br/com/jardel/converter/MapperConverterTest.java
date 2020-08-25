package br.com.jardel.converter;

import br.com.jardel.converter.mocks.MockPerson;
import br.com.jardel.data.entity.Person;
import br.com.jardel.data.vo.PersonVO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MapperConverterTest {

    private MockPerson mockPerson;

    @Before
    public void setUp() {
         mockPerson = new MockPerson();
    }

    @Test
    public void deveConverterObjetoDeOrigemParaDestino() {
        //Given
        Person person = mockPerson.mockPerson(0);
        PersonVO personVO = mockPerson.mockPersonVO(0);

        //When
        PersonVO result = MapperConverter.convert(person, PersonVO.class);

        //Then
        Assert.assertEquals(personVO.getId(), result.getId());
        Assert.assertEquals(personVO.getFirstName(), result.getFirstName());
        Assert.assertEquals(personVO.getLastName(), result.getLastName());
        Assert.assertEquals(personVO.getAddress(), result.getAddress());
        Assert.assertEquals(personVO.getGender(), result.getGender());
    }

    @Test
    public void deveConverterListaDeObjetosOrigemParaDestino() {

        //When
        List<PersonVO> result = MapperConverter.convert(mockPerson.mockPersonList(), PersonVO.class);

        //Then
        PersonVO personVO1 = mockPerson.mockPersonVO(1);
        PersonVO result1 = result.get(1);

        Assert.assertEquals(result.size(), 14);

        Assert.assertEquals(personVO1.getId(), result1.getId());
        Assert.assertEquals(personVO1.getFirstName(), result1.getFirstName());
        Assert.assertEquals(personVO1.getLastName(), result1.getLastName());
        Assert.assertEquals(personVO1.getAddress(), result1.getAddress());
        Assert.assertEquals(personVO1.getGender(), result1.getGender());

        PersonVO personVO7 = mockPerson.mockPersonVO(7);
        PersonVO result7 = result.get(7);

        Assert.assertEquals(personVO7.getId(), result7.getId());
        Assert.assertEquals(personVO7.getFirstName(), result7.getFirstName());
        Assert.assertEquals(personVO7.getLastName(), result7.getLastName());
        Assert.assertEquals(personVO7.getAddress(), result7.getAddress());
        Assert.assertEquals(personVO7.getGender(), result7.getGender());

        PersonVO personVO13 = mockPerson.mockPersonVO(13);
        PersonVO result13 = result.get(13);

        Assert.assertEquals(personVO13.getId(), result13.getId());
        Assert.assertEquals(personVO13.getFirstName(), result13.getFirstName());
        Assert.assertEquals(personVO13.getLastName(), result13.getLastName());
        Assert.assertEquals(personVO13.getAddress(), result13.getAddress());
        Assert.assertEquals(personVO13.getGender(), result13.getGender());
    }
}
