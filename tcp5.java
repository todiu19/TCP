import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class tcp5 {
    //                  ┌── [Byte Stream]  ──► DataInputStream / DataOutputStream (Đọc/ghi int, float, UTF...)
    //socket.getStream()│
    //                  └── [Reader/Writer] ─► InputStreamReader / OutputStreamWriter ─► BufferedReader / BufferedWriter / PrintWriter
    //                                         (Chuyển Byte -> Char)                     (Bộ đệm & đọc/ghi theo dòng)
    public static void main(String[] args) {
        String host = "36.50.135.242";
        int port = 2207;

        String scode = "B23DCCN827";
        String qcode = "KkmMW2kg";

        try(Socket socket = new Socket(host,port)){
            socket.setSoTimeout(5000);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            String req = scode + ";"+ qcode;

            out.writeUTF(req);

            int a = in.readInt();
            int b = in.readInt();

            int sum = a+b;
            int tich = a*b;

            out.writeInt(sum);
            out.writeInt(tich);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
