package com.xa.dt.mode.state;

/**
 * @author DangTing
 * @date 2019-11-05 17:38
 * @version: 1.0
 * @description: TODO
 */
public class RepeatVoteState implements VoteState {

    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
        //重复投票，暂时不做处理
        System.out.println("请不要重复投票");
    }
}
