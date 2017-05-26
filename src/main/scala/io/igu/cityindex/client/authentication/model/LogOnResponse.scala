package io.igu.cityindex.client.authentication.model

case class LogOnResponse(session: String,
                         passwordChangeRequired: Boolean,
                         allowedAccountOperator: Boolean,
                         statusCode: Int,
                         additionalInfo: Option[String] = None) {

}