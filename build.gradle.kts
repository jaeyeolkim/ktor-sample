plugins {
    kotlin("jvm") version "1.9.22"
    kotlin("plugin.jpa") version "1.9.22"
    id("io.ktor.plugin") version "2.3.12"
    application
}

group = "com.example"
version = "0.0.1"

application {
    mainClass.set("io.ktor.server.netty.EngineMain")
}

dependencies {
    implementation("io.ktor:ktor-server-netty")
    implementation("io.ktor:ktor-server-content-negotiation")
    implementation("io.ktor:ktor-serialization-jackson")
    implementation("io.ktor:ktor-server-config-yaml")

    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.hibernate.orm:hibernate-core:6.4.4.Final")
    implementation("jakarta.persistence:jakarta.persistence-api:3.1.0")
    implementation("com.h2database:h2:2.2.224")

    implementation("ch.qos.logback:logback-classic:1.5.13")

    testImplementation("io.ktor:ktor-server-test-host")
}

