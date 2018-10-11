package com.dmytro_verner.commands
import com.dmytro_verner.filesystem.State

class UnknownCommand extends Command {

  override def apply(state: State): State = state.setMessage("Command not found")
}
