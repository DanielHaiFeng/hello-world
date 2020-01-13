package com.xa.dt.mode.state;

/**
 * @author DangTing
 * @date 2019-11-05 17:40
 * @version: 1.0
 * @description: TODO
 */
public class BlackVoteState implements VoteState {

    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
        //记录黑名单中，禁止登录系统
        System.out.println("进入黑名单，将禁止登录和使用本系统");
    }
}
