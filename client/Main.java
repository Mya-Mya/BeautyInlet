package client;

import client.Boss;
import com.github.sarxos.webcam.Webcam;

public class Main {
    public static void main(String[] args) {
        System.out.println("こんにちは世界");
        Webcam a=Webcam.getDefault();
        Boss boss=new Boss("0023");
    }
}
