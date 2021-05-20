import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class intergame extends JFrame {
    JPanel j=new JPanel(new GridLayout(5,1));
    JPanel j1=new JPanel(new GridLayout(1,1));
    JPanel j2=new JPanel(new GridLayout(1,1));
    JPanel j3=new JPanel(new GridLayout(1,1));
    JPanel j4=new JPanel(new GridLayout(1,1));
    JPanel j5=new JPanel(new GridLayout(1,1));
    private JButton beginnew=new JButton("开始新的游戏");
    private JButton loadold=new JButton("读取存档");
    private  JButton setsound=new JButton("声音设置");
    BackgroundPanel bg=new BackgroundPanel(new ImageIcon("C:\\Users\\zqlwcldzz\\IdeaProjects\\saolei\\saolei\\loadpicture.jpeg").getImage());

    public  intergame(){
        super("请选择");
        setBounds(300,200,400,500);

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

        beginnew.addActionListener(e -> {new Settings(); this.dispose();});




        bg.add(j);
        this.add(bg);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new intergame();
    }
}
