package com.lejoow.aitrader.market.impl

import com.softwaremill.macwire._
import com.lejoow.aitrader.market.api.MarketService
import com.lightbend.lagom.internal.client.CircuitBreakerMetricsProviderImpl
import com.lightbend.lagom.scaladsl.api.{Descriptor, ServiceLocator}
import com.lightbend.lagom.scaladsl.api.ServiceLocator.NoServiceLocator
import com.lightbend.lagom.scaladsl.broker.kafka.LagomKafkaComponents
import com.lightbend.lagom.scaladsl.devmode.LagomDevModeComponents
import com.lightbend.lagom.scaladsl.persistence.cassandra.CassandraPersistenceComponents
import com.lightbend.lagom.scaladsl.server.{LagomApplication, LagomApplicationContext, LagomApplicationLoader, LagomServerComponents}
import play.api.libs.ws.ahc.AhcWSComponents
import play.api.Environment

import scala.collection.immutable
import scala.concurrent.ExecutionContext
import scala.concurrent.ExecutionContext

/**
  * Created by Joo on 2/5/2017.
  */
trait MarketComponents extends LagomServerComponents
  with CassandraPersistenceComponents {

  override implicit def executionContext: ExecutionContext

  def environment: Environment

  override lazy val lagomServer = serverFor[MarketService](wire[MarketServiceImpl])

  override lazy val jsonSerializerRegistry = MarketSerializerRegistry
}

abstract class MarketServiceApplication(context: LagomApplicationContext)
  extends LagomApplication(context)
    with MarketComponents
    with AhcWSComponents
    with LagomKafkaComponents {

}

class MarketServiceLoader extends LagomApplicationLoader {

  override def loadDevMode(context: LagomApplicationContext): LagomApplication =
    new MarketServiceApplication(context) with LagomDevModeComponents

  override def load(context: LagomApplicationContext): LagomApplication =
    new MarketServiceApplication(context) {
      override def serviceLocator: ServiceLocator = NoServiceLocator

      override lazy val circuitBreakerMetricsProvider = new CircuitBreakerMetricsProviderImpl(actorSystem)

    }

  override def describeServices: immutable.Seq[Descriptor] = List(
    readDescriptor[MarketService]
  )

}


