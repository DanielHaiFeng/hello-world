package com.xa.dt.mode.state;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @date 2019-11-05 17:37
 * @version: 1.0
 * @description: 具体状态类——正常投票
 */
public class NormalVoteState implements VoteState {

    @Override
    public void vote(String user, String voteItem, VoteManager voteManager) {
        //正常投票，记录到投票记录中
        voteManager.getMapVote().put(user, voteItem);
        System.out.println("恭喜投票成功");
    }
}
