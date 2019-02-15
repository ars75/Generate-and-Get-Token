package org.gettoken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GetToken {

    private static final String FILEPATH = "C:" + File.separator + "Users" + File.separator + "adityasatalkar" + File.separator + "dev" + File.separator + "SecurityToken" + File.separator + "tokens.txt";

    public List<Integer> getAuthenticationToken() {
        List<Integer> list = new ArrayList<Integer>();
        try {
            Scanner fileScanner = new Scanner(new FileReader(FILEPATH));
            Scanner lineScanner;
            String line;
            while (fileScanner.hasNextLine()) {
                line = fileScanner.nextLine();
                lineScanner = new Scanner(line);
                while (lineScanner.hasNext()) { //Check if there is anything in the line
                    if (lineScanner.hasNextInt()) { //Extract integers from file
                        list.add(lineScanner.nextInt());
                    }
                    else {
                        lineScanner.next();
                    }
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Sorry! This file is not found");
        }
        return list;
    }

    public void updateFile(List<Integer> integerList) throws IOException {
        File file = new File(FILEPATH);
        FileWriter writer = new FileWriter(file);
        for (int i = 1; i < integerList.size(); i++) {
            writer.write(String.valueOf(integerList.get(i)));
            if(i < integerList.size()-1){
                //This prevent creating a blank like at the end of the file**
                writer.write("\n");
            }
        }
        writer.close();
    }
}
