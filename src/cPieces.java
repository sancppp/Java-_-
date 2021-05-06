/*
 *棋子对象
 */

/*
 *为棋盘对象所引用，而不是游戏对象
 */
public class cPieces {
    private int x;  // x坐标
    private int y; // y坐标
    private int type; //  2白子 ，1黑子

    public cPieces(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getType() {
        return type;
    }
}
