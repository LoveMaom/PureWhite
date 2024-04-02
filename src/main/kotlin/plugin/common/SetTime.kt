package com.purewhite.plugin.common

import com.purewhite.plugin.config.FuckAdminConfig
import com.purewhite.plugin.config.FuckAdminConfig.fuckAdmin
import com.purewhite.plugin.config.FuckManagementConfig
import com.purewhite.plugin.config.FuckManagementConfig.fuckManagement
import com.purewhite.plugin.config.FuckMemberConfig
import com.purewhite.plugin.config.FuckMemberConfig.fuck
import net.mamoe.mirai.event.events.GroupMessageEvent
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*

object SetTime {
    // 获取现在时间
    private val calendar = Calendar.getInstance()
    private val year = calendar[Calendar.YEAR]
    private val month = calendar[Calendar.MONTH] + 1
    private val day = calendar[Calendar.DAY_OF_MONTH]
    private val hour = calendar[Calendar.HOUR_OF_DAY]
    private var minute = calendar[Calendar.MINUTE]
    private val second = calendar[Calendar.SECOND]

    fun time (event: GroupMessageEvent, type: String) {

        var endTimeVs : Long

        if (type == "草群友") {
            minute += FuckMemberConfig.memberCD
            endTimeVs = minute(minute)
            fuck[event.group.id]!![event.sender.id] = endTimeVs
        }
        if (type == "草群主") {
            minute += FuckAdminConfig.adminCD
            endTimeVs = minute(minute)
            fuckAdmin[event.group.id]!![event.sender.id] = endTimeVs
        }
        if (type == "草管理") {
            minute += FuckManagementConfig.managementCD
            endTimeVs = minute(minute)
            fuckManagement[event.group.id]!![event.sender.id] = endTimeVs
        }
    }
    private fun minute(num: Int): Long {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd-HH:mm:ss")
        return dateFormat.parse("$year-$month-$day-$hour:$num:$second", ParsePosition(0)).time / 1000
    }
}