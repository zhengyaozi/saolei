import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class story extends JFrame {

    JButton b=new JButton("继续游戏");
    BackgroundPanel bg=new BackgroundPanel(new ImageIcon("SP.png").getImage());

    public  story(){
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setBounds(250,250,1024,640);
        b.setBounds(800,450,140,60);

        this.add(b);
        this.add(bg);

        b.addActionListener(e -> {
            new Settings();
            this.dispose();
        });
        setLocationRelativeTo(null);
        setVisible(true);
    }


}
