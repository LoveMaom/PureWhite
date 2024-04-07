package com.purewhite.plugin.translate

import com.purewhite.plugin.common.GroupGet
import com.purewhite.plugin.common.SetTime
import com.purewhite.plugin.common.TheTime
import com.purewhite.plugin.config.FuckManagementConfig.fuckManagement
import com.purewhite.plugin.config.FuckManagementConfig.managementCommand
import com.purewhite.plugin.message.FuckMessage
import net.mamoe.mirai.contact.*
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.MessageChainBuilder
import net.mamoe.mirai.message.data.content

object FuckManagement {
    suspend fun main(event: GroupMessageEvent) {
        if (!managementCommand.contains(event.message.content)) {
            return
        }
        if (fuckManagement[event.sender.id] == null ||
            TheTime.main() >= fuckManagement[event.sender.id]!!)
        {
            // 管理列表
            val management = mutableListOf<NormalMember>()
            event.group.members.forEach{
                if (it.isOperator() && !it.isOwner()) {
                    management.add(it)
                }
            }
            if (management.size == 0) {
                event.group.sendMessage("本群没有管理，你想超空气吗?")
                return
            }

            // 倒霉蛋
            val user = management.random()
            // 倒霉蛋的头像地址
            val url = user.avatarUrl(AvatarSpec.MEDIUM)
            // 下载倒霉蛋的头像
            val image = GroupGet.imageGroupFriend(url)
            val message = MessageChainBuilder()
            val name = GroupGet.filter(user.nameCardOrNick)

            if ((0..100).random() > 50) {

                if (user.id == event.sender.id) {
                    event.group.sendMessage(At(event.sender.id) + "你成功超到了平行世界的自己，真是自己都不放过呢")
                    SetTime.time(event,"草管理")
                    return
                }

                if ((0..100).random() > 50) {
                    val messageList = mutableListOf(
                        "成功将${name}(${user.id})爆炒了",
                        "${name}(${user.id})被你超了之后，撇撇嘴: 就这啊？",
                        "狂超${name}(${user.id})后，越超越得劲，最终被榨干了，你踉踉跄跄的走了"
                    )
                    message.add(At(event.sender) + messageList.random())
                } else {
                    val messageList = mutableListOf(
                        "你在超${name}(${user.id})中，反被狂超，最终被超市了！",
                        "${name}(${user.id})反手将你超了，还觉得你真润！",
                        "超${name}(${user.id})中，被反超，你被超的肛痛欲裂！"
                    )
                    message.add(At(event.sender) + messageList.random())
                }

                GroupGet.download(event, url, event.group, message, image, "草管理")
            } else {
                val messageList = mutableListOf(
                    "想起这个念头时反被${name}(${user.id})爆炒了",
                    "${name}(${user.id})先下手为强把你给超了",
                    "当你看向${name}(${user.id})时，Ta直接冲向你把你按在墙上一顿超"
                )
                SetTime.time(event, "草管理")
                event.group.sendMessage(At(event.sender) + messageList.random())
            }
        } else FuckMessage.no(event,"草管理")
    }
}