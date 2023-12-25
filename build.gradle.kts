plugins {
    kotlin("jvm") version "1.9.21"
    id("org.jetbrains.dokka") version "1.9.10"
    application
}

application {
    mainClass.set("be.amedee.adventofcode.AppKt")
}

group = "be.amedee"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    testImplementation("io.kotest:kotest-core:4.1.3")
    testImplementation("io.kotest:kotest-assertions-core:5.6.2")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        events("skipped", "passed", "failed")
        showStandardStreams = true
    }
}

tasks.named("run") {
    dependsOn(tasks.test)
    group = "application"
}

kotlin {
    jvmToolchain(21)
}
