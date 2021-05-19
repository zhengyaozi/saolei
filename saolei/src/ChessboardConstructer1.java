import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JPopupMenu;  //弹出式菜单
import javax.swing.JMenuItem;  //这是 菜单项类


//用于**单人游戏**时构建棋盘
public class ChessboardConstructer1  implements ActionListener,Chessboard{

    private static JFrame frame=new JFrame();;

    public static JFrame getJFrame() {
        return frame;
    }
//

    //用于测试ChessboardConstructer1
    public static void main(String[] args){
        new ChessboardConstructer1();
    }


    ImageIcon bannerIcon = new ImageIcon("banner.png");
    ImageIcon guessIcon = new ImageIcon("guess.png");
    ImageIcon bombIcon = new ImageIcon("bomb.png");
    ImageIcon failIcon = new ImageIcon("fail.png");
    ImageIcon winIcon = new ImageIcon("win.png");
    ImageIcon winFlagIcon = new ImageIcon("win_flag.png");
    ImageIcon Flag1 = new ImageIcon("1.png");
    ImageIcon flag = new ImageIcon("flag.png");


        int ROW = GameStat.maprow;
    int COL = GameStat.mapcolumn;
//    int ROW = 9;
//    int COL = 9;
    int[][] data = new int[ROW][COL];//存放数据
    JButton[][] btns = new JButton[ROW][COL];
        int LEICOUNT = GameStat.maplei; //雷的数量
//    int LEICOUNT = 4;
    int LEICODE = -1;  //表示是雷
    int unopened = ROW * COL; //未开格子数
    int opened = 0; //已开格子数
    int seconds = 0; //时钟计数
    Boolean firstClick = true;//表示本次点击是否为首次点击
    int[][] buttonStat = new int[ROW][COL]; // 用以表示该格是否被插上旗子 1为插上了，0为没有插上

    JButton bannerBtn = new JButton(bannerIcon);
    JLabel label1 = new JLabel("待开：" + unopened);
    JLabel label2 = new JLabel("已开：" + opened);
    JLabel label3 = new JLabel("用时：" + seconds + "s");
    Timer timer = new Timer(1000, this);

    //初始化棋盘
    public ChessboardConstructer1(){
        int fraWidth = 12*COL+260;//frame的宽
        int fraHeight = 12*ROW+460;//frame的高
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        frame.setLocation(0, 0);
        float proportionW = screenWidth/fraWidth;
        float proportionH = screenHeight/fraHeight;

        modifyComponentSize(frame, proportionW,proportionH);
        frame.toFront();
        frame.setSize(12*COL+260, 12*ROW+460);
        frame.setResizable(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        setHeader();

        addLei();

        setButtons();

        timer.start();

        frame.setVisible(true);

    }

    //向数据数组中埋下雷
    private void addLei(){
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
        addLei();
        return;
    }

    //启动时创建并添加按钮
    private void setButtons(){
        Container con = new Container();
        con.setLayout(new GridLayout(ROW, COL));

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                buttonStat[i][j] = 0;
                JButton btn = new JButton(guessIcon);
                btn.setMargin(new Insets(0,0,0,0));
                btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        JButton btn = (JButton)e.getSource();
                        if(e.getButton()==1){
                            System.out.println("左键");
                            leftCliked(btn);
                        }
                        if(e.getButton()==3){
                            System.out.println("右键");
                            rightClicked(btn);
                        }
                    }
                });
                btn.setOpaque(true);
                btn.setBackground(new Color(244,183,113));
                btn.addActionListener(this);
                //JButton btn = new JButton(data[i][j] + "");
                con.add(btn);
                btns[i][j] = btn;
            }
        }

        frame.add(con, BorderLayout.CENTER);
    }

    //左键雷区时所做的操作
    private void leftCliked(JButton btn) {

        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if(btn.equals(btns[i][j])) {
                    //如果该格子已被插上旗子，则直接返回不做任何动作
                    if(buttonStat[i][j] == 1)
                        return;
                    //3.2避免首发触雷
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
                    if(data[i][j] == LEICODE) {
                        lose();
                    } else {
                        firstClick = false;
                        openCell(i, j);
                        checkWin();
                    }
                    return;
                }
            }
        }
    }

    //右键雷区时所做的操作
    private void rightClicked(JButton btn) {
        System.out.println("右键点击");
        for(int i = 0;i < ROW;i++){
            for(int j = 0;j< COL;j++){
                if(btn.equals(btns[i][j])){
                    if(buttonStat[i][j] == 0){
                        buttonStat[i][j] = 1;
                        Image temp = flag.getImage().getScaledInstance(btn.getWidth(),btn.getHeight(),flag.getImage().SCALE_DEFAULT);
                        flag = new ImageIcon(temp);
                        btn.setIcon(flag);
                        btn.setMargin(new Insets(0,0,0,0));
                    }else if(buttonStat[i][j] == 1){
                        buttonStat[i][j] = 0;
                        btn.setIcon(guessIcon);
                        btn.setMargin(new Insets(0,0,0,0));
                    }
                }
            }
        }
    }

    //设置头部的Banner以及状态栏的布局和控件
    private void setHeader() {
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints c1 = new GridBagConstraints(0,0,3,1,1.0,1.0,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0,0);
        panel.add(bannerBtn, c1);
        bannerBtn.addActionListener(this);

        label1.setOpaque(true);
        label1.setBackground(Color.white);
        label1.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        label2.setOpaque(true);
        label2.setBackground(Color.white);
        label2.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        label3.setOpaque(true);
        label3.setBackground(Color.white);
        label3.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        bannerBtn.setOpaque(true);
        bannerBtn.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        bannerBtn.setBackground(Color.white);

        GridBagConstraints c2 = new GridBagConstraints(0,1,1,1,1.0,1.0,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0,0);
        panel.add(label1, c2);
        GridBagConstraints c3 = new GridBagConstraints(1,1,1,1,1.0,1.0,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0,0);
        panel.add(label2, c3);
        GridBagConstraints c4 = new GridBagConstraints(2,1,1,1,1.0,1.0,GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0,0);
        panel.add(label3, c4);

        frame.add(panel, BorderLayout.NORTH);

    }

    //ActionListener事件响应：点击按钮和时钟都会触发这个方法
    @Override
    public void actionPerformed(ActionEvent e) {
        //时钟计时
        if(e.getSource() instanceof Timer) {
            seconds++;
            label3.setText("用时：" + seconds + "s");
            timer.start();
            return;
        }

        //监听顶部按钮的restart功能
        JButton btn = (JButton)e.getSource();
        if(btn.equals(bannerBtn)) {
            restart();
            return;
        }
    }

    //游戏胜利后的处理
    private void checkWin() {
        int count = 0;
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if(btns[i][j].isEnabled()) count++;
            }
        }
        if(count == LEICOUNT) {
            timer.stop();
            for (int i = 0; i < ROW; i++) {
                for (int j = 0; j < COL; j++) {
                    if(btns[i][j].isEnabled()) {
                        btns[i][j].setIcon(winFlagIcon);
                    }
                }
            }
            bannerBtn.setIcon(winIcon);
            JOptionPane.showMessageDialog(frame, "你赢了, Yeah！\n点击Banner重新开始", "赢了", JOptionPane.PLAIN_MESSAGE );
        }
    }

    //输掉游戏的处理
    private void lose() {
        timer.stop();
        bannerBtn.setIcon(failIcon);
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                if(btns[i][j].isEnabled()) {
                    JButton btn = btns[i][j];
                    if(data[i][j] == LEICODE) {
                        btn.setEnabled(false);
                        btn.setIcon(bombIcon);
                        btn.setDisabledIcon(bombIcon);
                    } else {
                        btn.setIcon(null);
                        btn.setEnabled(false);
                        btn.setOpaque(true);
                        btn.setText(data[i][j]+"");
                    }
                }
            }
        }
        new losepanel();
    }

    //打开格子的处理
    private void openCell(int i, int j) {
        JButton btn = btns[i][j];
        if(!btn.isEnabled()) return;

        btn.setIcon(null);
        btn.setEnabled(false);
        btn.setOpaque(true);
        btn.setBackground(Color.GREEN);
        btn.setText(data[i][j]+"");
        addOpenCount();

        //级联打开空格
        if(data[i][j] == 0) {
            if (i>0 && j>0 && data[i-1][j-1] == 0) openCell(i-1, j-1);
            if (i>0 && data[i-1][j] == 0) openCell(i-1, j);
            if (i>0 && j<COL-1 && data[i-1][j+1] == 0) openCell(i-1, j+1);
            if (j>0 && data[i][j-1] == 0) openCell(i, j-1);
            if (j<COL-1 && data[i][j+1] == 0) openCell(i, j+1);
            if (i<ROW-1 && j>0 && data[i+1][j-1] == 0) openCell(i+1, j-1);
            if (i<ROW-1 && data[i+1][j] == 0) openCell(i+1, j);
            if (i<ROW-1 && j<COL-1 && data[i+1][j+1] == 0) openCell(i+1, j+1);
        }
    }

    //计算并设置“代开”与“已开”
    private void addOpenCount() {
        opened++;
        unopened--;
        label1.setText("待开：" + unopened);
        label2.setText("已开：" + opened);
    }

    /**
     * 1. 给数据清零；2. 给按钮恢复状态。3.重新启动时钟。
     */



    //重新开始界面：内设计两个选项：1.重新开始本类型游戏2.回到模式选择菜单
    private void restart() {
        //在选择“再来一局”时触发
        //恢复了数据和按钮
        for (int i = 0; i < ROW; i++) {
            for (int j = 0; j < COL; j++) {
                buttonStat[i][j] = 0;
                data[i][j] = 0;
                btns[i][j].setBackground(new Color(244,183,113));
                btns[i][j].setEnabled(true);
                btns[i][j].setText("");
                btns[i][j].setIcon(guessIcon);
            }
        }
        //状态栏恢复
        unopened = ROW * COL;
        opened = 0;
        seconds = 0;
        label1.setText("待开：" + unopened);
        label2.setText("已开：" + opened);
        label3.setText("用时：" + seconds + "s");
        //重新启动！
        addLei();
        timer.start();
        firstClick = true;

        //在选择“返回模式选择界面”时触发

    }

    //自适应大小窗口的方法
    public static void modifyComponentSize(JFrame frame,float proportionW,float proportionH){

        try
        {
            Component[] components = frame.getRootPane().getContentPane().getComponents();
            for(Component co:components)
            {
//				String a = co.getClass().getName();//获取类型名称
//				if(a.equals("javax.swing.JLabel"))
//				{
//				}
                float locX = co.getX() * proportionW;
                float locY = co.getY() * proportionH;
                float width = co.getWidth() * proportionW;
                float height = co.getHeight() * proportionH;
                co.setLocation((int)locX, (int)locY);
                co.setSize((int)width, (int)height);
                int size = (int)(co.getFont().getSize() * proportionH);
                Font font = new Font(co.getFont().getFontName(), co.getFont().getStyle(), size);
                co.setFont(font);
            }
        }
        catch (Exception e)
        {
            // TODO: handle exception
        }

    }


}