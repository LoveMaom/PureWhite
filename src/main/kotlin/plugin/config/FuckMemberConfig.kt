package com.purewhite.plugin.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object FuckMemberConfig : AutoSavePluginConfig("FuckMemberConfig") {
    @ValueDescription("草群友冷却时间 单位分钟")
    var memberCD by value<Int>(10)
    @ValueDescription("草群友状态")
    var fuck: MutableMap<Long, MutableMap<Long, Long>> by value(mutableMapOf())
}