package com.lejoow.aitrader.market.api

import akka.{Done, NotUsed}
import com.lightbend.lagom.scaladsl.api.{Descriptor, Service, ServiceCall}

/**
  * Created by Joo on 2/5/2017.
  */
trait MarketService extends Service {

  def subscribeToPriceChange(symbol: String): ServiceCall[NotUsed, Done]

  def unsubscribeToPriceChange(symbol: String): ServiceCall[NotUsed, Done]

  override final def descriptor: Descriptor = {
    import Service._
    named("market").withCalls(
      pathCall("/api/market/subscribe/:symbol", subscribeToPriceChange _)
    ).withAutoAcl(true)
  }
}
