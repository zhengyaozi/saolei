import javax.swing.*;
import java.awt.*;

public class ShrinkPicture {

    private static ImageIcon myImage=new ImageIcon();

    public static ImageIcon AfterPicture(ImageIcon picture,int width,int height){
        Image smallPicture=picture.getImage().getScaledInstance(width,height, Image.SCALE_DEFAULT);
        myImage.setImage(smallPicture);
        return myImage;
    }

    //p=ShrinkPicture.AfterPicture(...);
}
