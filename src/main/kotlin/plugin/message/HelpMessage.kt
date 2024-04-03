package com.purewhite.plugin.message

import com.purewhite.plugin.config.CommandConfig.help
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.content

object HelpMessage {
    suspend fun main(event: GroupMessageEvent) {
        if (help.contains(event.message.content)) {
            event.group.sendMessage(
                "指令列表:" +
                        "\n草群友" +
                        "\n草管理" +
                        "\n草群主" +
                        "\n每日老婆"
            )
        }
    }
}