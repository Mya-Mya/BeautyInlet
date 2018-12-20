package webcam;

import basicBI.BITask;
import client.Boss;
import client.ChildTaskCleaner;
import com.github.sarxos.webcam.Webcam;
import datamanager.DataBox;
import datamanager.DataBoxKey;
import timetable.Ticket;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class WebcamTaker extends BITask {
    public WebcamTaker(ChildTaskCleaner cleaner, Ticket ticket) {
        super(cleaner, ticket);
        WebcamSaver saver=new NameAfterDate();
        WebcamConfigData configData= (WebcamConfigData) Boss.dataBox.get(DataBoxKey.WEBCAM);



        Webcam usingCam=null;
        List<Webcam> webcams= Webcam.getWebcams();
        for(Webcam w:webcams){
            if (w.toString().equals(configData.usingCamName)) {
                usingCam=w;
            }
        }
        if (usingCam==null) {
            System.out.println("設定されたカメラが見つからなかった");
            return;
        }
        usingCam.setViewSize(new Dimension(configData.width,configData.height));
        usingCam.open();
        BufferedImage image=usingCam.getImage();
        usingCam.close();
        try {
            ImageIO.write(image,
                    "jpg",
                    new File(configData.savingFolder + "\\"+saver.getFileName()+".jpg")
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
        ending();
    }
}
