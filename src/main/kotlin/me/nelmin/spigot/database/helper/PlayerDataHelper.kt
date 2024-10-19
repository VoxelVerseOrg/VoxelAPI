package me.nelmin.spigot.database.helper

import me.nelmin.spigot.VoxelAPI
import me.nelmin.spigot.database.MariaDB
import me.nelmin.spigot.database.models.PlayerData
import java.sql.ResultSet
import java.sql.SQLException
import java.util.*

@Throws(SQLException::class)
fun MariaDB.initPlayerData(playerData: PlayerData) {
    getConnection()
        .prepareStatement("INSERT INTO player_data(uuid, name, rank, clan, balance, deaths, kills) VALUES (?, ?, ?, ?, ?, ?, ?)")
        .apply {
            setString(1, playerData.uuid.toString())
            setString(2, playerData.name)
            setString(3, playerData.rank)
            setString(4, playerData.clan)
            setDouble(5, playerData.balance)
            setInt(6, playerData.kills)
            setInt(7, playerData.deaths)
            executeUpdate()
        }
}

@Throws(SQLException::class)
fun MariaDB.findPlayerDataByUUID(uuid: UUID): PlayerData? {
    var playerData = VoxelAPI.loadedPlayerData.find { it.uuid == uuid }
    if (playerData != null) return playerData

    val statement = getConnection().prepareStatement("SELECT * FROM player_data WHERE uuid = ?")
        .apply { setString(1, uuid.toString()) }
    val resultSet = statement.executeQuery()

    while (resultSet.next()) {
        playerData = PlayerData(
            uuid,
            resultSet.getString("name"),
            resultSet.getString("rank"),
            resultSet.getString("clan"),
            resultSet.getDouble("balance"),
            resultSet.getInt("deaths"),
            resultSet.getInt("kills"),
            resultSet.getBoolean("chat_enabled"),
            resultSet.getBoolean("msg_enabled")
        )

        statement.close()

        VoxelAPI.loadedPlayerData.apply {
            remove(playerData)
            add(playerData)
        }

        return playerData
    }

    return null
}

@Throws(SQLException::class)
fun MariaDB.findPlayerDataByName(playerName: String): PlayerData? {
    var playerData = VoxelAPI.loadedPlayerData.find { it.name == playerName }
    if (playerData != null) return playerData

    val statement = getConnection().prepareStatement("SELECT * FROM player_data WHERE name = ?")
        .apply { setString(1, playerName) }
    val resultSet = statement.executeQuery()

    while (resultSet.next()) {
        playerData = generatePlayerDataFromResultSet(resultSet)

        statement.close()

        VoxelAPI.loadedPlayerData.apply {
            remove(playerData)
            add(playerData)
        }

        return playerData
    }

    return null
}

@Throws(SQLException::class)
fun MariaDB.getAllPlayerData() {
    getConnection().prepareStatement("SELECT * FROM player_data").executeQuery().use { resultSet ->
        while (resultSet.next()) {
            val playerData = generatePlayerDataFromResultSet(resultSet)

            VoxelAPI.loadedPlayerData.add(playerData)
        }
    }
}

fun generatePlayerDataFromResultSet(resultSet: ResultSet): PlayerData {
    return PlayerData(
        UUID.fromString(resultSet.getString("uuid")),
        resultSet.getString("name"),
        resultSet.getString("rank"),
        resultSet.getString("clan"),
        resultSet.getDouble("balance"),
        resultSet.getInt("deaths"),
        resultSet.getInt("kills"),
        resultSet.getBoolean("chat_enabled"),
        resultSet.getBoolean("msg_enabled")
    )
}