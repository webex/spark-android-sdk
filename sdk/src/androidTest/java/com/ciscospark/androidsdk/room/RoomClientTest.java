package com.ciscospark.androidsdk.room;

import com.ciscospark.androidsdk.CompletionHandler;
import com.ciscospark.androidsdk.Result;
import com.ciscospark.androidsdk.Spark;
import com.ciscospark.androidsdk.SparkTestRunner;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

/**
 * Created by qimdeng on 7/25/18.
 */

public class RoomClientTest{
    private static RoomClient mClient;
    private static Room mRoom;

    @BeforeClass
    public static void setUp() throws Exception {
        Thread.sleep(10*1000);
        System.out.println("setup test case");
        Spark spark = SparkTestRunner.getSpark();
        mClient = spark.rooms();
    }

    @Test
    public void testcase1() throws  Exception{
        System.out.println("create room");
        mClient.create("TestRoom", null, new CompletionHandler<Room>() {
            @Override
            public void onComplete(Result<Room> result) {
                System.out.println(result.getData());
                mRoom = result.getData();
            }
        });
        Thread.sleep(10*1000);
    }

    @Test
    public void testcase2() throws Exception {
        System.out.println("list room");
        mClient.list(null, 3, null, null, new CompletionHandler<List<Room>>() {
            @Override
            public void onComplete(Result<List<Room>> result) {
                for (Room room : result.getData())
                    System.out.println(room);
            }
        });
        Thread.sleep(10*1000);
    }

    @Test
    public void testcase3() throws Exception {
        System.out.println("update room");
        mClient.update(mRoom.getId(),"UpdateTestRoom", new CompletionHandler<Room>() {
            @Override
            public void onComplete(Result<Room> result) {
                System.out.println(result.getData());
            }
        });
        Thread.sleep(10*1000);
    }

    @Test
    public void testcase4() throws Exception {
        System.out.println("get room");
        mClient.get(mRoom.getId(), new CompletionHandler<Room>() {
            @Override
            public void onComplete(Result<Room> result) {
                System.out.println(result.getData());
            }
        });
        Thread.sleep(10*1000);
    }

    @Test
    public void testcase5() throws Exception {
        System.out.println("delete room");
        mClient.delete(mRoom.getId(), new CompletionHandler<Void>() {
            @Override
            public void onComplete(Result<Void> result) {
                System.out.println(result);
            }
        });
        Thread.sleep(10*1000);
    }
}
