package com.purewhite.plugin.message

import com.purewhite.plugin.config.MessageConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.PlainText
import net.mamoe.mirai.message.data.buildMessageChain
import net.mamoe.mirai.utils.ExternalResource.Companion.toExternalResource
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import java.awt.RenderingHints
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

object FuckMessage {
    suspend fun no(event: GroupMessageEvent) {
        // 获取操人时间未到图片
        val file = File("data/com.purewhite.entertainment/FuckNo").listFiles()!!.random()
        if (!file.isFile) {
            event.group.sendMessage(MessageConfig.fuck.random())
            return
        }
        if (file.name.contains(".gif")) {
            // 发出图片
            event.group.sendMessage(buildMessageChain {
                +PlainText(MessageConfig.fuck.random())
                +file.uploadAsImage(event.group)
            })
            return
        }
        // 上传图片
        val image = withContext(Dispatchers.IO) {
            ImageIO.read(file)
        }
        val height = edit(image.height)
        // 设置大小
        val width = edit(image.width)
        // 创建画板
        val getImage = BufferedImage(width,height, BufferedImage.TYPE_INT_RGB)
        val graphics = getImage.createGraphics()
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON)
        graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY)
        // 放入图片
        graphics.drawImage(image,0,0,width,height,null)
        // 创建临时文件
        val tempFile = withContext(Dispatchers.IO) {
            File.createTempFile("temp", ".jpg")
        }
        // 保存图片
        withContext(Dispatchers.IO) {
            ImageIO.write(getImage, "jpg", tempFile)
        }
        val end = tempFile.toExternalResource()
        // 发出图片
        event.group.sendMessage(buildMessageChain {
            +PlainText(MessageConfig.fuck.random())
            +end.use { it.uploadAsImage(event.group) }
        })
        // 释放资源
        withContext(Dispatchers.IO) {
            end.close()
        }
    }
    private fun edit(num: Int): Int {
        return when(num){
            in 2000..3999 -> num / 16
            in 1000..1999 -> num / 8
            in 500..999 -> num / 4
            else -> num
        }

    }
}