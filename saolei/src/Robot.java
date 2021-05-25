import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Robot {
    public Robot(ChessboardConstructer3 b){
        Random rand = new Random();
        int r = rand.nextInt(b.ROW);
        int c = rand.nextInt(b.COL);
        if(b.data[r][c] == b.LEICODE){
            b.unopened--;
            b.p2grade++;//机器人插旗成功，积分加1
            b.p2Grade.setText("玩家2得分为 " + b.p2grade);
            Image image = b.Flag.getImage();
            Image smallImage = image.getScaledInstance(30, 30, Image.SCALE_FAST);
            ImageIcon smallIcon = new ImageIcon(smallImage);
            b.btns[r][c].setIcon(smallIcon);//设置按钮icon为暴雷图标
        }else{
            if(b.data[r][c] == 0) {
                Image image = b.Clicked.getImage();
                Image smallImage = image.getScaledInstance(30, 30, Image.SCALE_FAST);
                ImageIcon smallIcon = new ImageIcon(smallImage);
                b.btns[r][c].setIcon(smallIcon);//设置按钮icon为 0 图标
            }else if(b.data[r][c] == 1){
                Image image = b.n1.getImage();
                Image smallImage = image.getScaledInstance(30, 30, Image.SCALE_FAST);
                ImageIcon smallIcon = new ImageIcon(smallImage);
                b.btns[r][c].setIcon(smallIcon);//设置按钮icon为 1 图标
            }else if(b.data[r][c] == 2){
                Image image = b.n2.getImage();
                Image smallImage = image.getScaledInstance(30, 30, Image.SCALE_FAST);
                ImageIcon smallIcon = new ImageIcon(smallImage);
                b.btns[r][c].setIcon(smallIcon);//设置按钮icon为 2 图标
            }else if(b.data[r][c] == 3){
                Image image = b.n3.getImage();
                Image smallImage = image.getScaledInstance(30, 30, Image.SCALE_FAST);
                ImageIcon smallIcon = new ImageIcon(smallImage);
                b.btns[r][c].setIcon(smallIcon);//设置按钮icon为 3 图标
            }else if(b.data[r][c] == 4){
                Image image = b.n4.getImage();
                Image smallImage = image.getScaledInstance(30, 30, Image.SCALE_FAST);
                ImageIcon smallIcon = new ImageIcon(smallImage);
                b.btns[r][c].setIcon(smallIcon);//设置按钮icon为 4 图标
            }else if(b.data[r][c] == 5){
                Image image = b.n5.getImage();
                Image smallImage = image.getScaledInstance(30, 30, Image.SCALE_FAST);
                ImageIcon smallIcon = new ImageIcon(smallImage);
                b.btns[r][c].setIcon(smallIcon);//设置按钮icon为 5 图标
            }else if(b.data[r][c] == 6){
                Image image = b.n6.getImage();
                Image smallImage = image.getScaledInstance(30, 30, Image.SCALE_FAST);
                ImageIcon smallIcon = new ImageIcon(smallImage);
                b.btns[r][c].setIcon(smallIcon);//设置按钮icon为 6 图标
            }else if(b.data[r][c] == 7){
                Image image = b.n7.getImage();
                Image smallImage = image.getScaledInstance(30, 30, Image.SCALE_FAST);
                ImageIcon smallIcon = new ImageIcon(smallImage);
                b.btns[r][c].setIcon(smallIcon);//设置按钮icon为 7 图标
            }else if(b.data[r][c] == 8){
                Image image = b.n8.getImage();
                Image smallImage = image.getScaledInstance(30, 30, Image.SCALE_FAST);
                ImageIcon smallIcon = new ImageIcon(smallImage);
                b.btns[r][c].setIcon(smallIcon);//设置按钮icon为 8 图标
            }
            b.p2mis++;//机器人插旗错误，失误数加1
            b.p2mistake.setText("玩家2失误为 " + b.p2mis);
        }
    }

}
