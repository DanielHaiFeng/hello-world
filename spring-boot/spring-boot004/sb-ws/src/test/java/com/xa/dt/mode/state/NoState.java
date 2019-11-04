package com.xa.dt.mode.state;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @date 2019-11-01 18:10
 * @version: 1.0
 * @description: TODO
 */
public class NoState extends AbstractState {

    @Override
    public void startTag() {
        this.context.setState(SimpleXmlParserContext.startTagState);
        this.context.startTag();
    }

    @Override
    public void closeTag() {

    }
}
