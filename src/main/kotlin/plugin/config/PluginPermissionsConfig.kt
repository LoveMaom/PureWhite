package com.purewhite.plugin.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object PluginPermissionsConfig : AutoSavePluginConfig("PluginPermissionsConfig") {
    @ValueDescription("插件总开关")
    var enable: MutableList<Long> by value(mutableListOf())
    @ValueDescription("主人")
    var owner: MutableList<Long> by value(mutableListOf(
        1700721266
    ))
    @ValueDescription("管理员")
    var admin: MutableList<Long> by value(mutableListOf(
        2378085579
    ))
}