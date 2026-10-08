

import java.io.*;
import java.net.Socket;

public class tcp1 {

    public static void main(String[] args) {
        String host = "36.50.135.242";
        int port = 2208;

        String studentCode = "B23DCCN827";
        String qCode = "xeJxK90y";

        try( Socket socket = new Socket(host, port)){
            socket.setSoTimeout(5000);

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream())
            );

            //a gui scode, qcode

            String request = studentCode + ";" + qCode;

            writer.write(request);
            writer.newLine();
            writer.flush(); // ddaayr xuoongs server

            System.out.println("Sent: " + request);

            // b. nhaanj domain

            String response = reader.readLine();
            System.out.println("res: " + response);

            //c. tim domain .edu

            String[] domains = response.split(",");
            String eduDomains = new String();
            for (String domain : domains){
                domain = domain.trim();
                if(domain.endsWith(".edu")){
                    if(eduDomains.length() > 0){
                        eduDomains += (", ");
                    }
                    eduDomains += domain;
                }
            }
            System.out.println("EDU domains: "+ eduDomains);

            // send to server

            writer.write(eduDomains);
            writer.newLine();
            writer.flush();

            // close connect
            System.out.println("Done.");

        } catch (IOException e){
            e.printStackTrace();
        }

    }
}