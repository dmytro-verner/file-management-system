package com.dmytro_verner.commands

import com.dmytro_verner.files.{DirEntry, Directory}
import com.dmytro_verner.filesystem.State

class Mkdir(name: String) extends CreateEntry(name) {

  override def createSpecificEntry(state: State): DirEntry =
    Directory.empty(state.wd.path, name)
}