plugins {
    kotlin("jvm") version "2.2.20"
    application
}

group = "com.infosupport"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation("org.assertj:assertj-core:3.25.3")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
    compilerOptions {
        freeCompilerArgs.add("-opt-in=kotlin.RequiresOptIn")
    }
}

// Set default main class for quick runs
application {
    mainClass.set("nl.bramjanssens.Day02Kt")
}

// Make runs faster by avoiding task configuration
tasks.run.configure {
    // Disable build cache for run task
    outputs.upToDateWhen { false }
}