import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class WinPanel extends JFrame implements ActionListener {
    int screenWidth= Toolkit.getDefaultToolkit().getScreenSize().width;
    int screenHeight=Toolkit.getDefaultToolkit().getScreenSize().height;
    JLabel player1Name = new JLabel();//在获胜模式中用作获胜者的名字
    JLabel player2Name = new JLabel();
    JLabel p1Picture = new JLabel();//在获胜模式中用作获胜者的头像
    JLabel p2Picture = new JLabel();
    JButton restart = new JButton();//重开一局的按钮
    JButton toSetting = new JButton();//返回设置界面
    JLabel words = new JLabel();

    public static void main(String[] args) { new WinPanel("winner",GameStat.p1Icon); } //测试入口

    //平局构造方法
    public WinPanel(){
        int width = 600;
        int height = 400;
        this.setBounds((screenWidth-width)/2,(screenHeight-height)/2,width,height);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //设置顶头面板
        words.setText("平局");
        words.setBounds(290,100,100,50);
        player1Name.setText(GameStat.player1);
        player1Name.setBounds(75,175,100,50);
        player2Name.setText(GameStat.player2);
        player2Name.setBounds(475,175,100,50);
        Image smallPicture=GameStat.p1Icon.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        GameStat.p1Icon.setImage(smallPicture);
        p1Picture = new JLabel(GameStat.p1Icon);
        p1Picture.setBounds(50,80,100,100);
        smallPicture=GameStat.p2Icon.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
        GameStat.p2Icon.setImage(smallPicture);
        p2Picture = new JLabel(GameStat.p2Icon);
        p2Picture.setBounds(450,80,100,100);

        //再来一局按钮
        restart.setText("再来一局");
        restart.setBounds(50,250,200,100);
        restart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new ChessboardConstructer2();
                dispose();//点击后关闭本窗口
            }
        });

        //返回设置面板按钮
        toSetting.setText("返回设置面板");
        toSetting.setBounds(350,250,200,100);
        toSetting.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Settings();
                dispose();//点击后关闭本窗口
            }
        });

        this.setTitle("比试结果");
        this.add(restart);
        this.add(toSetting);
        this.add(words);
        this.add(p1Picture);
        this.add(p2Picture);
        this.add(player1Name);
        this.add(player2Name);
        this.setVisible(true);
    }


    //其中一方胜利构造方法
    public WinPanel(String winner,ImageIcon winIcon){
        int width = 600;
        int height = 400;
        this.setBounds((screenWidth-width)/2,(screenHeight-height)/2,width,height);
        this.setResizable(false);
        this.setLayout(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        //设置顶头面板
        words.setText(winner + "恭喜你胜利啦！！");
        words.setBounds(300,150,200,50);
        Image smallPicture=winIcon.getImage().getScaledInstance(150,150, Image.SCALE_DEFAULT);
        winIcon.setImage(smallPicture);
        p1Picture = new JLabel(winIcon);
        p1Picture.setBounds(50,80,150,150);


        //再来一局按钮
        restart.setText("再来一局");
        restart.setBounds(50,250,200,100);
        restart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new ChessboardConstructer2();
                dispose();//点击后关闭本窗口
            }
        });

        //返回设置面板按钮
        toSetting.setText("返回设置面板");
        toSetting.setBounds(350,250,200,100);
        toSetting.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                new Settings();
                dispose();//点击后关闭本窗口
            }
        });

        this.setTitle("比试结果");
        this.add(restart);
        this.add(toSetting);
        this.add(words);
        this.add(p1Picture);
        this.add(p2Picture);
        this.add(player1Name);
        this.add(player2Name);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
