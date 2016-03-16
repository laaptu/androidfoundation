package com.lft.espressointro.mockito.basics;

import com.lft.espressointro.mockito.Dummy;

import org.hamcrest.core.IsEqual;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

/**
 * Created by laaptu on 3/14/16.
 */
@RunWith(MockitoJUnitRunner.class)
public class PersonCheckerTest {

    /**
     * Here add the break points and run in debugger mode.
     * And see both person and person1,
     * the person value for age =0 and name =null i.e.
     * nothing will be set
     * but for person1 the value for age =30 and name = Hello
     * In debugging mode when we call  right click and evaluate expression
     * and type person.getAge() it will return 20, but for
     * person1.getAge() it will return 30 and for name it will return hello
     */

    @Ignore
    @Test
    public void firstMockTest() {
        //scripting part
        Person person = Mockito.mock(Person.class);
        Mockito.when(person.getAge()).thenReturn(20);
        Mockito.when(person.getName()).thenReturn("John");

        person.setAge(30);

        Person person1 = new Person("Hello", 30);

        //testing part
        PersonChecker personChecker = new PersonChecker();
        Assert.assertThat(true, IsEqual.equalTo(personChecker.checkPerson(person)));

    }


    @Test
    public void mockTestWithMultipleReturnValues() {
        Person person = Mockito.mock(Person.class);
        /**
         * One merit of Mockito seen is that, if we have to replicate this
         * We have to create at least 4 separate objects with 4 separate values,
         * or call setAge() four times with same object , and even we do that
         * we need to run test case in between those statement rather
         * than running one by one as in below*/
        Mockito.when(person.getAge()).thenReturn(20).thenReturn(30).thenReturn(40).thenReturn(50);


        /**
         * When we add break point here and then began to evaluate the expression
         * with getAge() till it begin to only give out 50,
         * and then run the above code, the test case will fail
         * as not the getAge() will only return 50*/
         System.out.println("Hello");
        Assert.assertEquals(20, person.getAge());
        Assert.assertEquals(30, person.getAge());
        Assert.assertEquals(40, person.getAge());
        Assert.assertEquals(50, person.getAge());
    }

    //for annotation to work, this class must @RunWith(MockitoJUnitRunner.class)
    //http://developer.android.com/training/testing/unit-testing/local-unit-tests.html
    @Mock
    Person mockPerson;

    @Mock
    Dummy dummy;

    @Ignore
    @Test
    public void workWithMockPerson() {
        Mockito.when(mockPerson.getName()).thenReturn("Hello");
        Mockito.when(dummy.getDummyValue()).thenReturn(10);
        Assert.assertEquals("Hello", mockPerson.getName());
        Assert.assertEquals(10,dummy.getDummyValue());
    }

    //Verification Test
    @Ignore
    @Test
    public void verificationTest() {
        Mockito.when(mockPerson.getName()).thenReturn("John");
        Assert.assertEquals("John",mockPerson.getName());

        //Verification which states that getName() has been called by any statement
        Mockito.verify(mockPerson).getName();
        //But if we add the following i.e. here getAge() hasn't been invoked yet, it
        // will throw error
        //Mockito.verify(mockPerson).getAge();

        //similarly we can verify other calls or methods have been called or not
        // i.e. mock verification doesn't have to be associated with its script
        //meaning we are only scripting for getName(), but user can invoke other
        //methods as well, and we can verify that as well

        mockPerson.setName("Hi");
        mockPerson.setAge(50);

        // this verification is only for one invocation,
        //meaning it checks whether this method has been invoked
        //once or not
        //if we add following line it will crash
        // as we are invoking it twice
        //mockPerson.setAge(100);
        Mockito.verify(mockPerson).setName(Matchers.anyString());
        Mockito.verify(mockPerson).setAge(Matchers.anyInt());

        // but if we want to verify that method has been called more than once
        // we can use
        mockPerson.setAge(100);
        Mockito.verify(mockPerson,Mockito.atLeast(2)).setAge(Matchers.anyInt());

    }
}
