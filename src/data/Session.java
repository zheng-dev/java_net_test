package data;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.session.IoSession;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/7/2.
 */
public class Session {
    static Logger logger = LogManager.getLogger(Session.class.getName());
    //玩家表
    public static HashMap<Integer, IoSession> roleTable = new HashMap<Integer, IoSession>();

    /**
     * 保存数据
     *
     * @param role
     * @param session
     */
    static public synchronized void add(Integer role, IoSession session) {
        IoSession v = roleTable.get(role);
        if (null == v) roleTable.put(role, session);
        else {
            logger.warn(String.format("have_logined role:%d,curr_session_id:%d  had_session_id:%d\r\n",
                    role, session.getId(), v.getId()));
        }
    }

    static public void login(IoSession session, Object msg) {
        String tokens[] = msg.toString().split(":");
        if (tokens[0].equals("login")) {
            try {
                Integer role = Integer.valueOf(tokens[1]);
                add(role, session);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            logger.info("msg" + msg.toString());
        }
    }

    static public synchronized void send(Integer role, String msg) {
        IoSession v = roleTable.get(role);
        if (null != v) {
            v.write(msg);
        } else {
            logger.warn("role_null");
        }
    }
}
