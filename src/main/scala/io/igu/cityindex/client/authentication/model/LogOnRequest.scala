package io.igu.cityindex.client.authentication.model

case class LogOnRequest(password: String,
                        appVersion: String,
                        appComments: String,
                        userName: String,
                        appKey: String)