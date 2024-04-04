package com.purewhite.plugin.common

import com.purewhite.plugin.config.CompelConfig.compel
import com.purewhite.plugin.config.CompelConfig.compelCD
import com.purewhite.plugin.config.EverydayWifeConfig.everydayWife
import com.purewhite.plugin.config.FuckAdminConfig.adminCD
import com.purewhite.plugin.config.FuckAdminConfig.fuckAdmin
import com.purewhite.plugin.config.FuckManagementConfig.fuckManagement
import com.purewhite.plugin.config.FuckManagementConfig.managementCD
import com.purewhite.plugin.config.FuckMemberConfig.fuck
import com.purewhite.plugin.config.FuckMemberConfig.memberCD
import net.mamoe.mirai.event.events.GroupMessageEvent
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*

object SetTime {

    fun time (event: GroupMessageEvent, type: String) {

        // 获取现在时间
        val calendar = Calendar.getInstance()
        val year = calendar[Calendar.YEAR]
        val month = calendar[Calendar.MONTH] + 1
        var day = calendar[Calendar.DAY_OF_MONTH]
        var hour = calendar[Calendar.HOUR_OF_DAY]
        var minute = calendar[Calendar.MINUTE]
        var second = calendar[Calendar.SECOND]

        var endTimeVs : Long

        if (type == "强上") {
            minute += compelCD
            endTimeVs = getTime(second,minute,hour,day,month,year)
            compel[event.sender.id] = endTimeVs
        }
        if (type == "草群友") {
            minute += memberCD
            endTimeVs = getTime(second,minute,hour,day,month,year)
            fuck[event.sender.id] = endTimeVs
        }
        if (type == "草群主") {
            minute += adminCD
            endTimeVs = getTime(second,minute,hour,day,month,year)
            fuckAdmin[event.sender.id] = endTimeVs
        }
        if (type == "草管理") {
            minute += managementCD
            endTimeVs = getTime(second,minute,hour,day,month,year)
            fuckManagement[event.sender.id] = endTimeVs
        }
        if (type == "每日老婆") {
            day += 1
            minute = 0
            second = 0
            hour = 0
            endTimeVs = getTime(second,minute,hour,day,month,year)
            everydayWife[event.sender.id] = endTimeVs
        }
    }
    private fun getTime(second: Int, minute: Int, hour: Int, day: Int, month: Int, year: Int): Long {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd-HH:mm:ss")
        return dateFormat.parse("$year-$month-$day-$hour:$minute:$second", ParsePosition(0)).time / 1000
    }

}