import java.io.*;
import java.net.Socket;


public class tcp3 {

    public static void main(String[] args) {
         String host = "36.50.135.242";
         int port = 2206;

         String scode = "B23DCCN827";
         String qcode = "r6zZ9i5z";
          try( Socket socket = new Socket(host, port)){
              InputStream in = socket.getInputStream();
              OutputStream out = socket.getOutputStream();

              String request = scode + ";"+ qcode+"\n";

              out.write(request.getBytes());
              out.flush();

              //bc
              byte[] buffer = new byte[1024];
              int len = in.read(buffer);

              String result = new String(buffer, 0 , len);
              String[] re = result.split(",");
              int [] a = new int[re.length];
              for(int i = 0 ; i< re.length ; i++){
                  a[i] = Integer.parseInt(re[i]);
              }
              int max1 = a[0];
              int max2 = a[0];
              int add = -1;
              for (int i = 0 ; i< re.length ; i++){
                  if(a[i] > max2 && a[i]< max1){
                      max2 = a[i];
                      add = i;
                  }
                  if (a[i]>max1) {
                      max2 = max1;
                      max1 = a[i];
                  }
              }
              String req2 = max2 +"," + add;
              out.write(req2.getBytes());
              out.flush();

              //d



          }
          catch (Exception e){
              throw new RuntimeException(e);
          }
    }
}
