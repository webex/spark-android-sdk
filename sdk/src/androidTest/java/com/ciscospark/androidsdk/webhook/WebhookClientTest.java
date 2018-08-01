package com.ciscospark.androidsdk.webhook;

import com.ciscospark.androidsdk.CompletionHandler;
import com.ciscospark.androidsdk.Result;
import com.ciscospark.androidsdk.Spark;
import com.ciscospark.androidsdk.SparkTestRunner;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

/**
 * Created by qimdeng on 7/27/18.
 */

public class WebhookClientTest {
    private final static String SparkWebhookTestTargetUrl = "https://ios-demo-pushnoti-server.herokuapp.com/webhook";
    private static WebhookClient mClient;
    private static Webhook mWebhook;

    @BeforeClass
    public static void setUp() throws Exception {
        Thread.sleep(10*1000);
        System.out.println("setup test case");
        Spark spark = SparkTestRunner.getSpark();
        mClient = spark.webhooks();
    }

    @Test
    public void testcase1() throws  Exception{
        System.out.println("create webhook");
        mClient.create("TestWebHook", SparkWebhookTestTargetUrl,
                "messages", "all", null, null, new CompletionHandler<Webhook>() {
            @Override
            public void onComplete(Result<Webhook> result) {
                System.out.println(result.getData());
                mWebhook = result.getData();
            }
        });
        Thread.sleep(10*1000);
    }

    @Test
    public void testcase2() throws Exception {
        System.out.println("lsit webhook");
        mClient.list(3, new CompletionHandler<List<Webhook>>() {
            @Override
            public void onComplete(Result<List<Webhook>> result) {
                for (Webhook webhook : result.getData()) {
                    System.out.println(webhook);
                }
            }
        });
        Thread.sleep(10 * 1000);
    }

    @Test
    public void testcase3() throws Exception {
        System.out.println("update webhook");
        mClient.update(mWebhook.getId(), "UpdatedWebHook",
                SparkWebhookTestTargetUrl + "Updated", "1qa2ws3ed", null, new CompletionHandler<Webhook>() {
                    @Override
                    public void onComplete(Result<Webhook> result) {
                        System.out.println(result.getData());
                    }
                });
        Thread.sleep(10*1000);
    }

    @Test
    public void testcase4() throws Exception {
        System.out.println("get webhook");
        mClient.get(mWebhook.getId(), new CompletionHandler<Webhook>() {
            @Override
            public void onComplete(Result<Webhook> result) {
                System.out.println(result.getData());
            }
        });
        Thread.sleep(10*1000);
    }

    @Test
    public void testcase5() throws Exception {
        System.out.println("delete webhook");
        mClient.delete(mWebhook.getId(), new CompletionHandler<Void>() {
            @Override
            public void onComplete(Result<Void> result) {
                System.out.println(result);
            }
        });

        Thread.sleep(10 * 1000);
    }
}
