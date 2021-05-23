import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class ChessboardConstructer2 extends JFrame implements ActionListener{
    int screenWidth= Toolkit.getDefaultToolkit().getScreenSize().width;
    int screenHeight=Toolkit.getDefaultToolkit().getScreenSize().height;
    JButton[][] button=new JButton[GameStat.mapcolumn][GameStat.maprow];
    int[][] data = new int[GameStat.mapcolumn][GameStat.maprow];
    public static int n=4;
    public static int plusN=0;
    int ROW = GameStat.maprow;
    int COL = GameStat.mapcolumn;
    int LEICOUNT =  GameStat.maplei;
    int LEICODE = -1;

    int[][] buttonStat = new int[GameStat.maprow][GameStat.mapcolumn];//表示按钮的状态 0为未开 1为插旗 2为已开
    JButton[][] btns = new JButton[GameStat.maprow][GameStat.mapcolumn];//承装雷区的所有按钮

    Timer timer = new Timer(1000,this); //用于计时
    int seconds = 0; //用于显示计时的秒数
    JLabel labelt = new JLabel("用时：" + seconds + "s"); //显示计时秒数的label

    ImageIcon Clicked = new ImageIcon("Clicked.png");
    ImageIcon Covered = new ImageIcon("Covered.png");
    ImageIcon Flag = new ImageIcon("FLag.jpg");
    ImageIcon mine = new ImageIcon("mine.png");
    ImageIcon seethrough = new ImageIcon("seethrough.png");


    //用于测试ChessboardConstructer2
    public static void main(String[] args) {
        new ChessboardConstructer2();
    }

    //默认的构造方法
    public ChessboardConstructer2(){
        int width = GameStat.mapcolumn*30 + 500;
        int height = GameStat.maprow*30 + 400;
        this.setBounds((screenWidth-width)/2,(screenHeight-height)/2,width,height);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel sweep=new JPanel(new GridLayout(GameStat.mapcolumn,GameStat.maprow,0,0));
        sweep.setBounds((width-GameStat.mapcolumn*30)/2,(height-GameStat.maprow*30)-50,GameStat.mapcolumn*30,GameStat.maprow*30);  //面板的大小位置

        //对number进行操作；
        addMine();

        //添加计时器、代开、已开的头部面板
        setHeader();

        Player p1=new Player(GameStat.player1,GameStat.p1Icon);
        JPanel p1Pane=p1.PlayPane(width,100,height,1);
        Player p2=new Player(GameStat.player2, GameStat.p2Icon);
        JPanel p2Pane=p2.PlayPane(width,100,height,2);
        p1Pane.setLocation(0,0);
        p2Pane.setLocation(100,0);


        //向sweep panel 中添加按钮，并加载buttonStat和btns两个数组
        for(int i=0;i<GameStat.maprow;i++){
            for(int j=0;j<GameStat.mapcolumn;j++){
                buttonStat[i][j] = 0; //将状态统统初始化为未开
                JButton btn=new JButton();
                btn.setMargin(new Insets(0,0,0,0));//设置按钮内边界为零
                btn.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        super.mouseClicked(e);
                        JButton btn = (JButton)e.getSource();
                        if (e.getButton() == MouseEvent.BUTTON1) {
                            //左键操作
                            leftClicked(btn);
                        }else if (e.getButton() == MouseEvent.BUTTON3) {
                            //右键操作
                            rightClicked(btn);
                        }
                    }
                });
                btn.setOpaque(true);
                Image image = Covered.getImage();
                Image smallImage = image.getScaledInstance(30, 30, Image.SCALE_FAST);
                ImageIcon smallIcon = new ImageIcon(smallImage);
                btn.setIcon(smallIcon);//设置储时按钮icon
                btn.addActionListener(this); //不清楚有什么用
                sweep.add(btn);//向Jpanel中添加按钮
            }
        }



        this.add(sweep);
        this.add(p1Pane);
        this.add(p2Pane);

        this.setVisible(true);
    }

    //左键按钮时所作的操作
    private void leftClicked(JButton btn) {
    }
    //右键按钮时所做的操作
    private void rightClicked(JButton btn) {
    }

    private void setHeader() {
        JPanel panel = new JPanel(new GridBagLayout());

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
}
