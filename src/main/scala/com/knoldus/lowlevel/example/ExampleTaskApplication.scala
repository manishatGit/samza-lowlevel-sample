package com.knoldus.lowlevel.example

import com.google.common.collect.{ImmutableList, ImmutableMap}
import org.apache.samza.application.TaskApplication
import org.apache.samza.application.descriptors.TaskApplicationDescriptor
import org.apache.samza.serializers.{KVSerde, StringSerde}
import org.apache.samza.system.kafka.descriptors.{KafkaInputDescriptor, KafkaSystemDescriptor}
import org.apache.samza.task.{StreamTask, StreamTaskFactory}

class ExampleTaskApplication extends TaskApplication{
  override def describe(appDescriptor: TaskApplicationDescriptor): Unit = {

    val systemName = "kafka"
    val zookeeper = ImmutableList.of("localhost:2181")
    val server = ImmutableList.of("localhost:9092")
    val streamConfig = ImmutableMap.of("replication.factor", "1")
    val inputTopic1 = "ExampleInputStream1"
    val inputTopic2 = "ExampleInputStream2"
    val outputTopic = "ExampleOutputStream"
    val serdeForKV = KVSerde.of(new StringSerde(), new StringSerde())

    val kafkaDesc = new KafkaSystemDescriptor(systemName)
      .withConsumerZkConnect(zookeeper)
      .withProducerBootstrapServers(server)
      .withDefaultStreamConfigs(streamConfig)

    val inputDesc1 = kafkaDesc.getInputDescriptor(inputTopic1, serdeForKV)
    val inputDesc2 = kafkaDesc.getInputDescriptor(inputTopic2, serdeForKV)
    val outputDesc = kafkaDesc.getOutputDescriptor(outputTopic, serdeForKV)

    appDescriptor.withDefaultSystem(kafkaDesc)
    appDescriptor.withInputStream(inputDesc1)
    appDescriptor.withInputStream(inputDesc2)
    appDescriptor.withOutputStream(outputDesc)

    appDescriptor.withTaskFactory(new StreamTaskFactory {
      override def createInstance(): StreamTask = new ExampleTask
    })
  }
}
