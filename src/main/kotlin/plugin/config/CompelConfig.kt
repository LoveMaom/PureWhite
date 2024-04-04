package com.purewhite.plugin.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object CompelConfig: AutoSavePluginConfig("CompelConfig") {
    @ValueDescription("强上冷却时间 单位分钟")
    var compelCD by value<Int>(10)
    @ValueDescription("回复时间倒计时 1000=1秒")
    var compelReply by value<Long>(12000)
    @ValueDescription("强上状态")
    var compel: MutableMap<Long, Long> by value(mutableMapOf())
}