import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.2"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.4.21"
	kotlin("plugin.spring") version "1.4.21"
}

group = "proj.eau"
version = "0.0.1-SNAPSHOT"

repositories {
	mavenCentral()
	jcenter()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.amshove.kluent:kluent:1.65")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "9"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
