package my_threed;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2017/6/25.
 */
public class Ticks implements Callable<Integer> {
    static Logger logger = LogManager.getLogger(Ticks.class.getName());

    /**
     * 测试入口
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
        Ticks ticks = new Ticks(5);
        int thread_num = 8;
        Future<Integer> future = null;
        ExecutorService es = Executors.newFixedThreadPool(thread_num);
        for (int i = 0; i < thread_num; i++) {
            future = es.submit(ticks);
        }
        logger.info("future:" + future.get(5, TimeUnit.SECONDS));
//        new Thread(ticks).start();
//        new Thread(ticks).start();
        Thread.sleep(2000);
        es.shutdown();
    }

    static Logger log = LogManager.getLogger(Ticks.class.getName());
    Integer tick_num = 0;//票数

    public Ticks(Integer tick_num) {
        this.tick_num = tick_num;
    }

    /**
     * 售票操作
     * 在线程中执行
     *
     * @return
     */
    private synchronized Integer sell() {
        while (this.tick_num > 0) {
            log.info(String.format("tick_num:%d thread_id:%d", this.tick_num, Thread.currentThread().getId()));
            this.tick_num--;
            this.waitHelp();
        }
        log.debug("thread_id:" + Thread.currentThread().getId() + "over");
        return this.tick_num;
    }

    //帮助测试
    // wait会释放已有资源
    // sleep不会释放锁资源[其他线程也就不能得到锁,占着毛坑了]
    private void waitHelp() {
        try {
            wait(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 线程入口
     */
    public Integer call() {
        this.sell();
        return 99;
    }
}
