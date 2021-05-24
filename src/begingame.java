import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class begingame {
    public static ChessboardConstructer1 game =new ChessboardConstructer1();

    public static void main(String[] args) throws IOException {
        new begingame();
        JFrame f=new JFrame(); f.setBounds(500,500,500,500);
        JButton BEGIN=new JButton("开始");
        f.add(BEGIN); f.setVisible(true);

        BEGIN.addActionListener(e -> {
            try {
                new  savegame();
            } catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }
        });
    }
}
