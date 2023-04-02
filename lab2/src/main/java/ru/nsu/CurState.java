package ru.nsu;

public class CurState {
    private int from;
    private String s;

    public CurState(int i, String str) {
        this.from = i;
        this.s = str;
    }

    public int getFrom() {
        return from;
    }

    public String getS() {
        return s;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof CurState)) {
            return false;
        }
        CurState other = (CurState) obj;
        return (other.getFrom() == this.from)  && ( (other.getS()).equals(this.s));
    }
}
