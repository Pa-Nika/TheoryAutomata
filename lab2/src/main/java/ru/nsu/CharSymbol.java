package ru.nsu;

public class CharSymbol implements Word {
    private char s;

    public CharSymbol(char c) {
        this.s = c;
    }

    public char getS() {
        return s;
    }

    @Override
    public String toString () {
        String str = "symbol(" + s + ")";
        return str;
    }
}
