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
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("io.kotest:kotest-assertions-core:5.8.0")
    testImplementation("io.kotest:kotest-assertions-core:5.8.0")
    testImplementation("io.kotest:kotest-framework-api:5.8.0")
    testImplementation("io.kotest:kotest-runner-junit5:5.8.0")
    testImplementation("io.mockk:mockk:1.13.9")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.11.0")
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



