package com.xa.dt.mode.state;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @date 2019-11-01 18:10
 * @version: 1.0
 * @description: TODO
 */
public class EndTagState extends AbstractState {

    @Override
    public void startTag() {

    }

    public void endTag(){
        this.context.flush(true);
    }

    @Override
    public void closeTag() {
        this.context.setState(SimpleXmlParserContext.closeTagState);
        this.context.closeTag();
    }
}
