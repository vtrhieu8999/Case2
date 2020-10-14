package BasicIO;

import java.io.*;
import java.util.List;

public class BasicIO<T extends LoginData> {
    public List<T> list;
    public Object getList(List<T> newList){
        Object obj;
        File file = new File("src/data/userIDs/list.dat");
        try {
            if (file.exists()) {
                FileInputStream fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                obj = objectInputStream.readObject();
                objectInputStream.close();
                fileInputStream.close();
                return obj;
            } else {
                list = newList;
                FileOutputStream fileOutputStream = new FileOutputStream(file, true);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(list);
                fileOutputStream.close();
                objectOutputStream.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return (Object) this.list;
    }
    public void synList(List<T> list){
        String path= "src/data/userIDs/list.dat";
        try {
            File file = new File(path);
            FileOutputStream fileOutputStream= new FileOutputStream(file, true);
            ObjectOutputStream objectOutputStream= new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(list);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String findUser(List<T> list, String name, String password){
        for(T instance: list) {
            if (instance.getUserName().equals(name) || instance.getPassword().equals(password))
                return instance.toString();
            }
        return null;
    }
    public void newUser(List<T> list, T instance ){
        list.add(instance);
        long id= list.size();

    }
}

