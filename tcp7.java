package TCP;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;


public class tcp7 {
    public static void main(String[] args) {
        String scode = "B23DCCN827";
        String qcode = "5ZbbihWT";

        String host = "36.50.135.242";
        int port = 2209;
        try(Socket socket = new Socket(host, port)){
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());

            String s = scode+";"+qcode;
            out.writeObject(s);
            out.flush();

            //
            Laptop a = (Laptop)in.readObject();

            String[] an = a.getName().split(" ");
            String tmp = an[0];
            an[0] = an[an.length-1];
            an[an.length-1] = tmp;


            a.setName(String.join(" ",an)) ;
            System.out.println(a.getName());
            a.setQuantity(Integer.parseInt(new StringBuilder(String.valueOf(a.getQuantity())).reverse().toString()));

            out.writeObject(a);
            out.flush();



        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
