name := "slickwithpostgresql"

scalaVersion := "2.10.3"

organization := "Knoldus"

libraryDependencies ++= List(
        "com.typesafe.slick"    %%   "slick"     %     "2.0.1",
        "com.typesafe"    % "config" %           "1.2.0",
        "org.slf4j"              %    "slf4j-api" %     "1.6.4",
        "postgresql"             %  "postgresql"   %    "9.1-901.jdbc4"
    )    
