package com.purewhite.plugin.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object MessageConfig: AutoSavePluginConfig("MessageConfig") {
    @ValueDescription("草群友时间未到信息 多个信息代表随机")
    var fuck: MutableList<String> by value(mutableListOf(
        "草人时间未到呢~",
        "别草了,时间没到呢"
    ))
}