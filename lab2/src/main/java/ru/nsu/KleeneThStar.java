package ru.nsu;

public class KleeneThStar implements Word {
    private Word s;

    public KleeneThStar(Word w) {
        this.s = w;
    }

    public Word getS() {
        return s;
    }
    @Override
    public String toString () {
        String str = "kleene(" + s.toString() + ")";
        return str;
    }
}
