import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class losepanel extends JFrame {

private   JPanel losepanel=new JPanel(new GridLayout(2,1));

    JPanel j1=new JPanel();
    JLabel saylose=new JLabel("你失败了");
    JPanel j2=new JPanel(new GridLayout(1,2));
    JButton returnboard=new JButton("返回棋盘点击上方banner重新开始");
    JButton returnsettings=new JButton("返回模式选择");

//
        public losepanel(){
            super("你没了");
            setBounds(300,200,500,200);
            j1.add(saylose);
            j2.add(returnboard);j2.add(returnsettings);
            losepanel.add(j1);
            losepanel.add(j2);
     returnboard.addActionListener(e -> this.dispatchEvent(new WindowEvent(this,WindowEvent.WINDOW_CLOSING) ));
     returnsettings.addActionListener(e -> {
          ChessboardConstructer1.getJFrame().dispose();
              new Settings();
              this.dispose();
          });

            this.add(losepanel);
            this.setVisible(true);
//            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        }

    public static void main(String[] args) {
        new losepanel();
    }
}
