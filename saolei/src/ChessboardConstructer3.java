import javax.swing.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Random;

public class ChessboardConstructer3 extends JFrame implements ActionListener{
    String boardName;
    int screenWidth= Toolkit.getDefaultToolkit().getScreenSize().width;
    int screenHeight=Toolkit.getDefaultToolkit().getScreenSize().height;
    int[][] data = new int[GameStat.maprow][GameStat.mapcolumn];
    int ROW = GameStat.maprow;
    int COL = GameStat.mapcolumn;
    int LEICOUNT =  GameStat.maplei;
    int LEICODE = -1;
    int count = 0; //用于计算回合进程
    Boolean firstClick = true;//该次点击是否为首次点击
    int unopened = LEICOUNT;//未打开的雷的数量

    //按钮音效
    AudioClip b1;
    AudioClip b2;
    File file1 = new File("button1.wav");
    File file2 = new File("boom.wav");


    //玩家分数相关
    public String player1 = GameStat.player1;
    public String player2 = GameStat.player2;
    public ImageIcon p1Icon = GameStat.p1Icon;
    public ImageIcon p2Icon = GameStat.p2Icon;
    public int p1grade = 0;//p1的成绩
    public int p2grade = 0;//p2的成绩
    public int p1mis = 0;//p1的失误
    public int p2mis = 0;//p2的失误
    int[][] buttonStat = new int[GameStat.maprow][GameStat.mapcolumn];//表示按钮的状态 0为未开 1为已开或已插旗 2为处于透视状态
    JButton[][] btns = new JButton[GameStat.maprow][GameStat.mapcolumn];//承装雷区的所有按钮
    JLabel p1name = new JLabel(player1);
    JLabel p2name = new JLabel(player2);
    JLabel p1Grade = new JLabel("玩家1得分为 " + p1grade);
    JLabel p1mistake = new JLabel("玩家1失误为 " + p1mis);
    JLabel p2Grade = new JLabel("玩家2得分为 " + p2grade);
    JLabel p2mistake = new JLabel("玩家2失误为 " + p2mis);
    JLabel coinLabel = new JLabel();

    //计时系统
    Timer timer = new Timer(1000,this); //用于计时
    int seconds = 0; //用于显示计时的秒数
    JLabel labelt = new JLabel("用时：" + seconds + "s"); //显示计时秒数的label

    //作弊系统
    JButton cheatbtn = new JButton();
    Boolean cheatStat = false;//此时是否处在作弊状态

    //功能按钮
    JButton restarter = new JButton();//重开一局的按钮
    JButton toIntergame = new JButton("返回菜单栏");

    //图标管理
    ImageIcon Clicked = new ImageIcon("Clicked.png");
    ImageIcon Covered = new ImageIcon("Covered.png");
    ImageIcon Flag = new ImageIcon("FLag.jpg");
    ImageIcon mine = new ImageIcon("mine.png");
    ImageIcon seethrough = new ImageIcon("seethrough.png");
    ImageIcon sweeepbg = new ImageIcon("sweepbg.jpg");
    ImageIcon n1 = new ImageIcon("1.png");
    ImageIcon n2 = new ImageIcon("2.png");
    ImageIcon n3 = new ImageIcon("3.png");
    ImageIcon n4 = new ImageIcon("4.png");
    ImageIcon n5 = new ImageIcon("5.png");
    ImageIcon n6 = new ImageIcon("6.png");
    ImageIcon n7 = new ImageIcon("7.png");
    ImageIcon n8 = new ImageIcon("8.png");
    ImageIcon cheatIcon = new ImageIcon("cheat.jpg");
    ImageIcon coin = new ImageIcon("coin.jpg");


    //用于测试ChessboardConstructer2
    public static void main(String[] args) {
        new ChessboardConstructer3();
    }

    //默认的构造方法/////////////////////////////////////////
    public ChessboardConstructer3(){
        int width = GameStat.mapcolumn*30 + 500;
        int height = GameStat.maprow*30 + 200;
        this.setBounds((screenWidth-width)/2,(screenHeight-height)/2,width,height);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel sweep=new JPanel(new GridLayout(GameStat.maprow,GameStat.mapcolumn,0,0));
        sweep.setBounds((width-GameStat.mapcolumn*30)/2,(height-GameStat.maprow*30)-50,GameStat.mapcolumn*30,GameStat.maprow*30);  //面板的大小位置

        //作弊按钮
        cheatbtn.setBorder(BorderFactory.createRaisedBevelBorder());
        Image cheatImage = cheatIcon.getImage();
        Image cheatSmallImage = cheatImage.getScaledInstance(30, 30, Image.SCALE_FAST);
        ImageIcon cheatSmallIcon = new ImageIcon(cheatSmallImage);
        cheatbtn.setIcon(cheatSmallIcon);//设置作弊按钮icon
        cheatbtn.setBounds((width-30)/2,90,30,30);
        cheatbtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                cheat();
            }
        });

        //重开按钮
        restarter.setText("再来一局");
        restarter.setBounds(250,50,100,50);
        restarter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new ChessboardConstructer2();
                dispose();//点击后关闭本窗口
            }
        });


        //召唤菜单栏按钮
        toIntergame.setBounds(width-350,50,100,50);
        toIntergame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new intergame();
            }
        });

        //对number进行操作；
        addMine();

        //添加计时器的头部面板
        setHeader();

        //玩家面板设置
        Player p1=new Player(GameStat.player1,GameStat.p1Icon);
        JPanel p1Pane=p1.PlayPane(width,250,height,1);
        p1Pane.setLocation(0,0);
        //玩家1的得分和失误
        p1Grade.setBounds(50,300,100,30);
        p1Grade.setForeground(new Color(71, 61, 50));
        p1Grade.setBackground(new Color(154, 228, 241));
        p1Grade.setOpaque(true);
        p1mistake.setBounds(50,350,100,30);
        p1mistake.setForeground(new Color(71, 61, 50));
        p1mistake.setBackground(new Color(154, 228, 241));
        p1mistake.setOpaque(true);
        p1name.setText(player1);
        p2name.setText(player2);
        p1name.setBounds(50,250,100,30);
        p1name.setBackground(new Color(154, 228, 241));
        p1name.setOpaque(true);
        p2name.setBounds(width-150,250,100,30);
        p2name.setBackground(new Color(154, 228, 241));
        p2name.setOpaque(true);


        Player p2=new Player(GameStat.player2, GameStat.p2Icon);
        JPanel p2Pane=p2.PlayPane(width,250,height,2);
        p2Pane.setLocation(width-250,0);
        //玩家2的得分和失误
        p2Grade.setBounds(width-150,300,100,30);
        p2Grade.setForeground(new Color(71, 61, 50));
        p2Grade.setBackground(new Color(154, 228, 241));
        p2Grade.setOpaque(true);
        p2mistake.setBounds(width-150,350,100,30);
        p2mistake.setForeground(new Color(71, 61, 50));
        p2mistake.setBackground(new Color(154, 228, 241));
        p2mistake.setOpaque(true);

        //回合指示器硬币
        Image coinImage = coin.getImage();
        Image coinSmallImage = coinImage.getScaledInstance(30, 30, Image.SCALE_FAST);
        ImageIcon coinSmallIcon = new ImageIcon(coinSmallImage);
        coinLabel.setIcon(coinSmallIcon);//设置金币icon
        coinLabel.setBounds(200,250,30,30);



        //向sweep panel 中添加按钮，并加载buttonStat和btns两个数组
        for(int i=0;i<GameStat.maprow;i++){
            for(int j=0;j<GameStat.mapcolumn;j++){
                buttonStat[i][j] = 0; //将状态统统初始化为未开
                JButton btn=new JButton();
                btn.setMargin(new Insets(0,0,0,0));//设置按钮内边界为零
                btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
//                        super.mouseClicked(e);
                        JButton btn = (JButton)e.getSource();
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            //左键操作
                            leftClicked(btn);
                        }else if (e.getButton() == MouseEvent.BUTTON3) {
                            //右键操作
                            rightClicked(btn);
                        }else if(e.getButton() == MouseEvent.MOUSE_ENTERED){

                        }
                    }
                });
                btn.setOpaque(true);
                Image image = Covered.getImage();
                Image smallImage = image.getScaledInstance(30, 30, Image.SCALE_FAST);
                ImageIcon smallIcon = new ImageIcon(smallImage);
                btn.setIcon(smallIcon);//设置初始按钮icon
                btn.addActionListener(this); //不清楚有什么用
                btns[i][j] = btn;
                sweep.add(btn);//向Jpanel中添加按钮
            }
        }

        //背景图片加入
        Image image = sweeepbg.getImage();
        Image smallImage = image.getScaledInstance(width,height,Image.SCALE_DEFAULT);
        ImageIcon smallIcon = new ImageIcon(smallImage);
        JLabel bgLabel = new JLabel(smallIcon);//背景的Label
        bgLabel.setBounds(0,0,width,height);//设置背景Label的位置和大小
        Container contain = this.getContentPane();


        this.add(sweep);
        this.add(p1Grade);
        this.add(p2Grade);
        this.add(p1mistake);
        this.add(p2mistake);
        this.add(coinLabel);
        this.add(p1Pane);
        this.add(p2Pane);
        this.add(cheatbtn);
        this.add(restarter);
        this.add(labelt);
        this.add(toIntergame);
        this.add(p1name);
        this.add(p2name);
        this.add(bgLabel);

        this.setTitle("人机对战扫雷");
        timer.start();

        this.setVisible(true);
    }


    //左键按钮时所作的操作
    private void leftClicked(JButton btn) {
        System.out.println("左键1");
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if(btn.equals(btns[i][j])){
                    //如果该旗子已开,则直接返回不做任何动作
                    if(buttonStat[i][j] == 1)
                        return;

                    //避免首发触雷
                    if(firstClick == true && data[i][j] == LEICODE){
                        data[i][j] = 0;
                        Random rand = new Random();
                        for (Boolean flag = false; !flag; ) {
                            int r = rand.nextInt(ROW);
                            int c = rand.nextInt(COL);
                            if(data[r][c] != LEICODE) {
                                data[r][c] = LEICODE;
                                flag = true;
                            }
                        }
                        //重新计算周围雷的数量
                        for (int m = 0; m < ROW; m++) {
                            for (int n = 0; n < COL; n++) {
                                int tempCount = 0;
                                if (m>0 && n>0 && data[m-1][n-1] == LEICODE) tempCount++;
                                if (m>0 && data[m-1][n] == LEICODE) tempCount++;
                                if (m>0 && n<COL-1 && data[m-1][n+1] == LEICODE) tempCount++;
                                if (n>0 && data[m][n-1] ==  LEICODE) tempCount++;
                                if (n<COL-1 && data[m][n+1] == LEICODE) tempCount++;
                                if (m<ROW-1 && n>0 && data[m+1][n-1] == LEICODE) tempCount++;
                                if (m<ROW-1 && data[m+1][n] == LEICODE) tempCount++;
                                if (m<ROW-1 && n<COL-1 && data[m+1][n+1] == LEICODE) tempCount++;
                                //3.1避免过度密集 内方法为重新构建埋雷
                                if(data[m][n] == LEICODE && tempCount == 8){
                                    readd();
                                    return;
                                }else if(data[m][n] == LEICODE)
                                    continue;
                                data[m][n] = tempCount;
                            }
                        }
                    }

                    //正常的展开
                    firstClick = false;
                    openCell(i,j);
                }
            }
        }

    }

    //正常地 左键 展开格子
    //判定回合进程，开到安全的格子则将其打开、开到雷则给对应玩家失误加1
    public void openCell(int r,int c) {
        JButton btn = btns[r][c];
        if(!btn.isEnabled()) return; //按钮不可用直接返回
        buttonStat[r][c] = 1;//无论怎样该格一定会被打开,所以直接改变其状态为已开
        //首先判定此次点击为哪位玩家的操作
        if(count < GameStat.at){ //判定为玩家的操作
            count++;
            //金币转向
            if(count == GameStat.at){
                Image coinImage = coin.getImage();
                Image coinSmallImage = coinImage.getScaledInstance(30, 30, Image.SCALE_FAST);
                ImageIcon coinSmallIcon = new ImageIcon(coinSmallImage);
                coinLabel.setIcon(coinSmallIcon);//设置金币icon
                coinLabel.setBounds(this.getWidth()-230,250,30,30);
            }

            //展开雷区图案操作
            if(data[r][c] == LEICODE){
                //播放背景音乐
                try {
                    b2 = Applet.newAudioClip(file2.toURL());
                } catch (MalformedURLException malformedURLException) {
                    malformedURLException.printStackTrace();
                }
                b2.play();
                unopened--;
                p1mis++;//玩家1踩雷，失误数加1
                p1grade--;//得分减一
                p1mistake.setText("玩家1失误为 " + p1mis);
                p1Grade.setText("玩家1得分为 " + p1grade);
                Image image = mine.getImage();
                Image smallImage = image.getScaledInstance(30, 30, Image.SCALE_FAST);
                ImageIcon smallIcon = new ImageIcon(smallImage);
                btn.setIcon(smallIcon);//设置按钮icon为暴雷图标
                checkWin();
            }else{
                //播放背景音乐
                try {
                    b1 = Applet.newAudioClip(file1.toURL());
                } catch (MalformedURLException malformedURLException) {
                    malformedURLException.printStackTrace();
                }
                b1.play();
                openNum(btn,data[r][c]);
            }

            //如果此次为玩家的最后一次操作，则循环new n次Robot
            if(count == GameStat.at){
                for(;count < GameStat.at*2;){
                    new Robot(this);
                    checkWin();

                    //如果为机器人最后一次行动则将回合转交给玩家
                    count++;
                    if(count == GameStat.at*2){
                        Image coinImage = coin.getImage();
                        Image coinSmallImage = coinImage.getScaledInstance(30, 30, Image.SCALE_FAST);
                        ImageIcon coinSmallIcon = new ImageIcon(coinSmallImage);
                        coinLabel.setIcon(coinSmallIcon);//设置金币icon
                        coinLabel.setBounds(200,250,30,30);
                        count = 0;
                        return;
                    }
                }
            }
        }
    }

    //右键按钮时所做的操作
    private void rightClicked(JButton btn) {
        System.out.println("右键1");
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if (btn.equals(btns[i][j])) {
                    //如果该旗子已开或不可用,则直接返回不做任何动作
                    if (buttonStat[i][j] == 1 || !btn.isEnabled())
                        return;

                    //播放背景音乐
                    try {
                        b1 = Applet.newAudioClip(file1.toURL());
                    } catch (MalformedURLException malformedURLException) {
                        malformedURLException.printStackTrace();
                    }
                    b1.play();

                    buttonStat[i][j] = 1;//无论怎样该格一定会被打开
                    //首先判定此次点击为哪位玩家的操作
                    if(count < GameStat.at){ //判定为玩家的操作
                        count++;
                        if(count == GameStat.at){
                            Image coinImage = coin.getImage();
                            Image coinSmallImage = coinImage.getScaledInstance(30, 30, Image.SCALE_FAST);
                            ImageIcon coinSmallIcon = new ImageIcon(coinSmallImage);
                            coinLabel.setIcon(coinSmallIcon);//设置金币icon
                            coinLabel.setBounds(this.getWidth()-230,250,30,30);
                        }
                        if(data[i][j] == LEICODE){
                            unopened--;
                            p1grade++;//玩家1插旗成功，积分加1
                            p1Grade.setText("玩家1得分为 " + p1grade);
                            Image image = Flag.getImage();
                            Image smallImage = image.getScaledInstance(30, 30, Image.SCALE_FAST);
                            ImageIcon smallIcon = new ImageIcon(smallImage);
                            btn.setIcon(smallIcon);//设置按钮icon为暴雷图标
                            checkWin();
                        }else{
                            openNum(btn,data[i][j]);
                            p1mis++;//玩家1插旗错误，失误数加1
                            p1mistake.setText("玩家1失误为 " + p1mis);
                        }

                        //如果此次为玩家的最后一次操作，则循环new n次Robot
                        if(count == GameStat.at){
                            for(;count < GameStat.at*2;){
                                new Robot(this);
                                checkWin();

                                //如果机器人行动完则转为玩家行动
                                count++;
                                if(count == GameStat.at*2){
                                    Image coinImage = coin.getImage();
                                    Image coinSmallImage = coinImage.getScaledInstance(30, 30, Image.SCALE_FAST);
                                    ImageIcon coinSmallIcon = new ImageIcon(coinSmallImage);
                                    coinLabel.setIcon(coinSmallIcon);//设置金币icon
                                    coinLabel.setBounds(200,250,30,30);
                                    count = 0;
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    //每次开出雷后检查是否胜利
    private void checkWin() {
        if(p1grade-p2grade > unopened || (unopened == 0 && p1grade > p2grade) || (unopened == 0 && p1grade == p2grade && p1mis < p2mis)) {//p1获胜
            new WinPanel(GameStat.player1,GameStat.p1Icon);
            dispose();
        }else if(p2grade-p1grade > unopened || (unopened == 0 && p2grade > p1grade) || (unopened == 0 && p1grade == p2grade && p2mis < p1mis)){//p2获胜
            new WinPanel(GameStat.player2,GameStat.p2Icon);
            dispose();
        }else if(unopened == 0 && p1grade == p2grade && p1mis == p2mis){//平局
            new WinPanel();
            dispose();
        }
    }

    //当开到数字时将数字显示出，并返回是否为
    private void openNum(JButton btn,int Num){
        if(Num == 0) {
            Image image = Clicked.getImage();
            Image smallImage = image.getScaledInstance(30, 30, Image.SCALE_FAST);
            ImageIcon smallIcon = new ImageIcon(smallImage);
            btn.setIcon(smallIcon);//设置按钮icon为 0 图标
        }else if(Num == 1){
            Image image = n1.getImage();
            Image smallImage = image.getScaledInstance(30, 30, Image.SCALE_FAST);
            ImageIcon smallIcon = new ImageIcon(smallImage);
            btn.setIcon(smallIcon);//设置按钮icon为 1 图标
        }else if(Num == 2){
            Image image = n2.getImage();
            Image smallImage = image.getScaledInstance(30, 30, Image.SCALE_FAST);
            ImageIcon smallIcon = new ImageIcon(smallImage);
            btn.setIcon(smallIcon);//设置按钮icon为 2 图标
        }else if(Num == 3){
            Image image = n3.getImage();
            Image smallImage = image.getScaledInstance(30, 30, Image.SCALE_FAST);
            ImageIcon smallIcon = new ImageIcon(smallImage);
            btn.setIcon(smallIcon);//设置按钮icon为 3 图标
        }else if(Num == 4){
            Image image = n4.getImage();
            Image smallImage = image.getScaledInstance(30, 30, Image.SCALE_FAST);
            ImageIcon smallIcon = new ImageIcon(smallImage);
            btn.setIcon(smallIcon);//设置按钮icon为 4 图标
        }else if(Num == 5){
            Image image = n5.getImage();
            Image smallImage = image.getScaledInstance(30, 30, Image.SCALE_FAST);
            ImageIcon smallIcon = new ImageIcon(smallImage);
            btn.setIcon(smallIcon);//设置按钮icon为 5 图标
        }else if(Num == 6){
            Image image = n6.getImage();
            Image smallImage = image.getScaledInstance(30, 30, Image.SCALE_FAST);
            ImageIcon smallIcon = new ImageIcon(smallImage);
            btn.setIcon(smallIcon);//设置按钮icon为 6 图标
        }else if(Num == 7){
            Image image = n7.getImage();
            Image smallImage = image.getScaledInstance(30, 30, Image.SCALE_FAST);
            ImageIcon smallIcon = new ImageIcon(smallImage);
            btn.setIcon(smallIcon);//设置按钮icon为 7 图标
        }else if(Num == 8){
            Image image = n8.getImage();
            Image smallImage = image.getScaledInstance(30, 30, Image.SCALE_FAST);
            ImageIcon smallIcon = new ImageIcon(smallImage);
            btn.setIcon(smallIcon);//设置按钮icon为 8 图标
        }
    }

    private void setHeader() {
        labelt.setBounds(0,0,100,50);
        labelt.setText("用时： " + seconds + "s");
        //待编写，将代开已开以及计时加入整个面板
    }

    //对data进行操作
    private void addMine() {
        Random rand = new Random();
        for (int i = 0; i < LEICOUNT; ) {
            int r = rand.nextInt(ROW);
            int c = rand.nextInt(COL);
            if(data[r][c] != LEICODE) {
                data[r][c] = LEICODE;
                i++;
            }
        }

        //计算周边的雷的数量
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                int tempCount = 0;
                if (i>0 && j>0 && data[i-1][j-1] == LEICODE) tempCount++;
                if (i>0 && data[i-1][j] == LEICODE) tempCount++;
                if (i>0 && j<COL-1 && data[i-1][j+1] == LEICODE) tempCount++;
                if (j>0 && data[i][j-1] ==  LEICODE) tempCount++;
                if (j<COL-1 && data[i][j+1] == LEICODE) tempCount++;
                if (i<ROW-1 && j>0 && data[i+1][j-1] == LEICODE) tempCount++;
                if (i<ROW-1 && data[i+1][j] == LEICODE) tempCount++;
                if (i<ROW-1 && j<COL-1 && data[i+1][j+1] == LEICODE) tempCount++;
                //3.1避免过度密集 内方法为重新构建埋雷
                if(data[i][j] == LEICODE && tempCount == 8){
                    readd();
                    return;
                }else if(data[i][j] == LEICODE)
                    continue;
                data[i][j] = tempCount;
            }
        }
    }

    //若是埋下的雷不符合要求则将data数组刷新，并重新埋雷
    public void readd(){
        //遍历将data数组中刷新为0
        for(int i = 0;i < ROW;i++){
            for(int j = 0;j < COL;j++){
                data[i][j] = 0;
            }
        }
        addMine();
        return;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //时钟计时
        if(e.getSource() instanceof Timer) {
            seconds++;
            labelt.setText("用时：" + seconds + "s");
            timer.start();
            return;
        }
    }

    private void cheat() {
        //未作弊状态下，全部打开
        if (!cheatStat) {
            cheatStat = true;
            for (int i = 0; i < GameStat.maprow; i++) {
                for (int j = 0; j < GameStat.mapcolumn; j++) {
                    //如果按钮为已开状态则跳过该方块的操作
                    if (buttonStat[i][j] == 1)
                        continue;
                    else if (buttonStat[i][j] == 0) {//按钮处于未开状态
                        buttonStat[i][j] = 2;//将按钮状态变为作弊中
                        if (data[i][j] == LEICODE) {
                            Image image = seethrough.getImage();
                            Image smallImage = image.getScaledInstance(30, 30, Image.SCALE_FAST);
                            ImageIcon smallIcon = new ImageIcon(smallImage);
                            btns[i][j].setIcon(smallIcon);//设置按钮icon为暴雷图标

                        } else {
                            openNum(btns[i][j], data[i][j]);
                        }
                    }
                }
            }
        } else if (cheatStat) {//已作弊状态下，将作弊方块打开
            cheatStat = false;//将状态改为未作弊状态
            for (int i = 0; i < GameStat.maprow; i++) {
                for (int j = 0; j < GameStat.mapcolumn; j++) {
                    //如果按钮为已开状态则跳过该方块的操作
                    if (buttonStat[i][j] == 1)
                        continue;
                    else if (buttonStat[i][j] == 2) {//按钮处于作弊状态
                        buttonStat[i][j] = 0;//将按钮状态变为未开
                        Image image = Covered.getImage();
                        Image smallImage = image.getScaledInstance(30, 30, Image.SCALE_FAST);
                        ImageIcon smallIcon = new ImageIcon(smallImage);
                        btns[i][j].setIcon(smallIcon);//设置按钮icon为暴雷图标
                    }
                }
            }
        }
    }
}
