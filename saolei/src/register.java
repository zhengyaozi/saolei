import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Character.isDigit;


public class register extends JFrame {
    private JPanel r=new JPanel(new GridLayout(5,1));
    private JPanel r2=new JPanel(new GridLayout(1,2));
    private JPanel r3=new JPanel(new GridLayout(1,2));
    private JPanel r4=new JPanel(new GridLayout());
    private JPanel r5=new JPanel(new GridLayout());
    private JPanel r6=new JPanel(new GridLayout(1,1));
    private JLabel putname=new JLabel("请输入你的账户");
    private JTextField writename=new JTextField();


    private JLabel putnum=new JLabel("请输入六位数字密码");
    private JTextField writenum=new JTextField();

    private JButton OK=new JButton("确认");
    BackgroundPanel bg=new BackgroundPanel(new ImageIcon("C:\\Users\\zqlwcldzz\\IdeaProjects\\saolei\\saolei\\zhangwei.jpeg").getImage());


    public register(){
        super("注册用户");
        setBounds(300,200,500,325);
        //添加按钮
        r2.add(putname);
        r2.add(writename);
        r6.add(OK);



        r3.add(putnum);
        r3.add(writenum);

        r4.setVisible(false);
        r5.setVisible(false);

        r6.setOpaque(false);
        r.setOpaque(false);
        r2.setOpaque(false);
        r3.setOpaque(false);
        putnum.setOpaque(false);
        writename.setOpaque(false);
        writenum.setOpaque(false);
        writenum.setOpaque(false);
        OK.setOpaque(false);
        r.add(r2);r.add(r4);  r.add(r3);  r.add(r5) ;r.add(r6);
//“确认”按钮的监听器
        OK.addActionListener(e -> {
            //判断六位数组合理
            if (writename.getText() != null && writenum.getText() != null && isnumer(writenum.getText()) && writenum.getText().length() == 6) {
                boolean result =true;
                //判断是否注册过账号
                for (int i = 0; i < GameStat.passwd.size(); i++) {
                    if (writename.getText().equals(GameStat.user.get(i)) && writenum.getText().equals(GameStat.passwd.get(i))) {
                        new TimeDialog().showDialog(new JFrame(), "你已经注册了账号", 3);
                        result=false;
                        break;
                    }
                }
                //确认无误后弹出注册成功
                if (result) {
                    new TimeDialog().showDialog(new JFrame(), "注册成功！", 3);
                    GameStat.user.add(writename.getText());
                    GameStat.passwd.add(writenum.getText());
                }
            } else {
                //六位数组不合理弹出窗口
                new TimeDialog().showDialog(new JFrame(), "你的输入有误", 3);
            }
        });
        bg.add(r);
        this.add(bg);
        setVisible(true);
        setResizable(false);
    }
    //判断是否输入了数字之外的东西
    public boolean isnumer(String str) {
        for (int i=0; i<str.length();i++){
            if (!isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
