plugins {
    id("net.fabricmc.fabric-loom-remap") version "1.15-SNAPSHOT"
    id("maven-publish")
}

version = project.property("mod_version") as String
group = project.property("maven_group") as String

base {
    archivesName = project.property("archives_base_name") as String
}

repositories {
    // Add repositories to retrieve artifacts from in here.
    // You should only use this when depending on other mods because
    // Loom adds the essential maven repositories to download Minecraft and libraries from automatically.
    // See https://docs.gradle.org/current/userguide/declaring_repositories.html
    // for more information about repositories.
    maven("https://repo.codemc.io/repository/relativitymc/")
}

loom {
    useIntermediateMappings = true
    intermediaryUrl = "https://repo.codemc.io/repository/relativitymc/org/relativitymc/intermediary/%1\$s/intermediary-%1\$s-v2.jar"

    splitEnvironmentSourceSets()

    mods {
        create("cntrafficsymbols_core") {
            sourceSet(sourceSets.main.get())
            sourceSet(sourceSets.named("client").get())
        }
    }

}

dependencies {
    // To change the versions see the gradle.properties file
    minecraft("com.mojang:minecraft:${project.property("mc_version")}")
    mappings("org.relativitymc:modern-yarn:${project.property("yarn_mappings")}:v2")
    modImplementation(
        "net.fabricmc:fabric-loader:${project.property("loader_version")}"
    )

    // Fabric API. This is technically optional, but you probably want it anyway.
    //modImplementation("net.fabricmc.fabric-api:fabric-api:${project.fabric_version}")
    
    // Uncomment the following line to enable the deprecated Fabric API modules. 
    // These are included in the Fabric API production distribution and allow you to update your mod to the latest modules at a later more convenient time.

    // modImplementation("net.fabricmc.fabric-api:fabric-api-deprecated:${project.fabric_version}")
    //modImplementation(files("nbtutils-0.0.1a1.jar"))
    //modImplementation(files("nbtutils_bridge_mc-0.0.1-a1.jar"))
    //modImplementation(files("number_id_revival-0.0.1-a1.jar"))
}

tasks.withType<JavaCompile>().configureEach {
    options.release = 25
}

java {
    // Loom will automatically attach sourcesJar to a RemapSourcesJar task and to the "build" task
    // if it is present.
    // If you remove this line, sources will not be generated.
    withSourcesJar()

    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25
}

tasks.jar {
    from("LICENSE") {
        rename {
            "${it}_${base.archivesName.get()}"
        }
    }
}

// configure the maven publication
publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }

    // See https://docs.gradle.org/current/userguide/publishing_maven.html for information on how to set up publishing.
    repositories {
        // Add repositories to publish to here.
        // Notice: This block does NOT have the same function as the block in the top level.
        // The repositories here will be used for publishing your artifact, not for
        // retrieving dependencies.
    }
}
