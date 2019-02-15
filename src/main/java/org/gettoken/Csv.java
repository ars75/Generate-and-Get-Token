package org.gettoken;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.List;

public class Csv {

    public List<Credentials> ReadFromCSV(List<Credentials> credentials, String filePath) throws Exception {
        CSVReader reader = new CSVReader(new FileReader(filePath));
        String [] nextLine;
        String username=null, password=null;
        while ((nextLine = reader.readNext()) != null) {
            // nextLine[] is an array of values from the line
            username = nextLine[0];
            password = nextLine[1];

            Credentials c = new Credentials(username,password);
            credentials.add(c);
        }
        return credentials;
    }
}
