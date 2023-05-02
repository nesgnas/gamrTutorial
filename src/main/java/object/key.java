package object;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class key extends superObject{
    public key() {
        this.setName("Box");
        try{
            this.setImage(ImageIO.read(new File("data/tiles/tile3rd/124.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
