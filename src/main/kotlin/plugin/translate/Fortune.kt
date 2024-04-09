package com.purewhite.plugin.translate

import com.purewhite.plugin.config.FortuneConfig.fortuneCommand
import com.purewhite.plugin.config.FortuneConfig.fortuneZodiac
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.At
import net.mamoe.mirai.message.data.MessageChainBuilder
import net.mamoe.mirai.message.data.content
import org.jsoup.Jsoup
import org.jsoup.select.Elements
import java.io.IOException

object Fortune {
    suspend fun main(event: GroupMessageEvent) {
        if (fortuneCommand.contains(event.message.content)) {
            try {
                val message = MessageChainBuilder()
                //获取网页内容
                val doc = Jsoup.connect("https://www.smxs.com/shengxiaoriyun").get()
                //将html内容放进集合中
                val list: Elements = doc.select("div[class=sxys3 fl]")
                val list2: Elements = doc.select("div[class=sxys3 fr]")
                val zodiacName : String
                if (fortuneZodiac[event.sender.id] != null) {
                    zodiacName = fortuneZodiac[event.sender.id]!!
                } else {
                    zodiacName = mutableListOf("鼠", "龙", "马", "牛", "虎", "兔", "蛇", "羊", "猴", "鸡", "狗", "猪").random()
                    fortuneZodiac[event.sender.id] = zodiacName
                }
                val zodiacList = when (zodiacName) {
                    "鼠" -> list[0]
                    "牛" -> list[1]
                    "兔" -> list[2]
                    "龙" -> list[3]
                    "马" -> list[4]
                    "羊" -> list[5]
                    "鸡" -> list[6]
                    "狗" -> list[7]
                    "虎" -> list2[0]
                    "蛇" -> list2[1]
                    "猴" -> list2[2]
                    "猪" -> list2[3]
                    else -> list[0]
                }
                val zodiac = when (zodiacName) {
                    "鼠" -> "shu"
                    "龙" -> "long"
                    "马" -> "ma"
                    "牛" -> "niu"
                    "虎" -> "hu"
                    "兔" -> "tu"
                    "蛇" -> "she"
                    "羊" -> "yang"
                    "猴" -> "hou"
                    "鸡" -> "ji"
                    "狗" -> "gou"
                    "猪" -> "zhu"
                    else -> null
                }
                //获取网页内容
                val doc2 = Jsoup.connect("https://www.smxs.com/shengxiaoriyun/${zodiac}").get()
                //将html内容放进集合中
                val ysdesc = doc2.select("div[class=ysdesc]")
                val kyzhi = doc2.select("div[class=kyzhi]")

                val career = mutableListOf(
                        "事业运势: \n${ysdesc[0].text()}",
                        "财运运势: \n${ysdesc[1].text()}",
                        "爱情运势: \n${ysdesc[2].text()}"
                    )

                message.append(At(event.sender))
                message.append("\n生肖: $zodiacName")
                message.append("\n${zodiacList.select("li")[0].text()}")
                message.append("\n${zodiacList.select("li")[1].text()}")
                message.append("\n${zodiacList.select("li")[2].text()}")
                message.append("\n幸运颜色: ${kyzhi[0].text()}")
                message.append("\n幸运位数: ${kyzhi[1].text()}")
                message.append("\n事业贵人: ${kyzhi[2].text()}")
                message.append("\n开运方向: ${kyzhi[3].text()}")
                message.append("\n${career.random()}")

                event.group.sendMessage(message.build())

            } catch (_: IOException) {
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    suspend fun set(event: GroupMessageEvent){
      if (event.message.content.contains("设置生肖")) {
          val zodiac = event.message.content.replace("设置生肖","").replace(" ","")
          val name = mutableListOf(
              "鼠", "龙", "马", "牛", "虎", "兔", "蛇", "羊", "猴", "鸡", "狗", "猪"
          )
          if (name.contains(zodiac)) {
              event.group.sendMessage(At(event.sender) + "恭喜你成功设置你自己的生肖为${zodiac}")
              fortuneZodiac[event.sender.id] = zodiac
          } else {
              event.group.sendMessage(At(event.sender) + "你设置的生肖名称不对,例子: 设置生肖 ${name.random()}")
          }
      }
    }
}