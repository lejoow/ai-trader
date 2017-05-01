package com.lejoow.aitrader.market.impl

import com.lightbend.lagom.scaladsl.playjson.{JsonSerializer, JsonSerializerRegistry}

import scala.collection.immutable

/**
  * Created by Joo on 2/5/2017.
  */
object MarketSerializerRegistry extends JsonSerializerRegistry{
  override def serializers: immutable.Seq[JsonSerializer[_]] = Seq(

  )
}
