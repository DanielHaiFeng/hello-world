package com.xa.dt.mode.state;

/**
 * @author DangTing[dangting@boco.com.cn]
 * @date 2019-11-01 18:07
 * @version: 1.0
 * @description: TODO
 */
public class SimpleXmlParserContext {

    public static State startTagState = new StartTagState();

    public static State closeTagState = new CloseTagState();

    public static State noTagState = new NoState();

    public static State endTagState = new EndTagState();

    private State state = noTagState;

    private StringBuffer tempString = new StringBuffer();

    public StringBuffer getTempString() {
        return tempString;
    }

    public void flush(boolean flush) {
        if (flush) {
            System.out.print(tempString.toString() + "\n");
        }
        tempString.setLength(0);
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
        this.state.setContext(this);
    }


    public void startTag() {
        this.state.startTag();
    }

    public void endTag() {
        this.state.endTag();
    }

    public void closeTag() {
        this.state.closeTag();
    }

    public static void main(String[] args) {
        String html = "<div><123></div>";
        SimpleXmlParserContext context = new SimpleXmlParserContext();
        context.setState(noTagState);
        for (int a = 0; a < html.length(); a++) {
            char c = html.charAt(a);
            if (String.valueOf(c).equals("<")) {
                context.startTag();
            } else if (String.valueOf(c).equals("/")) {
                context.endTag();
            } else if (String.valueOf(c).equals(">")) {
                context.closeTag();
            } else {
                context.getTempString().append(c);
            }
            continue;
        }
        context.flush(true);
    }
}
