plugins {
    kotlin("jvm") version "2.0.0-Beta3"
    id("com.github.ben-manes.versions") version "0.51.0"
    id("se.patrikerdes.use-latest-versions") version "0.2.18"
    id("org.jlleitschuh.gradle.ktlint") version "12.1.0"
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
    testImplementation("io.kotest:kotest-core:4.2.0.RC2")
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

tasks.dokkaJavadoc {
    outputDirectory.set(layout.buildDirectory.dir("docs/javadoc"))
}

kotlin {
    jvmToolchain(21)
}
