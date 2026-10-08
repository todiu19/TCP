import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class tcp4 {
    public static void main(String[] args) {

        String host = "36.50.135.242";
        int port = 2208;

        String scode = "B23DCCN827";
        String qcode = "DMS5aRXl";

        try(Socket socket = new Socket(host, port)){
            socket.setSoTimeout(5000);
//            a. Gửi một chuỗi gồm mã sinh viên và mã câu hỏi với định dạng studentCode;qCode.
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String req = scode+";"+qcode;

            writer.write(req);
            writer.newLine();
            writer.flush();
//                    Ví dụ: B15DCCN999;BAA62945
//
//            b. Nhận một chuỗi ngẫu nhiên từ server
            String rep = reader.readLine();
            System.out.println(rep);
//            Ví dụ: dgUOo ch2k22ldsOo
//
//            c. Liệt kê các ký tự (là chữ hoặc số) xuất hiện nhiều hơn một lần trong chuỗi và số lần xuất hiện của chúng và gửi lên server
            Map<Character, Integer> mp = new LinkedHashMap<>();
            for(char x : rep.toCharArray()){
                int i = mp.getOrDefault(x,0);
                mp.put(x,i+1);
            }
//            Ví dụ: d:2,O:2,o:2,2:3,\
            String req1 = "";
            for(Map.Entry<Character, Integer> x : mp.entrySet()){
                if(x.getValue()>1 && Character.isLetterOrDigit(x.getKey())){
                    System.out.print(x.getKey()+":"+x.getValue()+",");
                    req1 += x.getKey()+":"+x.getValue()+",";
                }
            }
            writer.write(req1);
            writer.newLine();
            writer.flush();
//
//            d. Đóng kết nối và kết thúc chương trình.
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
