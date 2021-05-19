import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Character.isDigit;


public class register extends JFrame {
    private JPanel r=new JPanel(new GridLayout(3,1));
    private JPanel r2=new JPanel(new GridLayout(1,2));
    private JPanel r3=new JPanel(new GridLayout(1,2));

    private JLabel putname=new JLabel("请输入你的账户");
    private JTextField writename=new JTextField();


    private JLabel putnum=new JLabel("请输入六位数字密码");
    private JTextField writenum=new JTextField();


    private JButton OK=new JButton("确认");



    public register(){
        super("注册用户");
        setBounds(300,200,400,200);
        //添加按钮
        r2.add(putname);
        r2.add(writename);

        r3.add(putnum);
        r3.add(writenum);

        r.add(r2); r.add(r3); r.add(OK);
//“确认”按钮的监听器
        OK.addActionListener(e -> {
            //判断六位数组合理
            if (writename.getText() != null && writenum.getText() != null && isnumer(writenum.getText()) && writenum.getText().length() == 6) {
                boolean result =true;
                //判断是否注册过账号
                for (int i = 0; i < GameStat.passwd.size(); i++) {
                    if (writename.getText().equals(GameStat.user.get(i)) && writenum.getText().equals(GameStat.passwd.get(i))) {
                        JFrame wrong = new JFrame("错误");
                        wrong.setBounds(200, 20, 300, 100);
                        wrong.setVisible(true);
                        wrong.setContentPane(new JLabel("你已经注册过了账号，请返回登录界面"));
                        result=false;
                        break;
                    }
                }
                //确认无误后弹出注册成功
                if (result) {
                    JFrame right = new JFrame("成功");
                    right.setBounds(200, 20, 300, 100);
                    right.setContentPane(new JLabel("注册成功，请返回登录界面进行登录"));
                    right.setVisible(true);
                    GameStat.user.add(writename.getText());
                    GameStat.passwd.add(writenum.getText());
                }
            } else {
                //六位数组不合理弹出窗口
                JFrame wrong = new JFrame("错误");
                wrong.setBounds(200, 20, 300, 100);
                wrong.setContentPane(new JLabel("你的输入有误，请输入正确的格式"));
                wrong.setVisible(true);
            }
        });
        this.add(r);
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
