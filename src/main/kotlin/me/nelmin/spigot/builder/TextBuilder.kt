package me.nelmin.spigot.builder

import me.clip.placeholderapi.PlaceholderAPI
import me.nelmin.spigot.VoxelAPI
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class TextBuilder(private var text: String, private val isPath: Boolean = false) {

    init {
        text = if (isPath) VoxelAPI.messagesConfig.get(text) ?: text else text
    }

    fun append(string: String, before: Boolean): TextBuilder {
        if (!isPath) {
            text = if (before) string + text else text + string
        }
        return this
    }

    fun prefix(prefix: String): TextBuilder {
        text = "$prefix $text"
        return this
    }

    fun replace(oldValue: Any, newValue: Any): TextBuilder {
        if (!isPath) text = text.replace(oldValue.toString(), newValue.toString())
        return this
    }

    fun replaceAll(map: Map<Any, Any>): TextBuilder {
        if (!isPath) {
            map.forEach { (oldValue, newValue) ->
                text = text.replace(oldValue.toString(), newValue.toString())
            }
        }
        return this
    }

    fun placeholderApi(player: Player): TextBuilder {
        PlaceholderAPI.setPlaceholders(player, text)
        return this
    }

    fun sendToConsole(): TextBuilder {
        VoxelAPI.plugin.server.consoleSender.sendMessage(getColorized())
        return this
    }

    fun sendTo(player: Player): TextBuilder {
        player.sendMessage(getColorized())
        return this
    }

    fun titleTo(player: Player, subTitle: String = ""): TextBuilder {
        player.sendTitle(getColorized(), subTitle, 1, 20, 1)
        return this
    }

    fun broadcast(): TextBuilder {
        VoxelAPI.plugin.server.broadcastMessage(getColorized())
        return this
    }

    fun getColorized(): String {
        return ChatColor.translateAlternateColorCodes('&', text)
    }

    fun get(): String {
        return text
    }
}