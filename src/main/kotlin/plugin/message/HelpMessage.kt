package com.purewhite.plugin.message

import com.purewhite.plugin.common.TheTime
import com.purewhite.plugin.config.CommandConfig.help
import com.purewhite.plugin.config.CompelConfig.compelCD
import com.purewhite.plugin.config.CompelConfig.compelCommand
import com.purewhite.plugin.config.EverydayWifeConfig.everydayCommand
import com.purewhite.plugin.config.FortuneConfig.fortuneCommand
import com.purewhite.plugin.config.FuckAdminConfig.adminCD
import com.purewhite.plugin.config.FuckAdminConfig.adminCommand
import com.purewhite.plugin.config.FuckManagementConfig.managementCD
import com.purewhite.plugin.config.FuckManagementConfig.managementCommand
import com.purewhite.plugin.config.FuckMemberConfig.memberCD
import com.purewhite.plugin.config.FuckMemberConfig.memberCommand
import com.purewhite.plugin.config.RankListConfig.rankListCommand
import net.mamoe.mirai.contact.nameCardOrNick
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.ForwardMessage
import net.mamoe.mirai.message.data.MessageChainBuilder
import net.mamoe.mirai.message.data.content

object HelpMessage {
    suspend fun main(event: GroupMessageEvent) {
        if (help.contains(event.message.content)) {
            val message = MessageChainBuilder()
            val nodeList = mutableListOf<ForwardMessage.Node>()

            val head = mutableListOf(
                "我怀了你的孩子!",
                "你的背叛让我心如刀绞!",
                "我的心碎成千万片",
                "被你抛弃后",
                "我宁愿面对残酷的事实",
                "我曾经以为我们之间的爱可以战胜一切"
            )

            val foot = mutableListOf(
                "为什么抛弃我?",
                "我真的无法再信任你了",
                "你却毫不在意地抛弃了我",
                "我觉得自己变得一无所有",
                "也不想再被你抛弃的感觉伤害",
                "却没想到最终还是被你抛弃"
            )

            val random = head.indices.random()

            val arr = mutableListOf<String>()
            arr.add(event.sender.nameCardOrNick + ": ${head[random]}")
            arr.add(event.sender.nameCardOrNick + ": ${foot[random]}")

            message.append(
                "$memberCommand" +
                    "\n冷却时间:${memberCD}分钟" +
                    "\n草一下也没什么事吧？ 0.o"
            )

            var setMessage = ForwardMessage.Node(event.bot.id, TheTime.main().toInt(), event.bot.nameCardOrNick, message.build())
            nodeList.add(setMessage)
            message.clear()

            message.append(
                "$managementCommand" +
                        "\n冷却时间:${managementCD}分钟" +
                        "\n管理来了也得挨草"
            )

            setMessage = ForwardMessage.Node(event.bot.id, TheTime.main().toInt(), event.bot.nameCardOrNick, message.build())
            nodeList.add(setMessage)
            message.clear()

            message.append(
                "$adminCommand" +
                        "\n冷却时间:${adminCD}分钟" +
                        "\n狂草群主 有选项哦"
            )

            setMessage = ForwardMessage.Node(event.bot.id, TheTime.main().toInt(), event.bot.nameCardOrNick, message.build())
            nodeList.add(setMessage)
            message.clear()

            message.append(
                "$everydayCommand" +
                        "\n冷却时间:每天24点" +
                        "\n随机选择一个老婆"
            )

            setMessage = ForwardMessage.Node(event.bot.id, TheTime.main().toInt(), event.bot.nameCardOrNick, message.build())
            nodeList.add(setMessage)
            message.clear()

            message.append(
                "$compelCommand" +
                        "\n冷却时间:${compelCD}分钟" +
                        "\n@[想要强上的对象]" +
                        "\n例子: ${compelCommand.random()}@群主"
            )

            setMessage = ForwardMessage.Node(event.bot.id, TheTime.main().toInt(), event.bot.nameCardOrNick, message.build())
            nodeList.add(setMessage)
            message.clear()

            message.append(
                "$fortuneCommand" +
                        "\n刷新时间:每天24点" +
                        "\n获取自己生肖的运势" +
                        "\n可以也可以设置自己的生肖" +
                        "\n例子: 设置生肖 猪"
            )

            setMessage = ForwardMessage.Node(event.bot.id, TheTime.main().toInt(), event.bot.nameCardOrNick, message.build())
            nodeList.add(setMessage)
            message.clear()

            message.append(
                "$rankListCommand" +
                        "\n获取当前群聊被草排行榜"
            )

            setMessage = ForwardMessage.Node(event.bot.id, TheTime.main().toInt(), event.bot.nameCardOrNick, message.build())
            nodeList.add(setMessage)
            message.clear()

            message.append(
                "以上所有功能都可以单独开启关闭" +
                        "\n例子: 关闭草群友" +
                        "\n一键关闭所有娱乐功能: 关闭娱乐功能" +
                "\n需要你是机器人主人/管理/群主/群管理"
            )

            setMessage = ForwardMessage.Node(event.bot.id, TheTime.main().toInt(), event.bot.nameCardOrNick, message.build())
            nodeList.add(setMessage)
            message.clear()

            event.group.sendMessage(ForwardMessage(
                arr.toList(),
                "帮助列表",
                "[聊天记录]",
                "聊天记录",
                "点击查看帮助列表",
                nodeList
            ))
        }
    }
}