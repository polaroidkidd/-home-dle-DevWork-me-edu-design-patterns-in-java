

plugins {
    id("application")
}

application {
    mainClass.set("dev.dle.solid.Main")
}




repositories {
    mavenCentral()
}



dependencies {
    implementation("org.javatuples:javatuples:1.2")
}