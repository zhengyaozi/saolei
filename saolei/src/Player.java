import javax.swing.*;
import java.awt.*;

public class Player {

    public static String name;  //玩家姓名
    public static ImageIcon picture=new ImageIcon();



    public Player(String name,ImageIcon picture){
        Player.name =name;
        Player.picture=picture;
    }


    public JPanel PlayPane(int totalWidth,int width,int height,int WhichPlayer){
        JPanel player=new JPanel(null);
        player.setSize(width,height);



        if(WhichPlayer==1){
            Image smallPicture=GameStat.p1Icon.getImage().getScaledInstance(100,100, Image.SCALE_DEFAULT);
            GameStat.p1Icon.setImage(smallPicture);
            picture=GameStat.p1Icon;
            JLabel jl=new JLabel(picture);
            jl.setBounds(0,0,100,100);
            jl.setVisible(true);
            player.add(jl);

        }

        else {
            picture=GameStat.p2Icon;
            JLabel jl=new JLabel(picture);
            jl.setBounds(0,0,100,100);
            jl.setVisible(true);
            player.add(jl);
        }

        player.setVisible(true);
        return player;
    }
}
