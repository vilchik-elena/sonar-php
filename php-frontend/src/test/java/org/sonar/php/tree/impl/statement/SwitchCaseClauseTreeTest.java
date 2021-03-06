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
package org.sonar.php.tree.impl.statement;

import org.junit.Test;
import org.sonar.php.PHPTreeModelTest;
import org.sonar.php.parser.PHPLexicalGrammar;
import org.sonar.plugins.php.api.tree.Tree.Kind;
import org.sonar.plugins.php.api.tree.statement.CaseClauseTree;
import org.sonar.plugins.php.api.tree.statement.DefaultClauseTree;

import static org.fest.assertions.Assertions.assertThat;

public class SwitchCaseClauseTreeTest extends PHPTreeModelTest {

  @Test
  public void case_clause() throws Exception {
    CaseClauseTree tree = parse("case $a: $b; break;", PHPLexicalGrammar.SWITCH_CASE_CLAUSE);

    assertThat(tree.is(Kind.CASE_CLAUSE)).isTrue();
    assertThat(tree.expression().is(Kind.VARIABLE_IDENTIFIER)).isTrue();
    assertThat(tree.caseSeparatorToken().text()).isEqualTo(":");
    assertThat(tree.statements()).hasSize(2);
  }

  @Test
  public void default_clause() throws Exception {
    DefaultClauseTree tree = parse("default : $b; break;", PHPLexicalGrammar.SWITCH_CASE_CLAUSE);

    assertThat(tree.is(Kind.DEFAULT_CLAUSE)).isTrue();
    assertThat(tree.caseSeparatorToken().text()).isEqualTo(":");
    assertThat(tree.statements()).hasSize(2);
  }

}
