// Copyright 2000-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package com.intellij.openapi.command.impl;

import com.intellij.CommonBundle;
import com.intellij.idea.ActionsBundle;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.util.text.StringUtil;

/**
 * author: lesya
 */
class Redo extends UndoRedo {
  Redo(UndoManagerImpl manager, FileEditor editor) {
    super(manager, editor);
  }

  @Override
  protected UndoRedoStacksHolder getStackHolder() {
    return myManager.getRedoStacksHolder();
  }

  @Override
  protected UndoRedoStacksHolder getReverseStackHolder() {
    return myManager.getUndoStacksHolder();
  }

  @Override
  protected String getActionName() {
    return CommonBundle.message("redo.confirmation.title");
  }

  @Override
  protected String getActionName(String commandName) {
    if (StringUtil.isEmpty(commandName)) commandName = ActionsBundle.message("action.redo.description.empty");
    return CommonBundle.message("redo.command.confirmation.text", commandName);
  }

  @Override
  protected void performAction() {
    myUndoableGroup.redo();
  }

  @Override
  protected EditorAndState getBeforeState() {
    return myUndoableGroup.getStateBefore();
  }

  @Override
  protected EditorAndState getAfterState() {
    return myUndoableGroup.getStateAfter();
  }

  @Override
  protected void setBeforeState(EditorAndState state) {
    myUndoableGroup.setStateBefore(state);
  }

  @Override
  protected boolean isRedo() {
    return true;
  }
}
