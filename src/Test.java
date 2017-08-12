import data.MyPoint;
import my_lambda.MyLambda;
import my_net.mina.MinaServer;
import my_net.nio.MyNio;
import my_threed.Console;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.io.*;
import java.util.Properties;


public class Test {
    static Logger logger = LogManager.getLogger(Test.class.getName());

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
//        testNet();
//        MyLambda.main(args);
        MyPoint.main(args);
//        logger.info("sha1:" + tool.EncoderHandler.sha1("123".getBytes()));
    }

    /**
     * 测试Properties文件
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void testProp() throws FileNotFoundException, IOException {
        File f = new File("");
        try {
            Properties prop = new Properties();
            InputStream in = new BufferedInputStream(new FileInputStream("out/a.conf"));
            prop.load(in);
            String test = prop.getProperty("test");
            Integer yy = Integer.valueOf(prop.getProperty("yy"));
            logger.info("value:" + test + " yy:" + (yy + 1) + ";path:" + f.getCanonicalPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用系统默认程序打开文件
     *
     * @throws IOException
     */
    public static void openWithSystemExe() throws IOException {
        File fs = new File("F:\\workspace\\qq.txt");
        Desktop dt = Desktop.getDesktop();
        dt.open(fs);
    }

    /**
     * 读取文件内容
     *
     * @return BufferedReader
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static BufferedReader openFileInBR(String fileName) throws FileNotFoundException, IOException {
        FileInputStream fis = new FileInputStream(fileName);
        InputStreamReader isReader = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isReader);
        System.out.print(br.readLine());//打印一行
        br.close();
        return br;
    }

    /**
     * 运行系统程序
     *
     * @param cmd "mstsc"
     * @throws IOException
     */
    public static void cmd(String cmd) throws IOException {
        Runtime rt = Runtime.getRuntime();
        rt.exec(cmd);
    }

    //测试变体参数,不同与{}
    public static void testArgs(int... args) {
        for (int arg : args) {
            logger.info("arg:" + arg);
        }
    }

    /**
     * 网络测试
     *
     * @throws Exception
     */
    private static void testNet() throws Exception {
//        MinaServer netServer = new MinaServer("127.0.0.1", 8200);
        MyNio netServer = new MyNio("127.0.0.1", 8200);
        new Thread(netServer).start();
        new Thread(new Console(netServer)).start();
    }
}
