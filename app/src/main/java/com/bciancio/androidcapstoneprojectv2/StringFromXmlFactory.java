package com.bciancio.androidcapstoneprojectv2;

import java.io.*;

/**
 * Created by Student on 11/3/2015.
 */
public class StringFromXmlFactory {

    /**
     * This method will take the name of an xml file and convert its contents into a string
     * @param filename
     * @return
     */
    public static String getXmlString(String filename) {
        StringBuilder sb = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(new File(filename)))) {
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line.trim());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }
}
