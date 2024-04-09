package com.purewhite.plugin.config

import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object RankListConfig : AutoSavePluginConfig("RankListConfig") {
    @ValueDescription("被添加的群聊禁止使用该功能")
    var enable: MutableList<Long> by value(mutableListOf())

    @ValueDescription("被草排行榜指令")
    var rankListCommand: MutableList<String> by value(mutableListOf(
        "被草排行榜",
        "被撅排行榜"
    ))
    @ValueDescription("本群被草排行榜")
    var fuckRankList: MutableMap<Long, MutableMap<Long ,Long>> by value(mutableMapOf())
}