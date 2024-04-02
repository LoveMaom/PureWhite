package com.purewhite.plugin.translate

import com.purewhite.plugin.common.GroupGet
import com.purewhite.plugin.common.GroupGet.checkGroup
import com.purewhite.plugin.common.GroupGet.download
import com.purewhite.plugin.common.GroupGet.groupList
import com.purewhite.plugin.common.GroupGet.imageGroupFriend
import com.purewhite.plugin.common.SetTime
import com.purewhite.plugin.common.TheTime
import com.purewhite.plugin.config.FuckMemberConfig.fuck
import com.purewhite.plugin.message.FuckMessage
import net.mamoe.mirai.contact.AvatarSpec
import net.mamoe.mirai.contact.getMember
import net.mamoe.mirai.contact.nameCardOrNick
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.MessageChainBuilder
import net.mamoe.mirai.message.data.content

object FuckMember {
    suspend fun main(event: GroupMessageEvent){
        if (event.message.content == "草群友") {
            if (fuck[event.group.id] == null) fuck[event.group.id] = mutableMapOf()
            if (fuck[event.group.id]!![event.sender.id] != null && TheTime.main() < fuck[event.group.id]!![event.sender.id]!!) {
                FuckMessage.no(event)
                return
            }

            // 获取倒霉蛋的qq号
            var memberNumber = groupList(event).random()
            // 倒霉蛋的user
            var memberInfo = event.bot.getGroup(checkGroup(event,memberNumber))!!.getMember(memberNumber)!!
            // 获取倒霉蛋的头像地址
            var url = event.bot.getGroup(checkGroup(event,memberNumber))!!.members[memberNumber]!!.avatarUrl(AvatarSpec.MEDIUM)
            // 创建用来表达倒霉蛋的语句
            val message = MessageChainBuilder()
            // 下载倒霉蛋的头像
            var image = imageGroupFriend(url)
            var name = GroupGet.filter(memberInfo.nameCardOrNick)

            if ((0..100).random() > 50) {
                // 成功超到提醒
                if (checkGroup(event,memberNumber) == event.group.id)
                    message.add(At(event.sender.id) + "成功超到了\n本群的${name}(${memberNumber})")
                else message.add(At(event.sender.id) + "成功超到了\n来自群聊(${checkGroup(event,memberNumber)})\n${name}(${memberNumber})")

                if (memberNumber == event.sender.id) {
                    message.clear()
                    message.add(At(event.sender.id) + "你成功超到了平行世界的自己，真是自己都不放过呢")
                }

                download(event, url, event.group, message, image, "草群友")
            } else {
                val randomNumber = (0..100).random()
                if (randomNumber > 50) {
                    // 北朝提醒
                    if (checkGroup(event,memberNumber) == event.group.id)
                        message.add(At(event.sender.id) + "你反被本群的${name}(${memberNumber})超了")
                    else message.add(At(event.sender.id) + "你反被来自群聊(${checkGroup(event,memberNumber)})${name}(${memberNumber})超了")

                    download(event, url, event.group, message, image, "草群友")
                    return
                }
                if (randomNumber < 40) {
                    // 稀奇古怪使用方法
                    val messageList : MutableList<String> = mutableListOf(
                        "你被一只飞来的乌鸦误认为是蚯蚓而被叼走了",
                        "你在准备实施时被水潭中的自己吓到了导致牛牛一蹶不振",
                        "你因为实施计划的时候太大声，惊动了邻居，结果被邻居冲进来一顿活塞运动",
                        "你因为追逐${name}(${memberNumber})时，不小心从高楼坠落身亡，万幸的是牛牛碎了一地",
                        "你在追赶${name}(${memberNumber})时不慎摔倒，牛牛着地断掉了",
                        "你在对${name}(${memberNumber})超的太疯狂，最终精疲力尽跌倒而亡",
                        "你在独自探索${name}(${memberNumber})的黑洞时消失在其中，导致精尽人亡"
                    )
                    SetTime.time(event,"草群友")
                    message.add(At(event.sender.id) + messageList.random())
                    event.group.sendMessage(message.build())
                    return
                }
                // 获取倒霉蛋的qq号
                memberNumber = groupList(event).random()
                // 倒霉蛋的user
                memberInfo = event.bot.getGroup(checkGroup(event,memberNumber))!!.getMember(memberNumber)!!
                // 获取倒霉蛋的头像地址
                url = event.bot.getGroup(checkGroup(event,memberNumber))!!.members[memberNumber]!!.avatarUrl(AvatarSpec.MEDIUM)
                // 下载倒霉蛋的头像
                image = imageGroupFriend(url)
                name = GroupGet.filter(memberInfo.nameCardOrNick)

                // 反被别人超
                if (checkGroup(event,memberNumber) == event.group.id)
                    message.add(At(event.sender.id) + "你被本群的${name}(${memberNumber})抓住一顿乱超")
                else message.add(At(event.sender.id) + "你被来自群聊(${checkGroup(event,memberNumber)})的${name}(${memberNumber})抓住一顿乱超")

                download(event, url, event.group, message, image, "草群友")
            }
        }
    }
}