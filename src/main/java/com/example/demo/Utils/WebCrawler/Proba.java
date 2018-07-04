package com.example.demo.Utils.WebCrawler;


import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@Component
public class Proba {

    public void f() throws MalformedURLException, IOException{
        URL url = new URL("https://bilete.cfrcalatori.ro/vanzare/rute_.aspx?keyimt=dtm=29-03-2018&p=Medias&d=Sibiu&ora=06&lng=ro&cautavans=0");

        // Get the input stream through URL Connection
        URLConnection con = url.openConnection();
        InputStream is = con.getInputStream();


        // Once you have the Input Stream, it's just plain old Java IO stuff.

        // For this case, since you are interested in getting plain-text web page
        // I'll use a reader and output the text content to System.out.

        // For binary content, it's better to directly read the bytes from stream and write
        // to the target file.


        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String line = null;

        // read each line and write to System.out
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
    }
}
