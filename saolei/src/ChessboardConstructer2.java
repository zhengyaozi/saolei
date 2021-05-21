//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.awt.event.MouseAdapter;
//import java.awt.event.MouseEvent;
//
//public class ChessboardConstructer2 extends JFrame {
//    int screenWidth= Toolkit.getDefaultToolkit().getScreenSize().width;
//    int screenHeight=Toolkit.getDefaultToolkit().getScreenSize().height;
//    JButton[][] button=new JButton[GameStat.mapcolumn][GameStat.maprow];
//    int[][] numbers=new int[GameStat.mapcolumn][GameStat.maprow];
//    public static int n=4;
//    public static int plusN=0;
//
//
//    public ChessboardConstructer2(int width,int height){
//        this.setBounds((screenWidth-width)/2,(screenHeight-height)/2,width,height);
//        this.setResizable(false);
//        this.setLayout(null);
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//        JPanel sweep=new JPanel(new GridLayout(GameStat.maprow,GameStat.mapcolumn,0,0));
//        sweep.setBounds();  //面板的大小位置
//
//        //对number进行操作；
//        addMine();
//        setNumbers();
//
//        Player p1=new Player();
//        Player p2=new Player();
//
//        JPanel p1Pane=p1.PlayPane();
//        JPanel p2Pane=p2.PlayPane();
//
//        p1Pane.setLocation();
//        p2Pane.setLocation();
//
//
//
//        for(int j=0;j<GameStat.maprow;j++){
//            for(int i=0;i<GameStat.mapcolumn;i++){
//                JButton jb=new JButton();
//                jb.addActionListener(new ActionListener() {
//                    @Override
//                    public void actionPerformed(ActionEvent e) {
//
//                    }
//                });
//
//                jb.addMouseListener(new MouseAdapter() {
//                    @Override
//                    public void mouseClicked(MouseEvent e) {
//                        if (e.getButton() == MouseEvent.BUTTON3) {
//                        }
//                    }
//
//                });
//
//                sweep.add(jb);
//            }
//        }
//
//
//
//
//
//
//        this.add(p1Pane);
//        this.add(p2Pane);
//        this.add(sweep);
//        this.setVisible(true);
//    }
//
//    private void addMine() {
//    }
//
//    private void setNumbers() {
//    }
//}
