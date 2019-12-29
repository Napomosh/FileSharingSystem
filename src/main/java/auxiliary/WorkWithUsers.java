package auxiliary;

/*
Работа с пользователями
 */

import java.io.*;
import java.util.ArrayList;

public class WorkWithUsers {
    public static boolean isUserRegistered(String name) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader
                ("C:\\java\\fileSharingSystem\\dataBase\\users.txt"));

        String login;
        while ((login = reader.readLine()) != null) {
            login = login.split("&")[0];
            if (login.equals(name)) {
                reader.close();
                return true;
            }
        }
        reader.close();
        return false;
    }

    public static boolean isAuthDataValid(String name, String password) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader
                ("C:\\java\\fileSharingSystem\\dataBase\\users.txt"));

        String accountData;
        while ((accountData = reader.readLine()) != null) {
            if (accountData.split("&")[0].equals(name) && accountData.split("&")[1].equals(password)) {
                reader.close();
                return true;
            }
        }
        reader.close();
        return false;
    }

    public static void addNewUser(String name, String password) throws IOException {
        FileOutputStream dataBaseWriter = new FileOutputStream
                ("C:\\java\\fileSharingSystem\\dataBase\\users.txt", true);

        byte[] data = (name + "&" + password + "\n").getBytes();
        synchronized(dataBaseWriter) {
            dataBaseWriter.write(data);
            dataBaseWriter.close();
        }

        String pathToNewDir = "C:\\java\\fileSharingSystem\\dirs" + "\\" + name;
        new File(pathToNewDir).mkdir();

        dataBaseWriter = new FileOutputStream
            ("C:\\java\\fileSharingSystem\\dataBase\\directoriesOfUsers.txt", true);

        synchronized (dataBaseWriter) {
            data = (name + "&" + name + "\n").getBytes();
            dataBaseWriter.write(data);
            dataBaseWriter.close();
        }
    }

    public static ArrayList<String> getAllUsers() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader
                ("C:\\java\\fileSharingSystem\\dataBase\\directoriesOfUsers.txt"));

        ArrayList<String> res = new ArrayList<>();
        String tmp;
        while ((tmp = reader.readLine()) != null) {
            tmp = tmp.split("&")[0];
            res.add(tmp);
        }
        reader.close();
        return res;
    }
}
