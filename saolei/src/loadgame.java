import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class loadgame {
    ArrayList<ChessboardConstructer2> lgame=new ArrayList<>();
    public loadgame() throws IOException, ClassNotFoundException {
        try {
            ObjectInputStream ois1=new ObjectInputStream(new FileInputStream("oos.txt"));

            lgame=(ArrayList<ChessboardConstructer2>)ois1.readObject();
            ois1.close();
        } catch (IOException FileNotFoundException){
            new TimeDialog().showDialog(new JFrame(), "没有找到存档", 3);
        }


    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new loadgame();
    }
}
