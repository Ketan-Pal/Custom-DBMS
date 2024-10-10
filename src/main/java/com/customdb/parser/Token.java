package com.customdb.parser;

import lombok.Getter;


public class Token {
    public enum TokenType{
        KEYWORD,IDENTIFIER,LITERAL,OPERATOR,PUNCTUATION,WHITESPACE,COMMENT,END
    }

    @Getter
    private TokenType type;
    @Getter
    private String value;

    public Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString(){
        return "Token{"+"type="+type+", value='"+value+"'"+"}";
    }
}
