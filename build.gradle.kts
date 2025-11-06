plugins {
    kotlin("jvm") version "2.0.21"
    application
    jacoco
}

version = project.version.toString()

kotlin {
    jvmToolchain(21)
}

application {
    mainClass.set("SendCommandToServerKt")
}

repositories {
    mavenCentral()
    mavenLocal()
}

dependencies {
    implementation("io.accelerate:tdl-client-java:0.30.1")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.17.3")

    testImplementation(kotlin("test"))
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`

    val action = System.getProperty("action")
    val actions = action?.let { listOf(it) } ?: emptyList()
    //noinspection GroovyAssignabilityCheck
    args(actions)
}

tasks.test {
    useJUnitPlatform()
    systemProperty("cucumber.junit-platform.naming-strategy", "long")
    testLogging {
        events("passed", "skipped", "failed")
    }

    ignoreFailures = true
    finalizedBy(tasks.jacocoTestReport)
}


jacoco {
    reportsDirectory.set(layout.buildDirectory.dir("jacoco"))
}

tasks.jacocoTestReport {
    dependsOn(tasks.test) // tests are required to run before generating the report
    reports {
        xml.required.set(true)
        csv.required.set(false)
        html.required.set(false)
    }
}

