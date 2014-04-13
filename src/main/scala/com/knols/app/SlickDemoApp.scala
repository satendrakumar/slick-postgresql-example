package com.knols.app

import scala.slick.driver.PostgresDriver.simple._
import java.sql.Date
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory

object SlickDemoApp extends App with DomainComponent {

  // pick database connection  information from src/main/resources/application.conf

  val config = ConfigFactory.load("application")
  val url = config.getString("db.url")
  val username = config.getString("db.username")
  val password = config.getString("db.password")
  val driver = config.getString("db.driver")

  val dbObject = Database.forURL(url, username, password, null, driver)

  // open session with the database
  dbObject withSession { implicit session: Session =>
    // create  table  
    try {
      employees.ddl.create
    } catch {
      case ex: Exception => println(ex.getMessage)
    }
    // insert employee into database 
    val insertedCount = employees.insert(Employee("satendra", "satendra@knoldus.com", "consultant", Date.valueOf("2013-06-03")))
    // insert employee into database return auto incremented primary key
    val autoIncPkey = insertAndReturnPKey.insert(Employee("anand", "anand@knoldus.com", "consultant", Date.valueOf("2013-07-03")))

    println(autoIncPkey)

    println("======================retrieve from database ====================")
    // println all records
    employees.list foreach println

    // delete records
    val query = for { emp <- employees if (emp.name === "satendra") } yield emp
    val deletedCount = query.delete

  }

}

