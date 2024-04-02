package com.purewhite.plugin.common

import java.io.File

object Create {
    //创建文件夹
    fun createFolder(path: String) {

        val filePath = File(path)

        // 创建文件夹及其父文件夹（不存在的话）
        if (!filePath.exists()) {
            val created = filePath.mkdirs()

            if (created) {
                println("文件夹创建成功")
            } else {
                println("文件夹创建失败")
            }
        } else {
            println("文件夹已存在 不创建")
        }

    }
}