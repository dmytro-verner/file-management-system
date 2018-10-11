package com.dmytro_verner.commands

import com.dmytro_verner.filesystem.State

trait Command {

  def apply(state: State): State
}

object Command {

  def from(input: String): Command = new UnknownCommand
}
