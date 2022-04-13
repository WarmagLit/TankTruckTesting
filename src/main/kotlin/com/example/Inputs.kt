package com.exampleimport

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Inputs: Table() {
    val uuid: Column<String> = varchar("id", 40)
    val height: Column<Int> = integer("height")
    val diameter: Column<Int> = integer("diameter")
    val volume: Column<Int> = integer("volume")

    override val primaryKey = PrimaryKey(uuid, name = "PK_InputID")
}