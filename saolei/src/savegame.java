import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class savegame {
  ArrayList<ChessboardConstructer1> loadchessbroad=new ArrayList<>();
    public savegame() throws IOException {

        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("C:\\Users\\zqlwcldzz\\IdeaProjects\\saolei\\oos.txt"));
        loadchessbroad.add(begingame.game);
        oos.writeObject(loadchessbroad);
        oos.close();
    }

}
