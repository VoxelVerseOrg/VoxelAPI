package me.nelmin.spigot.database.models

import me.nelmin.spigot.VoxelAPI
import java.util.*

class PlayerData(
    val uuid: UUID,
    var name: String,
    var rank: String,
    var clan: String,
    var balance: Double,
    var deaths: Int,
    var kills: Int,
    var chatEnabled: Boolean,
    var msgEnabled: Boolean,
) {
    fun addMoney(amount: Double): PlayerData = setBalance(balance + amount)
    fun removeMoney(amount: Double): PlayerData = setBalance(balance - amount)
    fun setBalance(amount: Double): PlayerData {
        this.balance = amount
        if (this.balance < 0) this.balance = 0.0
        if (this.balance >= Double.MAX_VALUE) this.balance = Double.MAX_VALUE - 1.0
        return this
    }

    fun addKills(amount: Int): PlayerData = setKills(kills + amount)
    fun removeKills(amount: Int): PlayerData = setKills(kills - amount)
    fun setKills(amount: Int): PlayerData {
        this.kills = amount
        if (this.kills < 0) this.kills = 0
        if (this.kills >= Int.MAX_VALUE) this.kills = Int.MAX_VALUE - 1
        return this
    }

    fun addDeaths(amount: Int): PlayerData = setDeaths(deaths + amount)
    fun removeDeaths(amount: Int): PlayerData = setDeaths(deaths - amount)
    fun setDeaths(amount: Int): PlayerData {
        this.deaths = amount
        if (this.deaths < 0) this.deaths = 0
        if (this.deaths >= Int.MAX_VALUE) this.deaths = Int.MAX_VALUE - 1
        return this
    }

    fun updateData() {
        VoxelAPI.mariadb.getConnection()
            .prepareStatement("UPDATE player_data SET rank = ?, clan = ?, balance = ?, deaths = ?, kills = ?, chat_enabled = ?, msg_enabled = ? WHERE uuid = ?")
            .apply {
                setString(1, rank)
                setString(2, clan)
                setDouble(3, balance)
                setInt(4, kills)
                setInt(5, deaths)
                setBoolean(6, chatEnabled)
                setBoolean(7, msgEnabled)
                setString(8, uuid.toString())
                executeUpdate()
            }
    }
}