plugins {
	java
	id("org.springframework.boot") version "3.0.4"
	id("io.spring.dependency-management") version "1.1.2"
}

group = "com.stock.flex"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	// Spring Boot Core
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")

	// Database Migration
	implementation("org.flywaydb:flyway-core")

	// JSON Web Token (JWT)
	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

	// H2 Database
	runtimeOnly("com.h2database:h2")

	// Lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// Spring Boot DevTools
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	// Swagger
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.0")

	// Oracle Database JDBC Driver
	runtimeOnly("com.oracle.database.jdbc:ojdbc8")

	// Spring Security Test
	testImplementation("org.springframework.security:spring-security-test")

	// Spring Boot Test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks {
	// Configuração da tarefa bootJar
	val bootJar by getting(org.springframework.boot.gradle.tasks.bundling.BootJar::class) {
		archiveFileName.set("stock-flex.jar")
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
