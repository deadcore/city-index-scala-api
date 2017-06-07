package io.igu.cityindex.market.model

case class MarketSearchRequest(searchByMarketCode: Boolean = true,
                               searchByMarketName: Boolean = true,
                               spreadProductType: Boolean = true,
                               cfdProductType: Boolean,
                               binaryProductType: Boolean,
                               includeOptions: Boolean,
                               query: String,
                               tradingAccountId: Option[Int] = None,
                               maxResults: Int = 200,
                               useMobileShortName: Boolean = false)