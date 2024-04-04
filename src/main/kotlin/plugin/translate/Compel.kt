package com.purewhite.plugin.translate

import com.purewhite.plugin.common.GroupGet
import com.purewhite.plugin.common.SetTime
import com.purewhite.plugin.common.TheTime
import com.purewhite.plugin.config.CompelConfig.compel
import com.purewhite.plugin.config.CompelConfig.compelReply
import com.purewhite.plugin.config.RecordConfig.recordSet
import com.purewhite.plugin.message.FuckMessage
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.mamoe.mirai.contact.AvatarSpec
import net.mamoe.mirai.contact.NormalMember
import net.mamoe.mirai.contact.getMemberOrFail
import net.mamoe.mirai.contact.nameCardOrNick
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.ListeningStatus
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.MessageChainBuilder
import net.mamoe.mirai.message.data.content

object Compel {
    @OptIn(DelicateCoroutinesApi::class)
    suspend fun main(event: GroupMessageEvent) {
        if (event.message.content.contains("强上") && event.message.any { it is At }) {
            if (compel[event.sender.id] == null || TheTime.main() >= compel[event.sender.id]!!) {
                // 获取被强上的对象
                val member = event.group.getMemberOrFail(GroupGet.atNum(event.message.content))
                val oneSender = event.sender
                val name = GroupGet.filter(member.nameCardOrNick)
                if ((0..100).random() > 30) {
                    event.group.sendMessage(
                        At(event.sender) +
                                "\n你尾随成功" +
                                "\n接下来你该如何呢？" +
                                "\n1.当场弓虽女干" +
                                "\n2.把路过的都超飞" +
                                "\n3.猎奇" +
                                "\n4.饶他一次"
                    )
                    val listener = GlobalEventChannel.subscribe<GroupMessageEvent> {
                        var boolean = false
                        if (sender == oneSender) {
                            val message = MessageChainBuilder()
                            val url = member.avatarUrl(AvatarSpec.LARGE)
                            val image = GroupGet.imageGroupFriend(url)
                            when (it.message.content) {
                                "1" -> {
                                    when ((0..100).random()) {
                                        in 50..100 -> {
                                            val messageList = mutableListOf(
                                                "你冲上去，撕光${name}(${member.id})的衣服，在路人震惊的目光中暴超了Ta，你直接欢呼:老子终于超市你这个小烧货了",
                                                "你扒开衣服，从${name}(${member.id})的背后冲了过去，撕开丝袜对着门路就怼了进去，直接强行活塞运动，在${name}(${member.id})痛苦嚎叫中结束了你的活塞运动",
                                                "你扑向${name}${member.id}在警察赶到前，能做的全部做了个遍，大呼：这波不亏"
                                            )
                                            message.add(messageList.random())
                                            event.group.sendMessage(At(sender) + messageList.random())
                                            GroupGet.download(event, url, event.group, message, image, "强上")
                                        }

                                        else -> {
                                            val messageList = mutableListOf(
                                                "你冲上去，结果被${name}(${member.id})一巴掌打晕，把你抱起进入小巷中一顿乱超",
                                                "${name}(${member.id})反手将你抱起，在你震惊的注视下，Ta的牛牛直接捅破了你的衣物，直冲云霄，你只觉得一阵舒爽，然后倒地不起",
                                                "${name}${member.id}一JIO把你的牛牛踹断了"
                                            )
                                            message.add(messageList.random())
                                            event.group.sendMessage(At(sender) + messageList.random())
                                            GroupGet.download(event, url, event.group, message, image, "强上")
                                        }
                                    }
                                    boolean = true
                                }

                                "2" -> {
                                    val members = mutableListOf<NormalMember>()
                                    val random = (0..100).random()
                                    if (recordSet[event.group.id] == null || recordSet[event.group.id]!!.size < 5) {
                                        for (i in 0 until 5) {
                                            val randomMember = event.group.members.random()
                                            if (!members.contains(randomMember)) {
                                                members.add(event.group.members.random())
                                            }
                                        }
                                    }
                                    if (recordSet[event.group.id]!!.size >= 5) {
                                        for (i in 0 until 5) {
                                            members.add(event.group.getMemberOrFail(recordSet[event.group.id]!![i]))
                                        }
                                    }
                                    if (random > 80) {
                                        group.sendMessage("你在想着超路人时被${members[0].nameCardOrNick}(${members[0].id})、${members[1].nameCardOrNick}(${members[1].id})和${members[2].nameCardOrNick}(${members[2].id})超了")
                                    }
                                    if (random > 50) message.add(At(sender) + "${members[0].nameCardOrNick}(${members[0].id})、${members[1].nameCardOrNick}(${members[1].id})和${members[2].nameCardOrNick}(${members[2].id})正在路上走着撞见你正在超${member.nameCardOrNick}(${member.id})，结果被你一起超了")
                                    else if (random > 10) message.add(At(sender) + "${members[0].nameCardOrNick}(${members[0].id})、${members[1].nameCardOrNick}(${members[1].id})、${members[2].nameCardOrNick}(${members[2].id})和${members[3].nameCardOrNick}(${members[3].id})正在路上走着撞见你正在超${member.nameCardOrNick}(${member.id})，结果被你一起超了")
                                    else message.add(At(sender) + "${members[0].nameCardOrNick}(${members[0].id})、${members[1].nameCardOrNick}(${members[1].id})、${members[2].nameCardOrNick}(${members[2].id})、${members[3].nameCardOrNick}(${members[3].id})和${members[4].nameCardOrNick}(${members[4].id})正在路上走着撞见你正在超${member.nameCardOrNick}(${member.id})，结果被你一起超了")

                                    boolean = true
                                    GroupGet.download(event, url, event.group, message, image, "强上")
                                }

                                "3" -> {
                                    val messageList = mutableListOf(
                                        "你阴恻恻的笑着，拿起大砍刀，把${member.nameCardOrNick}(${member.id})的头砍了下来，带回去四面八方的都糟蹋了一遍",
                                        "你把${member.nameCardOrNick}(${member.id})四肢砍断，并阴笑说着：你跑不掉了哦！",
                                        "你把${member.nameCardOrNick}(${member.id})打晕，拦腰砍断，对着Ta的下体一顿输出"
                                    )
                                    message.add(At(sender) + messageList.random())
                                    boolean = true
                                    GroupGet.download(event, url, event.group, message, image, "强上")
                                }

                                "4" -> {
                                    val messageList = mutableListOf(
                                        "恁真是个废物捏",
                                        "你真是杂鱼",
                                        "这都饶，你阳痿吗？"
                                    )
                                    SetTime.time(event,"强上")
                                    event.group.sendMessage(At(sender) + messageList.random())
                                }
                            }
                        }
                        if (boolean) ListeningStatus.STOPPED else ListeningStatus.LISTENING
                    }
                    GlobalScope.launch {
                        delay(compelReply)
                        if (listener.isActive) {
                            event.group.sendMessage("时间到咯")
                            listener.complete()
                        }
                    }
                } else {
                    val messageList = mutableListOf(
                        "在尾随途中被${member.nameCardOrNick}(${member.id})抓住并一顿暴超",
                        "你被${member.nameCardOrNick}(${member.id})发现了，并像痴汉一样冲向你，把你按在地上一顿超",
                        "${member.nameCardOrNick}(${member.id})发现了正在跟踪的你，用计将你和老婆婆水乳交融在一起了"
                    )
                    event.group.sendMessage(At(event.sender) + messageList.random())
                    SetTime.time(event,"强上")
                }
            } else FuckMessage.no(event,"强上")
        }
    }
}