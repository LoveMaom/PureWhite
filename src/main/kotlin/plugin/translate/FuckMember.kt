package com.purewhite.plugin.translate

import com.purewhite.plugin.common.GroupGet
import com.purewhite.plugin.common.GroupGet.checkGroup
import com.purewhite.plugin.common.GroupGet.download
import com.purewhite.plugin.common.GroupGet.groupList
import com.purewhite.plugin.common.GroupGet.imageGroupFriend
import com.purewhite.plugin.common.SetTime
import com.purewhite.plugin.common.TheTime
import com.purewhite.plugin.config.FuckMemberConfig.FuckEnable
import com.purewhite.plugin.config.FuckMemberConfig.additionalFuck
import com.purewhite.plugin.config.FuckMemberConfig.byFuckMe
import com.purewhite.plugin.config.FuckMemberConfig.byFuckMe2
import com.purewhite.plugin.config.FuckMemberConfig.fuck
import com.purewhite.plugin.config.FuckMemberConfig.memberCommand
import com.purewhite.plugin.config.FuckMemberConfig.successFuckMe
import com.purewhite.plugin.config.FuckMemberConfig.successFuckMember
import com.purewhite.plugin.config.FuckMemberConfig.successFuckMember2
import com.purewhite.plugin.config.RankListConfig.fuckRankList
import com.purewhite.plugin.config.RecordConfig.recordSet
import com.purewhite.plugin.message.FuckMessage
import net.mamoe.mirai.contact.AvatarSpec
import net.mamoe.mirai.contact.getMember
import net.mamoe.mirai.contact.nameCardOrNick
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.MessageChainBuilder
import net.mamoe.mirai.message.data.content

object FuckMember {
    suspend fun main(event: GroupMessageEvent) {
        if (memberCommand.contains(event.message.content)) {
            if (fuck[event.sender.id] == null || TheTime.main() >= fuck[event.sender.id]!!) {
                // 获取倒霉蛋的qq号
                val memberNumber = if (FuckEnable) {
                    if (recordSet[event.group.id] != null) recordSet[event.group.id]!!.random()
                    else event.group.members.random().id
                } else groupList(event).random()

                // 倒霉蛋的user
                val memberInfo = event.bot.getGroup(checkGroup(event, memberNumber))!!.getMember(memberNumber)!!
                // 获取倒霉蛋的头像地址
                val url = event.bot.getGroup(checkGroup(event, memberNumber))!!.members[memberNumber]!!.avatarUrl(AvatarSpec.MEDIUM)
                // 创建用来表达倒霉蛋的语句
                val message = MessageChainBuilder()
                // 下载倒霉蛋的头像
                val image = imageGroupFriend(url)
                val name = GroupGet.filter(memberInfo.nameCardOrNick)
                if (fuckRankList[event.group.id] == null) fuckRankList[event.group.id] = mutableMapOf()
                if ((0..100).random() > 40) {
                    // 成功超到提醒
                    if (checkGroup(event, memberNumber) == event.group.id)
                        message.add(At(event.sender) + successFuckMember.random()
                            .replace("%name%", name)
                            .replace("%memberNumber%","$memberNumber"))
                    else message.add(At(event.sender) + successFuckMember2.random()
                        .replace("%name%", name)
                        .replace("%memberNumber%","$memberNumber")
                        .replace("%group%","${checkGroup(event, memberNumber)}")
                    )
                    if (memberNumber == event.sender.id) {
                        message.clear()
                        message.add(At(event.sender) + successFuckMe.random())
                    }
                    if (fuckRankList[event.group.id]!![memberInfo.id] == null) fuckRankList[event.group.id]!![memberInfo.id] = 0

                    // 判断是否是本群人
                    if (checkGroup(event, memberNumber) != event.group.id)
                        fuckRankList[checkGroup(event, memberNumber)]!![memberInfo.id] = fuckRankList[checkGroup(event, memberNumber)]!![memberInfo.id]!! + 1
                    else
                        fuckRankList[event.group.id]!![memberInfo.id] = fuckRankList[event.group.id]!![memberInfo.id]!! + 1

                    download(event, url, event.group, message, image, "草群友")
                } else {
                    val randomNumber = (0..100).random()
                    if (randomNumber > 50) {
                        // 北朝提醒
                        if (checkGroup(event, memberNumber) == event.group.id)
                            message.add(At(event.sender.id) + byFuckMe.random()
                                .replace("%name%",name)
                                .replace("%memberNumber%",memberNumber.toString()))
                        else message.add(At(event.sender.id) + byFuckMe2.random()
                            .replace("%group%",checkGroup(event, memberNumber).toString())
                            .replace("%name%",name)
                            .replace("%memberNumber%",memberNumber.toString()))
                        download(event, url, event.group, message, image, "草群友")
                        if (fuckRankList[event.group.id]!![event.sender.id] == null) fuckRankList[event.group.id]!![event.sender.id] = 0

                        fuckRankList[event.group.id]!![event.sender.id] = fuckRankList[event.group.id]!![event.sender.id]!! + 1

                        return
                    } else {
                        // 稀奇古怪使用方法
                        SetTime.time(event, "草群友")
                        message.add(At(event.sender.id) + additionalFuck.random()
                            .replace("%name%",name)
                            .replace("%memberNumber%",memberNumber.toString()))
                        event.group.sendMessage(message.build())
                        return
                    }
                }
            } else FuckMessage.no(event,"草群友")
        }

    }
}