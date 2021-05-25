import javax.swing.*;
import java.util.ArrayList;

class GameStat {
    public static ArrayList<String> user =new ArrayList<String>();
    public static ArrayList<String> passwd =new ArrayList<String>();
    public static int playerCnt=1;
<<<<<<< HEAD
    public static int mapcolumn=5;
    public static int maprow=5;
    public static int maplei=5;
=======
    public static int mapcolumn=30;
    public static int maprow=24;
    public static int maplei=20;
>>>>>>> d6ccc2577f366e87dce7bab57e7805e12bb7a602
    public static String player1 = "Luffy";
    public static String player2 = "Marco";
    public static String player3 = "Sauron";
    public static ImageIcon p1Icon = new ImageIcon("p1.png");
    public static ImageIcon p2Icon = new ImageIcon("Marco.png");
    public static ImageIcon p3Icon = new ImageIcon("p2.png");
    public static int at = 4;//玩家每回合行动次数 actiontime 默认为四次
}
