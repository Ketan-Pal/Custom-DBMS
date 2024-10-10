package com.customdb.parser;
import java.util.HashSet;
import java.util.Set;

public class SQLLexer {
    private static final Set<String> keywords;
    private static final Set<Character> operators;

    static {
        keywords = new HashSet<>();
        keywords.add("SELECT");
        keywords.add("FROM");
        keywords.add("WHERE");
        keywords.add("AND");
        keywords.add("OR");
        keywords.add("NOT");
        keywords.add("INSERT");
        keywords.add("INTO");
        keywords.add("VALUES");
        keywords.add("UPDATE");
        keywords.add("SET");
        keywords.add("DELETE");

        operators = new HashSet<>();
        operators.add('=');
        operators.add('>');
        operators.add('<');
        operators.add('+');
        operators.add('-');
        operators.add('*');
        operators.add('/');
        operators.add('%');
        operators.add('!');
    }

    private String sql;
    private int position;

    public SQLLexer(String sql) {
        this.sql = sql;
        this.position = 0;
    }

    public Token nextToken() {
        if (position >= sql.length()) {
            return new Token(Token.TokenType.END, "");
        }

        char currentChar = sql.charAt(position);
        if (Character.isLetter(currentChar)) {
            StringBuffer buffer = new StringBuffer();
            while (position < sql.length() && (Character.isLetterOrDigit(sql.charAt(position)) || sql.charAt(position) == '_')) {
                buffer.append(sql.charAt(position));
                position++;
            }
            String word = buffer.toString().toUpperCase();
            if (keywords.contains(word)) {
                return new Token(Token.TokenType.KEYWORD, word);
            } else {
                return new Token(Token.TokenType.IDENTIFIER, word);
            }
        }

        if (Character.isDigit(currentChar)) {
            StringBuilder buffer = new StringBuilder();
            while (position < sql.length() && Character.isDigit(sql.charAt(position))) {
                buffer.append(sql.charAt(position));
                position++;
            }
            return new Token(Token.TokenType.LITERAL, buffer.toString());
        }
        if (operators.contains(currentChar)) {
            position++;
            return new Token(Token.TokenType.OPERATOR, String.valueOf(currentChar));
        }

        if (currentChar == ',' || currentChar == ';' || currentChar == '(' || currentChar == ')') {
            position++;
            return new Token(Token.TokenType.PUNCTUATION, String.valueOf(currentChar));
        }

        throw new RuntimeException("Unexpected character: " + currentChar);

    }


}
