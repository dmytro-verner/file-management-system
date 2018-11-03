package com.dmytro_verner.filesystem

import com.dmytro_verner.commands.Command
import com.dmytro_verner.files.Directory

object FileSystem extends App {

  val root = Directory.ROOT

  State(root, root).show
  io.Source.stdin.getLines().foldLeft(State(root, root))((currentState, newLine) => {
    val state = Command.from(newLine).apply(currentState)
    state.show
    state
  })
}