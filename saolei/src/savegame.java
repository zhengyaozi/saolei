import java.io.*;
import java.util.ArrayList;

public class savegame {
    public savegame() throws IOException, ClassNotFoundException {
        ObjectInputStream ois=new ObjectInputStream(new FileInputStream("C:\\Users\\zqlwcldzz\\IdeaProjects\\saolei\\oos.txt"));
        ArrayList<ChessboardConstructer1> oldchessbroad=(ArrayList<ChessboardConstructer1>)ois.readObject();
        ois.close();
        ArrayList<ChessboardConstructer1> loadchessbroad=oldchessbroad;
//      ArrayList<ChessboardConstructer1> loadchessbroad=returngame();
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("C:\\Users\\zqlwcldzz\\IdeaProjects\\saolei\\oos.txt"));

        loadchessbroad.add(begingame.game);
        oos.writeObject(loadchessbroad);

        oos.close();
    }

}
