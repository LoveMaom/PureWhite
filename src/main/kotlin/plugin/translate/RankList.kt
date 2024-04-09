package com.purewhite.plugin.translate

import com.purewhite.plugin.common.GroupGet
import com.purewhite.plugin.common.TheTime
import com.purewhite.plugin.config.RankListConfig.fuckRankList
import com.purewhite.plugin.config.RankListConfig.rankListCommand
import net.mamoe.mirai.contact.AvatarSpec
import net.mamoe.mirai.contact.NormalMember
import net.mamoe.mirai.contact.getMemberOrFail
import net.mamoe.mirai.contact.nameCardOrNick
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.*
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage


object RankList {
    suspend fun main(event: GroupMessageEvent) {
        if (rankListCommand.contains(event.message.content)) {

            if (fuckRankList[event.group.id] != null && fuckRankList[event.group.id]!!.isNotEmpty()) {

                // 从大到小排序 被草次数
                val list = mutableMapOf<Long,Long>()
                fuckRankList[event.group.id]!!.toList().sortedByDescending { it.second }.forEach {
                    list[it.first] = it.second
                }
                val message = MessageChainBuilder()
                val nodeList = mutableListOf<ForwardMessage.Node>()

                val arr = mutableListOf<String>()

                if (list.isNotEmpty()) {
                    val members = mutableListOf<NormalMember>()
                    list.forEach {
                        members.add(event.group.getMemberOrFail(it.key))
                    }
                    var setMessage : ForwardMessage.Node
                    for (i in 0 until 10) {
                        if (list.size < i + 1) break
                        message.append( buildMessageChain{
                            +PlainText("第${i + 1}名")
                            +GroupGet.imageGroupFriend(members[i].avatarUrl(AvatarSpec.LARGE)).uploadAsImage(event.group)
                            +PlainText("\nQQ号: ${members[i].id}")
                            +PlainText("\n群名称: ${members[i].nameCardOrNick}")
                            +PlainText("\n被草${list[members[i].id]}次")
                        })
                        setMessage = ForwardMessage.Node(event.bot.id, TheTime.main().toInt(), event.bot.nameCardOrNick, message.build())
                        nodeList.add(setMessage)
                        message.clear()
                    }
                    arr.add("RBQ第一人: ${members[0].nameCardOrNick}")
                    arr.add("被草${list[members[0].id]}次")
                    event.group.sendMessage(ForwardMessage(
                        arr.toList(),
                        "肉便器榜单",
                        "[聊天记录]",
                        "聊天记录",
                        "点击查看榜单",
                        nodeList
                    ))
                }
            }else {
                event.group.sendMessage("本群未有人被草呢~")
            }
        }
    }

}