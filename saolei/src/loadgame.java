import javax.swing.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;

public class loadgame {

    public loadgame() throws IOException, ClassNotFoundException {
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream("C:\\Users\\zqlwcldzz\\IdeaProjects\\saolei\\oos.txt"));
        Object obj=ois.readObject();
        ChessboardConstructer1 lgame=(ChessboardConstructer1)obj;

        ois.close();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        new loadgame();
    }
}
