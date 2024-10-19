plugins {
    kotlin("jvm") version "2.1.0-Beta1"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "me.nelmin.spigot"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/") {
        name = "spigotmc-repo"
    }
    maven("https://oss.sonatype.org/content/groups/public/") {
        name = "sonatype"
    }
    maven("https://jitpack.io") {
        name = "jitpack"
    }
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/") {
        name = "placeholder-api"
    }
}

dependencies {
    // Minecraft
    compileOnly("org.spigotmc:spigot-api:1.21.1-R0.1-SNAPSHOT")
    compileOnly("com.github.MilkBowl:VaultAPI:1.7")
    compileOnly("me.clip:placeholderapi:2.11.6")

    // Jetbrains
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
}

val targetJavaVersion = 21
kotlin {
    jvmToolchain(targetJavaVersion)
}

tasks.build {
    dependsOn("shadowJar")
}