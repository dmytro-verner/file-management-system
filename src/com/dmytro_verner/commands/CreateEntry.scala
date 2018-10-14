package com.dmytro_verner.commands

import com.dmytro_verner.files.{DirEntry, Directory}
import com.dmytro_verner.filesystem.State

abstract class CreateEntry(name: String) extends Command {

  override def apply(state: State): State = {
    val wd = state.wd
    if (wd.hasEntry(name)) {
      state.setMessage("Entry " + name + " already exists")
    } else if (name.contains(Directory.SEPARATOR)) {
      state.setMessage(name + " must not contain separators")
    } else if (checkIllegal(name)) {
      state.setMessage(name + ": illegal entry name")
    } else {
      doCreateEntry(state)
    }
  }

  def checkIllegal(str: String): Boolean = {
    name.contains(".")
  }

  def doCreateEntry(state:State): State = {

    def updateStructure(currentDirectory: Directory, path: List[String], newEntry: DirEntry): Directory = {
      if (path.isEmpty) currentDirectory.addEntry(newEntry)
      else {
        val oldEntry = currentDirectory.findEntry(path.head).asDirectory
        currentDirectory.replaceEntry(oldEntry.name, updateStructure(oldEntry, path.tail, newEntry))
      }

      /*
      /a/b
        (contents)
        (new entry) /d

        newRoot = updateStructure(root, ["a", "b"], /d) = root.replaceEntry("a", updateStructure(/a, ["b"], /d) = /a.replaceEntry("b", updateStructure(/b, [], /d) = /b.add(/d)
         => path.isEmpty?
         => oldEntry = /a
         root.replaceEntry("a", updateStructure(/a, ["b"], /d) = /a.replaceEntry("b", updateStructure(/b, [], /d) = /b.add(/d)
          => path.isEmpty?
          => oldEntry = /b
            /a.replaceEntry("b", updateStructure(/b, [], /d) = /b.add(/d)
              => path.isEmpty? => /b.add(/d)
       */
    }

    val wd = state.wd

    // all the directories in the full path
    val allDirsInPath = wd.getAllFoldersInPath

    // create new directory entry in the wd
    val newEntry: DirEntry = createSpecificEntry(state)

    // update the whole directory structure starting from the root
    val newRoot = updateStructure(state.root, allDirsInPath, newEntry)

    // find new working directory instance given wd's full path, in the new directory structure
    val newWd = newRoot.findDescendant(allDirsInPath)

    State(newRoot, newWd)
  }

  def createSpecificEntry(state: State): DirEntry
}
