package com.xigaiou.xigaiouproject.common;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.net.InetAddress;

/**
 * 雪花算法64位
 */
@Slf4j
public class GeneratorIdUtil {
    private static Logger logger = LoggerFactory.getLogger(GeneratorIdUtil.class);

    /**
     * 开始时间截,这里设置为项目开始的时间2018-05-24 从2018-05-24算起大约能够使用69年
     */
    private final long twepoch = 1527150129903L;

    /**
     * 机器id所占的位数,这里设置位10L，因为datacenterIdBits不需要
     * 如果workerIdBits=5L 则datacenterIdBits=5L
     */
    private final long workerIdBits = 10L;

    /*
     * 数据标识id所占的位数，根据项目需求可以不用
     */
    // private final long datacenterIdBits = 5L;

    /**
     * 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /*
     * 支持的最大数据标识id，结果是31
     */
    // private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    /**
     * 序列在id中占的位数
     */
    private final long sequenceBits = 12L;

    /**
     * 机器ID向左移12位
     */
    private final long workerIdShift = sequenceBits;

    /*
     * 数据标识id向左移17位(12+5)
     */
    // private final long datacenterIdShift = sequenceBits + workerIdBits;

    /**
     * 时间截向左移22位(5+5+12)
     * //+ datacenterIdBits;
     */
    private final long timestampLeftShift = sequenceBits + workerIdBits;

    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 工作机器ID(0~1023)
     */
    private long workerId;

    /**
     * 数据中心ID(0~31)
     */
    private long datacenterId;

    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;

    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;

    /**
     * 构造函数
     *
     * @param workerId 工作ID (0~1023)
     */
    private GeneratorIdUtil(long workerId) {
        logger.info("workerId : {}", workerId);
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        this.workerId = workerId;
    }

    /**
     * 构造函数
     */
    public GeneratorIdUtil() {
        this.workerId = getIdWorker();
        logger.info("workerId : {}", workerId);
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
    }


    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    public synchronized String nextId() {
        long timestamp = timeGen();

        //如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //毫秒内序列溢出
            if (sequence == 0) {
                //阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else { //时间戳改变，毫秒内序列重置
            sequence = 0L;
        }

        //上次生成ID的时间截
        lastTimestamp = timestamp;

        //移位并通过或运算拼到一起组成64位的ID
        return String.valueOf(((timestamp - twepoch) << timestampLeftShift)
                // | (datacenterId << datacenterIdShift)
                | (workerId << workerIdShift)
                | sequence);
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 获取机器编号，设置机器编号的方式有很多，这里根据业务需求取IP地址的后三位
     * 根据前面分析该ID位0-1023
     * param
     */
    private int getIdWorker() {
        InetAddress addr = null;
        String ip = "";
        try {
            addr = InetAddress.getLocalHost();
            /*获取本机IP*/
            ip = addr.getHostAddress();
            logger.info("本机IP ： {}  ", ip);
            return Integer.parseInt(ip.split("\\.")[3]);
        } catch (Exception e) {
            logger.error("获取机器编号错误 ： {}  ", ip + e);
        }
        return 0;
    }
}