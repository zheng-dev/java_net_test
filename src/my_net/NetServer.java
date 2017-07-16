package my_net;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Administrator on 2017/7/9.
 */
public interface NetServer {
    static Logger logger = LogManager.getLogger(NetServer.class.getName());

    /**
     * 关闭监听
     *
     * @return
     */
    public boolean shutdown();

    /**
     * 初始一个监听端口
     *
     * @param ip
     * @param port
     * @throws Exception
     */
    public void init(String ip, int port) throws Exception;

    /**
     * 发送消息
     *
     * @param msg {Integer role_uid, String text}
     */
    public void send(String msg[]);
}
