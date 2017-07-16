package my_net.mina;

import data.Session;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * Created by Administrator on 2017/1/28.
 */
public class ServerHandler extends IoHandlerAdapter {
    static Logger logger = LogManager.getLogger(ServerHandler.class.getName());

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        logger.info(String.format("received msg:%s id:%s%n", message.toString(), session.getId()));
        Session.login(session, message);//代理处理所有网络消息
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        logger.info(String.format("messageSent id:%d msg:%s%n", session.getId(), message.toString()));
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
        logger.info("session_open id:" + session.getId()
        );
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 30000);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
        logger.info("sessionClosed id:" + session.getId()
        );
    }
}

