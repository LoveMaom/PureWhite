package com.purewhite.plugin.config

import com.purewhite.plugin.config.CompelConfig.provideDelegate
import com.purewhite.plugin.config.FuckMemberConfig.provideDelegate
import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object FuckAdminConfig : AutoSavePluginConfig("FuckAdminConfig") {
    @ValueDescription("被添加的群聊禁止使用该功能")
    var enable: MutableList<Long> by value(mutableListOf())

    @ValueDescription("草群主指令")
    var adminCommand by value(mutableListOf(
        "草群主",
        "超群主",
        "操群主"
    ))
    @ValueDescription("草群主冷却时间 单位分钟")
    var adminCD by value<Int>(10)
    @ValueDescription("回复时间倒计时 1000=1秒")
    var adminReply by value<Long>(12000)
    @ValueDescription("草群主状态")
    var fuckAdmin: MutableMap<Long, Long> by value(mutableMapOf())
}