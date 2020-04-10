package com.knoldus.lowlevel.example

import org.apache.samza.system.{IncomingMessageEnvelope, OutgoingMessageEnvelope, SystemStream}
import org.apache.samza.task.{MessageCollector, StreamTask, TaskCoordinator}

class ExampleTask extends StreamTask{
  override def process(envelope: IncomingMessageEnvelope, collector: MessageCollector, coordinator: TaskCoordinator): Unit = {
    val outputStream = new SystemStream("kafka", "ExampleOutputStream")
    val processedMsg = Map("key"-> envelope.getMessage.toString)
    collector.send(new OutgoingMessageEnvelope(outputStream, processedMsg))
  }
}
