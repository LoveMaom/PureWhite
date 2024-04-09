package com.purewhite.plugin.config

import com.purewhite.plugin.config.CompelConfig.provideDelegate
import net.mamoe.mirai.console.data.AutoSavePluginConfig
import net.mamoe.mirai.console.data.ValueDescription
import net.mamoe.mirai.console.data.value

object FuckMemberConfig : AutoSavePluginConfig("FuckMemberConfig") {
    @ValueDescription("被添加的群聊禁止使用该功能")
    var enable: MutableList<Long> by value(mutableListOf())

    @ValueDescription("草群友指令")
    var memberCommand by value(mutableListOf(
        "草群友",
        "超群友",
        "操群友"
    ))
    @ValueDescription("true = 本群超 | false = 全群超")
    var FuckEnable by value(true)

    @ValueDescription("草群友冷却时间 单位分钟")
    var memberCD by value<Int>(10)

    @ValueDescription("成功草到本群群友语句%name%=操到成员名字 %memberNumber%=操到成员的QQ")
    var successFuckMember by value(mutableListOf(
        "成功超到了\n本群的%name%(%memberNumber%)"
    ))
    @ValueDescription("成功草到别群群友语句%name%=操到成员名字 %memberNumber%=操到成员的QQ %group%=群聊QQ")
    var successFuckMember2 by value(mutableListOf(
        "成功超到了\n来自群聊(%group%)\n%name%(%memberNumber%)"
    ))
    @ValueDescription("成功草到自己")
    var successFuckMe by value(mutableListOf(
        "你成功超到了平行世界的自己，真是自己都不放过呢"
    ))
    @ValueDescription("被本群成员草提醒 %name%=操到你的成员名字 %memberNumber%=操到你的成员的QQ")
    var byFuckMe by value(mutableListOf(
        "你反被本群的%name%(%memberNumber%)超了"
    ))
    @ValueDescription("被别群成员草提醒 %name%=操到你的成员名字 %memberNumber%=操到你的成员的QQ %group%=群聊QQ")
    var byFuckMe2 by value(mutableListOf(
        "你反被来自群聊%group%的%name%(%memberNumber%)超了"
    ))
    @ValueDescription("被别群成员草提醒 %name%=操到你的成员名字 %memberNumber%=操到你的成员的QQ %group%=群聊QQ")
    var additionalFuck by value(mutableListOf(
        "你被一只飞来的乌鸦误认为是蚯蚓而被叼走了",
        "你在准备实施时被水潭中的自己吓到了导致牛牛一蹶不振",
        "你因为实施计划的时候太大声，惊动了邻居，结果被邻居冲进来一顿活塞运动",
        "你因为追逐%name%(%memberNumber%)时，不小心从高楼坠落身亡，万幸的是牛牛碎了一地",
        "你在追赶%name%(%memberNumber%)时不慎摔倒，牛牛着地断掉了",
        "你在对%name%(%memberNumber%)超的太疯狂，最终精疲力尽跌倒而亡",
        "你在独自探索%name%(%memberNumber%)的黑洞时消失在其中，导致精尽人亡"
    ))

    @ValueDescription("草群友状态")
    var fuck: MutableMap<Long, Long> by value(mutableMapOf())
}