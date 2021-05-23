//import javax.swing.*;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.ObjectInput;
//import java.io.ObjectInputStream;
//import java.util.ArrayList;
//
//public class loadgame {
//
//    public loadgame() throws IOException, ClassNotFoundException {
//        ObjectInputStream ois1=new ObjectInputStream(new FileInputStream("oos.txt"));
//
//       ArrayList<ChessboardConstructer1> lgame=(ArrayList<ChessboardConstructer1>)ois1.readObject();
//        ois1.close();
//
//    }
//
//    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        new loadgame();
//    }
//}
