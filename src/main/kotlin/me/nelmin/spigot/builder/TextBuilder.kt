package me.nelmin.spigot.other

import me.nelmin.spigot.VVE
import org.bukkit.ChatColor
import org.bukkit.entity.Player

class TextBuilder(private val text: String, private val isPath: Boolean = false) {
    private val plugin = VVE.instance
    private var updatedText = if (isPath) plugin.messages.get(text) ?: text else text

    fun append(string: String, before: Boolean): TextBuilder {
        if (!isPath) {
            updatedText = if (before) string + updatedText else updatedText + string
        }
        return this
    }

    fun prefix(): TextBuilder {
        val prefix = plugin.config.get("prefix") ?: "&e&lEconomy&r &8&l»&r"
        updatedText = "$prefix $updatedText"
        return this
    }

    fun replace(oldValue: Any, newValue: Any): TextBuilder {
        if (!isPath) updatedText = updatedText.replace(oldValue.toString(), newValue.toString())
        return this
    }

    fun replaceAll(map: Map<Any, Any>): TextBuilder {
        if (!isPath) {
            map.forEach { (oldValue, newValue) ->
                updatedText = updatedText.replace(oldValue.toString(), newValue.toString())
            }
        }
        return this
    }

    fun sendToConsole(): TextBuilder {
        plugin.server.consoleSender.sendMessage(getColorized())
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
        plugin.server.broadcastMessage(getColorized())
        return this
    }

    fun getColorized(): String {
        return ChatColor.translateAlternateColorCodes('&', updatedText)
    }

    fun get(): String {
        return updatedText
    }
}