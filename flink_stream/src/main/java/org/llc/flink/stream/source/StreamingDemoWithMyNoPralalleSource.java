package org.llc.flink.stream.source;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingEventTimeWindows;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.llc.flink.stream.source.custom.CustomNoParallelSource;

/**
 * 使用并行度为1的source
 */
public class StreamingDemoWithMyNoPralalleSource {

    public static void main(String[] args) throws Exception {
        //获取Flink的运行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        //获取数据源
        DataStreamSource<Long> text = env.addSource(new CustomNoParallelSource()).setParallelism(1);//注意：针对此source，并行度只能设置为1

        DataStream<Long> num = text.map((MapFunction<Long, Long>) value -> {
            System.out.println("接收到数据：" + value);
            return value;
        });

        //每2秒钟处理一次数据
        DataStream<Long> sum = num.windowAll(TumblingEventTimeWindows.of(Time.seconds(2))).sum(0);

        DataStream<Long> max = num.windowAll(TumblingEventTimeWindows.of(Time.seconds(10))).sum(0);
        //打印结果
        sum.print().setParallelism(1);
        max.print().setParallelism(1);
        String jobName = StreamingDemoWithMyNoPralalleSource.class.getSimpleName();
        env.execute(jobName);
    }
}
