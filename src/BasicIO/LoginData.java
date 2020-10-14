package BasicIO;

import model.Customer.BasicCustomer;

import java.io.*;
import java.util.List;

public class LoginData implements Serializable, readOverrideFile {
    protected String userName;
    protected String password;
    protected long id;
    //blank constructor
    public LoginData() {
    }

    //complete constructor
    public LoginData(String userName, String password, long id) {
        this.userName = userName;
        this.password = password;
        this.id = id;
    }

    //casting down
    protected LoginData(LoginData instance) {
        this.userName = instance.getUserName();
        this.password = instance.getPassword();
        this.id = instance.getId();
    }


    //getter setter
    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return this.id;
    }

    @Override
    public void writeFile(Object instance, String path) {
        File file = new File(path);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(instance);
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getFile(String path) {
        File file = new File(path);
        Object obj;
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            obj = objectInputStream.readObject();
            objectInputStream.close();
            return obj;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString(){
        return "BasicCustomer/"+ this.id;
    }
}
