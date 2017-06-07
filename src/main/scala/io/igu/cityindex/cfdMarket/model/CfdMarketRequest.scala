package io.igu.cityindex.cfdMarket.model

case class CfdMarketRequest(marketName: String,
                            marketCode: String,
                            clientAccountId: String,
                            maxResults: Int,
                            useMobileShortName: Boolean,
                            includeOptions: Boolean,
                            tradingAccountId: Int)