package com.purewhite.plugin.translate

import com.purewhite.plugin.config.*
import com.purewhite.plugin.config.CompelConfig.compelCommand
import com.purewhite.plugin.config.EverydayWifeConfig.everydayCommand
import com.purewhite.plugin.config.FortuneConfig.fortuneCommand
import com.purewhite.plugin.config.FuckAdminConfig.adminCommand
import com.purewhite.plugin.config.FuckManagementConfig.managementCommand
import com.purewhite.plugin.config.FuckMemberConfig.memberCommand
import com.purewhite.plugin.config.PluginPermissionsConfig.admin
import com.purewhite.plugin.config.PluginPermissionsConfig.owner
import com.purewhite.plugin.config.RankListConfig.rankListCommand
import com.purewhite.plugin.message.SwitchMessage
import net.mamoe.mirai.contact.isOperator
import net.mamoe.mirai.contact.isOwner
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.message.data.content

object Switch {
    suspend fun main (event: GroupMessageEvent){
        if (owner.contains(event.sender.id) || admin.contains(event.sender.id) || event.sender.isOwner() || event.sender.isOperator()) {
            if (event.message.content.contains("开启")) {
                val open = event.message.content.replace("开启","").replace(" ","")
                // 插件总功能
                if (open == "娱乐功能") {
                    if (PluginPermissionsConfig.enable.contains(event.group.id)) {
                        SwitchMessage.main(event,open,"开启")
                        PluginPermissionsConfig.enable.remove(event.group.id)
                    } else event.group.sendMessage("${open}功能，已经是开启状态了")
                    return
                }
                // 被草排行榜
                if (rankListCommand.contains(open)) {
                    if (RankListConfig.enable.contains(event.group.id)) {
                        SwitchMessage.main(event,open,"开启")
                        RankListConfig.enable.remove(event.group.id)
                    } else event.group.sendMessage("${open}功能，已经是开启状态了")
                    return
                }
                // 草群友开关
                if (memberCommand.contains(open)) {
                    if (FuckMemberConfig.enable.contains(event.group.id)) {
                        SwitchMessage.main(event,open,"开启")
                        FuckMemberConfig.enable.remove(event.group.id)
                    } else event.group.sendMessage("${open}功能，已经是开启状态了")
                    return
                }
                // 草管理开关
                if (managementCommand.contains(open)) {
                    if (FuckManagementConfig.enable.contains(event.group.id)) {
                        SwitchMessage.main(event, open, "开启")
                        FuckManagementConfig.enable.remove(event.group.id)
                    } else event.group.sendMessage("${open}功能，已经是开启状态了")
                    return
                }
                // 草群主开关
                if (adminCommand.contains(open)) {
                    if (FuckAdminConfig.enable.contains(event.group.id)) {
                        SwitchMessage.main(event, open, "开启")
                        FuckAdminConfig.enable.remove(event.group.id)
                    } else event.group.sendMessage("${open}功能，已经是开启状态了")
                    return
                }
                // 强上开关
                if (compelCommand.contains(open)) {
                    if (CompelConfig.enable.contains(event.group.id)) {
                        SwitchMessage.main(event, open, "开启")
                        CompelConfig.enable.remove(event.group.id)
                    } else event.group.sendMessage("${open}功能，已经是开启状态了")
                    return
                }
                // 每日老婆开关
                if (everydayCommand.contains(open)) {
                    if (EverydayWifeConfig.enable.contains(event.group.id)) {
                        SwitchMessage.main(event, open, "开启")
                        EverydayWifeConfig.enable.remove(event.group.id)
                    } else event.group.sendMessage("${open}功能，已经是开启状态了")
                    return
                }
                // 运势开关
                if (fortuneCommand.contains(open)) {
                    if (FortuneConfig.enable.contains(event.group.id)) {
                        SwitchMessage.main(event, open, "开启")
                        FortuneConfig.enable.remove(event.group.id)
                    } else event.group.sendMessage("${open}功能，已经是开启状态了")
                    return
                }
            }
            if (event.message.content.contains("关闭")) {
                val open = event.message.content.replace("关闭","").replace(" ","")
                // 插件总功能
                if (open == "娱乐功能") {
                    if (!PluginPermissionsConfig.enable.contains(event.group.id)) {
                        SwitchMessage.main(event,open,"关闭")
                        PluginPermissionsConfig.enable.add(event.group.id)
                    } else event.group.sendMessage("${open}功能，已经是关闭状态了")
                    return
                }
                // 被草排行榜
                if (rankListCommand.contains(open)) {
                    if (!RankListConfig.enable.contains(event.group.id)) {
                        SwitchMessage.main(event,open,"关闭")
                        RankListConfig.enable.add(event.group.id)
                    } else event.group.sendMessage("${open}功能，已经是关闭状态了")
                    return
                }
                // 草群友开关
                if (memberCommand.contains(open)) {
                    if (!FuckMemberConfig.enable.contains(event.group.id)) {
                        SwitchMessage.main(event,open,"关闭")
                        FuckMemberConfig.enable.add(event.group.id)
                    } else event.group.sendMessage("${open}功能，已经是关闭状态了")
                    return
                }
                // 草管理开关
                if (managementCommand.contains(open)) {
                    if (!FuckManagementConfig.enable.contains(event.group.id)) {
                        SwitchMessage.main(event,open,"关闭")
                        FuckManagementConfig.enable.add(event.group.id)
                    } else event.group.sendMessage("${open}功能，已经是关闭状态了")
                    return
                }
                // 草群主开关
                if (adminCommand.contains(open)) {
                    if (!FuckAdminConfig.enable.contains(event.group.id)) {
                        SwitchMessage.main(event,open,"关闭")
                        FuckAdminConfig.enable.add(event.group.id)
                    } else event.group.sendMessage("${open}功能，已经是关闭状态了")
                    return
                }
                // 强上开关
                if (compelCommand.contains(open)) {
                    if (!CompelConfig.enable.contains(event.group.id)) {
                        SwitchMessage.main(event,open,"关闭")
                        CompelConfig.enable.add(event.group.id)
                    } else event.group.sendMessage("${open}功能，已经是关闭状态了")
                    return
                }
                // 每日老婆开关
                if (everydayCommand.contains(open)) {
                    if (!EverydayWifeConfig.enable.contains(event.group.id)) {
                        SwitchMessage.main(event,open,"关闭")
                        EverydayWifeConfig.enable.add(event.group.id)
                    } else event.group.sendMessage("${open}功能，已经是关闭状态了")
                    return
                }
                // 运势开关
                if (fortuneCommand.contains(open)) {
                    if (!FortuneConfig.enable.contains(event.group.id)) {
                        SwitchMessage.main(event,open,"关闭")
                        FortuneConfig.enable.add(event.group.id)
                    } else event.group.sendMessage("${open}功能，已经是关闭状态了")
                    return
                }
            }
        }
    }
}