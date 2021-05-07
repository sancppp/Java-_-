# Java课设
## 1、需求分析
### 1.人人对战
#### 1.初始化棋盘
#### 2.开始对战

```
	通过监听鼠标点击事件，完成开始棋局模块。用户在点击“开始”按钮后初始化棋盘，并开始计时。先手方需先下一枚棋子，棋局才正式开始。棋局开始后，用户可以选择中途退出（则另一方胜利）或者重新开始新一盘棋局。
```

##### 1.下棋功能

```
	在正常开始棋局后，两方棋手轮流下棋，只有当用户将当前棋子下在棋盘内的交叉点处且该交叉点处没有棋子时，才判定下棋有效。否则当前用户不能完成该轮下棋。
```

##### 2.判断输赢

```
	在每一次落下棋子后，调用该模块。如果判断有一方已经胜利，则及时弹出该句结果，并结束棋局。在每局五颗棋子率先连成直线的一方，系统自动判定为赢，另一方为输。如果整个棋盘上最后一个位置落棋后没有一方能够将5颗棋子连成一线，则判定该局为和棋。
```

##### 3.悔棋功能

```
	某一方点击悔棋按钮前另一方没有落棋时才可以悔棋。点击悔棋按钮后，上一步下棋撤销。选择悔棋的一方重新获得一次落棋机会。
```

#### 3.重新开始

    	与"开始对战"相同，通过监听鼠标点击事件，完成重新开始棋局模块。点击该按钮后，界面回到初始化后的棋盘。用户可以再次开始新的棋局。与此同时，计时清零。

#### 4.退出

    	如果游戏中一方选择退出，则自动判定留下的一方胜利。

### 2.人机对战

#### 1.机器下棋算法

```
	需实现一个胜率较高的算法。
```

## 2.具体实现

### 1、棋盘绘制

```
	经过调查网络上已发布的他人制作的五子棋小游戏，我们决定采用经典的19*19棋盘，且所有字体均为黑体。
	利用BorderLayout布局，EAST为按钮部分；CENTER 为棋盘部分。
```

```java
        // GUI绘制
        super("Game");// 设置标题
        setSize(890, 750);// 设置宽高
        setLocation(400, 50);// 设置位置
        this.setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置默认关闭
        this.setLayout(new BorderLayout());
        // 设置图标
        ImageIcon icon = new ImageIcon();
        setIconImage(icon.getImage());
        // 导入棋盘对象
        cBoard = new cBoard();
        Color bac = Color.decode("#a9a9a9");
        cBoard.setBackground(bac);
        this.add(cBoard, BorderLayout.CENTER);
        this.getContentPane().setBackground(bac);
        JPanel j = new JPanel();
        JPanel j1 = new JPanel(new GridLayout(5, 1, 10, 20));
        JButton temp = new JButton("");
        temp.setVisible(false);
        JButton temp2 = new JButton("");
        temp2.setVisible(false);
        new_game = new JButton("开始游戏");
        new_game.setFont(blackFont);
        JPanel j2 = new JPanel();
        group = new ButtonGroup();
        perButton = new JRadioButton("人人对战");
        perButton.setFont(blackFont);
        comButton = new JRadioButton("人机对战");
        comButton.setFont(blackFont);
        group.add(perButton);
        group.add(comButton);
        j2.add(perButton);
        j2.add(comButton);
        perButton.setSelected(true);
        //细节处理
        perButton.setFocusPainted(false);
        perButton.setBorderPainted(false);
        perButton.setContentAreaFilled(false);
        comButton.setFocusPainted(false);
        comButton.setBorderPainted(false);
        comButton.setContentAreaFilled(false); 

        rule = new JButton("游戏说明");
        give_up = new JButton("认输");
        about = new JButton("关于");
        exit = new JButton("退出");
        rule.setFont(blackFont);
        give_up.setFont(blackFont);
        about.setFont(blackFont);
        exit.setFont(blackFont);

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
```

