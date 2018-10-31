package com.jr.rest.jersey.tutorialsPoint._1_HWWithResource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ilya Galatyuk
 */
public class UserDao {
    public List<User> getAllUsers(){

        List<User> userList = null;
        try {
            File file = new File("Users.dat");
            if (!file.exists()) {
                User user = new User(1, "Mahesh", "Teacher");
                userList = new ArrayList<User>();
                userList.add(user);
                saveUserList(userList);
            }
            else{
                FileInputStream fis = new FileInputStream(file);
                ObjectInputStream ois = new ObjectInputStream(fis);
                userList = (List<User>) ois.readObject();
                ois.close();
            }
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return userList;
    }
    private void saveUserList(List<User> userList){
        try {
            File file = new File("Users1.dat");
            FileOutputStream fos;
            fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(userList);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
