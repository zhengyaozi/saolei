import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class selectplayer extends JFrame {
JPanel c=new JPanel(new GridLayout(1,3));
JPanel player1=new JPanel(new GridLayout(3,1));

JPanel selectpicture=new JPanel(new GridLayout(2,2));
ImageIcon p1=new ImageIcon("p1.png");
    ImageIcon p2=new ImageIcon("p2.png");
    ImageIcon p3=new ImageIcon("p3.png");
    ImageIcon p4=new ImageIcon("p4.png");
JButton finish1=new JButton("确认");

    Image Image = p1.getImage();
    Image smallImage = Image.getScaledInstance(200, 200, Image.SCALE_FAST);
    ImageIcon smallIcon = new ImageIcon(smallImage);
    JButton playerpicture1= new JButton(smallIcon);
    Image Image2 = p2.getImage();
    Image smallImage2 = Image2.getScaledInstance(200, 200, Image.SCALE_FAST);
    ImageIcon smallIcon2 = new ImageIcon(smallImage2);
    JButton playerpicture2= new JButton(smallIcon2);
    Image Image3 = p3.getImage();
    Image smallImage3 = Image3.getScaledInstance(200, 200, Image.SCALE_FAST);
    ImageIcon smallIcon3 = new ImageIcon(smallImage3);
    JButton playerpicture3= new JButton(smallIcon3);
    Image Image4 = p4.getImage();
    Image smallImage4 = Image4.getScaledInstance(200, 200, Image.SCALE_FAST);
    ImageIcon smallIcon4 = new ImageIcon(smallImage4);
    JButton playerpicture4= new JButton(smallIcon4);


   JLabel finalplayerone=new JLabel("请输入你的名字");

    JTextField name1=new JTextField("player1");



    public selectplayer() throws HeadlessException {
        super("请选择人物1");
        setBounds(250,250,700,500);

//        this.setResizable(false);

       selectpicture.add(playerpicture1);
       selectpicture.add(playerpicture2);
       selectpicture.add(playerpicture3);
       selectpicture.add(playerpicture4);
        player1.add(finalplayerone);
//        player2.add(finalplayertwo);
        player1.add(name1);
//        player2.add(name2);
        player1.add(finish1);
//        player2.add(finish2);

        c.setOpaque(false);
        player1.setOpaque(false);
//        player2.setOpaque(false);
        selectpicture.setOpaque(false);
        playerpicture1.setOpaque(false);
        playerpicture2.setOpaque(false);
        playerpicture3.setOpaque(false);
        playerpicture4.setOpaque(false);
        name1.setOpaque(false);

        finish1.setOpaque(false);

 AtomicInteger re= new AtomicInteger();
        finish1.addActionListener(e -> {
      GameStat.player1=name1.getText();
            new TimeDialog().showDialog(new JFrame(), "玩家一设置名称成功！，可点击图片选择人物", 3);
            player1.setVisible(false);
        });
       playerpicture1.addActionListener(e -> {
           if (GameStat.player1.equals("Luffy")){
               new TimeDialog().showDialog(new JFrame(), "请设置名称", 2);
           }else {
               GameStat.p1Icon = p1;
               new TimeDialog().showDialog(new JFrame(), "玩家一设置人物成功！", 2);
               new selectplayer2();
               this.dispose();
           } });
        playerpicture2.addActionListener(e -> {
            if (GameStat.player1.equals("Luffy")){
                new TimeDialog().showDialog(new JFrame(), "请设置名称", 2);
            }else {
                GameStat.p1Icon = p2;
                new TimeDialog().showDialog(new JFrame(), "玩家一设置人物成功！", 2);
                new selectplayer2();
                this.dispose();
            } });

        playerpicture3.addActionListener(e -> {
            if (GameStat.player1.equals("Luffy")){
                new TimeDialog().showDialog(new JFrame(), "请设置名称", 2);
            }else {
                GameStat.p1Icon = p3;
                new TimeDialog().showDialog(new JFrame(), "玩家一设置人物成功！", 2);
                new selectplayer2();
                this.dispose();
            }  });

        playerpicture4.addActionListener(e -> {
            if (GameStat.player1.equals("Luffy")){
                new TimeDialog().showDialog(new JFrame(), "请设置名称", 2);
            }else {
                GameStat.p1Icon = p4;
                new TimeDialog().showDialog(new JFrame(), "玩家一设置人物成功！", 2);
                new selectplayer2();
                this.dispose();
            }  });

        c.add(player1); c.add(selectpicture);
        this.add(c);

this.setVisible(true);
    }
    public static void main(String[] args) {
        new selectplayer();
    }
}
