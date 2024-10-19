package me.nelmin.spigot.database.models

class Clan(
    var name: String,
    var tag: String,
    var ownerUUID: String,
    var maxMembers: Int,
    var clanUpgrades: Int,
    var baseUpgrades: Int,
    var spaceshipUpgrades: Int,
)