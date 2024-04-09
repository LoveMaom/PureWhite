package com.purewhite.plugin.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object EntertainmentConfig: AutoSavePluginConfig("EntertainmentConfig") {

    @ValueDescription("群发每日新闻 true=开 false=关 需要关闭机器人后修改")
    var newsEnable by value(true)
    @ValueDescription("每日新闻发送时间 0~23点")
    var newsHour by value(8)
    @ValueDescription("每日新闻发送时间 0~59点")
    var newsMinute by value(0)

    @ValueDescription("以下功能黑名单")
    var enable: MutableList<Long> by value(mutableListOf())

    @ValueDescription("给我取名功能别名")
    var nameCommand: MutableList<String> by value(mutableListOf(
        "给我取名"
    ))
    @ValueDescription("疯狂星期四功能别名")
    var KFCCommand: MutableList<String> by value(mutableListOf(
        "疯狂星期四"
    ))
    @ValueDescription("每日新闻功能别名")
    var newsCommand: MutableList<String> by value(mutableListOf(
        "每日新闻"
    ))

}