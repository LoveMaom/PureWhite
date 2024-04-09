package com.purewhite.plugin.translate

import com.purewhite.plugin.common.GroupGet
import com.purewhite.plugin.config.EntertainmentConfig.KFCCommand
import com.purewhite.plugin.config.EntertainmentConfig.nameCommand
import com.purewhite.plugin.config.EntertainmentConfig.newsCommand
import com.purewhite.plugin.config.EntertainmentConfig.newsHour
import com.purewhite.plugin.config.EntertainmentConfig.newsMinute
import com.purewhite.plugin.config.PluginPermissionsConfig.bot
import kotlinx.coroutines.*
import net.mamoe.mirai.Bot
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.content
import net.mamoe.mirai.utils.ExternalResource.Companion.uploadAsImage
import org.json.JSONObject
import org.jsoup.Jsoup
import java.net.URL
import java.util.*


object GetApi {
    suspend fun main (event: GroupMessageEvent) {
        if (nameCommand.contains(event.message.content)) {
            try {
                val getHtml = Jsoup.connect("https://api.lolimi.cn/API/naen/api.php").ignoreContentType(true).get()
                val name = JSONObject(JSONObject(getHtml.body().text())["data"].toString()).get("name").toString()
                event.group.sendMessage(At(event.sender) + name)
            } catch (e: Exception) {
                event.group.sendMessage("获取网络内容出错")
                e.printStackTrace()
            }
        }
        if (KFCCommand.contains(event.message.content)) {
            try {
                val getHtml = Jsoup.connect("https://jkyapi.top/API/fkxqs.php").ignoreContentType(true).get().text().replace("\\r","\n")
                event.group.sendMessage(At(event.sender) + getHtml)
            } catch (e: Exception) {
                event.group.sendMessage("获取网络内容出错")
                e.printStackTrace()
            }
        }
        if (newsCommand.contains(event.message.content)) {
            try {
                val getHtml = Jsoup.connect("http://api.suxun.site/api/sixs?type=json").ignoreContentType(true).get()
                val url = JSONObject(getHtml.body().text())["image"].toString()
                val image = GroupGet.imageGroupFriend(url)
                event.group.sendMessage(image.uploadAsImage(event.group))
            } catch (e: Exception) {
                event.group.sendMessage("获取网络内容出错")
                e.printStackTrace()
            }
        }
    }
    fun time() {
        val calendar = Calendar.getInstance()
        calendar[Calendar.HOUR_OF_DAY] = newsHour // 控制时

        calendar[Calendar.MINUTE] = newsMinute // 控制分

        calendar[Calendar.SECOND] = 0 // 控制秒

        val time: Date = calendar.time // 得出执行任务的时间,此处为今天的8：00：00


        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                runBlocking {
                    messageSend()
                }
            }
        }, time, 1000 * 60 * 60 * 24) // 这里设定将延时每天固定执行
    }

    private suspend fun messageSend(){
        val memberBot = Bot.getInstance(bot)
        val groups = memberBot.groups
        try {
            val getHtml = Jsoup.connect("http://api.suxun.site/api/sixs?type=json").ignoreContentType(true).get()
            val url = JSONObject(getHtml.body().text())["image"].toString()
            val image = GroupGet.imageGroupFriend(url)
            groups.forEach {
                coroutineScope {
                    launch {
                        if (groups[it.id]!!.botMuteRemaining == 0) {
                            delay(1000)
                            groups[it.id]!!.sendMessage(image.uploadAsImage(groups[it.id]!!))
                            withContext(Dispatchers.IO) {
                                withContext(Dispatchers.IO) {
                                    URL(url).openStream()
                                }.close()
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
