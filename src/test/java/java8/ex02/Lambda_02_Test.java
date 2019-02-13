package java8.ex02;

import java8.data.Account;
import java8.data.Data;
import java8.data.Person;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Exercice 02 - Map
 */
public class Lambda_02_Test {

    // tag::PersonToAccountMapper[]
    @SuppressWarnings("hiding")
	interface PersonToAccountMapper<Account, String> {
        Account map(Person p);
    }
    // end::PersonToAccountMapper[]

    // tag::map[]
    @SuppressWarnings("hiding")
	private  <String , Account> List<Account> map(List<Person> personList, PersonToAccountMapper <Account, String> mapper) {
    	List<Account> accounts = new ArrayList<Account>();
    	for(Person person : personList){
    		accounts.add(mapper.map(person));
    	}
        return accounts;
    }
    // end::map[]


    // tag::test_map_person_to_account[]
    @Test
    public void test_map_person_to_account() throws Exception {

        List<Person> personList = Data.buildPersonList(100);
       
		List<Account> result = map(personList, p -> {
			Account account = new Account();
			account.setOwner(p);
			account.setBalance(100);
			return account;
		});

        assertThat(result, hasSize(personList.size()));
        assertThat(result, everyItem(hasProperty("balance", is(100))));
        assertThat(result, everyItem(hasProperty("owner", notNullValue())));
    }
    // end::test_map_person_to_account[]

    // tag::test_map_person_to_firstname[]
    @Test
    public void test_map_person_to_firstname() throws Exception {

        List<Person> personList = Data.buildPersonList(100);

        List<String> result = map(personList, p -> p.getFirstname());

        assertThat(result, hasSize(personList.size()));
        assertThat(result, everyItem(instanceOf(String.class)));
        assertThat(result, everyItem(startsWith("first")));
    }
    // end::test_map_person_to_firstname[]
}
