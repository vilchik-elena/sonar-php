/*
 * SonarQube PHP Plugin
 * Copyright (C) 2010 SonarSource and Akram Ben Aissi
 * sonarqube@googlegroups.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.php.tree.impl.declaration;

import java.util.Iterator;

import javax.annotation.Nullable;

import org.sonar.php.tree.impl.PHPTree;
import org.sonar.php.tree.impl.lexical.InternalSyntaxToken;
import org.sonar.plugins.php.api.tree.Tree;
import org.sonar.plugins.php.api.tree.expression.IdentifierTree;
import org.sonar.plugins.php.api.tree.lexical.SyntaxToken;
import org.sonar.plugins.php.api.tree.statement.TraitAliasTree;
import org.sonar.plugins.php.api.tree.statement.TraitMethodReferenceTree;
import org.sonar.plugins.php.api.visitors.VisitorCheck;

import com.google.common.collect.Iterators;

public class TraitAliasTreeImpl extends PHPTree implements TraitAliasTree {

  private static final Kind KIND = Kind.TRAIT_ALIAS;

  private final TraitMethodReferenceTree methodReference;
  private final InternalSyntaxToken asToken;
  private final SyntaxToken modifier;
  private final IdentifierTree alias;
  private final InternalSyntaxToken eosToken;

  public TraitAliasTreeImpl(
    TraitMethodReferenceTree methodReference,
    InternalSyntaxToken asToken,
    @Nullable SyntaxToken modifier,
    @Nullable IdentifierTree alias,
    InternalSyntaxToken eos
    ) {
    this.methodReference = methodReference;
    this.asToken = asToken;
    this.modifier = modifier;
    this.alias = alias;
    this.eosToken = eos;
  }

  @Override
  public TraitMethodReferenceTree methodReference() {
    return methodReference;
  }

  @Override
  public SyntaxToken asToken() {
    return asToken;
  }

  @Nullable
  @Override
  public SyntaxToken modifierToken() {
    return modifier;
  }

  @Nullable
  @Override
  public IdentifierTree alias() {
    return alias;
  }

  @Override
  public SyntaxToken eosToken() {
    return eosToken;
  }

  @Override
  public Kind getKind() {
    return KIND;
  }

  @Override
  public Iterator<Tree> childrenIterator() {
    return Iterators.forArray(methodReference, asToken, modifier, alias, eosToken);
  }

  @Override
  public void accept(VisitorCheck visitor) {
    visitor.visitTraitAlias(this);
  }

}
