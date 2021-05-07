import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class test extends JFrame {
    JRadioButton perButton;
    JRadioButton comButton;
    JButton new_game; // 开始游戏
    JButton give_up;
    JButton rule;
    JButton about;
    JButton exit;
    ButtonGroup group;
    Color background = Color.decode("#a9a9a9");

    test() {
        super("Game");// 设置标题
        setSize(890, 750);// 设置宽高
        setLocation(400, 50);// 设置位置
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置默认关闭
        this.setLayout(new BorderLayout());
        ImageIcon icon = new ImageIcon("./cat.png");
        setIconImage(icon.getImage());
        JPanel j = new JPanel();
        JPanel j1 = new JPanel(new GridLayout(5, 1, 10, 20));
        // 设置棋局的相关选项

        JButton temp = new JButton("");
        temp.setVisible(false);
        JButton temp2 = new JButton("");
        temp2.setVisible(false);
        new_game = new JButton("开始游戏");
        JPanel j2 = new JPanel();
        group = new ButtonGroup();
        perButton = new JRadioButton("人人对战");
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
        j.setBackground(background);
        j1.setBackground(background);
        j2.setBackground(background);
        j3.setBackground(background);
        j4.setBackground(background);

        this.add(j, BorderLayout.EAST);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new test();
    }
}
