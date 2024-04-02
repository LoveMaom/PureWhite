package com.purewhite.plugin.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object FuckManagementConfig : AutoSavePluginConfig("FuckManagementConfig"){
    @ValueDescription("草管理冷却时间 单位分钟")
    var managementCD by value<Int>(10)
    @ValueDescription("草管理状态")
    var fuckManagement: MutableMap<Long, MutableMap<Long, Long>> by value(mutableMapOf())
}