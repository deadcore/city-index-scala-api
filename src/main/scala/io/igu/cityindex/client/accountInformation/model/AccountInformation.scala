package io.igu.cityindex.client.accountInformation.model

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
                                      daysUntilExpiryForDemo: BigDecimal,
                                      legalPartyUniqueReference: Int)

case class TradingAccount(tradingAccountId: Int,
                          tradingAccountCode: String,
                          tradingAccountStatus: String,
                          tradingAccountType: String)

case class AccountHolder(legalPartyId: Int,
                         name: String)
