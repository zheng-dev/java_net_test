package my_net.mina;

import data.Session;
import my_net.NetServer;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.transport.socket.SocketAcceptor;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;

import java.net.InetSocketAddress;

/**
 * mina test class
 * Created by Administrator on 2017/1/28.
 */
public class MinaServer implements NetServer {
    SocketAcceptor acceptor;

    /**
     * 初始监听2
     *
     * @param ip
     * @param port
     * @throws Exception
     */
    public void init(String ip, int port) throws Exception {
        this.acceptor = new NioSocketAcceptor(Runtime.getRuntime().availableProcessors() + 1);
        //解析器
        DefaultIoFilterChainBuilder chain = this.acceptor.getFilterChain();
        chain.addLast("codec", new ProtocolCodecFilter(
                new TextLineCodecFactory()
        ));
        //绑定处理器handler
        this.acceptor.setHandler(new ServerHandler());
        //绑定8200 端口
        this.acceptor.bind(new InetSocketAddress(ip, port));
        logger.info(toString().format("ok ip:%s,port:%d proce_num:%d%n", ip, port, Runtime.getRuntime().availableProcessors()));
    }

    /**
     * 关闭socket
     *
     * @return
     */
    public boolean shutdown() {
        this.acceptor.unbind();
        this.acceptor.dispose();
        logger.info("server_shutdown_ok");
        return true;
    }

    public MinaServer(String ip, int port) throws Exception {
        init(ip, port);
    }

    public void send(String msg[]) {
        Session.send(Integer.valueOf(msg[0]), msg[1] + "\r\n");
    }
}
