package com.knoldus.lowlevel.example

import org.apache.samza.runtime.LocalApplicationRunner
import org.apache.samza.util.CommandLine

object ExampleTaskRunner {
  def main(args: Array[String]): Unit = {
    val cmdLine = new CommandLine
    val options = cmdLine.parser.parse("--config-factory=org.apache.samza.config.factories.PropertiesConfigFactory",
      "--config-path=file:///home/knoldus/Documents/samza/samza-poc/src/main/config/Example.properties")
    val conf = cmdLine.loadConfig(options)
    val runner = new LocalApplicationRunner(new ExampleTaskApplication, conf)
    runner.run()
    runner.waitForFinish()
  }
}
