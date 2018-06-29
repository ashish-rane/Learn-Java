package com.xpert.java.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClientDemo {

    public String get(String getUrl) throws IOException{
        URL url = new URL(getUrl);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        //conn.setRequestProperty("Content-Type", "application/json");

        return this.read(conn.getInputStream());
    }

    public String post(String postUrl, String data) throws IOException{
        URL url = new URL(postUrl);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("POST");

        conn.setDoInput(true);
        this.sendData(conn, data);
        return this.read(conn.getInputStream());
    }

    private String read(InputStream inputStream) throws IOException{
        String inputLine;
        StringBuilder body;
        try(BufferedReader in = new BufferedReader(new InputStreamReader(inputStream))){
            body = new StringBuilder();
            while ((inputLine = in.readLine()) != null){
                body.append(inputLine);
            }
            in.close();
            return body.toString();
        }
        catch (IOException ex){
            throw ex;
        }
    }

    protected void sendData(HttpURLConnection conn, String data) throws IOException{

        conn.setDoOutput(true); // this is required to get the output stream
        try(DataOutputStream wr = new DataOutputStream(conn.getOutputStream())){
            wr.writeBytes(data);
            wr.flush();
            wr.close();
        }
        catch (IOException ex){
            throw ex;
        }
    }

    public static void main(String[] args){
        HttpClientDemo demo = new HttpClientDemo();

        // HTTP GET
        try{

            String body = demo.get("http://httpbin.org/get");
            System.out.println(body);
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

        // HTTP POST
        try {
            String body = demo.post("http://httpbin.org/post", "data=test data");
            System.out.println(body);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
