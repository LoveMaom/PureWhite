package com.purewhite.plugin.config

import com.purewhite.plugin.config.CompelConfig.provideDelegate
import com.purewhite.plugin.config.FuckMemberConfig.provideDelegate
import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object FuckManagementConfig : AutoSavePluginConfig("FuckManagementConfig"){
    @ValueDescription("被添加的群聊禁止使用该功能")
    var enable: MutableList<Long> by value(mutableListOf())

    @ValueDescription("草管理指令")
    var managementCommand by value(mutableListOf(
        "草管理",
        "超管理",
        "操管理"
    ))
    @ValueDescription("草管理冷却时间 单位分钟")
    var managementCD by value<Int>(10)
    @ValueDescription("草管理状态")
    var fuckManagement: MutableMap<Long, Long> by value(mutableMapOf())
}