package io.igu.cityindex.prices.model

import com.lightstreamer.ls_client.UpdateInfo

import scala.util.Try

case class Price(marketId: Int,
                 tickDate: String,
                 bid: BigDecimal,
                 offer: BigDecimal,
                 price: BigDecimal,
                 high: BigDecimal,
                 low: BigDecimal,
                 change: BigDecimal,
                 direction: Int,
                 delta: BigDecimal,
                 impliedVolatility: BigDecimal,
                 auditId: String,
                 statusSummary: String)

object Price {

  def apply(updateInfo: UpdateInfo): Try[Price] = Try(Price(
    marketId = updateInfo.getNewValue("MarketId").toInt,
    tickDate = updateInfo.getNewValue("TickDate"),
    bid = BigDecimal(updateInfo.getNewValue("Bid")),
    offer = BigDecimal(updateInfo.getNewValue("Offer")),
    price = BigDecimal(updateInfo.getNewValue("Price")),
    high = BigDecimal(updateInfo.getNewValue("High")),
    low = BigDecimal(updateInfo.getNewValue("Low")),
    change = BigDecimal(updateInfo.getNewValue("Change")),
    direction = updateInfo.getNewValue("Direction").toInt,
    delta = BigDecimal(updateInfo.getNewValue("Delta")),
    impliedVolatility = BigDecimal(updateInfo.getNewValue("ImpliedVolatility")),
    auditId = updateInfo.getNewValue("AuditId"),
    statusSummary = updateInfo.getNewValue("StatusSummary")
  ))
}