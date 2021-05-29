
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class cBoard extends JPanel {
    public static final int cBoard_SIZE = 19;// 19 * 19
    public int margin = 20;// 边距
    // 因为无法知道棋子个数，所以用集合来存储棋子
    private final List<cPieces> piecesList = new ArrayList<cPieces>();

    // 绘画方法
    public void paint(Graphics g) {
        int length = getLength();// 格子长度
        super.paint(g);
        Graphics2D g1 = (Graphics2D) g;
        // 设置抗锯齿
        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // 画网格
        for (int i = 0; i < cBoard_SIZE; i++) {
            // 横线
            g.drawLine(margin, margin + length * i, margin + (cBoard_SIZE - 1) * length, margin + length * i);
            // ````竖线
            g.drawLine(margin + length * i, margin, margin + length * i, margin + (cBoard_SIZE - 1) * length);
        }

        // 设置颜色以及画棋盘上的点 5个点
        // 10是圆的半径
        g1.setColor(new Color(116, 88, 49));
        g1.fillOval(margin + 9 * getLength() - 5, margin + 9 * getLength() - 5, 10, 10);
        g1.fillOval(margin + 4 * getLength() - 5, margin + 4 * getLength() - 5, 10, 10);
        g1.fillOval(margin + 4 * getLength() - 5, margin + 15 * getLength() - 5, 10, 10);
        g1.fillOval(margin + 14 * getLength() - 5, margin + 4 * getLength() - 5, 10, 10);
        g1.fillOval(margin + 14 * getLength() - 5, margin + 15 * getLength() - 5, 10, 10);

        // 画棋子
        drawChessPieces(g1);
    }

    /*
     * 画棋子
     */
    public void drawChessPieces(Graphics2D g) {
        // 得到FontMetrics对象
        // 主要为了设置字体居中
        g.setFont(new Font("黑体", Font.PLAIN, 18));
        FontMetrics metrics = g.getFontMetrics();
        int ascent = metrics.getAscent();
        int descent = metrics.getDescent();

        for (int i = 0; i < piecesList.size(); i++) {
            cPieces x = piecesList.get(i);
            // 设置棋子颜色
            if (x.getType() == 1)
                g.setColor(Color.black);
            else
                g.setColor(Color.white);

            g.fillOval(x.getX() * getLength() - getPieceLength() / 2 + margin,
                    x.getY() * getLength() - getPieceLength() / 2 + margin, getPieceLength(), getPieceLength());

            if (x.getType() == 1)
                g.setColor(Color.white);
            else
                g.setColor(Color.black);
            String num = i + 1 + "";
            g.drawString(num, x.getX() * getLength() + margin - metrics.stringWidth(num) / 2,
                    x.getY() * getLength() + margin - (ascent + descent) / 2 + ascent);

        }

    }

    /*
     * 落子
     */
    public void addPieces(cPieces x) {
        piecesList.add(x);
        // 只能重新绘画
        repaint();
    }

    /*
     * 获取格子大小
     */
    public int getLength() {
        return (getHeight() - 2 * margin) / (cBoard_SIZE - 1);
    }
    /*
     * 获取边距
     */

    public int getMargin() {
        return margin;
    }

    /*
     * 获取边长
     */
    public int getcBoardSize() {
        return cBoard_SIZE;
    }

    /*
     * 获取棋子半径
     */
    public int getPieceLength() {
        return getLength() - 5;
    }

    /*
     * 清空落子集合
     */
    public void clearList() {
        piecesList.clear();
        repaint();
    }

}
