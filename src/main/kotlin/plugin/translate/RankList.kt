package com.purewhite.plugin.translate

import com.purewhite.plugin.common.GroupGet
import com.purewhite.plugin.common.TheTime
import com.purewhite.plugin.config.RankListConfig.fuckRankList
import com.purewhite.plugin.config.RankListConfig.rankListCommand
import com.purewhite.plugin.config.RankListConfig.totalRankListCommand
import net.mamoe.mirai.contact.*
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
                        setMessage = ForwardMessage.Node(members[i].id, TheTime.main().toInt(), members[i].nameCardOrNick, message.build())
                        nodeList.add(setMessage)
                        message.clear()
                    }
                    arr.add(members[0].nameCardOrNick)
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
    suspend fun total(event: GroupMessageEvent){
        if (!totalRankListCommand.contains(event.message.content)) return
        if (fuckRankList.isNotEmpty()) {
            // 获取所有群聊ID
            val groups = mutableListOf<Long>()
            // 用来装每个群的群成员并且排序
            val list = mutableMapOf<Member,Long>()
            // 装入每个群聊第一名
            val set = mutableMapOf<Member,Long>()
            // 最终塞入每个群聊第一名的排序
            val members = mutableMapOf<Member,Long>()
            // 塞入所有成员
            val member = mutableListOf<Member>()
            fuckRankList.forEach { groups.add(it.key) }
            for (i in groups.indices) {
                fuckRankList[groups[i]]!!.toList().sortedByDescending { it.second }.forEach {
                    list[event.bot.getGroup(groups[i])!!.getMemberOrFail(it.first)] = it.second
                }
                set[list.toList()[0].first] = list.toList()[0].second
                list.clear()
            }
            set.toList().sortedByDescending { it.second }.forEach {
                members[it.first] = it.second
            }
            members.toList().forEach {
                member.add(it.first)
            }
            val message = MessageChainBuilder()
            val nodeList = mutableListOf<ForwardMessage.Node>()

            val arr = mutableListOf<String>()
            var setMessage : ForwardMessage.Node
            for (i in member.indices) {
                val num = GroupGet.checkGroup(event,members.toList()[i].first.id)
                message.append( buildMessageChain{
                    +PlainText("第${i + 1}名")
                    +GroupGet.imageGroupFriend(member[i].avatarUrl(AvatarSpec.LARGE)).uploadAsImage(event.group)
                    +PlainText("\nQQ号: ${member[i].id}")
                    +PlainText("\n来自: $num")
                    +PlainText("\n群名称: ${member[i].nameCardOrNick}")
                    +PlainText("\n被草${members[member.toList()[i]]}次")
                })
                setMessage = ForwardMessage.Node(member[i].id, TheTime.main().toInt(), member[i].nameCardOrNick, message.build())
                nodeList.add(setMessage)
                message.clear()
            }
            val num = GroupGet.checkGroup(event,members.toList()[0].first.id)
            arr.add(member[0].nameCardOrNick)
            arr.add("被草${fuckRankList[num]!![member.toList()[0].id]}次")
            event.group.sendMessage(ForwardMessage(
                arr.toList(),
                "每个群第一肉便器榜单",
                "[聊天记录]",
                "聊天记录",
                "点击查看榜单",
                nodeList
            ))
        }
    }

}