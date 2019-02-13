package java8.ex02;

import java8.data.Data;
import java8.data.Person;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 02 - Redéfinition
 */
public class Method_02_Test {

    // tag::IDao[]
    interface IDao {
        List<Person> findAll();
        
        default String format(List<Person> people){     	
        	return "["+ people.size()+" persons]";
        }

    }
    // end::IDao[]

    // tag::DaoA[]
    class DaoA implements IDao {

        List<Person> people = Data.buildPersonList(20);

        @Override
        public List<Person> findAll() {
            return people;
        }
        
        @Override
		public String format(List<Person> people){   
        	return "DaoA"+IDao.super.format(people);
        }
        
    }
    // end::DaoA[]

    @Test
    public void test_daoA_format() throws Exception {

        DaoA daoA = new DaoA();

        String result = daoA.format(daoA.findAll());

        assertThat(result, is("DaoA[20 persons]"));
    }
}
