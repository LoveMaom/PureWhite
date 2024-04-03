package com.purewhite

import com.purewhite.plugin.common.Create
import com.purewhite.plugin.config.FuckAdminConfig
import com.purewhite.plugin.config.FuckManagementConfig
import com.purewhite.plugin.config.FuckMemberConfig
import com.purewhite.plugin.config.MessageConfig
import com.purewhite.plugin.translate.FuckAdmin
import com.purewhite.plugin.translate.FuckManagement
import com.purewhite.plugin.translate.FuckMember
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.utils.info

object PureWhite : KotlinPlugin(
    JvmPluginDescription(
        id = "com.purewhite.entertainment",
        name = "Pure White",
        version = "0.0.3",
    ) {

        author("Love_Maom")
    }
) {
    override fun onEnable() {
        logger.info { "噼里啪啦,最弱插件启动成功" }
        // 创建文件夹
        Create.createFolder("data/com.purewhite.entertainment/FuckNo")
        FuckMemberConfig.reload()
        FuckAdminConfig.reload()
        FuckManagementConfig.reload()
        MessageConfig.reload()
        GlobalEventChannel.subscribeAlways<GroupMessageEvent> {
            FuckMember.main(this)
            FuckAdmin.main(this)
            FuckManagement.main(this)
        }
    }
}