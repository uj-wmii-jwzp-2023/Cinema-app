plugins {
	java
	id("org.springframework.boot") version "2.7.0"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "uj.wmii.jwzp"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")

	implementation("io.springfox:springfox-boot-starter:3.0.0")
	implementation("io.springfox:springfox-swagger-ui:3.0.0")
	implementation("javax.servlet:javax.servlet-api:4.0.1")
    implementation("org.projectlombok:lombok:1.18.22")

	implementation ("org.springframework.boot:spring-boot-starter-thymeleaf")
	implementation ("org.thymeleaf.extras:thymeleaf-extras-springsecurity5")

    testImplementation("junit:junit:4.13.2")
	runtimeOnly("org.postgresql:postgresql:42.5.4")
	runtimeOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}