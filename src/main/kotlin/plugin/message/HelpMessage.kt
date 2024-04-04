package com.purewhite.plugin.message

import com.purewhite.plugin.config.CommandConfig.help
import com.purewhite.plugin.config.CompelConfig.compelCD
import com.purewhite.plugin.config.FuckAdminConfig.adminCD
import com.purewhite.plugin.config.FuckManagementConfig.managementCD
import com.purewhite.plugin.config.FuckMemberConfig.memberCD
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.content

object HelpMessage {
    suspend fun main(event: GroupMessageEvent) {
        if (help.contains(event.message.content)) {
            event.group.sendMessage(
                "指令列表:" +
                        "\n草群友(CD=${memberCD}分钟)" +
                        "\n草管理(CD=${managementCD}分钟)" +
                        "\n草群主(CD=${adminCD}分钟)" +
                        "\n每日老婆" +
                        "\n强上@[想要强上的对象](CD=${compelCD}分钟)"
            )
        }
    }
}