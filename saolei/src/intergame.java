import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class intergame extends JFrame {
    JPanel j=new JPanel(new GridLayout(7,1));
    JPanel j1=new JPanel(new GridLayout(1,1));
    JPanel j2=new JPanel(new GridLayout(1,1));
    JPanel j3=new JPanel(new GridLayout(1,1));
    JPanel j4=new JPanel(new GridLayout(1,1));
    JPanel j5=new JPanel(new GridLayout(1,1));

    private JButton beginnew=new JButton("开始新的游戏");
    private JButton loadold=new JButton("读取存档");
    private  JButton setsound=new JButton("声音设置");
    BackgroundPanel bg=new BackgroundPanel(new ImageIcon("hzw1.gif").getImage());

    public  intergame(){
        super("请选择");
        setBounds(300,200,800,450);
     setResizable(false);
        j.setOpaque(false);
        j1.setOpaque(false);
        j2.setOpaque(false);
        j3.setOpaque(false);
        j4.setOpaque(false);
        j5.setOpaque(false);


        beginnew.setOpaque(false);
        loadold.setOpaque(false);

        j4.setVisible(false);
        j5.setVisible(false);

        j1.add(beginnew);
        j2.add(loadold);
        j3.add(setsound);

        j.add(j1);
        j.add(j4);
        j.add(j2);
        j.add(j5);
        j.add(j3);



        beginnew.addActionListener(e -> {new story(); this.dispose();});
        loadold.addActionListener(e -> {
        JFrame f=new JFrame();
        JPanel p=new JPanel();
        f.setBounds(250,250,50,150);

            loadgame game= null;
            try {
                game = new loadgame();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            } catch (ClassNotFoundException classNotFoundException) {
                new TimeDialog().showDialog(new JFrame(), "读档出问题了", 2);
            }
            ArrayList<ChessboardConstructer2> g= game.lgame;
                 for (int i=0;i<g.size();i++){
                     JButton j=new JButton("存档"+i);
                     int finalI = i;
                     j.addActionListener(e1 -> {
                         //构造方法
                         try {
                              loadgame lg=new loadgame();
                              ArrayList<ChessboardConstructer2> loadg= lg.lgame;
                             new ChessboardConstructer2(loadg.get(finalI));
                         } catch (IOException ioException) {
                             ioException.printStackTrace();
                         } catch (ClassNotFoundException classNotFoundException) {
                             new TimeDialog().showDialog(new JFrame(), "读档出问题了", 2);
                         }
                     });
                     p.add(j);
                 }
                 f.add(p);
                 setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            if (g.size()!=0)f.setVisible(true);
        });

 setsound.addActionListener(e -> {
       new  AudioPlayDemo();
 });
        bg.add(j);
        this.add(bg);
        setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new intergame();
    }
}
