import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

/*
 * 人（黑子）：1 电脑（白子）：0
 *
 */
public class game extends JFrame {
    private cBoard cBoard;
    // 存放棋子数组
    private final int[][] gameArray;// 1:人类黑子 ， 2 ：电脑白字/人类白子 0：未落子
    int type;// 模式 0:人人对战 1：人机对战 -1未开始
    int person = 1;// 默认为1
    int max; // 最大的分点
    JRadioButton perButton;
    JRadioButton comButton;
    JButton new_game; // 开始游戏
    JButton give_up;
    JButton rule;
    JButton about;
    JButton exit;
    ButtonGroup group;

    // GUI绘图
    public game() {
        super("Game");// 设置标题
        setSize(890, 750);// 设置宽高
        setLocation(400, 50);// 设置位置
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置默认关闭
        this.setLayout(new BorderLayout());
        // 设置图标
        ImageIcon icon = new ImageIcon();
        setIconImage(icon.getImage());
        Font f = new Font("黑体",Font.BOLD,12);
        // 导入棋盘对象
        cBoard = new cBoard();
        Color bac = Color.decode("#a9a9a9");
        cBoard.setBackground(bac);
        // 初始化数组
        int chessNum = cBoard.getcBoardSize();
        gameArray = new int[chessNum][chessNum];
        for (int[] x : gameArray) {
            Arrays.fill(x, 0);
        }
        // 点击鼠标落子
        cBoard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                play(e);
            }
        });
        this.add(cBoard, BorderLayout.CENTER);
        this.getContentPane().setBackground(bac);
        JPanel j = new JPanel();
        JPanel j1 = new JPanel(new GridLayout(5, 1, 10, 20));
        // 设置棋局的相关选项

        JButton temp = new JButton("");
        temp.setVisible(false);
        JButton temp2 = new JButton("");
        temp2.setVisible(false);
        new_game = new JButton("开始游戏");
        new_game.setFont(f);
        JPanel j2 = new JPanel();
        group = new ButtonGroup();
        perButton = new JRadioButton("人人对战");
        perButton.setFont(f);
        comButton = new JRadioButton("人机对战");
        group.add(perButton);
        group.add(comButton);
        j2.add(perButton);
        j2.add(comButton);
        perButton.setSelected(true);

        perButton.setFocusPainted(false);// 不绘制bai焦点
        perButton.setBorderPainted(false); // 不绘制边du界
        perButton.setContentAreaFilled(false); // 不填充所占zhi的矩形区域dao
        comButton.setFocusPainted(false);// 不绘制bai焦点
        comButton.setBorderPainted(false); // 不绘制边du界
        comButton.setContentAreaFilled(false); // 不填充所占zhi的矩形区域dao

        rule = new JButton("游戏说明");
        give_up = new JButton("认输");
        about = new JButton("关于");
        exit = new JButton("退出");

        JPanel j3 = new JPanel(new GridLayout(5, 1, 0, 30));
        JPanel j4 = new JPanel(new GridLayout(3, 1, 0, 30));
        // 菜单部分1
        // j1.add(temp);
        j3.add(temp);
        j3.add(new_game);
        j3.add(j2);
        j3.add(give_up);
        j1.add(j3);

        j4.add(rule); // 菜单部分2
        j4.add(about); // 菜单部分3
        j4.add(exit);// 菜单部分4
        j1.add(j4);
        j1.add(temp2);

        j.add(j1);
        j.setBackground(bac);
        j1.setBackground(bac);
        j2.setBackground(bac);
        j3.setBackground(bac);
        j4.setBackground(bac);

        this.add(j, BorderLayout.EAST);
        this.setVisible(true);// 设置可见

        type = -1;
        give_up.setEnabled(false);
        // 开始游戏
        new_game.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (type == -1) {
                    super.mouseClicked(e);
                    type = perButton.isSelected() ? 0 : 1;
                    give_up.setEnabled(true);
                    new_game.setEnabled(false);
                }
            }
        });
        // 认输
        give_up.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (type != -1) {
                    System.out.println(person);
                    String a = "黑子方";
                    if (type == 0) {
                        if (person == 2)
                            a = "白子方";
                    } else if (type == 1) {
                        a = "你";
                    }
                    type = -1;
                    JOptionPane.showMessageDialog(null, a + "输了", "tip", JOptionPane.PLAIN_MESSAGE);
                    end();
                }
            }
        });
        // 退出
        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });
        // 游戏说明
        rule.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String tip1 = "五子棋玩法规则：对局双方各执一色棋子在行棋时必须用手将棋子放在棋盘的空白交叉点上，\n黑方先，白方后，交替落子，每次只能下一子。\n";
                String tip2 = "输赢规则：当有任意一方棋子连成5个，该玩家即可获胜，游戏结束\n";
                String tip3 = "提示1.人人对战:先执子为黑，后执子为白\n";
                String tip4 = "提示2.人机对战：玩家执黑，电脑执白";
                JOptionPane.showMessageDialog(null, tip1 + tip2 + tip3 + tip4, "游戏说明", JOptionPane.PLAIN_MESSAGE);
            }
        });
        // 关于
        about.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null, "本游戏由李朵、田震雄、邹俊文协作完成", "关于", JOptionPane.PLAIN_MESSAGE);
            }
        });

    }

    // 鼠标点击后
    public void play(MouseEvent e) {
        if (type != -1) {
            int length = cBoard.getLength(); // 格子长度
            double x1 = (double) (e.getX() - cBoard.margin) / length;
            double y1 = (double) (e.getY() - cBoard.margin) / length;
            int x2 = (int) Math.rint(x1);
            int y2 = (int) Math.rint(y1);
            System.out.println("坐标：" + y2 + " " + x2);
            if (x2 >= 0 && y2 >= 0 && x2 <= cBoard.getcBoardSize() && y2 <= cBoard.getcBoardSize()
                    && gameArray[y2][x2] == 0) {
                gameArray[y2][x2] = person;
                cBoard.addPieces(new cPieces(x2, y2, person));
                // 落子输赢判断条件算法
                // 判断输赢
                if (!ifWin(y2, x2, person)) {
                    // 没赢
                    if (type == 0) { // 人人对战
                        person = person == 1 ? 2 : 1;
                    } else if (type == 1) { // 人机对战
                        ITnext();
                    }
                } else {
                    // 有一方赢了
                    if (person == 1)
                        JOptionPane.showMessageDialog(null, "黑子赢了", "tip", JOptionPane.PLAIN_MESSAGE);
                    else
                        JOptionPane.showMessageDialog(null, "白子赢了", "tip", JOptionPane.PLAIN_MESSAGE);
                    end();
                }
            }
        }

    }

    /*
     * 输赢算法
     */
    public boolean ifWin(int x, int y, int type) {
        String com = "";
        if (type == 1)
            com = "11111";
        else
            com = "22222";
        String a1 = getString(gameArray[x]);
        if (a1.indexOf(com) != -1)
            return true;
        String a2 = getString2(y);
        if (a2.indexOf(com) != -1)
            return true;
        String a3 = getString3(x, y);
        if (a3.indexOf(com) != -1)
            return true;
        String a4 = getString4(x, y);
        if (a4.indexOf(com) != -1)
            return true;
        return false;

    }

    // 结束算法
    public void end() {
        // 还原数组
        for (int[] x : gameArray) {
            Arrays.fill(x, 0);
        }
        // 默认为1
        person = 1;
        // 清空落子集合
        cBoard.clearList();
        type = -1;
        give_up.setEnabled(false);
        new_game.setEnabled(true);
    }

    /*
     * int数组转字符串
     */
    /* 将横转字符串 */
    public String getString(int[] arr) {
        String s = "";
        for (int i = 0; i < arr.length; i++)
            s += arr[i];
        return s;
    }

    /* 将竖方向转字符串 */
    public String getString2(int y) {
        String s = "";
        for (int i = 0; i < gameArray.length; i++)
            s += gameArray[i][y];
        return s;
    }

    /* 将撇方向转字符串 */
    public String getString3(int x, int y) {
        String s = "";
        if (x <= y) {
            int gap = y - x;
            for (int i = 0; i + gap <= 18; i++)
                s += gameArray[i][i + gap];
        } else {
            int gap = x - y;
            for (int i = 0; i + gap <= 18; i++)
                s += gameArray[gap + i][i];
        }

        return s;

    }

    /* 将捺方向转字符串 */
    public String getString4(int x, int y) {
        String s = "";
        if (x + y <= 18) {
            int gap = y + x;
            for (int i = gap; i >= 0; i--)
                s += gameArray[i][gap - i];
        } else {
            int gap = x + y;
            for (int i = 18; gap - i <= 18; i--)
                s += gameArray[i][gap - i];
        }

        return s;

    }

    /* 电脑落子算法 */
    public void ITnext() {
        max = 0;
        int nextX = 0, nextY = 0;
        int[][] gameArr2 = new int[19][19];
        for (int i = 0; i < gameArr2.length; i++) {
            gameArr2[i] = gameArray[i].clone();
        }
        int[] xx = { -2, -1, 0, 1, 2 };

        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                if (gameArray[i][j] != 0) {

                    for (int a = 0; a < xx.length; a++) {
                        for (int b = 0; b < xx.length; b++) {
                            if (ifBorder(i + xx[a], j + xx[b]))
                                continue;
                            if (gameArr2[i + xx[a]][j + xx[b]] == 0) {
                                gameArr2[i + xx[a]][j + xx[b]] = fun(i + xx[a], j + xx[b]);
                                if (gameArr2[i + xx[a]][j + xx[b]] > max) {
                                    max = gameArr2[i + xx[a]][j + xx[b]];
                                    nextX = i + xx[a];
                                    nextY = j + xx[b];
                                }
                            }
                        }
                    }
                }
            }
        }
        gameArray[nextX][nextY] = 2;
        cBoard.addPieces(new cPieces(nextY, nextX, 2));
        if (ifWin(nextX, nextY, 2)) {
            JOptionPane.showMessageDialog(null, "你太菜了，机器赢了", "tip", JOptionPane.PLAIN_MESSAGE);
            end();
        }

    }

    public int fun(int x, int y) {
        if (x >= 0 && x < 19 && y >= 0 && y < 19 && gameArray[x][y] == 0) {
            int min = x <= y ? x : y;
            if (x + y >= 18)
                min = x >= y ? 18 - x : 18 - y;
            int a = getScore(x, y, 2);
            int b = getScore(x, y, 1);
            int end = a + b + min;
            if (end > max) {
                System.out.println("进攻：" + a);
                System.out.println("防守：" + b);
                System.out.println("总的：" + end);
            }
            gameArray[x][y] = 0;
            return end;
        }
        return 0;
    }

    // 获取当前落子得分
    public int getScore(int x, int y, int type) {
        gameArray[x][y] = type;
        // 横
        String s1 = getString(gameArray[x]);
        int a1 = getScoreType(s1, y, type);
        // 竖
        String s2 = getString2(y);
        int a2 = getScoreType(s2, x, type);
        // 撇
        String s3 = getString3(x, y);
        int a3 = getScoreType(s3, y >= x ? x : y, type);
        // 捺
        String s4 = getString4(x, y);
        int a = y;
        if (x + y > 18)
            a = 18 - x;
        int a4 = getScoreType(s4, a, type);

        int cout[] = new int[11];
        for (int i = 0; i < 11; i++)
            cout[i] = 0;
        cout[a1]++;
        cout[a2]++;
        cout[a3]++;
        cout[a4]++;

        if (a1 == 10 & a2 == 10 && a3 == 10 && a4 == 10)
            return 0;
        if (cout[0] >= 1) {
            if (type == 1)
                return 90000;
            return 100000;
        }
        if (cout[1] >= 1 || (cout[2] >= 2) || (cout[2] >= 1 && cout[3] >= 1)) {
            // System.out.println(Arrays.toString(cout));
            if (type == 1)
                return 9000;
            return 10000;
        }
        if (cout[3] >= 2) {
            if (type == 1)
                return 4500;
            return 5000;
        }
        if (cout[3] >= 1 && cout[4] >= 1) {
            if (type == 1)
                return 900;
            return 1000;
        }
        if (cout[2] >= 1) {
            if (type == 1)
                return 450;
            return 500;
        }
        if (cout[3] >= 1) {
            if (type == 1)
                return 180;
            return 200;
        }
        if (cout[5] >= 2) {
            if (type == 1)
                return 90;
            return 100;
        }
        if (cout[4] >= 1)
            return 50;
        if (cout[5] >= 1 && cout[6] >= 1)
            return 10;
        if (cout[5] >= 1)
            return 5;
        if (cout[6] >= 1)
            return 3;
        if (cout[7] >= 1)
            return -5;
        if (cout[8] >= 1)
            return -5;
        if (cout[9] >= 1)
            return -5;
        return 0;
    }

    // 返回所属类型数组
    public int getScoreType(String s, int x, int type) {
        // 先横匹配
        String[][] matchS1 = { { "11111" }, { "011110" }, { "011112", "211110", "0101110", "0111010", "0110110" },
                { "01110", "010110", "011010" },
                { "001112", "211100", "010112", "211010", "011012", "210110", "10011", "11001", "10101", "2011102" },
                { "00110", "01100", "01010", "010010" }, { "000112", "211000", "001012", "210100", "010012", "210010",
                        "10001", "2010102", "2011002", "2001102" },
                { "211112" }, { "21112" }, { "2112" } };

        String[][] matchS2 = { { "22222" }, { "022220" }, { "022221", "122220", "0202220", "0222020", "0220220" },
                { "02220", "020220", "022020" },
                { "002221", "122200", "020221", "122020", "022021", "120220", "20022", "22002", "20202", "1022201" },
                { "00220", "02200", "02020", "020020" }, { "000221", "122000", "002021", "120200", "020021", "120020",
                        "20002", "1020201", "1022001", "1002201" },
                { "122221" }, { "12221" }, { "1221" } };
        String[][] matchS;
        if (type == 1)
            matchS = matchS1;
        else
            matchS = matchS2;

        int i, j;
        for (i = 0; i < matchS.length; i++) {
            for (j = 0; j < matchS[i].length; j++) {
                int index = 0;
                while ((index = s.indexOf(matchS[i][j], index)) != -1) {
                    if (x >= index && x < index + matchS[i][j].length()) {
                        // System.out.println(matchS[i][j]);
                        return i;
                    }
                    index += matchS[i][j].length() - 1;
                }
            }
        }
        return 10;
    }

    // 判断是否越界
    public boolean ifBorder(int x, int y) {
        if (x < 0 || x >= 19 || y < 0 || y >= 19)
            return true;
        else
            return false;
    }

    public static void main(String[] args) {
        new game();
    }
}
