plugins {
    kotlin("jvm") version "1.9.0"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    // JavaFX
    implementation("org.openjfx:javafx-controls:21.0.0")
    implementation("org.openjfx:javafx-fxml:21.0.0")
    // Corrutinas
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")
}
application {
    // Nombre del archivo Kotlin que contiene main() + Kt
    mainClass.set("MultiplicationTablesAppKt")
}

tasks.withType<JavaExec> {
    val osName = System.getProperty("os.name").lowercase()
    val platform = when {
        osName.contains("win") -> "win"
        osName.contains("mac") -> "mac"
        else -> "linux"
    }
    val javafxModules = listOf("javafx.controls", "javafx.fxml")
    jvmArgs = listOf(
        "--module-path", configurations.runtimeClasspath.get().asPath,
        "--add-modules", javafxModules.joinToString(",")
    )
}
