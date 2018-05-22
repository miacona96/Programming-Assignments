public class PairInt {
    private int x;
    private int y;

    public PairInt(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {return x;}
    public int getY() {return y;}

    public PairInt setX(int x) {this.x = x; return this;}
    public PairInt setY(int y) {this.y = y; return this;}

    public boolean equals(Object z) {
        if(z.getClass() != this.getClass() ) return false;
        return ((PairInt) z).getX() == this.getX() && ((PairInt) z).getY() == this.getY();
    }
    public String toString() {return "(" + x + ", " + y + ")";}
    public PairInt copy() {return new PairInt(x,y);}
}
