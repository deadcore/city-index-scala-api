package io.igu.cityindex.market.model

case class MarketInformationResponse(MarketInformation: MarketInformation)

case class MarketEod(marketEodUnit: String,
                     marketEodAmount: Double)

case class StartTimeUtc(utcDateTime: String,
                        offsetMinutes: Double)

case class MarketPricingTimes(dayOfWeek: Double,
                              startTimeUtc: StartTimeUtc,
                              endTimeUtc: StartTimeUtc)

case class MarketSpreads(spreadTimeUtc: String,
                         spread: Double,
                         spreadUnits: Double)

case class Bands(lowerBound: Double,
                 marginFactor: Double)

case class StepMargin(
                       eligibleForStepMargin: Boolean,
                       stepMarginConfigured: Boolean,
                       inheritedFromParentAccountOperator: Boolean,
                       bands: List[Bands]
                     )

case class MarketInformation(marketId: Double,
                             name: String,
                             exchangeId: Double,
                             exchangeName: String,
                             marginFactor: Double,
                             minMarginFactor: String,
                             maxMarginFactor: String,
                             clientMarginFactor: String,
                             marginFactorUnits: Double,
                             minDistance: Double,
                             minDistanceUnits: Double,
                             webMinSize: Double,
                             maxSize: Double,
                             marketSizesCurrencyCode: String,
                             maxLongSize: Double,
                             maxShortSize: Double,
                             market24H: Boolean,
                             priceDecimalPlaces: Double,
                             defaultQuoteLength: Double,
                             tradeOnWeb: Boolean,
                             limitUp: Boolean,
                             limitDown: Boolean,
                             longPositionOnly: Boolean,
                             closeOnly: Boolean,
                             marketEod: List[MarketEod],
                             priceTolerance: Double,
                             convertPriceToPipsMultiplier: Double,
                             marketSettingsTypeId: Double,
                             marketSettingsType: String,
                             mobileShortName: String,
                             centralClearingType: String,
                             centralClearingTypeDescription: String,
                             marketCurrencyId: Double,
                             phoneMinSize: Double,
                             dailyFinancingAppliedAtUtc: String,
                             nextMarketEodTimeUtc: String,
                             tradingStartTimeUtc: String,
                             tradingEndTimeUtc: String,
                             marketPricingTimes: List[MarketPricingTimes],
                             marketBreakTimes: List[_], //TODO build the DTO
                             marketSpreads: List[MarketSpreads],
                             guaranteedOrderPremium: Double,
                             guaranteedOrderPremiumUnits: Double,
                             guaranteedOrderMinDistance: Double,
                             guaranteedOrderMinDistanceUnits: Double,
                             priceToleranceUnits: Double,
                             marketTimeZoneOffsetMinutes: Double,
                             quantityConversionFactor: Double,
                             pointFactorDivisor: Double,
                             betPer: Double,
                             marketUnderlyingTypeId: Double,
                             marketUnderlyingType: String,
                             allowGuaranteedOrders: Boolean,
                             ordersAwareMargining: Boolean,
                             ordersAwareMarginingMinimum: Double,
                             commissionChargeMinimum: Double,
                             commissionRate: Double,
                             commissionRateUnits: Double,
                             expiryUtc: String,
                             futureRolloverUTC: String,
                             allowRollover: Boolean,
                             expiryBasisId: Double,
                             expiryBasisText: String,
                             stepMargin: StepMargin,
                             optionTypeId: String,
                             optionType: String,
                             strikePrice: String,
                             marketTypeId: Double,
                             marketType: String,
                             weighting: Double,
                             fxFinancing: String,
                             underlyingRicCode: String,
                             newsUnderlyingOverrideType: String,
                             newsUnderlyingOverrideCode: String
                            )
