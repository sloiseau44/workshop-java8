package java8.ex01;

import java8.data.Data;
import java8.data.Person;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 01 - Méthode par défaut
 */

public class Method_01_Test {

    // tag::IDao[]
    interface IDao {
        List<Person> findAll();
        
        default int sumAge(List<Person> people){
        	int age=0;
        	for(Person person : people){
        		age+=person.getAge();
        	}
        	
        	return age;
        }
        
    }
   //  end::IDao[]

    class DaoA implements IDao {

        List<Person> people = Data.buildPersonList(20);

        @Override
        public List<Person> findAll() {
            return people;
        }
    }

    class DaoB implements IDao {

        List<Person> people = Data.buildPersonList(100);

        @Override
        public List<Person> findAll() {
            return people;
        }
    }

    @Test
    public void test_daoA_sumAge() throws Exception {

        DaoA daoA = new DaoA();

        int result = daoA.sumAge(daoA.findAll());

        assertThat(result, is(210));
    }

    @Test
    public void test_daoB_sumAge() throws Exception {

        DaoB daoB = new DaoB();

        int result = daoB.sumAge(daoB.findAll());

        assertThat(result, is(5050));

    }
}
