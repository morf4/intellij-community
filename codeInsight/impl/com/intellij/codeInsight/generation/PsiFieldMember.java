/*
 * Copyright (c) 2000-2005 by JetBrains s.r.o. All Rights Reserved.
 * Use is subject to license terms.
 */
package com.intellij.codeInsight.generation;

import com.intellij.psi.*;
import com.intellij.psi.util.PropertyUtil;
import com.intellij.psi.util.PsiFormatUtil;
import org.jetbrains.annotations.Nullable;

/**
 * @author peter
 */
public class PsiFieldMember extends PsiElementClassMember<PsiField> implements EncapsulatableClassMember {
  private static final int FIELD_OPTIONS = PsiFormatUtil.SHOW_NAME | PsiFormatUtil.SHOW_TYPE | PsiFormatUtil.TYPE_AFTER;

  public PsiFieldMember(final PsiField field) {
    super(field, PsiFormatUtil.formatVariable(field, FIELD_OPTIONS, PsiSubstitutor.EMPTY));
  }

  @Nullable
  public PsiMethod generateGetter() {
    PsiField field = getElement();
    return createMethodIfNotExists(field, PropertyUtil.generateGetterPrototype(field));
  }

  @Nullable
  private static PsiMethod createMethodIfNotExists(final PsiField field, final PsiMethod template) {
    PsiMethod existing = field.getContainingClass().findMethodBySignature(template, false);
    return existing == null ? template : null;
  }

  @Nullable
  public PsiMethod generateSetter() {
    PsiField field = getElement();
    if (field.hasModifierProperty(PsiModifier.FINAL)) {
      return null;
    }
    return createMethodIfNotExists(field, PropertyUtil.generateSetterPrototype(field));
  }
}
