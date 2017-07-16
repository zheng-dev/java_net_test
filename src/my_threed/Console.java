package my_threed;

import my_net.NetServer;

import java.util.Scanner;

/**
 * Created by Administrator on 2017/7/2.
 */
public class Console implements Runnable {
    Scanner s = new Scanner(System.in);
    String cmd;
    NetServer server;

    public Console(final NetServer server) {
        this.server = server;
    }

    public void run() {
        while (true) {
            System.out.print("cmd:");
            cmd = s.nextLine();
            if (cmd.equals("quit")) {
                this.server.shutdown();
                break;
            }
            String msg[] = cmd.split(":");
            if (msg.length > 1) this.server.send(msg);
        }
    }
}
