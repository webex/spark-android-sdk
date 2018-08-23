/*
 * Copyright 2016-2018 Cisco Systems Inc
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.ciscospark.androidsdk.membership;

import java.util.List;

import com.ciscospark.androidsdk.CompletionHandler;
import com.ciscospark.androidsdk.Result;
import com.ciscospark.androidsdk.Spark;
import com.ciscospark.androidsdk.SparkTestRunner;
import com.ciscospark.androidsdk.auth.JWTAuthenticator;
import com.ciscospark.androidsdk.membership.internal.MembershipClientImpl;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by zhiyuliu on 31/08/2017.
 */

public class MembershipClientTest {

    private static final String SPARK_ROOM_CALL_ROOM_ID = "Y2lzY29zcGFyazovL3VzL1JPT00vOWM2ZjQyZDAtMGFmNi0xMWU4LTg2ODQtZmQ0MTFjYTUzOWZl";
    private static String sparkUserEmail = "sparksdktestuser15@tropo.com";
    private static MembershipClient mClient;
    private static Membership mMembership, mModerator;

    @BeforeClass
    public static void setUp() throws Exception {
        Thread.sleep(10*1000);
        System.out.println("setup test case");
        Spark spark = SparkTestRunner.getSpark();
        mClient = spark.memberships();
    }

    @Test
    public void testcase1() throws  Exception{
        System.out.println("create membership");
        mClient.create(SPARK_ROOM_CALL_ROOM_ID, null, sparkUserEmail, false, new CompletionHandler<Membership>() {
            @Override
            public void onComplete(Result<Membership> result) {
                System.out.println(result.getData());
                mMembership = result.getData();
            }
        });
        Thread.sleep(10*1000);
    }

    @Test
    public void testcase2() throws Exception {
        System.out.println("lsit membership");
        mClient.list(SPARK_ROOM_CALL_ROOM_ID, null, null, 0, new CompletionHandler<List<Membership>>() {
            @Override
            public void onComplete(Result<List<Membership>> result) {
                for (Membership membership : result.getData()) {
                    System.out.println(membership);
                    if (membership.getPersonEmail().equals("qimdeng@cisco.com"))
                        mModerator = membership;
                }
            }
        });
        Thread.sleep(10 * 1000);
    }

    @Test
    public void testcase3() throws Exception {
        System.out.println("update membership");
        mClient.update(mModerator.getId(), !mModerator.isModerator(), new CompletionHandler<Membership>() {
            @Override
            public void onComplete(Result<Membership> result) {
                System.out.println(result.getData());
            }
        });
        Thread.sleep(10*1000);
    }

    @Test
    public void testcase4() throws Exception {
        System.out.println("get membership");
        mClient.get(mMembership.getId(), new CompletionHandler<Membership>() {
            @Override
            public void onComplete(Result<Membership> result) {
                System.out.println(result.getData());
            }
        });
        Thread.sleep(10*1000);
    }

    @Test
    public void testcase5() throws Exception {
        System.out.println("delete membership");
        mClient.delete(mMembership.getId(), new CompletionHandler<Void>() {
            @Override
            public void onComplete(Result<Void> result) {
                System.out.println(result);
            }
        });

        Thread.sleep(10 * 1000);
    }
}
