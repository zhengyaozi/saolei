import javax.swing.*;
import java.awt.*;

class Settings<background> extends JFrame {
    JPanel c = new JPanel(new GridLayout(4, 1));


    private JPanel c1 = new JPanel(new GridLayout(1, 2));
    private JPanel c2 = new JPanel(new GridLayout(1, 4));
    private JPanel c3 = new JPanel(new GridLayout(3, 2));

    private JRadioButton dan = new JRadioButton("单人模式", true);
    private JRadioButton shuang = new JRadioButton("双人模式");
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
    private JButton finishbtn = new JButton("开始游戏吧");
//    ImageIcon  background = new  ImageIcon("C:\\Users\\zqlwcldzz\\Desktop\\projet picture\\chessbroad.jpeg");
BackgroundPanel bg=new BackgroundPanel(new ImageIcon("C:\\Users\\zqlwcldzz\\Desktop\\projet picture\\chessbroad.jpeg").getImage());
    public Settings() {

        super("");
        setBounds(300,200,400,300);
//        JLabel jl = new JLabel(background);
//        jl.setBounds(0,0,400,300);

//        this.getLayeredPane().add(jl);
        //添加按钮

        easybtn.setOpaque(false);


        playerCnt.add(dan);
        playerCnt.add(shuang);

        difficulty.add(easybtn);
        difficulty.add(middlebtn);
        difficulty.add(diffbtn);
        difficulty.add(byyourself);

        c1.add(dan);
        c1.add(shuang);
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
        c3.setVisible(false);
        c3.setOpaque(false);

        c.add(c1);
        c.add(c2);
        c.add(c3);
        c.add(finishbtn);
        c.setOpaque(false);

        bg.add(c);
        this.add(bg);
//增加自定义监听器，选择自定义的时候会弹出设置界面
        byyourself.addItemListener(e -> c3.setVisible(byyourself.isSelected()));
        //点击开始游戏按钮
        finishbtn.addActionListener(e -> {
            //传输数据
            if(dan.isSelected()) GameStat.playerCnt = 1;
            if (shuang.isSelected()) GameStat.playerCnt = 2;
            if (easybtn.isSelected()) GameStat.mapcolumn=9;  GameStat.maprow=9; GameStat.maplei=10;
            if (middlebtn.isSelected()) GameStat.mapcolumn=16;  GameStat.maprow=16; GameStat.maplei=40;
            if (diffbtn.isSelected()) GameStat.mapcolumn=30;  GameStat.maprow=16; GameStat.maplei=99;
            if (byyourself.isSelected()) {
                boolean result = true;
                try {
                    int col = Integer.parseInt(zdycolumn.getText());
                    int row = Integer.parseInt(zdyrow.getText());
                    int zhadan = Integer.parseInt(zdyrow.getText());
                } catch (Exception ex) {
                    new TimeDialog().showDialog(new JFrame(), "输入了奇奇怪怪的东西，请重新输入", 3);
                    result = false;
                }
                if (result) {
                    if (Integer.parseInt(zdycolumn.getText()) > 30 || Integer.parseInt(zdycolumn.getText()) < 1 || Integer.parseInt(zdyrow.getText()) > 24 || Integer.parseInt(zdyrow.getText()) < 1 || Integer.parseInt(zdyrow.getText()) < 1 || Integer.parseInt(zdyrow.getText()) > 360) {
                        new TimeDialog().showDialog(new JFrame(), "不符合自定义棋盘和炸弹的限制，请重新输入", 6);
                    } else {
                        GameStat.mapcolumn = Integer.parseInt(zdycolumn.getText());
                        GameStat.maprow = Integer.parseInt(zdyrow.getText());
                        GameStat.maplei = Integer.parseInt(zdyrow.getText());
                    }
                }
            }
            new ChessboardConstructer1();
            Settings.this.dispose();
        });

        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Settings();
    }

}
