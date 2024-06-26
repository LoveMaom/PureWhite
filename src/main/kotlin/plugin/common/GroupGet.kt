package com.purewhite.plugin.common

import com.purewhite.plugin.config.RankListConfig.fuckRankList
import com.purewhite.plugin.config.RecordConfig.recordSet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.mamoe.mirai.contact.Contact
import net.mamoe.mirai.event.events.BotLeaveEvent
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.event.events.MemberLeaveEvent
import net.mamoe.mirai.message.data.MessageChainBuilder
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import java.io.File
import java.net.URL

object GroupGet {
    // 获取近期聊天的人
    fun record(event: GroupMessageEvent) {
        if (recordSet[event.group.id] == null) {
            recordSet[event.group.id] = mutableListOf()
        }
        if (!recordSet[event.group.id]!!.contains(event.sender.id)) {
            recordSet[event.group.id]!!.add(event.sender.id)
        }
        if (recordSet[event.group.id]!!.size > 10) {
            recordSet[event.group.id]!!.removeAt(0)
        }
    }
    // 检测退出的成员 并删除对应配置文件
    fun quit(event: MemberLeaveEvent){
        if (recordSet[event.group.id]!!.contains(event.member.id)) {
            recordSet[event.group.id]!!.remove(event.member.id)
        }
        if (fuckRankList[event.group.id]!!.contains(event.member.id)) {
            fuckRankList[event.group.id]!!.remove(event.member.id)
        }
    }
    // 检测被踢出的群聊
    fun botQuit(event: BotLeaveEvent){
        if (fuckRankList.contains(event.groupId)) {
            fuckRankList.remove(event.groupId)
        }
    }
    // 获取艾特的人中的数字
    fun atNum(text: String): Long {
        val regex = Regex("\\d+")
        val result = regex.find(text)?.value
        return result!!.toLong()
    }
    // 获取所有群聊的成员
    fun groupList(event: GroupMessageEvent): List<Long> {
        val list : MutableList<Long> = mutableListOf()
        for (groupNumber in event.bot.groups) {
            val group = event.bot.getGroup(groupNumber.id)
            for (memberNumber in group!!.members) {
                list.add(memberNumber.id)
            }
        }
        return list
    }
    // 查询该QQ是哪个群聊的
    fun checkGroup(event: GroupMessageEvent, number: Long): Long {
        var num = 0L
        for (groupNumber in event.bot.groups) {
            val group = event.bot.getGroup(groupNumber.id)
            for (memberNumber in group!!.members) {
                if (memberNumber.id == number) {
                    num = group.id
                }
            }
        }
        return num
    }
    // 下载头像图片
    suspend fun imageGroupFriend(url: String): File {
        // 获取群友头像
        val urlFile = File("user.jpg")

        // 下载群友头像
        withContext(Dispatchers.IO) {
            URL(url).openStream()
        }.use { input ->
            urlFile.outputStream().use { output ->
                input.copyTo(output)
            }
        }

        return urlFile
    }

    // 发出图片
    suspend fun download(event: GroupMessageEvent, url: String, group: Contact, message: MessageChainBuilder, image: File, type: String) {

        SetTime.time(event,type)
        image.let { it2 -> message.add(it2.uploadAsImage(group)) }
        group.sendMessage(message.build())

        withContext(Dispatchers.IO) {
            withContext(Dispatchers.IO) {
                URL(url).openStream()
            }.close()
        }
    }

    // 过滤不可见字符
    fun filter(input: String): String {
        val regex = Regex("\\p{Cntrl}")
        return input.replace(regex, "")
    }
}