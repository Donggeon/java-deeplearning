package org.deeplearning4j.streaming.pipeline.flink;

import org.apache.camel.CamelContext;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.deeplearning4j.streaming.conversion.dataset.RecordToDataSet;
import org.deeplearning4j.streaming.pipeline.kafka.BaseKafkaPipeline;
import org.nd4j.linalg.dataset.DataSet;

public class FlinkStreamingPipeline extends BaseKafkaPipeline<DataStream<DataSet>, RecordToDataSet> {
  protected StreamExecutionEnvironment execEnv;

  public FlinkStreamingPipeline(String kafkaTopic, String inputUri, String inputFormat, String kafkaBroker,
                                String zkHost, CamelContext camelContext, String hadoopHome, String dataType,
                                String sparkAppName, int kafkaPartitions, RecordToDataSet recordToDataSetFunction,
                                int numLabels, DataStream<DataSet> dataset) {
    super(kafkaTopic, inputUri, inputFormat, kafkaBroker, zkHost, camelContext, hadoopHome, dataType, sparkAppName, kafkaPartitions, recordToDataSetFunction, numLabels, dataset);
  }

  @Override
  public void initComponents() {

  }

  @Override
  public DataStream<DataSet> createStream() {
    return null;
  }

  @Override
  public void startStreamingConsumption(long timeout) {

  }
}
