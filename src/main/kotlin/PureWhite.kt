package com.purewhite

import com.purewhite.plugin.common.Create
import com.purewhite.plugin.common.GroupGet
import com.purewhite.plugin.config.*
import com.purewhite.plugin.message.HelpMessage
import com.purewhite.plugin.translate.*
import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.event.GlobalEventChannel
import net.mamoe.mirai.event.events.GroupMessageEvent
import net.mamoe.mirai.utils.info

object PureWhite : KotlinPlugin(
    JvmPluginDescription(
        id = "com.purewhite.entertainment",
        name = "Pure White",
        version = "0.0.4",
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
        EverydayWifeConfig.reload()
        CompelConfig.reload()
        RecordConfig.reload()
        FortuneConfig.reload()
        GlobalEventChannel.subscribeAlways<GroupMessageEvent> {
            if (group.botMuteRemaining < 1) {
                FuckMember.main(this)
                FuckAdmin.main(this)
                FuckManagement.main(this)
                EverydayWife.main(this)
                HelpMessage.main(this)
                Compel.main(this)
                GroupGet.record(this)
                Fortune.main(this)
                Fortune.set(this)
            }
        }
    }
}