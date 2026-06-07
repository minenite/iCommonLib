import net.fabricmc.loom.task.RemapJarTask

plugins {
    id ("net.fabricmc.fabric-loom-remap") version "1.15-SNAPSHOT"
    id ("maven-publish")
	id ("java-library")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

base {
    archivesName = "iCommon-Fabric"
    version = "1.21.11"
    group = "com.javazilla.mods"
}

dependencies {
	annotationProcessor("com.pkware.jabel:jabel-javac-plugin:1.0.1-1")
    compileOnly("com.pkware.jabel:jabel-javac-plugin:1.0.1-1")

    implementation(project(mapOf("path" to ":iCommon-API")))
    implementation(project(mapOf("path" to ":iCommon-API")))

	// 1.21.8
	minecraft("com.mojang:minecraft:1.21.11")
	mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:" + project.property("loader_version"))
	
	setOf(
		"fabric-api-base",
		// "fabric-command-api-v1",
		"fabric-lifecycle-events-v1",
		"fabric-networking-api-v1"
	).forEach {
		// Add each module as a dependency
		modImplementation(fabricApi.module(it, "0.138.3+1.21.11"))
	}
}

sourceSets {
    main {
        java {
            srcDir("src/main/java")
        }
        resources {
            srcDir("${rootProject.projectDir}/iCommon-API/src/main/resources")
        }
    }
}

// 1.20.5 now requires JDK 21
tasks.withType<JavaCompile>().configureEach {
    sourceCompatibility = JavaVersion.VERSION_21.toString() // for the IDE support
    options.release.set(21)

    javaCompiler.set(
        javaToolchains.compilerFor {
            languageVersion.set(JavaLanguageVersion.of(21))
        }
    )
}

//tasks.getByName("compileJava") {
    //sourceCompatibility = 16
    //options.release = 8
//}


tasks.withType<Jar> { duplicatesStrategy = DuplicatesStrategy.INHERIT }

tasks.getByName<ProcessResources>("processResources") {
    duplicatesStrategy = DuplicatesStrategy.INCLUDE
    filesMatching("fabric.mod.json") {
        if(null != System.getenv("BUILD_NUMBER")){
			expand(mutableMapOf("version" to System.getenv("BUILD_NUMBER").toString()))
		} else {
			expand(mutableMapOf("version" to "dev"))
		}
    }
}

// val remapJar = tasks.getByName<RemapJarTask>("jar")

/*
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            groupId = project.group.toString()
            artifactId = project.name.lowercase()
            version = project.version.toString()
            
            pom {
                name.set(project.name.lowercase())
                description.set("A concise description of my library")
                url.set("http://www.example.com/")
            }

            artifact(jar)
        }
    }

    repositories {
        val mavenUsername: String? by project
        val mavenPassword: String? by project
        mavenPassword?.let {
            maven(url = "https://repo.codemc.io/repository/maven-releases/") {
                credentials {
                    username = mavenUsername
                    password = mavenPassword
                }
            }
        }
    }
}
*/