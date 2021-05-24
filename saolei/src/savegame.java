import java.io.*;
import java.util.ArrayList;

public class savegame {

    public savegame() throws IOException, ClassNotFoundException {
      try {
          ObjectInputStream ois=new ObjectInputStream(new FileInputStream("oos.txt"));

        ArrayList<ChessboardConstructer1> oldchessbroad=(ArrayList<ChessboardConstructer1>)ois.readObject();
        ois.close();
        ArrayList<ChessboardConstructer1> loadchessbroad=oldchessbroad;
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("oos.txt"));

        loadchessbroad.add(begingame.game);
        oos.writeObject(loadchessbroad);

        oos.close();}catch (IOException FileNotFoundException){
          ArrayList<ChessboardConstructer1> loadchessbroad=new ArrayList<>();
          ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("oos.txt"));

          loadchessbroad.add(begingame.game);
          oos.writeObject(loadchessbroad);

          oos.close();
      }
    }

}
