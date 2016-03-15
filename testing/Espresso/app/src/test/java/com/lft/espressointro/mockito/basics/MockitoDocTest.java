package com.lft.espressointro.mockito.basics;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

/**
 * Created by laaptu on 3/14/16.
 */
//http://site.mockito.org/mockito/docs/current/org/mockito/Mockito.html
@RunWith(MockitoJUnitRunner.class)
public class MockitoDocTest {

    @Mock
    Person person;

    //stubbing with exception
    @Ignore
    @Test(expected = Exception.class)
    public void multipleArgsTest() {
        Mockito.doThrow(new Exception("This is throwing excpetion")).when(person).setName(null);
        person.setName("hello");
        person.setName(null);
    }

    //verifying with order
    //meaning whether the functions are executed in order or not
    @Ignore
    @Test
    public void inorderExecutionTest() {
        InOrder inOrder = Mockito.inOrder(person);
        person.setName("Hello");
        person.setAge(20);
        person.getAge();
        person.getAge();

        inOrder.verify(person).setName(Matchers.anyString());
        inOrder.verify(person).setAge(Matchers.anyInt());
        inOrder.verify(person, Mockito.atLeast(1)).getAge();
        //this will
        //inOrder.verify(person).getName();
    }

    //verifying no interaction with other mock objects
    @Ignore
    @Test
    public void verifyNoInteractionTest() {
        Person somePerson = Mockito.mock(Person.class);
        Person otherPerson = Mockito.mock(Person.class);

        Mockito.when(person.getName()).thenReturn("Santosh");

//        person.getAge();

        //zero interaction means, the object method call
        //has been made or not
        // the method needs not be of scripted mock
        // we are just scripting getName(), but even if we call getAge(),
        //we will satisfy some interaction
        //for property call, interaction is only considered if
        // property call has been scripted,
        // we scripted
        //Mockito.when(person.address).thenReturn("LA");
//        String address = person.address;
        //then some interaction is registered
        //but if we comment script,then it isn't considered an interaction

        Mockito.verifyZeroInteractions(somePerson, otherPerson, person);
        //this will fail as person.getName() has been invoked
//        Mockito.verifyZeroInteractions(somePerson,otherPerson,person);
    }

    @Ignore
    @Test
    public void usingAnswerTest() {
        //looks like for answering,
        // we always need a return statement
        //meaning if person.addAge(int a) ,doesn't have any
        //return statement, then the following can't be added
        Mockito.when(person.addAge(Matchers.anyInt())).then(new Answer<Object>() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                int passedInt = (int) args[0];
                return passedInt * 30;
            }
        });
        //further following answer states that ,we can
        //modify method call as well
        // in our  person.addAge(int passedAge)
        //we are just adding age = age+passedAge
        //and passing that
        //but we can modify the method as by our own
        //where we simply multiplied the passed value with age
        person.setAge(10);
        Assert.assertEquals(900, person.addAge(30));

        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Person somePerson = (Person) invocation.getMock();
                String passedArgument = (String) args[0];
                System.out.println("Passed Argument " + passedArgument);
                somePerson.setName((String) args[0]);
                return args[0];
            }
        }).when(person).addName(Matchers.anyString());

        person.setName("Arnold");
        person.addName("Hello");
        System.out.println(person.getName());

    }

    @Test
    public void diffBetweenMockNSpy() {
//        Person spyPerson = new Person("Spy",10);
        /**
         * The main diff between Mock and Spy objects is that
         * In mock objects, when we create script for object
         * behaviour, it will work according to that script
         * only, meaning whatever values we set ,it won't work
         * there.
         * But Spy means a real object, just like
         * we create using new SomeClass(), where we can set
         * values and modify it. Further, on spy we can
         * also mock values as per our need*/
        Person spyPerson = Mockito.spy(Person.class);
        //the value won't be set
        person.setAge(20);
        //the value will be set
        spyPerson.setAge(30);
        //as per documentation,rather than using when and return () way
        //Mockito.when(spyPerson.getAge()).thenReturn(100);
        //use doReturn|Answer|Throw() families
        Mockito.doReturn(10).when(spyPerson).getAge();
        System.out.println("Mock Person age =" + person.getAge());
        System.out.println("Spy Person age =" + spyPerson.getAge());
        //partial mock
        //right now when get call person.getAge() it will be zero
        //as it is working on mock object
        person.addAge(50);
        Mockito.when(person.addAge(Matchers.anyInt())).thenCallRealMethod();
        Mockito.when(person.getAge()).thenCallRealMethod();
        person.addAge(20);
        System.out.println("Mock person age1 =" + person.getAge());
    }

    @Ignore
    @Test
    public void resettingMocks() {
        /**
         * Don't use resetting mocks, test should be small
         * and focused on single behaviour, it resets the script*/

        Mockito.when(person.getName()).thenReturn("Mockito");
        System.out.println("Person getName() =" + person.getName());
        Mockito.reset(person);
        System.out.println("Person getName()1 =" + person.getName());

    }

    /**
     * Seems like it is doing when() type,
     * but it is just like a syntax, so that it will be
     * easier to write test ( just an assumption)
     * */

    @Ignore
    @Test
    public void testForBehaviorDrivenDevelopment() {
        Bartender bartender = Mockito.mock(Bartender.class);
        Bar bar = new Bar(bartender);
        //BDDMockito.given(bartender.askForRandomDrink()).willReturn(Bartender.Drink.CockTail);
        //BDDMockito.when(bartender.askForRandomDrink()).thenReturn(Bartender.Drink.Mojito);
//        Mockito.when(bartender.askForRandomDrink()).thenReturn(Bartender.Drink.CockTail);
        Bartender.Drink drink = bar.buyDrink();
        Assert.assertEquals(drink, Bartender.Drink.CockTail);
    }
    @Ignore
    @Test
    public void testWithSomeSettings(){
        Bartender bartender = Mockito.mock(Bartender.class);
        Bar bar = new Bar(bartender);


        Mockito.when(bartender.askForRandomDrink()).thenCallRealMethod();
        bar.buyDrink();
        //similar to timeout( ) we can write our own settings for verification
        //Couldn't understand timeout concept right now
        Mockito.verify(bartender,Mockito.timeout(2000)).askForRandomDrink();
    }
}
