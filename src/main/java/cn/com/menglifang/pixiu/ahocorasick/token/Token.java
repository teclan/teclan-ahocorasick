package cn.com.menglifang.pixiu.ahocorasick.token;

import cn.com.menglifang.pixiu.ahocorasick.emit.Emit;

public abstract class Token {

    private String fragment;

    public Token(String fragment) {
        this.fragment = fragment;
    }

    public String getFragment() {
        return this.fragment;
    }

    public abstract boolean isMatch();

    public abstract Emit getEmit();

}
