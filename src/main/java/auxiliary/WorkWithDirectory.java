package auxiliary;

/*
Работа с директориями пользователей
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class WorkWithDirectory {
    public static String[] getListOfFiles(String dirPath) {
        File userDir = new File(dirPath);
        return userDir.list();
    }

    public static boolean isDirectoryEmpty(String[] listOfFiles){
        return listOfFiles == null;
    }

    public static ArrayList<String> getAllAvailableDirectoriesForUser(String user) throws IOException {
        ArrayList<String> res = new ArrayList<>();
        BufferedReader dataBaseReader = new BufferedReader (new FileReader
                ("C:\\java\\fileSharingSystem\\dataBase\\directoriesOfUsers.txt"));

        String tmp;
        while ((tmp = dataBaseReader.readLine()) != null) {
            String[] lineFromDB;
            lineFromDB = tmp.split("&");
            if (lineFromDB[0].equals(user)) {
                res.addAll(Arrays.asList(lineFromDB).subList(1, lineFromDB.length));
                break;
            }
        }

        return res;
    }

    public static void addDirectoryToUser(String directory, String user) throws IOException {
        BufferedReader dataBaseReader = new BufferedReader (new FileReader
                ("C:\\java\\fileSharingSystem\\dataBase\\directoriesOfUsers.txt"));

        String tmp;
        StringBuilder bufferOfFile = new StringBuilder();

        while ((tmp = dataBaseReader.readLine()) != null) {
            if (tmp.split("&")[0].equals(user)) {
                tmp += "&" + directory;
            }
            bufferOfFile.append(tmp).append("\n");
        }
        dataBaseReader.close();

        BufferedWriter dataBaseWriter = new BufferedWriter (new FileWriter
                ("C:\\java\\fileSharingSystem\\dataBase\\directoriesOfUsers.txt"));
        synchronized (dataBaseWriter) {
            dataBaseWriter.write(bufferOfFile.toString());
            dataBaseWriter.close();
        }
    }

    public static boolean hasUserThisDirectory(String user, String directory) throws IOException {
        ArrayList<String> dirs = getAllAvailableDirectoriesForUser(user);
        for(String tmp : dirs){
            if(tmp.equals(directory)){
                return true;
            }
        }
        return false;
    }

    public static ArrayList<String> doSearch(String user, String searchRequest) throws IOException {
        ArrayList<String> allUserDirectories = getAllAvailableDirectoriesForUser(user);
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
}
