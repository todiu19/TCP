package TCP;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class tcp8 {
    public static String mn(String s){
        String[] x = s.split(" ");
        StringBuilder s1 = new StringBuilder();
        s1.append(x[x.length-1].toUpperCase()+", ");
        for(int i = 0; i< x.length-1; i++){
            s1.append(Character.toUpperCase(x[i].charAt(0)));
            s1.append(x[i].substring(1).toLowerCase()+" ");
        }
        return s1.toString();
    }
    public static String mb(String s){
        String[] x = s.split("-");
        StringBuilder ss = new StringBuilder();
        ss.append(x[1]+"/"+x[0]+"/"+x[2]);
        return ss.toString();
    }
    public static String mu(String s){
        String[] x = s.split(" ");
        StringBuilder ss = new StringBuilder();
        for (int i = 0; i < x.length-1; i++) {
            ss.append(Character.toLowerCase(x[i].charAt(0)));
        }
        ss.append(x[x.length-1].toLowerCase());
        return ss.toString();
    }

    public static void main(String[] args) {
        String scode = "B23DCCN827";
        String qcode = "OChz2NAb";

        String host = "36.50.135.242";
        int port = 2209;
        try(Socket socket = new Socket(host,port)){
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            String s = scode+";"+qcode;
            out.writeObject(s);
            out.flush();

            //
            Customer c = (Customer)in.readObject();
            c.setUserName(mu(c.getName()));
            c.setName(mn(c.getName()));
            c.setDayOfBirth(mb(c.getDayOfBirth()));

            System.out.println(c.getName());
            System.out.println(c.getDayOfBirth());
            System.out.println(c.getUserName());
            out.writeObject(c);
            out.flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
