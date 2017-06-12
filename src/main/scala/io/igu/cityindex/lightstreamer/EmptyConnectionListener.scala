package io.igu.cityindex.lightstreamer

import com.lightstreamer.ls_client.{ConnectionListener, PushConnException, PushServerException}

object EmptyConnectionListener extends ConnectionListener {
  override def onFailure(e: PushServerException): Unit = {}

  override def onFailure(e: PushConnException): Unit = {}

  override def onActivityWarning(b: Boolean): Unit = {}

  override def onDataError(e: PushServerException): Unit = {}

  override def onSessionStarted(b: Boolean): Unit = {}

  override def onClose(): Unit = {}

  override def onEnd(i: Int): Unit = {}

  override def onConnectionEstablished(): Unit = {}

  override def onNewBytes(l: Long): Unit = {}
}
