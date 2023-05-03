import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "3.0.5"
    id("io.spring.dependency-management") version "1.1.0"
    kotlin("jvm") version "1.8.10"
    kotlin("plugin.allopen") version "1.8.10"
    kotlin("plugin.jpa") version "1.8.10"
    kotlin("plugin.noarg") version "1.8.10"
    kotlin("plugin.spring") version "1.8.10"
}

group = "by.inbar"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

tasks {
    withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "17"
        }
    }

    withType<Test> {
        useJUnitPlatform()
    }
}

noArg {
    invokeInitializers = true
    annotations(
        "by.inbar.backend.dto.annotation.DefaultConstructor",
        "jakarta.persistence.Entity",
        "jakarta.persistence.MappedSuperclass",
        "jakarta.persistence.Embedabble"
    )
}

allOpen {
    annotations(
        "by.inbar.backend.dto.annotation.DefaultConstructor",
        "jakarta.persistence.Entity",
        "jakarta.persistence.MappedSuperclass",
        "jakarta.persistence.Embedabble"
    )
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.slf4j:slf4j-api:2.0.6")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.2")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")
    implementation("org.liquibase:liquibase-core:4.21.1")
    implementation("org.postgresql:postgresql:42.5.3")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-rest")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}
