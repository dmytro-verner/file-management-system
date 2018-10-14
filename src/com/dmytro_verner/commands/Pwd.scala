package com.dmytro_verner.commands

import com.dmytro_verner.filesystem.State

class Pwd extends Command {

  override def apply(state: State): State =
    state.setMessage(state.wd.path)
}
