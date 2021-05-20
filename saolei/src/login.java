
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class login {

    private JFrame jFrame = new JFrame("欢迎来到雷区");
    private Container c = jFrame.getContentPane();
    private JLabel a1 = new JLabel("name");
    private JTextField username = new JTextField();
    private JLabel a2 = new JLabel("password");
    private JTextField password = new JTextField();
    private JButton okbtn = new JButton("OK");
    private JButton cancelbtn = new JButton("cancel");
    private JButton regist =new JButton("注册账号");

    public login() {
        //设置窗体的位置及大小
        jFrame.setBounds(600, 200, 300, 220);
        jFrame.setResizable(false);
        //设置一层相当于桌布的东西
        c.setLayout(new BorderLayout());//布局管理器
        //设置按下右上角X号后关闭
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //初始化--往窗体里放其他控件
        init();
        //设置窗体可见
        jFrame.setVisible(true);
        listerner();
    }
    public void init() {
        /*标题部分--North*/
        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        titlePanel.add(new JLabel("登录界面"));
        c.add(titlePanel, "North");

        /*输入部分--Center*/
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(null);
        a1.setBounds(50, 20, 50, 20);
        a2.setBounds(50, 60, 50, 20);
        fieldPanel.add(a1);
        fieldPanel.add(a2);
        username.setBounds(110, 20, 120, 20);
        password.setBounds(110, 60, 120, 20);
        fieldPanel.add(username);
        fieldPanel.add(password);
        c.add(fieldPanel, "Center");
//ygu
        /*按钮部分--South*/
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(okbtn);
        buttonPanel.add(cancelbtn);
        buttonPanel.add(regist);
        c.add(buttonPanel, "South");

    }
    public void listerner() {
        //确认按下去获取
        okbtn.addActionListener(e -> {
            //判断账号是否存在
            int n =0;
            for (int i = 0; i < GameStat.passwd.size(); i++) {
                if (!username.getText().equals(GameStat.user.get(i)) || !password.getText().equals(GameStat.passwd.get(i))) {n++;}}
            if (n>=GameStat.passwd.size()){
                JFrame wrong = new JFrame("错误");
                wrong.setBounds(200, 20, 300, 100);
                wrong.setVisible(true);
                wrong.setContentPane(new JLabel("账号不存在或存在非法字符，请输入正确的字符或注册账号"));
            }else{
                new intergame();
            }
        });
        //取消按下去清空
        cancelbtn.addActionListener(e -> {
            username.setText("");
            password.setText("");
        });
        //注册用户
        regist.addActionListener(e -> new register());
    }


    //测试
    public static void main(String[] args) {
        new login();

    }
}