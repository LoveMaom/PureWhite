package com.purewhite.plugin.translate

import com.purewhite.plugin.common.GroupGet
import com.purewhite.plugin.common.SetTime
import com.purewhite.plugin.common.TheTime
import com.purewhite.plugin.config.FuckAdminConfig.fuckAdmin
import com.purewhite.plugin.message.FuckMessage
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import net.mamoe.mirai.contact.*
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.ListeningStatus
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.MessageChainBuilder
import net.mamoe.mirai.message.data.content


object FuckAdmin {
    @OptIn(DelicateCoroutinesApi::class)
    suspend fun main(event: GroupMessageEvent){
        if (event.message.content == "草群主") {
            if (event.sender.permission.isOwner()) {
                event.group.sendMessage("群主发癫想草自己是不可能的哦！")
                return
            }
            if (fuckAdmin[event.sender.id] != null && TheTime.main() < fuckAdmin[event.sender.id]!!) {
                FuckMessage.no(event)
                return
            }

            // 创建用来表达倒霉蛋的语句
            val message = MessageChainBuilder()
            // 获取倒霉蛋
            val admin = event.group.owner
            // 获取倒霉蛋的头像地址
            val url = admin.avatarUrl(AvatarSpec.MEDIUM)
            // 下载倒霉蛋的头像
            val image = GroupGet.imageGroupFriend(url)
            // 当前聊天成员
            val member = event.sender


            val oneMessage = mutableListOf(
                "呀！群主好活泼",
                "嘿！滑溜溜的群主",
                "恩？娇小的群主诶"
            )
            val twoMessage = mutableListOf(
                "群主还在乱跳，看我一只手拿捏住了，接下来你想干什么？",
                "群主真能挣扎呀！两只手擒住群主的腰，那么接下来你要做？",
                "还扑腾呢，直接勒住群主的脖子，接下来看你的了"
            )
            val qunP = mutableListOf(
                "群P",
                "群p",
                "群批"
            )
            val fuck = mutableListOf(
                "拖走爆炒",
                "拖走暴超",
                "拖走爆草"
            )
            val rogue = mutableListOf(
                "当众耍流氓",
                "就地正法",
                "妖孽哪里跑"
            )
            val kill = mutableListOf(
                "在群主对象面前超群主",
                "NTR"
            )
            val num = mutableListOf(
                "1",
                "2",
                "3",
                "4",
                "5"
            )
            event.group.sendMessage(oneMessage.random())
            event.group.sendMessage(twoMessage.random() +
                    "\n请输入" +
                    "\n1.${qunP.random()}" +
                    "\n2.${fuck.random()}" +
                    "\n3.${rogue.random()}" +
                    "\n4.${kill.random()}" +
                    "\n5.饶他一次")

            // 创建监听
            val listener = GlobalEventChannel.subscribe<GroupMessageEvent> {
                // 最终判断开关监听
                var end = false
                // 判断是否是群友本人回复
                if (member == sender && qunP.contains(it.message.content) || fuck.contains(it.message.content) || rogue.contains(it.message.content) || kill.contains(it.message.content) || num.contains(it.message.content)) {
                    if ((0 .. 100).random() < 80) {
                        // 群P内容
                        if (qunP.contains(it.message.content) || "1" == it.message.content) {
                            if ((0 .. 100).random() < 70) {
                                end = true
                                val members = mutableListOf<NormalMember?>()
                                for (i in 0 until 3) {
                                    val randomMember = group.members.random()
                                    if (!members.contains(randomMember)) {
                                        members.add(randomMember)
                                    }
                                }
                                val messageList = mutableListOf(
                                    "对着群主能深入的全部深入了，妈呀太吓人了",
                                    "一人塞肛，一人塞嘴，两人用群主的手",
                                    "四个人对着群主牛奶射击，导致群主浑身上下白茫茫一片"
                                )
                                message.add(At(sender) + "你带着3位小伙伴" +
                                        "\n${members[0]!!.nameCardOrNick}(${members[0]!!.id})" +
                                        "\n${members[1]!!.nameCardOrNick}(${members[1]!!.id})" +
                                        "\n${members[2]!!.nameCardOrNick}(${members[2]!!.id})" +
                                        "\n${messageList.random()}")
                                GroupGet.download(event, url, event.group, message, image, "草群主")
                            } else {
                                end = true
                                val messageList = mutableListOf(
                                    "呀！你们四个人居然没有超过一个群主，群主还在挑衅你: 杂鱼行不行啊？",
                                    "四人精疲力尽，群主还在对你们做活塞运动，真是太逊了",
                                    "咦，这么弱，四个人反被群主超了，哈哈哈"
                                )
                                SetTime.time(event,"草群主")
                                event.group.sendMessage(At(sender) + messageList.random())
                            }
                        }
                        if (fuck.contains(it.message.content) || "2" == it.message.content) {
                            if ((0 .. 100).random() < 70) {
                                end = true
                                val messageList = mutableListOf(
                                    "将群主拖进小巷子中，按在墙上对着就是一顿活塞运动，还来句: 群主你真润，下次我还来",
                                    "将群主抱进自家卧室，按在床上，嘿咻嘿咻的完成了自己的需求并且觉得群主不够润，还打了群主屁股几下",
                                    "将群主拖到天桥底下，任凭群主挣扎，直接对着群主就戳了进去，群主惨叫一声晕死过去，你根本不犹豫接着戳，直到你完成了自己的需求，丢下了群主"
                                )
                                message.add(At(sender) + messageList.random())
                                GroupGet.download(event, url, event.group, message, image, "草群主")
                            } else {
                                end = true
                                val messageList = mutableListOf(
                                    "在你拖群主的过程中，群主直接对你二弟一拳过去，你痛不欲生，还被群主拉走，最终你肛疼欲裂",
                                    "在你要戳群主时，群主一记猴子偷桃，你反被群主勒住然后被活塞运动",
                                    "在你想拖走群主前，不料群主反手一麻袋套你脑门上，对你一顿拳打脚踢"
                                )
                                SetTime.time(event,"草群主")
                                event.group.sendMessage(At(sender) + messageList.random())
                            }
                        }
                        if (rogue.contains(it.message.content)  || "3" == it.message.content) {
                            if ((0 .. 100).random() < 70) {
                                end = true
                                val messageList = mutableListOf(
                                    "你直接裸奔过去将群主抱住，撕开群主丝袜，直接怼了进去，在群主一声声不要中，舒服的一叹",
                                    "你一把群主抱起，边超边给路人看",
                                    "把群主绑在电线杆上，爽完后，群主又被路人爽"
                                )
                                message.add(At(sender) + messageList.random())
                                GroupGet.download(event, url, event.group, message, image, "草群主")
                            } else {
                                end = true
                                val messageList = mutableListOf(
                                    "群主直接将你绑在电线杆上，让一群人将你爆炒而亡",
                                    "群主一巴掌把你拍晕，将你的衣服扒光公主抱给一个黑人，最终你的肛疼欲裂",
                                    "群主把你打晕好心将你捐给哥布林"
                                )
                                SetTime.time(event,"草群主")
                                event.group.sendMessage(At(sender) + messageList.random())
                            }
                        }
                        if (kill.contains(it.message.content)  || "4" == it.message.content) {
                            if ((0 .. 100).random() < 70) {
                                end = true
                                val messageList = mutableListOf(
                                    "你将群主绑好，并用群主手机打开视频电话给群主对象，相机对准群主，最终群主一声声的不要中挂断了视频电话",
                                    "你冲进群主房间，把群主对象绑在椅子上，然后你将被下药的群主抱来，当着对象的面一顿乱超",
                                    "你将群主砍成一节一节对着在群主对象震惊不可思议的眼神中，超了群主的脑袋"
                                )
                                message.add(At(sender) + messageList.random())
                                GroupGet.download(event, url, event.group, message, image, "草群主")
                            } else {
                                end = true
                                val messageList = mutableListOf(
                                    "群主先你一步将你的对象给超了",
                                    "群主把你绑起来当你面超你对象",
                                    "群主将你绑在电线杆上，然后让黑人小哥超市你"
                                )
                                SetTime.time(event,"草群主")
                                event.group.sendMessage(At(sender) + messageList.random())
                            }
                        }
                    } else {
                        end = true
                        messageEnd(sender,event)
                    }
                    if (member == sender && "饶他一次" == it.message.content ||  "5" == it.message.content) {
                        end = true
                        val messageList = mutableListOf(
                            "你行不行啊这都能饶？",
                            "禽兽不如啊你",
                            "你真是个杂鱼呢"
                        )
                        SetTime.time(event,"草群主")
                        event.group.sendMessage(At(sender) + messageList.random())
                    }
                }
                if (end) ListeningStatus.STOPPED else ListeningStatus.LISTENING
            }
            GlobalScope.launch {
                delay(12000)
                if (listener.isActive) {
                    event.group.sendMessage("时间到咯")
                    listener.complete()
                }
            }

        }
    }
    private suspend fun messageEnd(sender: Member, event: GroupMessageEvent) {
        val messageList = mutableListOf(
            "你神色匆匆又鬼鬼祟祟的样子被群主看在眼里，群主直接拿起把你拖进小巷，并且掏出比你还大的大宝贝对你活塞运动",
            "你的眼神中闪烁着一丝狡诈的光芒被群主看到了，群主直接猴子偷桃将你的大宝贝收了",
            "你的行为像是隐藏着什么东西，被群主发现后跟了你一路，最终发现你的目的是Ta，群主等你要关门的时候冲进来，将你爆炒了"
        )
        SetTime.time(event,"草群主")
        event.group.sendMessage(At(sender) + messageList.random())
    }
}