package com.xa.dt.mode.state;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @date 2019-11-01 18:09
 * @version: 1.0
 * @description: TODO
 */
public class StartTagState extends AbstractState {

    @Override
    public void startTag() {
        //开启标签
        System.out.print("开始标签\n");
    }

    public void endTag(){
        this.context.setState(SimpleXmlParserContext.endTagState);
        this.context.endTag();
    }

    @Override
    public void closeTag() {
        this.context.setState(SimpleXmlParserContext.closeTagState);
        this.context.closeTag();
    }
}
