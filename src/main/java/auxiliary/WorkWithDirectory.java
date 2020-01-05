package auxiliary;

/*
Работа с директориями пользователей
 */

import java.io.*;
import java.util.ArrayList;

public class WorkWithDirectory {
    static synchronized void writeInDB(Object message, boolean rewriteFile) throws IOException {
        BufferedWriter dataBaseWriter;
        if(rewriteFile) {
            dataBaseWriter = new BufferedWriter(new FileWriter
                    ("C:\\java\\fileSharingSystem\\dataBase\\directoriesOfUsers.txt"));
        }
        else{
            dataBaseWriter = new BufferedWriter(new FileWriter
                    ("C:\\java\\fileSharingSystem\\dataBase\\directoriesOfUsers.txt", true));
        }
        dataBaseWriter.write(message.toString());
        dataBaseWriter.close();
    }

    private static ArrayList<String> getAllAvailableDirectoriesForUser(String user){
        ArrayList<String> res = new ArrayList<>();
        BufferedReader dataBaseReader;
        try {
            dataBaseReader = new BufferedReader(new FileReader
                    ("C:\\java\\fileSharingSystem\\dataBase\\directoriesOfUsers.txt"));

            String tmp;
            while ((tmp = dataBaseReader.readLine()) != null) {
                String[] lineFromDB;
                lineFromDB = tmp.split("&");
                if (lineFromDB[0].equals(user)) {
                    for(int i = 1; i < lineFromDB.length; i++){
                        res.add(lineFromDB[i]);
                    }
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return res;
    }

    public static String[] getListOfFiles(String user) {
        File userDir = new File("C:\\java\\fileSharingSystem\\dirs\\" + user);
        String[]listOfFiles = userDir.list();
        String[] result;

        try {
            result = new String[listOfFiles.length + 1];
            result[0] = user;
            System.arraycopy(listOfFiles, 0, result, 1, listOfFiles.length);
        } catch (NullPointerException e){
            System.out.println(e.getMessage());
            result = new String[1];
            result[0] = "Somthing was wrong!";
        }

        return result;
    }

    public static boolean isDirectoryEmpty(String[] listOfFiles){
        return listOfFiles == null;
    }

    public static ArrayList<String> getAllAvailableDirectoriesForUserWithoutMod(String user){
        ArrayList<String> res = new ArrayList<>();
        BufferedReader dataBaseReader = null;
        try {
            dataBaseReader = new BufferedReader(new FileReader
                    ("C:\\java\\fileSharingSystem\\dataBase\\directoriesOfUsers.txt"));

            String tmp;
            while ((tmp = dataBaseReader.readLine()) != null) {
                String[] lineFromDB;
                lineFromDB = tmp.split("&");
                if (lineFromDB[0].equals(user)) {
                    for (int i = 1; i < lineFromDB.length; i++) {
                        res.add(lineFromDB[i].split("#")[0]);
                    }
                    break;
                }
            }
        }   catch (IOException e) {
                System.out.println(e.getMessage());
            }

        return res;
    }

    public static void addDirectoryToUser(String directory, String user, String mod){
        BufferedReader dataBaseReader = null;
        try {
            dataBaseReader = new BufferedReader(new FileReader
                    ("C:\\java\\fileSharingSystem\\dataBase\\directoriesOfUsers.txt"));

            String tmp;
            StringBuilder bufferOfFile = new StringBuilder();

            while ((tmp = dataBaseReader.readLine()) != null) {
                if (tmp.split("&")[0].equals(user)) {
                    StringBuilder newLine = new StringBuilder();
                    newLine.append("&").append(directory).append("#").append(mod);
                    tmp += newLine.toString();
                }
                bufferOfFile.append(tmp).append("\n");
            }
            dataBaseReader.close();

            writeInDB(bufferOfFile, true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean hasUserThisDirectory(String user, String directory){
        ArrayList<String> dirs = getAllAvailableDirectoriesForUserWithoutMod(user);
        for(String tmp : dirs){
            if(tmp.equals(directory)){
                return true;
            }
        }
        return false;
    }

    public static ArrayList<String> doSearch(String user, String searchRequest){
        ArrayList<String> allUserDirectories = getAllAvailableDirectoriesForUserWithoutMod(user);
        ArrayList<String> resultOfSearching = new ArrayList<>();

        String[] listOfFilesInOneDirectory;

        for (String allUserDirectory : allUserDirectories) {
            listOfFilesInOneDirectory = getListOfFiles(allUserDirectory);

            if (listOfFilesInOneDirectory.length != 0) {
                for (String s : listOfFilesInOneDirectory) {
                    if (s.length() >= searchRequest.length() &&
                            searchRequest.equals(s.substring(0, searchRequest.length()))) {
                        resultOfSearching.add(s);
                    }
                }
            }
        }

        if(resultOfSearching.isEmpty()){
            resultOfSearching.add("File not found!");
        }
        return resultOfSearching;
    }

    public static boolean isDirectoryEditable(String user, String directory){
        ArrayList<String> dirs = getAllAvailableDirectoriesForUser(user);
        for(String tmp : dirs){
            if(tmp.split("#")[0].equals(directory) && tmp.split("#")[1].equals("readAndMod")){
                return true;
            }
        }
        return false;
    }

    public static ArrayList<String> getAllEditableDirectoryForUsers(String user){
        ArrayList<String> allDirs = getAllAvailableDirectoriesForUserWithoutMod(user);
        ArrayList<String> res = new ArrayList<>();

        for(String tmp : allDirs){
            if(isDirectoryEditable(user, tmp)){
                res.add(tmp);
            }
        }
        return res;
    }
}
