import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class tcp10 {
    public static void main(String[] args) {
        String scode = "B23DCCN827";
        String qcode = "xDEuy96v";

        String host = "36.50.135.242";
        int port = 2210;

        try(Socket socket = new Socket(host, port)){

            GZIPOutputStream out = new GZIPOutputStream(socket.getOutputStream());
            String s = scode+";"+qcode+"\n";
            out.write(s.getBytes(StandardCharsets.UTF_8));
            out.finish();
            out.flush();

            //
            GZIPInputStream in = new GZIPInputStream(socket.getInputStream());
            byte[] b = new byte[1024];;
            int len = in.read(b);
            String ss = new String(b,0,len, StandardCharsets.UTF_8);
            char[] x = ss.trim().toCharArray();
            Arrays.sort(x);
            ss = new String(x);
            ss+="\n";

            GZIPOutputStream out2 = new GZIPOutputStream(socket.getOutputStream());
            out2.write(ss.getBytes(StandardCharsets.UTF_8));
            out2.finish();
            out2.flush();



        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
