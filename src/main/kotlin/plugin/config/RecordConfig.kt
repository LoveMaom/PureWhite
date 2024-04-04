package com.purewhite.plugin.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object RecordConfig: AutoSavePluginConfig("RecordConfig") {
    @ValueDescription("记录的近期活跃成员")
    var recordSet: MutableMap<Long, MutableList<Long>> by value(mutableMapOf())
}