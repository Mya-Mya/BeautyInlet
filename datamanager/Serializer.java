package datamanager;

import java.io.*;

public class Serializer implements Serializable{
    private String fileName;
    public Serializer(String fileName) {
        this.fileName=fileName;
        File f=new File(fileName);
        if(!f.canRead()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void save(Object object){
        try{
            FileOutputStream fos=new FileOutputStream(fileName);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.flush();
            oos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object load(){
        Object got=null;
        try{
            FileInputStream fis=new FileInputStream(fileName);
            ObjectInputStream ois=new ObjectInputStream(fis);
            got=ois.readObject();
            ois.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return got;
    }
}
