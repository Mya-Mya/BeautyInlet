package webcam;

import com.github.sarxos.webcam.Webcam;

import java.io.Serializable;

public class WebcamConfigData implements Serializable {
    public String savingFolder;
    public String usingCamName;
    public int width;
    public int height;
}
