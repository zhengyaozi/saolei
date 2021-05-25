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
    JPanel j6=new JPanel(new GridLayout(1,1));
    JPanel j7=new JPanel(new GridLayout(1,1));
    private JButton beginnew=new JButton("开始新的游戏");
    private JButton loadold=new JButton("读取存档");
    private  JButton setsound=new JButton("声音设置");
    private JButton setenvir=new JButton("风格设置");
    BackgroundPanel bg=new BackgroundPanel(new ImageIcon("loadpicture.jpeg").getImage());

    public  intergame(){
        super("请选择");
        setBounds(300,200,400,500);

        j.setOpaque(false);
        j1.setOpaque(false);
        j2.setOpaque(false);
        j3.setOpaque(false);
        j4.setOpaque(false);
        j5.setOpaque(false);
        j6.setOpaque(false);
        j7.setOpaque(false);
        beginnew.setOpaque(false);
        loadold.setOpaque(false);
        setenvir.setOpaque(false);
        j4.setVisible(false);
        j5.setVisible(false);

        j1.add(beginnew);
        j2.add(loadold);
        j3.add(setsound);
        j6.add(setenvir);
        j.add(j1);
        j.add(j4);
        j.add(j2);
        j.add(j5);
        j.add(j3);
        j.add(j7);
        j.add(j6);

        beginnew.addActionListener(e -> {new Settings(); this.dispose();});
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
                classNotFoundException.printStackTrace();
            }
            ArrayList<ChessboardConstructer2> g= game.lgame;
                 for (int i=0;i<g.size();i++){
                     JButton j=new JButton("存档"+i);
                     j.addActionListener(e1 -> {
                         //构造方法
                         try {
                             new loadgame();
                         } catch (IOException ioException) {
                             ioException.printStackTrace();
                         } catch (ClassNotFoundException classNotFoundException) {
                             classNotFoundException.printStackTrace();
                         }
                       
                     });
                     p.add(j);
                 }
                 f.add(p);
            if (g.size()!=0)f.setVisible(true);
        });
//    setenvir.addActionListener(e -> {
//        JFrame j=new JFrame();
//        JPanel p=new JPanel();
//        JButton b=new JButton("改变风格");
//        p.add(b); j.add(p);p.setBounds(50,50,50,50);j.setVisible(true);
//        b.addActionListener(e12 -> {
//            new intergame();
//            intergame.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//        });
//    });

 setsound.addActionListener(new ActionListener() {
     @Override
     public void actionPerformed(ActionEvent e) {

     }
 });
        bg.add(j);
        this.add(bg);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new intergame();
    }
}
