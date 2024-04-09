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
        version = "0.0.6",
    ) {

        author("Love_Maom")
    }
) {
    override fun onEnable() {
        logger.info { "噼里啪啦,最弱插件启动成功" }
        // 创建文件夹
        Create.createFolder("data/com.purewhite.entertainment/No")
        Create.createFolder("data/com.purewhite.entertainment/Yes")
        FuckMemberConfig.reload()
        FuckAdminConfig.reload()
        FuckManagementConfig.reload()
        MessageConfig.reload()
        EverydayWifeConfig.reload()
        CompelConfig.reload()
        RecordConfig.reload()
        FortuneConfig.reload()
        RankListConfig.reload()
        PluginPermissionsConfig.reload()
        GlobalEventChannel.subscribeAlways<GroupMessageEvent> {
            if (group.botMuteRemaining < 1) {
                if (!PluginPermissionsConfig.enable.contains(this.group.id)) {
                    if (!FuckMemberConfig.enable.contains(this.group.id)) FuckMember.main(this)
                    if (!FuckAdminConfig.enable.contains(this.group.id)) FuckAdmin.main(this)
                    if (!FuckManagementConfig.enable.contains(this.group.id)) FuckManagement.main(this)
                    if (!EverydayWifeConfig.enable.contains(this.group.id)) EverydayWife.main(this)
                    if (!CompelConfig.enable.contains(this.group.id)) Compel.main(this)
                    if (!FortuneConfig.enable.contains(this.group.id)) Fortune.main(this)
                    if (!FortuneConfig.enable.contains(this.group.id)) Fortune.set(this)
                    if (!RankListConfig.enable.contains(this.group.id)) RankList.main(this)
                    HelpMessage.main(this)
                    GroupGet.record(this)
                }
                Switch.main(this)
            }
        }
    }
}