import java.io.*;
import java.util.ArrayList;

public class savegame {
    public savegame() throws IOException, ClassNotFoundException {


//      ArrayList<ChessboardConstructer1> loadchessbroad=returngame();
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("C:\\Users\\zqlwcldzz\\IdeaProjects\\saolei\\oos.txt"));
        ArrayList<ChessboardConstructer1> loadchessbroad=new ArrayList<>();
        loadchessbroad.add(begingame.game);
        oos.writeObject(loadchessbroad);

        oos.close();
    }
// public ArrayList<ChessboardConstructer1> returngame() throws IOException, ClassNotFoundException {
//     ObjectInputStream ois=new ObjectInputStream(new FileInputStream("C:\\Users\\zqlwcldzz\\IdeaProjects\\saolei\\oos.txt"));
//     Object obj=ois.readObject();
//     ArrayList<ChessboardConstructer1> loadchessbroad=(ArrayList<ChessboardConstructer1>)obj;
//     ois.close();
//     return  loadchessbroad;
// }
}
