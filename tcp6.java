import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class tcp6 {
    public static void main(String[] args) {
        String scode = "B23DCCN827";
        String qcode = "cm2jL4YW";

        String host = "36.50.135.242";
        int port = 2207;

        try(Socket socket = new Socket(host,port)){

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            String ss = scode+";"+qcode;

            out.writeUTF(ss);
            out.flush();
            //
            String s = in.readUTF();
            int a = in.readInt();
            StringBuilder s1 = new StringBuilder();
            for(int i= 0 ; i<s.length(); i++){

                if('A'<=s.charAt(i) && s.charAt(i)<='Z'){
                    s1.append((char)((s.charAt(i)-'A'- a +26)%26 +'A'));
                }
                else s1.append(s.charAt(i));
            }
            out.writeUTF(s1.toString());
            out.flush();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
