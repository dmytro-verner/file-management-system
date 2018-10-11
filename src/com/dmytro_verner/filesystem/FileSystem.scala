package com.dmytro_verner.filesystem

import java.util.Scanner

import com.dmytro_verner.commands.Command
import com.dmytro_verner.files.Directory

object FileSystem extends App {

  val root = Directory.ROOT
  var state = State(root, root)
  val scanner = new Scanner(System.in)

  while (true) {
    state.show
    val input = scanner.nextLine()
    state = Command.from(input).apply(state)
  }
}