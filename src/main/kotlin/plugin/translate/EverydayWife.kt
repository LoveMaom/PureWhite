package com.purewhite.plugin.translate

import com.purewhite.plugin.common.GroupGet
import com.purewhite.plugin.common.TheTime
import com.purewhite.plugin.config.EverydayWifeConfig.everydayCommand
import com.purewhite.plugin.config.EverydayWifeConfig.everydayWife
import com.purewhite.plugin.config.EverydayWifeConfig.everydayWifeMember
import net.mamoe.mirai.contact.*
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.*

object EverydayWife {
    suspend fun main(event: GroupMessageEvent) {
        if (everydayCommand.contains(event.message.content)) {
            val message = MessageChainBuilder()
            if (everydayWife[event.sender.id] == null || TheTime.main() >= everydayWife[event.sender.id]!!) {
                val rank = mutableListOf<NormalMember>()
                val members = event.group.members
                for (i in members.indices) {
                    val randomMember = members.random()
                    if (members.size > 30) {
                        if (rank.size > 20) break
                    }
                    else if (members.size > 20) {
                        if (rank.size > 10) break
                    }
                    else if (members.size <= 10){
                        if (rank.size >= 1) break
                    }
                    when (randomMember.active.rank) {
                        6 -> rank.add(randomMember)
                        5 -> rank.add(randomMember)
                        4 -> rank.add(randomMember)
                        3 -> rank.add(randomMember)
                        2 -> rank.add(randomMember)
                        1 -> rank.add(randomMember)
                    }
                }
                val wife = rank.random()
                message.add(
                    At(event.sender)
                            +"\n恭喜你，你今日的老婆是: \n${wife.nameCardOrNick}(${wife.id})"
                            +"\n————————"
                )
                val image = GroupGet.imageGroupFriend(wife.avatarUrl(AvatarSpec.LARGE))
                everydayWifeMember[event.sender.id] = wife.id
                GroupGet.download(event,wife.avatarUrl,event.group,message,image,"每日老婆")
            } else {
                val groupNum = GroupGet.checkGroup(event, everydayWifeMember[event.sender.id]!!)
                if (groupNum == 0L) {
                    event.group.sendMessage("无法获取你今天的老婆，原因：你的老婆已经退出你老婆原本所在的群聊")
                    return
                }
                val member = event.bot.getGroupOrFail(groupNum).getMemberOrFail(everydayWifeMember[event.sender.id]!!)
                message.add(
                    At(event.sender) +
                            "\n你已经有老婆了" +
                            "\n今日的老婆是: \n${member.nameCardOrNick}(${member.id})" +
                            "\n————————"
                )
                val image = GroupGet.imageGroupFriend(member.avatarUrl(AvatarSpec.LARGE))
                GroupGet.download(event, member.avatarUrl, event.group, message, image, "每日老婆")
                return
            }
         }

    }
}