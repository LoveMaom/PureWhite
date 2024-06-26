package com.purewhite.plugin.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object MessageConfig: AutoSavePluginConfig("MessageConfig") {
    @ValueDescription("草群友时间未到信息 多个信息代表随机")
    var fuck: MutableList<String> by value(mutableListOf(
        "时间未到呢~还剩下%time分钟才能使用哦",
        "时间没到呢!还剩下%time分钟才能使用哦"
    ))
    var functionNo : MutableList<String> by value(mutableListOf(
        "成功关闭%type%功能咯"
    ))
    var functionYes : MutableList<String> by value(mutableListOf(
        "成功开启%type%功能咯"
    ))
}