package io.igu.cityindex.accountInformation.model

case class AccountInformationResponse(logonUserName: String,
                                      clientAccountId: Int,
                                      cultureId: Int,
                                      clientAccountCurrency: String,
                                      accountOperatorId: Int,
                                      tradingAccounts: Seq[TradingAccount],
                                      personalEmailAddress: String,
                                      hasMultipleEmailAddresses: Boolean,
                                      accountHolders: Seq[AccountHolder],
                                      clientTypeId: Int,
                                      linkedClientAccounts: Seq[_],
                                      isNfaEnabledClient: Boolean,
                                      isFifo: Option[String],
                                      daysUntilExpiryForDemo: BigDecimal)

case class TradingAccount(tradingAccountId: Int,
                          tradingAccountCode: String,
                          tradingAccountStatus: String,
                          tradingAccountType: String)

case class AccountHolder(legalPartyId: Int,
                         name: String)
