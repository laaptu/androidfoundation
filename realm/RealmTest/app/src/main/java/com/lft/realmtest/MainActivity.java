package com.lft.realmtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lft.realmtest.model.FifthModel;
import com.lft.realmtest.model.Person;
import com.lft.realmtest.model.ThirdModel;
import com.lft.realmtest.model.User;

import java.util.UUID;

import io.realm.Realm;


//https://realm.io/docs/java/latest/
public class MainActivity extends AppCompatActivity {

    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //realmFirstObject();
        //realmFirstObjectThroughTransaction();
        realm = Realm.getInstance(MainApplication.getRealmConfig());
        realmTransactionAsync();
    }

    private void realmFirstObject() {
        /**
         * This by default creates default.realm database*/
        realm = Realm.getInstance(this);
        realm.beginTransaction();
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setId(UUID.randomUUID().hashCode());
            person.setAge(i);
            person.setName("Person " + String.valueOf(i));
            //realm.copyToRealm(person);
            /**
             * to update any this , there needs to be a primary key*/
            realm.copyToRealmOrUpdate(person);
        }
        realm.commitTransaction();

    }

    /**
     * This is same as realmFirstObject, but what it does
     * extra is that we don't have to write any beginTransaction()
     * or commitTransaction(), further it also handles any error
     */
    private void realmFirstObjectThroughTransaction() {
        //realm = Realm.getInstance(this);
        //realm = Realm.getInstance(MainApplication.getRealmConfig());
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                createNewUser();
                createNewPerson();
            }
        });

    }

    private void createNewPerson() {
        for (int i = 0; i < 10; i++) {
            Person person = new Person();
            person.setId(UUID.randomUUID().hashCode());
            person.setAge(i);
            person.setName("Person " + String.valueOf(i));
            //realm.copyToRealm(person);
            realm.copyToRealmOrUpdate(person);
        }
    }

    private void createNewUser() {
        ThirdModel thirdModel = new ThirdModel();
        thirdModel.setName("ThirdModel");
        realm.copyToRealm(thirdModel);
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setEmail("email@email.com " + String.valueOf(i));
            user.setName("Name " + String.valueOf(i));
            //realm.copyToRealm(person);
            realm.copyToRealm(user);
        }
    }

    private void realmTransactionAsync() {
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                FifthModel fifthModel = new FifthModel((int) (Math.random() * 1000), "Model fifth");
                realm.copyToRealm(fifthModel);
            }
        }, new Realm.Transaction.Callback() {
            @Override
            public void onSuccess() {
                super.onSuccess();
                System.out.println("Transaction Success");
            }

            @Override
            public void onError(Exception e) {
                super.onError(e);
                System.out.println("Transaction Error");
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
        //need to find out whether this cancels the callback
        realm.removeAllChangeListeners();
    }
}
