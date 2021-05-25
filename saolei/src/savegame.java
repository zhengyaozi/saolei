import java.io.*;
import java.util.ArrayList;

public class savegame {

    public savegame() throws IOException, ClassNotFoundException {
      try {
          ObjectInputStream ois=new ObjectInputStream(new FileInputStream("oos.txt"));

        ArrayList<ChessboardConstructer2> oldchessbroad=(ArrayList<ChessboardConstructer2>)ois.readObject();
        ois.close();
        ArrayList<ChessboardConstructer2> loadchessbroad=oldchessbroad;
        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("oos.txt"));

        loadchessbroad.add(selectplayer2.game2);
        oos.writeObject(loadchessbroad);

        oos.close();}catch (IOException FileNotFoundException){
          ArrayList<ChessboardConstructer2> loadchessbroad=new ArrayList<>();
          ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("oos.txt"));

          loadchessbroad.add
                  (selectplayer2.game2);
          oos.writeObject(loadchessbroad);

          oos.close();
      }
    }

}
