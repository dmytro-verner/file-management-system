package com.dmytro_verner.commands

import com.dmytro_verner.files.{DirEntry, File}
import com.dmytro_verner.filesystem.State

class Touch(name: String) extends CreateEntry(name) {

  override def createSpecificEntry(state: State): DirEntry =
    File.empty(state.wd.path, name)
}
