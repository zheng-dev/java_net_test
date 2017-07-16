package my_net.mina;

/**
 * Created by Administrator on 2017/7/2.
 */
public class MyWebSocket {
}
//  try {
////                String a = new String(Base64.getDecoder().decode(this.sec.getBytes(StandardCharsets.UTF_8)))
//          String a = this.sec + "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
//          String key = Base64.getEncoder().encodeToString(EncoderHandler.sha1(a.getBytes()).getBytes());
//          session.write("HTTP/1.1 101 Switching Protocols\r\n" +
//          "Upgrade:websocket\r\n" +
//          "Connection:Upgrade\r\n" +
//          "Sec-WebSocket-Accept:" + key + "\r\n" +
//          "WebSocket-Protocol:chat\r\n" +
//          "Sec-WebSocket-Origin: null\n\r" +
//          "Sec-WebSocket-Location: ws://127.0.0.1:8200\r\n");
//          logger.info("send over:" + key);
//          } catch (Exception e) {
//          e.printStackTrace();
//          }