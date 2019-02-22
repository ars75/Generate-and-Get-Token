package org.gettoken;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String CREDENTIALS_PATH = System.getProperty("user.dir") + File.separator + "credentials.csv";

    public static void main(String[] args) {
        List<Credentials> credentials = new ArrayList<Credentials>();
        Credentials credential = new Credentials();
        Csv csv = new Csv();
        // Convert CSV to Credentials Object
        try {
            credentials = csv.ReadFromCSV(credentials, CREDENTIALS_PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // get security token from file
        GetToken getToken = new GetToken();
        List<Integer> token = getToken.getAuthenticationToken();
        int securityToken = token.get(0);
        System.out.println("Token is : " + securityToken);

        // copies token to clipboard
        StringSelection selection = new StringSelection(String.valueOf(securityToken));
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);

        // print records
        // credential.printRecords(credentials);

        App app = new App();
        try {
            // if backup codes are less than 5 generate new ones
            if (token.size()<5){
                app.generateTokens(credentials);
            }
            // else delete the recently used token and update the file
            else {
                getToken.updateFile(token);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
