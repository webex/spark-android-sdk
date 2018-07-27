package com.ciscospark.androidsdk.message;

import android.app.Application;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.MediumTest;
import android.support.test.runner.AndroidJUnit4;

import com.ciscospark.androidsdk.Spark;
import com.ciscospark.androidsdk.auth.OAuthTestUserAuthenticator;
import com.ciscospark.androidsdk.room.RoomClient;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static android.support.test.InstrumentationRegistry.getInstrumentation;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class MessageClientTest {

    private static String auth_token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJhbmRyb2lkX3Rlc3R1c2VyXzEiLCJuYW1lIjoiQW5kcm9pZFRlc3RVc2VyMSIsImlzcyI6ImNkNWM5YWY3LThlZDMtNGUxNS05NzA1LTAyNWVmMzBiMWI2YSJ9.eJ99AY9iNDhG4HjDJsY36wgqOnNQSes_PIu0DKBHBzs";
    private static String client_id = "C338457876c27e853fb5e4dc85c6087164073e1e2bf415c081b600fe654d7af60";
    private static String client_sec = "0dc8b9c77c8b5597e7eef4b72c7dfaf8d5b834fdba24cf0a0b55eb85ae209b31";
    private static String redirect = "AndroidKitchenSink://response";
    private static OAuthTestUserAuthenticator authenticator;
    private static Context context;
    private static Spark spark;
    private static MessageClient messageClient;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        System.out.println("final set up");
        final CountDownLatch signal = new CountDownLatch(1);
        getInstrumentation().runOnMainSync(() -> {
            context = InstrumentationRegistry.getContext();
            authenticator = new OAuthTestUserAuthenticator(
                    client_id, client_sec, "spark:all", redirect, "sparksdktestuser16@tropo.com", null, "Test(123)");
            spark = new Spark((Application) context.getApplicationContext(), authenticator);
            authenticator.authorize(result -> {
                System.out.println("get result" + result.toString());
                if (authenticator.isAuthorized()) {
                    if (result.isSuccessful()) {
                        System.out.println(result.getData());
                        messageClient = spark.messages();
                    } else {
                        System.out.println(result.getError());
                        assert false;
                    }
                }
                signal.countDown();
            });
        });
        signal.await(30, TimeUnit.SECONDS);
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        System.out.println("final tear down");
        if (authenticator.isAuthorized()) {
            authenticator.deauthorize();
        }
    }

    private void createRoom() {
        RoomClient roomClient = spark.rooms();
        roomClient.create("test room", null, result -> {
            System.out.println(result);
        });
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("tear down");
    }

    @Test
    public void list() {
        System.out.println("list");
        if (authenticator.isAuthorized()) {
        }
    }

    @Test
    public void post() {
        System.out.println("post");
        if (authenticator.isAuthorized()) {
            messageClient.post("xionxiao@cisco.com", "hello world!", null, null, result -> {
                System.out.println(result);
            });
        }
    }

    @Test
    public void downloadFile() {
    }

    @Test
    public void downloadThumbnail() {
    }

    @Test
    public void get() {
    }

    @Test
    public void delete() {
    }
}