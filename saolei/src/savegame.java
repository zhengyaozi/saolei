import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class savegame {
    public savegame() throws IOException {

        ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream("C:\\Users\\zqlwcldzz\\IdeaProjects\\saolei\\oos.txt"));
        oos.writeObject(begingame.game);
        oos.close();
    }

}
