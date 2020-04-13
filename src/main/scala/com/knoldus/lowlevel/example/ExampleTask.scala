package com.knoldus.lowlevel.example

import org.apache.samza.system.{IncomingMessageEnvelope, OutgoingMessageEnvelope, SystemStream}
import org.apache.samza.task.{MessageCollector, StreamTask, StreamTaskFactory, TaskCoordinator}

class ExampleTask extends StreamTask {// with StreamTaskFactory {
  override def process(envelope: IncomingMessageEnvelope, collector: MessageCollector, coordinator: TaskCoordinator): Unit = {
    val outputStream = new SystemStream("kafka", "ExampleOutputStream")
//    val processedMsg = Map("key"-> envelope.getMessage.toString.toUpperCase())
    collector.send(new OutgoingMessageEnvelope(outputStream, envelope.getMessage.toString.toUpperCase()))
  }

//  override def createInstance(): StreamTask = new ExampleTask
}
