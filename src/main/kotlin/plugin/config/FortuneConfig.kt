package com.purewhite.plugin.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object FortuneConfig : AutoSavePluginConfig("FortuneConfig") {
    @ValueDescription("今日运势指令")
    var fortuneCommand by value(mutableListOf(
        "今日运势",
        "今天运气"
    ))
    @ValueDescription("生肖绑定")
    var fortuneZodiac: MutableMap<Long,String> by value(mutableMapOf())
}