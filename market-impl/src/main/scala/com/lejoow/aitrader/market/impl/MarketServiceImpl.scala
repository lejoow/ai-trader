package com.lejoow.aitrader.market.impl

import akka.{Done, NotUsed}
import com.lejoow.aitrader.market.api.MarketService
import com.lightbend.lagom.scaladsl.api.ServiceCall
import com.lightbend.lagom.scaladsl.persistence.PersistentEntityRegistry

/**
  * Created by Joo on 2/5/2017.
  */
class MarketServiceImpl(persistentEntityRegistry: PersistentEntityRegistry) extends MarketService {

  override def subscribeToPriceChange(symbol: String): ServiceCall[NotUsed, Done] = ???

  override def unsubscribeToPriceChange(symbol: String): ServiceCall[NotUsed, Done] = ???

}
