package com.ciscospark.androidsdk.people;

import com.ciscospark.androidsdk.CompletionHandler;
import com.ciscospark.androidsdk.Result;
import com.ciscospark.androidsdk.Spark;
import com.ciscospark.androidsdk.SparkTestRunner;
import com.ciscospark.androidsdk.room.Room;
import com.ciscospark.androidsdk.room.RoomClient;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

/**
 * Created by qimdeng on 7/26/18.
 */

public class PeopleClientTest {
    private static PersonClient mClient;
    private static Person mPerson;

    @BeforeClass
    public static void setUp() throws Exception {
        Thread.sleep(10*1000);
        System.out.println("setup test case");
        Spark spark = SparkTestRunner.getSpark();
        mClient = spark.people();
    }

    @Test
    public void testcase1() throws Exception {
        System.out.println("list person");
        mClient.list("qimdeng@cisco.com", null, 3, new CompletionHandler<List<Person>>() {
            @Override
            public void onComplete(Result<List<Person>> result) {
                for (Person person : result.getData())
                    System.out.println(person);
                mPerson = result.getData().get(0);
            }
        });
        Thread.sleep(10*1000);
    }

    @Test
    public void testcase2() throws Exception {
        System.out.println("get person");
        mClient.get(mPerson.getId(), new CompletionHandler<Person>() {
            @Override
            public void onComplete(Result<Person> result) {
                System.out.println(result.getData());
            }
        });
        Thread.sleep(10*1000);
    }

    @Test
    public void testcase3() throws Exception {
        System.out.println("getMe");
        mClient.getMe(new CompletionHandler<Person>() {
            @Override
            public void onComplete(Result<Person> result) {
                System.out.println(result);
            }
        });
        Thread.sleep(10*1000);
    }
}
