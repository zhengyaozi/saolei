import javax.swing.*;
import java.awt.*;

class Settings<background> extends JFrame {
    JPanel c = new JPanel(new GridLayout(5, 1));

    private JPanel c1 = new JPanel(new GridLayout(1, 2));
    private JPanel c2 = new JPanel(new GridLayout(1, 4));
    private JPanel c3 = new JPanel(new GridLayout(4, 2));
   private  JPanel c4= new  JPanel(new GridLayout());
    private JRadioButton dan = new JRadioButton("单人模式", true);
    private JRadioButton shuang = new JRadioButton("双人模式");
    private JRadioButton renji=new JRadioButton("人机对战");
    private JRadioButton san=new JRadioButton("三人模式");
    private ButtonGroup playerCnt = new ButtonGroup();

    private JRadioButton easybtn = new JRadioButton("简单", true);
    private JRadioButton middlebtn = new JRadioButton("中等");
    private JRadioButton diffbtn = new JRadioButton("困难");
    private JRadioButton byyourself = new JRadioButton("自定义");
    private ButtonGroup difficulty = new ButtonGroup();

    private JLabel column = new JLabel("棋盘高");
    private JTextField zdycolumn = new JTextField();
    private JLabel row = new JLabel("棋盘宽");
    private JTextField zdyrow = new JTextField();
    private JLabel lei = new JLabel("你想要多少颗雷");
    private JTextField zdylei = new JTextField();
    private JLabel at=new JLabel("回合次数");
    private JTextField atnumber=new JTextField();

    private JButton finishbtn = new JButton("开始游戏吧");

BackgroundPanel bg=new BackgroundPanel(new ImageIcon("hzw3.gif").getImage());
    public Settings() {

        super("游戏设置");
        setBounds(300,200,700,500);
        dan.setOpaque(false);
        shuang.setOpaque(false);
        san.setOpaque(false);
        easybtn.setOpaque(false);
        middlebtn.setOpaque(false);
        diffbtn.setOpaque(false);
        byyourself.setOpaque(false);
        finishbtn.setOpaque(false);
        renji.setOpaque(false);
        playerCnt.add(dan);
        playerCnt.add(shuang);
        playerCnt.add(san);
        playerCnt.add(renji);
        difficulty.add(easybtn);
        difficulty.add(middlebtn);
        difficulty.add(diffbtn);
        difficulty.add(byyourself);

        c1.add(dan);
        c1.add(shuang);
        c1.add(san);
        c1.add(renji);
        c1.setOpaque(false);

        c2.add(easybtn);
        c2.add(middlebtn);
        c2.add(diffbtn);
        c2.add(byyourself);
        c2.setOpaque(false);

        c3.add(column);
        c3.add(zdycolumn);
        c3.add(row);
        c3.add(zdyrow);
        c3.add(lei);
        c3.add(zdylei);
        c3.add(at);
        c3.add(atnumber);
        c3.setVisible(false);
        c3.setOpaque(false);
        c4.setVisible(false);
        c.add(c1);
        c.add(c2);
        c.add(c3);
        c.add(c4);
        c.add(finishbtn);
        c.setOpaque(false);

        bg.add(c);
        this.add(bg);
//增加自定义监听器，选择自定义的时候会弹出设置界面
        byyourself.addItemListener(e ->{ c3.setVisible(byyourself.isSelected()); if (dan.isSelected()){at.setVisible(false);atnumber.setVisible(false);}   });
        //点击开始游戏按钮
        finishbtn.addActionListener(e -> {
            //传输数据
            if (easybtn.isSelected()){ GameStat.mapcolumn=9;  GameStat.maprow=9; GameStat.maplei=10;}
            if (middlebtn.isSelected()){ GameStat.mapcolumn=16;  GameStat.maprow=16; GameStat.maplei=40;}
            if (diffbtn.isSelected()){ GameStat.mapcolumn=30;  GameStat.maprow=16; GameStat.maplei=99;}
            int row=0; int col = 0;  int at1=0;
            if (byyourself.isSelected()) {
                boolean result = true;
                try {
                     col = Integer.parseInt(zdycolumn.getText());
                     row = Integer.parseInt(zdyrow.getText());
                     at1=Integer.parseInt(atnumber.getText());
                } catch (Exception ex) {
                    new TimeDialog().showDialog(new JFrame(), "输入了奇奇怪怪的东西", 2);
                    result = false;
                }
                if (result) {
                    if (Integer.parseInt(zdycolumn.getText()) > 30 || Integer.parseInt(zdycolumn.getText()) < 1 || Integer.parseInt(zdyrow.getText()) > 24
                            || Integer.parseInt(zdyrow.getText()) < 1 || Integer.parseInt(zdylei.getText()) > 0.5*col*row
                            ||Integer.parseInt(zdylei.getText())<0
                            ||Integer.parseInt(atnumber.getText())>5||Integer.parseInt(atnumber.getText())<1) {
                        new TimeDialog().showDialog(new JFrame(), "不符合自定义棋盘的规则", 2);
                        result=false;
                    } else {
                        GameStat.mapcolumn = Integer.parseInt(zdycolumn.getText());
                        GameStat.maprow = Integer.parseInt(zdyrow.getText());
                        GameStat.maplei = Integer.parseInt(zdyrow.getText());
                        GameStat.at = Integer.parseInt(atnumber.getText());
                    }
                }
            }
         if (GameStat.mapcolumn!=5){
             if(dan.isSelected()){ GameStat.playerCnt = 1; new ChessboardConstructer1();
                Settings.this.dispose();}
            if (shuang.isSelected()){ GameStat.playerCnt = 2; new selectplayer();
                Settings.this.dispose();}
         if (renji.isSelected()) { GameStat.playerCnt = 2 ;new selectplayer(); Settings.this.dispose(); }
         if (san.isSelected()){ GameStat.playerCnt = 3;  new selectplayer();Settings.this.dispose();}
         }
        });
        setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
       new Settings() ;
    }

}
