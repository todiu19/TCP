import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class tcp9 {
    public static void main(String[] args) {
        String scode = "B23DCCN827";
        String qcode = "917bvngc";

        String host = "36.50.135.242";
        int port = 2210;

        try(Socket socket = new Socket(host, port)){

            GZIPOutputStream out1 = new GZIPOutputStream((socket.getOutputStream()));
            String s = scode+";"+qcode+"\n";
            out1.write(s.getBytes(StandardCharsets.UTF_8));
            out1.finish();
            out1.flush();

            //
            GZIPInputStream in = new GZIPInputStream(socket.getInputStream());
            byte[] b = new byte[1024];
            int len = in.read(b);
            String ss = new String(b,0,len,StandardCharsets.UTF_8);
            StringBuilder sss = new StringBuilder(ss.trim());
            ss = sss.reverse().toString();
            String bs64 = Base64.getEncoder().encodeToString(ss.getBytes());
            ss+="|"+bs64+"\n";

            GZIPOutputStream out2 = new GZIPOutputStream((socket.getOutputStream()));
            out2.write(ss.getBytes(StandardCharsets.UTF_8));
            out2.finish();
            out2.flush();



        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
