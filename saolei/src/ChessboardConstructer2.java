import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class ChessboardConstructer2 extends JFrame {
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
//    ImageIcon p1Icon = new ImageIcon("Luffy.png");
//    ImageIcon p2Icon = new ImageIcon("Marco.png");


    //用于测试ChessboardConstructer2
    public static void main(String[] args) {
        new ChessboardConstructer2();
    }

    //默认的构造方法
    public ChessboardConstructer2(){
        int width = GameStat.maprow*30 + 200;
        int height = GameStat.mapcolumn*30 + 200;
        this.setBounds((screenWidth-width)/2,(screenHeight-height)/2,width,height);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel sweep=new JPanel(new GridLayout(GameStat.maprow,GameStat.mapcolumn,0,0));
        sweep.setBounds((this.getWidth()-sweep.getWidth())/2,(this.getHeight()-sweep.getHeight())/2,GameStat.maprow*30,GameStat.mapcolumn*30);  //面板的大小位置

        //对number进行操作；
        addMine();

        Player p1=new Player(GameStat.player1,GameStat.p1Icon);
        JPanel p1Pane=p1.PlayPane(width,100,height,1);
        Player p2=new Player(GameStat.player2, GameStat.p2Icon);
        JPanel p2Pane=p2.PlayPane(width,100,height,2);

        p1Pane.setLocation(0,0);
        p2Pane.setLocation(100,0);



        for(int j=0;j<GameStat.maprow;j++){
            for(int i=0;i<GameStat.mapcolumn;i++){
                JButton jb=new JButton();
                int finalI = i;
                int finalJ = j;
                jb.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if(data[finalI][finalJ]==LEICODE){

                        }
                    }
                });

                jb.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getButton() == MouseEvent.BUTTON3) {
                        }
                    }

                });

                sweep.add(jb);
            }
        }
        //用于读档的构造方法






        this.add(sweep);
        this.add(p1Pane);
        this.add(p2Pane);

        this.setVisible(true);
    }

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
}
