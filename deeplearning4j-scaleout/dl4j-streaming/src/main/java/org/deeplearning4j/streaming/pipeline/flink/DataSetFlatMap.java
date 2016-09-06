package org.deeplearning4j.streaming.pipeline.flink;

import java.util.Collection;
import org.apache.commons.net.util.Base64;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;
import org.datavec.api.writable.Writable;
import org.deeplearning4j.streaming.conversion.dataset.RecordToDataSet;
import org.deeplearning4j.streaming.serde.RecordDeSerializer;
import org.nd4j.linalg.dataset.DataSet;

public class DataSetFlatMap implements FlatMapFunction<Tuple2<String, String>, DataSet> {
  private int numLabels;
  private RecordToDataSet recordToDataSet;

  public DataSetFlatMap(int numLabels, RecordToDataSet recordToDataSet) {
    this.numLabels = numLabels;
    this.recordToDataSet = recordToDataSet;
  }

  @Override
  public void flatMap(Tuple2<String, String> tuple2,
                      Collector<DataSet> collector) throws Exception {
    byte[] bytes = Base64.decodeBase64(tuple2.f1);
    Collection<Collection<Writable>> records = new RecordDeSerializer().deserialize("topic", bytes);
    DataSet dataset = recordToDataSet.convert(records, numLabels);
    collector.collect(dataset);
  }

}
