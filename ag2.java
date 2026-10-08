import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ag2 {
    public static void main(String[] args) {
        String scode = "B23DCCN827";
        String qcode = "oquhM8Ul";

        String host = "36.50.135.242";
        int port = 2206;

        try(Socket socket = new Socket(host, port)){
            socket.setSoTimeout(5000);

            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();

            String req = scode + ";" + qcode+"\n";

            out.write(req.getBytes());
            out.flush();

            //b

            byte[] b = new byte[1024];
            int len = in.read(b);

            String rep = new String(b, 0, len);

            //c

            String[] ar = rep.split(",");
            int[] arr = new int[ar.length];
            int i = 0;
            for(String x:ar){
                arr[i++] = Integer.parseInt(x);
            }
            Arrays.sort(arr);
            int min = arr[1]-arr[0];
            int x = arr[0];
            int y = arr[1];
            for(int j = 1; j< arr.length - 1; j++){
                if(arr[j+1]-arr[j]<min){
                    min = arr[j+1]-arr[j];
                    x = arr[j];
                    y = arr[j+1];
                }
            }

            req = new String(min+","+x+","+y);
            out.write(req.getBytes());
            out.flush();
//            System.out.println(min+","+x+","+y);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
