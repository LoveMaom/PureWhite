package com.purewhite.plugin.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object EverydayWifeConfig: AutoSavePluginConfig("EverydayWifeConfig") {
    @ValueDescription("每日老婆状态")
    var everydayWife: MutableMap<Long, Long> by value(mutableMapOf())

    @ValueDescription("每日老婆的人选")
    var everydayWifeMember: MutableMap<Long, Long> by value(mutableMapOf())
}