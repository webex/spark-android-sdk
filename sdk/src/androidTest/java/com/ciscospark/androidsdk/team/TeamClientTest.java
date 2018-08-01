package com.ciscospark.androidsdk.team;

import com.ciscospark.androidsdk.CompletionHandler;
import com.ciscospark.androidsdk.Result;
import com.ciscospark.androidsdk.Spark;
import com.ciscospark.androidsdk.SparkTestRunner;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by qimdeng on 7/27/18.
 */

public class TeamClientTest {
    public static String jwtUserID1 = "dce2861a-debb-4834-802b-6f08515c0bf2";
    public static String jwtUserID2 = "11bc13ac-5a84-4a1f-a1be-4b0910e8d10d";
    private static TeamClient mClient;
    private static Team mTeam;
    private static List<TeamMembership> teamMemberShipList = new ArrayList<>();

    @BeforeClass
    public static void setUp() throws Exception {
        Thread.sleep(10*1000);
        System.out.println("setup test case");
        Spark spark = SparkTestRunner.getSpark();
        mClient = spark.teams();
    }

    @Test
    public void testcase1() throws  Exception{
        System.out.println("create team");
        mClient.create("TestTeam", new CompletionHandler<Team>() {
                    @Override
                    public void onComplete(Result<Team> result) {
                        System.out.println(result.getData());
                        mTeam = result.getData();
                    }
                });
        Thread.sleep(10*1000);
    }

    @Test
    public void testcase2() throws Exception {
        System.out.println("add MembershipToTeam");
        SparkTestRunner.getSpark().teamMembershipClient().create(mTeam.getId(), jwtUserID1,
                null, false, new CompletionHandler<TeamMembership>() {
            @Override
            public void onComplete(Result<TeamMembership> result) {
                System.out.println(result.getData());
                teamMemberShipList.add(result.getData());
            }
        });
        SparkTestRunner.getSpark().teamMembershipClient().create(mTeam.getId(), jwtUserID2,
                null, false, new CompletionHandler<TeamMembership>() {
            @Override
            public void onComplete(Result<TeamMembership> result) {
                System.out.println(result.getData());
                teamMemberShipList.add(result.getData());
            }
        });
        Thread.sleep(10 * 1000);
    }

    @Test
    public void testcase3() throws Exception {
        System.out.println("list TeamMemberShips");
        SparkTestRunner.getSpark().teamMembershipClient().list(mTeam.getId(), 0, new CompletionHandler<List<TeamMembership>>() {
            @Override
            public void onComplete(Result<List<TeamMembership>> result) {
                for (TeamMembership teamMembership : result.getData()) {
                    System.out.println(teamMembership);
                }
            }
        });
        Thread.sleep(10 * 1000);
    }

    @Test
    public void testcase4() throws Exception {
        System.out.println("delete TeamMembership");
        SparkTestRunner.getSpark().teamMembershipClient().delete(teamMemberShipList.get(0).getId(), new CompletionHandler<Void>() {
                    @Override
                    public void onComplete(Result<Void> result) {
                        System.out.println(result);
                    }
                });
        Thread.sleep(10*1000);
    }

    @Test
    public void testcase5() throws Exception {
        System.out.println("update TeamMembership");
        SparkTestRunner.getSpark().teamMembershipClient().update(teamMemberShipList.get(1).getId(), true, new CompletionHandler<TeamMembership>() {
            @Override
            public void onComplete(Result<TeamMembership> result) {
                System.out.println(result.getData());
            }
        });
        Thread.sleep(10*1000);
    }

    @Test
    public void testcase6() throws Exception {
        System.out.println("get team");
        mClient.get(mTeam.getId(), new CompletionHandler<Team>() {
            @Override
            public void onComplete(Result<Team> result) {
                System.out.println(result.getData());
            }
        });

        Thread.sleep(10 * 1000);
    }

    @Test
    public void testcase7() throws Exception {
        System.out.println("update team");
        mClient.update(mTeam.getId(), "TestUpdatedTeam", new CompletionHandler<Team>() {
            @Override
            public void onComplete(Result<Team> result) {
                System.out.println(result.getData());
            }
        });

        Thread.sleep(10 * 1000);
    }

    @Test
    public void testcase8() throws Exception {
        System.out.println("delete team");
        mClient.delete(mTeam.getId(), new CompletionHandler<Void>() {
            @Override
            public void onComplete(Result<Void> result) {
                System.out.println(result);
            }
        });

        Thread.sleep(10 * 1000);
    }
}
