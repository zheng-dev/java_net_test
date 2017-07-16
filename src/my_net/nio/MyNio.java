package my_net.nio;

import data.SessionNio;
import my_net.NetServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * Created by Administrator on 2017/7/9.
 */
public class MyNio implements NetServer, Runnable {
    private Selector selector;

    private boolean selectLoop = true;

    String ip = "";
    int port;

    public MyNio(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void run() {
        try {
            init(this.ip, this.port);
            listen();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 初始listen
     */
    public void init(String ip, int port) throws Exception {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(ip, port));
        this.selector = Selector.open();
        serverSocketChannel.register(this.selector, SelectionKey.OP_ACCEPT);
        logger.info("start ok:" + ip + ":" + port);
    }

    public void listen() throws Exception {
        while (this.selectLoop) {
            this.selector.select();//必需阻塞住，有新的事件就遍历所有key
            Iterator<SelectionKey> ite = this.selector.selectedKeys().iterator();
            while (ite.hasNext()) {
                SelectionKey key = ite.next();
                ite.remove();//很关键,一个key可能有多个key,不重复处理就要删除
                if (key.isAcceptable()) {
                    ServerSocketChannel server = (ServerSocketChannel) (key).channel();
                    SocketChannel channel = server.accept();
                    channel.configureBlocking(false);
//                    channel.socket().setSoTimeout(1);
                    channel.register(this.selector, SelectionKey.OP_READ);
                    logger.debug("read|write ok");

                } else if (key.isValid() && key.isReadable()) {
                    try {
                        SessionNio.login((SocketChannel) key.channel());
                    } catch (IOException e) {
                        key.cancel();
                        ((SocketChannel) key.channel()).socket().close();
                        key.channel().close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else if (key.isWritable()) {
//                    logger.debug("write ok");
                }
            }

        }
    }

    /**
     * 关闭监听
     *
     * @return
     */
    public boolean shutdown() {
        logger.warn("====selectLoop=====\r\n");
        try {
            this.selector.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        this.selectLoop = false;//关闭监听
        return true;
    }

    public void send(String msg[]) {
        SessionNio.send(Integer.valueOf(msg[0]), msg[1] + "\r\n");
    }
}

