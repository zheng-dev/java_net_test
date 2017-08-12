package tool;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/8/12.
 */
public class Http {

    public static void main(String args[]){
        try{
            String web="http://www.baidu.com";
            URL url=new URL(web);
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            connection.connect();

            BufferedReader reader=new BufferedReader(new InputStreamReader(
                    connection.getInputStream()
            ));
            StringBuffer sBuffer=new StringBuffer();
            String line="";

            while ((line=reader.readLine())!=null){
                sBuffer.append(line).append("\r\n");
            }

            System.out.println(sBuffer);
            connection.disconnect();

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
