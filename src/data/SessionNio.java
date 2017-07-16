package data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;

/**
 * Created by Administrator on 2017/7/2.
 */
public class SessionNio {
    static Logger logger = LogManager.getLogger(SessionNio.class.getName());
    //玩家表
    public static HashMap<Integer, SocketChannel> roleTable = new HashMap<Integer, SocketChannel>();

    /**
     * 保存数据
     *
     * @param role
     * @param session
     */
    static public synchronized void add(Integer role, SocketChannel session) throws Exception {
        Channel v = roleTable.get(role);
        if (null == v) roleTable.put(role, session);
        else {
            logger.warn(String.format("have_logined role:%d,curr_session_id:%d  had_session_id:%d\r\n",
                    role, 1, 2));
        }
    }

    static public void login(SocketChannel session) throws Exception {
        ByteBuffer buffer = ByteBuffer.allocate(20);
        session.read(buffer);
        byte data[] = buffer.array();
        String msg = (new String(data)).trim();
        logger.info("msg:" + msg+"add:"+session.socket().getRemoteSocketAddress().toString());
        String tokens[] = msg.split(":");
        if (tokens[0].equals("login")) {
            try {
                Integer role = Integer.valueOf(tokens[1]);
                add(role, session);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static public synchronized void send(Integer role, String msg) {
        SocketChannel v = roleTable.get(role);
        if (null != v) {
            try {
                v.write(ByteBuffer.wrap(msg.getBytes()));
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            logger.warn("role_null");
        }
    }
}
