package com.purewhite.plugin.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object CommandConfig: AutoSavePluginConfig("CommandConfig") {
    @ValueDescription("帮助的指令")
    var help: MutableList<String> by value(mutableListOf(
        "帮助",
        "功能"
    ))
}