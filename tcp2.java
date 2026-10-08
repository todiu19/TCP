import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class tcp2 {
    public static void main(String[] args) {
        String host  = "36.50.135.242";
        int port = 2206;
        String studentcode = "B23DCCN827";
        String qcode = "oquhM8Ul";

        try (Socket socket = new Socket(host,port)){
        socket.setSoTimeout(5000);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();

        //a
        String req = studentcode + ";" + qcode+"\n";
        out.write(req.getBytes());
        out.flush();

        //b;
        byte[] b = new byte[1024];
        int len = in.read(b);
        String response = new String(b, 0, len);
        System.out.println(response);

        //c
        String [] s = response.split(",");
        int[] arr = new int[s.length];
        int k = 0;
        for(String x : s){
            arr[k++] = Integer.parseInt(x);
        }

        Arrays.sort(arr);
        int n = arr.length;
        int min = arr[1] - arr[0] ;
        int big = arr[1];
        int sm = arr[0];
        for(int i = 0 ; i < n - 1 ; i++ ){
            for( int j = i+1 ; j< n ; j++){
                if( arr[j] - arr[i] <= min && arr[j]>arr[1]){
                    big = arr[j];
                    sm = arr[i];
                    min = arr[j] - arr[i];
                }
            }
        }
        String req1 = min+","+sm+","+big;
        System.out.println(req1);
        out.write(req1.getBytes());
        out.flush();
        //d


        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
