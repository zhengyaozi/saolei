import javax.swing.*;
import java.util.ArrayList;

class GameStat {
    public static ArrayList<String> user =new ArrayList<String>();
    public static ArrayList<String> passwd =new ArrayList<String>();
    public static int playerCnt=1;
    public static int mapcolumn=10;
    public static int maprow=10;
    public static int maplei=50;
    public static String player1 = "Luffy";
    public static String player2 = "Marco";
    public static ImageIcon p1Icon = new ImageIcon("Luffy.png");
    public static ImageIcon p2Icon = new ImageIcon("Marco.png");
    public static int at = 4;//玩家每回合行动次数 actiontime 默认为四次
}
