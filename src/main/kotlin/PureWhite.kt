package com.purewhite

import net.mamoe.mirai.console.plugin.jvm.JvmPluginDescription
import net.mamoe.mirai.console.plugin.jvm.KotlinPlugin
import net.mamoe.mirai.utils.info

object PureWhite : KotlinPlugin(
    JvmPluginDescription(
        id = "com.purewhite.entertainment",
        name = "Pure White",
        version = "0.0.1",
    ) {

        author("Love_Maom")
    }
) {
    override fun onEnable() {
        logger.info { "Plugin loaded" }
    }
}