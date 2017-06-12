package io.igu.cityindex.lightstreamer

import com.lightstreamer.ls_client.{HandyTableListener, UpdateInfo}
import io.igu.cityindex.lightstreamer.HandyTableListenerBuilder.OnUpdate

case class HandyTableListenerBuilder(onUpdateFun: Option[OnUpdate] = None) extends HandyTableListener {

  def onUpdate(t: OnUpdate): HandyTableListenerBuilder = {
    def newFunc(i: Int, s: String, updateInfo: UpdateInfo): Unit = {
      onUpdateFun.foreach(func => func(i, s, updateInfo))
      t(i, s, updateInfo)
    }

    this.copy(
      onUpdateFun = Some(newFunc)
    )
  }

  override def onRawUpdatesLost(i: Int, s: String, i1: Int): Unit = {}

  override def onUnsubscrAll(): Unit = {}

  override def onUnsubscr(i: Int, s: String): Unit = {}

  override def onSnapshotEnd(i: Int, s: String): Unit = {}

  override def onUpdate(i: Int, s: String, updateInfo: UpdateInfo): Unit = onUpdateFun.foreach(func => func(i, s, updateInfo))

}

object HandyTableListenerBuilder {
  type OnUpdate = (Int, String, UpdateInfo) => Unit


  val empty = new HandyTableListenerBuilder
}