package com.purewhite.plugin.message

import com.purewhite.plugin.config.MessageConfig.functionNo
import com.purewhite.plugin.config.MessageConfig.functionYes
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.message.data.buildMessageChain
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import java.io.File

object SwitchMessage {
    suspend fun main (event: GroupMessageEvent,type: String,enable: String){
        if (enable == "开启") {
            // 获取成功图片
            val file = File("data/com.purewhite.entertainment/Yes").listFiles()
            if (file!!.isEmpty()) {
                event.group.sendMessage(functionYes.random().replace("%type%",type))
                return
            }
            event.group.sendMessage(
                buildMessageChain {
                    +At(event.sender)
                    +PlainText(functionYes.random().replace("%type%",type))
                    +file.random().uploadAsImage(event.group)
                }
            )
        }
        if (enable == "关闭") {
            // 获取失败图片
            val file = File("data/com.purewhite.entertainment/No").listFiles()
            if (file!!.isEmpty()) {
                event.group.sendMessage(functionNo.random().replace("%type%",type))
                return
            }
            event.group.sendMessage(
                buildMessageChain {
                    +At(event.sender)
                    +PlainText(functionNo.random().replace("%type%",type))
                    +file.random().uploadAsImage(event.group)
                }
            )
        }

    }
}