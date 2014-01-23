/*******************************************************************************
 * Copyright (c) 2011 - 2014 DigiArea, Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     DigiArea, Inc. - initial API and implementation
 *******************************************************************************/
package com.digiarea.jse.parser;
import java.util.*;
import com.digiarea.jse.*;

/** Token Manager. */
public class ASTParserTokenManager implements ASTParserConstants
{
  private List<Comment> comments;

  private final Stack<JavadocComment> javadocStack = new Stack<JavadocComment> ();

  private JavadocComment lastJavadoc;

  void pushJavadoc()
  {
    javadocStack.push(lastJavadoc);
  }
  JavadocComment popJavadoc()
  {
    if (javadocStack.empty())
    {
      return null;
    } else
    {
      return javadocStack.pop();
    }
  }
  List<Comment> getComments()
  {
    return comments;
  }

  void clearComments()
  {
    comments = null;
    javadocStack.clear();
    lastJavadoc = null;
  }

  private void CommonTokenAction(Token token)
  {
    lastJavadoc = null;
    if (token.specialToken != null)
    {
      if (comments == null)
      {
        comments = new LinkedList<Comment>();
      }
      Token special = token.specialToken;
      if (special.kind == JAVA_DOC_COMMENT)
      {
        lastJavadoc = NodeFacade.JavadocComment(special.image.substring(3, special.image.length() - 2));
        comments.add(lastJavadoc);
      }
      else if (special.kind == SINGLE_LINE_COMMENT)
      {
        LineComment comment = NodeFacade.LineComment(special.image.substring(2));
        comments.add(comment);
      }
      else if (special.kind == MULTI_LINE_COMMENT)
      {
        BlockComment comment = NodeFacade.BlockComment(special.image.substring(2, special.image.length() - 2));
        comments.add(comment);
      }
    }
  }

  /** Fake getter :) */
  int getLengthOfMatch() {
    return lengthOfMatch;
  }

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0, long active1, long active2)
{
   switch (pos)
   {
      case 0:
         if ((active1 & 0x2000000002000000L) != 0L)
            return 1;
         if ((active0 & 0xfffffffffffff000L) != 0L || (active1 & 0x1L) != 0L)
         {
            jjmatchedKind = 78;
            return 38;
         }
         if ((active0 & 0x100L) != 0L || (active1 & 0x20100000000000L) != 0L)
            return 56;
         return -1;
      case 1:
         if ((active0 & 0x100L) != 0L)
            return 61;
         if ((active0 & 0x803000000L) != 0L)
            return 38;
         if ((active0 & 0xfffffff7fcfff000L) != 0L || (active1 & 0x1L) != 0L)
         {
            if (jjmatchedPos != 1)
            {
               jjmatchedKind = 78;
               jjmatchedPos = 1;
            }
            return 38;
         }
         return -1;
      case 2:
         if ((active0 & 0x2000098200000000L) != 0L)
            return 38;
         if ((active0 & 0xdffff675fefff000L) != 0L || (active1 & 0x1L) != 0L)
         {
            if (jjmatchedPos != 2)
            {
               jjmatchedKind = 78;
               jjmatchedPos = 2;
            }
            return 38;
         }
         return -1;
      case 3:
         if ((active0 & 0x510012040c0b0000L) != 0L)
            return 38;
         if ((active0 & 0x8effe571f2f4f000L) != 0L || (active1 & 0x1L) != 0L)
         {
            jjmatchedKind = 78;
            jjmatchedPos = 3;
            return 38;
         }
         return -1;
      case 4:
         if ((active0 & 0x88dbe57012c07000L) != 0L)
         {
            if (jjmatchedPos != 4)
            {
               jjmatchedKind = 78;
               jjmatchedPos = 4;
            }
            return 38;
         }
         if ((active0 & 0x6240001e0348000L) != 0L || (active1 & 0x1L) != 0L)
            return 38;
         return -1;
      case 5:
         if ((active0 & 0x44b042002002000L) != 0L)
            return 38;
         if ((active0 & 0x8890e15090c05000L) != 0L)
         {
            jjmatchedKind = 78;
            jjmatchedPos = 5;
            return 38;
         }
         return -1;
      case 6:
         if ((active0 & 0x600090804000L) != 0L)
            return 38;
         if ((active0 & 0x8890815000401000L) != 0L)
         {
            jjmatchedKind = 78;
            jjmatchedPos = 6;
            return 38;
         }
         return -1;
      case 7:
         if ((active0 & 0x880815000000000L) != 0L)
         {
            jjmatchedKind = 78;
            jjmatchedPos = 7;
            return 38;
         }
         if ((active0 & 0x8010000000401000L) != 0L)
            return 38;
         return -1;
      case 8:
         if ((active0 & 0x800810000000000L) != 0L)
            return 38;
         if ((active0 & 0x80005000000000L) != 0L)
         {
            jjmatchedKind = 78;
            jjmatchedPos = 8;
            return 38;
         }
         return -1;
      case 9:
         if ((active0 & 0x5000000000L) != 0L)
            return 38;
         if ((active0 & 0x80000000000000L) != 0L)
         {
            jjmatchedKind = 78;
            jjmatchedPos = 9;
            return 38;
         }
         return -1;
      case 10:
         if ((active0 & 0x80000000000000L) != 0L)
         {
            jjmatchedKind = 78;
            jjmatchedPos = 10;
            return 38;
         }
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0, long active1, long active2)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0, active1, active2), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 26:
         return jjStopAtPos(0, 129);
      case 33:
         jjmatchedKind = 93;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x1000000000L, 0x0L);
      case 37:
         jjmatchedKind = 112;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x200000000000000L, 0x0L);
      case 38:
         jjmatchedKind = 109;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x40004000000000L, 0x0L);
      case 40:
         return jjStopAtPos(0, 81);
      case 41:
         return jjStopAtPos(0, 82);
      case 42:
         jjmatchedKind = 107;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x10000000000000L, 0x0L);
      case 43:
         jjmatchedKind = 105;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x4008000000000L, 0x0L);
      case 44:
         return jjStopAtPos(0, 88);
      case 45:
         jjmatchedKind = 106;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x8010000000000L, 0x8L);
      case 46:
         jjmatchedKind = 89;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x2000000000000000L, 0x0L);
      case 47:
         jjmatchedKind = 108;
         return jjMoveStringLiteralDfa1_0(0x100L, 0x20000000000000L, 0x0L);
      case 58:
         jjmatchedKind = 96;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x0L, 0x4L);
      case 59:
         return jjStopAtPos(0, 87);
      case 60:
         jjmatchedKind = 92;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x402000400000000L, 0x0L);
      case 61:
         jjmatchedKind = 91;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x200000000L, 0x0L);
      case 62:
         jjmatchedKind = 128;
         return jjMoveStringLiteralDfa1_0(0x0L, 0xd800000800000000L, 0x0L);
      case 63:
         return jjStopAtPos(0, 95);
      case 64:
         return jjStopAtPos(0, 90);
      case 91:
         return jjStopAtPos(0, 85);
      case 93:
         return jjStopAtPos(0, 86);
      case 94:
         jjmatchedKind = 111;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x100000000000000L, 0x0L);
      case 97:
         return jjMoveStringLiteralDfa1_0(0x3000L, 0x0L, 0x0L);
      case 98:
         return jjMoveStringLiteralDfa1_0(0x1c000L, 0x0L, 0x0L);
      case 99:
         return jjMoveStringLiteralDfa1_0(0x7e0000L, 0x0L, 0x0L);
      case 100:
         return jjMoveStringLiteralDfa1_0(0x3800000L, 0x0L, 0x0L);
      case 101:
         return jjMoveStringLiteralDfa1_0(0x1c000000L, 0x0L, 0x0L);
      case 102:
         return jjMoveStringLiteralDfa1_0(0x3e0000000L, 0x0L, 0x0L);
      case 103:
         return jjMoveStringLiteralDfa1_0(0x400000000L, 0x0L, 0x0L);
      case 105:
         return jjMoveStringLiteralDfa1_0(0x1f800000000L, 0x0L, 0x0L);
      case 108:
         return jjMoveStringLiteralDfa1_0(0x20000000000L, 0x0L, 0x0L);
      case 110:
         return jjMoveStringLiteralDfa1_0(0x1c0000000000L, 0x0L, 0x0L);
      case 112:
         return jjMoveStringLiteralDfa1_0(0x1e00000000000L, 0x0L, 0x0L);
      case 114:
         return jjMoveStringLiteralDfa1_0(0x2000000000000L, 0x0L, 0x0L);
      case 115:
         return jjMoveStringLiteralDfa1_0(0xfc000000000000L, 0x0L, 0x0L);
      case 116:
         return jjMoveStringLiteralDfa1_0(0x3f00000000000000L, 0x0L, 0x0L);
      case 118:
         return jjMoveStringLiteralDfa1_0(0xc000000000000000L, 0x0L, 0x0L);
      case 119:
         return jjMoveStringLiteralDfa1_0(0x0L, 0x1L, 0x0L);
      case 123:
         return jjStopAtPos(0, 83);
      case 124:
         jjmatchedKind = 110;
         return jjMoveStringLiteralDfa1_0(0x0L, 0x80002000000000L, 0x0L);
      case 125:
         return jjStopAtPos(0, 84);
      case 126:
         return jjStopAtPos(0, 94);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0, long active1, long active2)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0, active1, active2);
      return 1;
   }
   switch(curChar)
   {
      case 38:
         if ((active1 & 0x4000000000L) != 0L)
            return jjStopAtPos(1, 102);
         break;
      case 42:
         if ((active0 & 0x100L) != 0L)
            return jjStartNfaWithStates_0(1, 8, 61);
         break;
      case 43:
         if ((active1 & 0x8000000000L) != 0L)
            return jjStopAtPos(1, 103);
         break;
      case 45:
         if ((active1 & 0x10000000000L) != 0L)
            return jjStopAtPos(1, 104);
         break;
      case 46:
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x2000000000000000L, active2, 0L);
      case 58:
         if ((active2 & 0x4L) != 0L)
            return jjStopAtPos(1, 130);
         break;
      case 60:
         if ((active1 & 0x2000000000000L) != 0L)
         {
            jjmatchedKind = 113;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x400000000000000L, active2, 0L);
      case 61:
         if ((active1 & 0x200000000L) != 0L)
            return jjStopAtPos(1, 97);
         else if ((active1 & 0x400000000L) != 0L)
            return jjStopAtPos(1, 98);
         else if ((active1 & 0x800000000L) != 0L)
            return jjStopAtPos(1, 99);
         else if ((active1 & 0x1000000000L) != 0L)
            return jjStopAtPos(1, 100);
         else if ((active1 & 0x4000000000000L) != 0L)
            return jjStopAtPos(1, 114);
         else if ((active1 & 0x8000000000000L) != 0L)
            return jjStopAtPos(1, 115);
         else if ((active1 & 0x10000000000000L) != 0L)
            return jjStopAtPos(1, 116);
         else if ((active1 & 0x20000000000000L) != 0L)
            return jjStopAtPos(1, 117);
         else if ((active1 & 0x40000000000000L) != 0L)
            return jjStopAtPos(1, 118);
         else if ((active1 & 0x80000000000000L) != 0L)
            return jjStopAtPos(1, 119);
         else if ((active1 & 0x100000000000000L) != 0L)
            return jjStopAtPos(1, 120);
         else if ((active1 & 0x200000000000000L) != 0L)
            return jjStopAtPos(1, 121);
         break;
      case 62:
         if ((active1 & 0x8000000000000000L) != 0L)
         {
            jjmatchedKind = 127;
            jjmatchedPos = 1;
         }
         else if ((active2 & 0x8L) != 0L)
            return jjStopAtPos(1, 131);
         return jjMoveStringLiteralDfa2_0(active0, 0L, active1, 0x5800000000000000L, active2, 0L);
      case 97:
         return jjMoveStringLiteralDfa2_0(active0, 0x240020060000L, active1, 0L, active2, 0L);
      case 98:
         return jjMoveStringLiteralDfa2_0(active0, 0x1000L, active1, 0L, active2, 0L);
      case 101:
         return jjMoveStringLiteralDfa2_0(active0, 0x2080000800000L, active1, 0L, active2, 0L);
      case 102:
         if ((active0 & 0x800000000L) != 0L)
            return jjStartNfaWithStates_0(1, 35, 38);
         break;
      case 104:
         return jjMoveStringLiteralDfa2_0(active0, 0x704000000080000L, active1, 0x1L, active2, 0L);
      case 105:
         return jjMoveStringLiteralDfa2_0(active0, 0xc0000000L, active1, 0L, active2, 0L);
      case 108:
         return jjMoveStringLiteralDfa2_0(active0, 0x104100000L, active1, 0L, active2, 0L);
      case 109:
         return jjMoveStringLiteralDfa2_0(active0, 0x3000000000L, active1, 0L, active2, 0L);
      case 110:
         return jjMoveStringLiteralDfa2_0(active0, 0x1c008000000L, active1, 0L, active2, 0L);
      case 111:
         if ((active0 & 0x1000000L) != 0L)
         {
            jjmatchedKind = 24;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0xc000020602604000L, active1, 0L, active2, 0L);
      case 114:
         return jjMoveStringLiteralDfa2_0(active0, 0x3800c00000008000L, active1, 0L, active2, 0L);
      case 115:
         return jjMoveStringLiteralDfa2_0(active0, 0x2000L, active1, 0L, active2, 0L);
      case 116:
         return jjMoveStringLiteralDfa2_0(active0, 0x18000000000000L, active1, 0L, active2, 0L);
      case 117:
         return jjMoveStringLiteralDfa2_0(active0, 0x21100000000000L, active1, 0L, active2, 0L);
      case 119:
         return jjMoveStringLiteralDfa2_0(active0, 0x40000000000000L, active1, 0L, active2, 0L);
      case 120:
         return jjMoveStringLiteralDfa2_0(active0, 0x10000000L, active1, 0L, active2, 0L);
      case 121:
         return jjMoveStringLiteralDfa2_0(active0, 0x80000000010000L, active1, 0L, active2, 0L);
      case 124:
         if ((active1 & 0x2000000000L) != 0L)
            return jjStopAtPos(1, 101);
         break;
      default :
         break;
   }
   return jjStartNfa_0(0, active0, active1, active2);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0, long old1, long active1, long old2, long active2)
{
   if (((active0 &= old0) | (active1 &= old1) | (active2 &= old2)) == 0L)
      return jjStartNfa_0(0, old0, old1, old2);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0, active1, 0L);
      return 2;
   }
   switch(curChar)
   {
      case 46:
         if ((active1 & 0x2000000000000000L) != 0L)
            return jjStopAtPos(2, 125);
         break;
      case 61:
         if ((active1 & 0x400000000000000L) != 0L)
            return jjStopAtPos(2, 122);
         else if ((active1 & 0x800000000000000L) != 0L)
            return jjStopAtPos(2, 123);
         break;
      case 62:
         if ((active1 & 0x4000000000000000L) != 0L)
         {
            jjmatchedKind = 126;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active0, 0L, active1, 0x1000000000000000L);
      case 97:
         return jjMoveStringLiteralDfa3_0(active0, 0x808000000180000L, active1, 0L);
      case 98:
         return jjMoveStringLiteralDfa3_0(active0, 0x1000000000000L, active1, 0L);
      case 99:
         return jjMoveStringLiteralDfa3_0(active0, 0x200000000000L, active1, 0L);
      case 101:
         return jjMoveStringLiteralDfa3_0(active0, 0x8000L, active1, 0L);
      case 102:
         return jjMoveStringLiteralDfa3_0(active0, 0x800000L, active1, 0L);
      case 105:
         return jjMoveStringLiteralDfa3_0(active0, 0x4140400000000000L, active1, 0x1L);
      case 108:
         return jjMoveStringLiteralDfa3_0(active0, 0x8000100020000000L, active1, 0L);
      case 110:
         return jjMoveStringLiteralDfa3_0(active0, 0x800200c0600000L, active1, 0L);
      case 111:
         return jjMoveStringLiteralDfa3_0(active0, 0x4800100004000L, active1, 0L);
      case 112:
         return jjMoveStringLiteralDfa3_0(active0, 0x20003000000000L, active1, 0L);
      case 114:
         if ((active0 & 0x200000000L) != 0L)
            return jjStartNfaWithStates_0(2, 33, 38);
         return jjMoveStringLiteralDfa3_0(active0, 0x610000000000000L, active1, 0L);
      case 115:
         return jjMoveStringLiteralDfa3_0(active0, 0x4004023000L, active1, 0L);
      case 116:
         if ((active0 & 0x8000000000L) != 0L)
         {
            jjmatchedKind = 39;
            jjmatchedPos = 2;
         }
         return jjMoveStringLiteralDfa3_0(active0, 0x2050410050000L, active1, 0L);
      case 117:
         return jjMoveStringLiteralDfa3_0(active0, 0x100000000a000000L, active1, 0L);
      case 119:
         if ((active0 & 0x80000000000L) != 0L)
            return jjStartNfaWithStates_0(2, 43, 38);
         break;
      case 121:
         if ((active0 & 0x2000000000000000L) != 0L)
            return jjStartNfaWithStates_0(2, 61, 38);
         break;
      default :
         break;
   }
   return jjStartNfa_0(1, active0, active1, 0L);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(1, old0, old1, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0, active1, 0L);
      return 3;
   }
   switch(curChar)
   {
      case 61:
         if ((active1 & 0x1000000000000000L) != 0L)
            return jjStopAtPos(3, 124);
         break;
      case 97:
         return jjMoveStringLiteralDfa4_0(active0, 0x80000001c0808000L, active1, 0L);
      case 98:
         return jjMoveStringLiteralDfa4_0(active0, 0x2000000L, active1, 0L);
      case 99:
         return jjMoveStringLiteralDfa4_0(active0, 0x80000000040000L, active1, 0L);
      case 100:
         if ((active0 & 0x4000000000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 62, 38);
         break;
      case 101:
         if ((active0 & 0x10000L) != 0L)
            return jjStartNfaWithStates_0(3, 16, 38);
         else if ((active0 & 0x20000L) != 0L)
            return jjStartNfaWithStates_0(3, 17, 38);
         else if ((active0 & 0x4000000L) != 0L)
            return jjStartNfaWithStates_0(3, 26, 38);
         else if ((active0 & 0x1000000000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 60, 38);
         return jjMoveStringLiteralDfa4_0(active0, 0x20010010002000L, active1, 0L);
      case 103:
         if ((active0 & 0x20000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 41, 38);
         break;
      case 105:
         return jjMoveStringLiteralDfa4_0(active0, 0x10040000000000L, active1, 0L);
      case 107:
         return jjMoveStringLiteralDfa4_0(active0, 0x200000000000L, active1, 0L);
      case 108:
         if ((active0 & 0x100000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 44, 38);
         return jjMoveStringLiteralDfa4_0(active0, 0x1001000004000L, active1, 0x1L);
      case 109:
         if ((active0 & 0x8000000L) != 0L)
            return jjStartNfaWithStates_0(3, 27, 38);
         break;
      case 110:
         return jjMoveStringLiteralDfa4_0(active0, 0x800000000000000L, active1, 0L);
      case 111:
         if ((active0 & 0x400000000L) != 0L)
            return jjStartNfaWithStates_0(3, 34, 38);
         return jjMoveStringLiteralDfa4_0(active0, 0x600002000000000L, active1, 0L);
      case 114:
         if ((active0 & 0x80000L) != 0L)
            return jjStartNfaWithStates_0(3, 19, 38);
         return jjMoveStringLiteralDfa4_0(active0, 0x4000000000000L, active1, 0L);
      case 115:
         if ((active0 & 0x100000000000000L) != 0L)
            return jjStartNfaWithStates_0(3, 56, 38);
         return jjMoveStringLiteralDfa4_0(active0, 0x20300000L, active1, 0L);
      case 116:
         return jjMoveStringLiteralDfa4_0(active0, 0x48804000401000L, active1, 0L);
      case 117:
         return jjMoveStringLiteralDfa4_0(active0, 0x2000000000000L, active1, 0L);
      case 118:
         return jjMoveStringLiteralDfa4_0(active0, 0x400000000000L, active1, 0L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0, active1, 0L);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(2, old0, old1, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0, active1, 0L);
      return 4;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa5_0(active0, 0x604000000000L, active1, 0L);
      case 99:
         return jjMoveStringLiteralDfa5_0(active0, 0x50000000000000L, active1, 0L);
      case 101:
         if ((active0 & 0x20000000L) != 0L)
            return jjStartNfaWithStates_0(4, 29, 38);
         else if ((active1 & 0x1L) != 0L)
            return jjStartNfaWithStates_0(4, 64, 38);
         return jjMoveStringLiteralDfa5_0(active0, 0x801000004000L, active1, 0L);
      case 104:
         if ((active0 & 0x40000L) != 0L)
            return jjStartNfaWithStates_0(4, 18, 38);
         return jjMoveStringLiteralDfa5_0(active0, 0x80000000000000L, active1, 0L);
      case 105:
         return jjMoveStringLiteralDfa5_0(active0, 0x9000000400000L, active1, 0L);
      case 107:
         if ((active0 & 0x8000L) != 0L)
            return jjStartNfaWithStates_0(4, 15, 38);
         break;
      case 108:
         if ((active0 & 0x40000000L) != 0L)
         {
            jjmatchedKind = 30;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active0, 0x82000000L, active1, 0L);
      case 110:
         return jjMoveStringLiteralDfa5_0(active0, 0x10000000L, active1, 0L);
      case 114:
         if ((active0 & 0x20000000000000L) != 0L)
            return jjStartNfaWithStates_0(4, 53, 38);
         return jjMoveStringLiteralDfa5_0(active0, 0x2012000003000L, active1, 0L);
      case 115:
         if ((active0 & 0x100000L) != 0L)
            return jjStartNfaWithStates_0(4, 20, 38);
         return jjMoveStringLiteralDfa5_0(active0, 0x800000000000000L, active1, 0L);
      case 116:
         if ((active0 & 0x200000L) != 0L)
            return jjStartNfaWithStates_0(4, 21, 38);
         else if ((active0 & 0x100000000L) != 0L)
            return jjStartNfaWithStates_0(4, 32, 38);
         else if ((active0 & 0x4000000000000L) != 0L)
            return jjStartNfaWithStates_0(4, 50, 38);
         return jjMoveStringLiteralDfa5_0(active0, 0x8000000000000000L, active1, 0L);
      case 117:
         return jjMoveStringLiteralDfa5_0(active0, 0x800000L, active1, 0L);
      case 118:
         return jjMoveStringLiteralDfa5_0(active0, 0x40000000000L, active1, 0L);
      case 119:
         if ((active0 & 0x200000000000000L) != 0L)
         {
            jjmatchedKind = 57;
            jjmatchedPos = 4;
         }
         return jjMoveStringLiteralDfa5_0(active0, 0x400000000000000L, active1, 0L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0, active1, 0L);
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0, long old1, long active1)
{
   if (((active0 &= old0) | (active1 &= old1)) == 0L)
      return jjStartNfa_0(3, old0, old1, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0, 0L, 0L);
      return 5;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa6_0(active0, 0x5000L);
      case 99:
         if ((active0 & 0x1000000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 48, 38);
         else if ((active0 & 0x8000000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 51, 38);
         return jjMoveStringLiteralDfa6_0(active0, 0x800000000000L);
      case 100:
         return jjMoveStringLiteralDfa6_0(active0, 0x10000000L);
      case 101:
         if ((active0 & 0x2000000L) != 0L)
            return jjStartNfaWithStates_0(5, 25, 38);
         else if ((active0 & 0x40000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 42, 38);
         break;
      case 102:
         return jjMoveStringLiteralDfa6_0(active0, 0x10000000000L);
      case 103:
         return jjMoveStringLiteralDfa6_0(active0, 0x200000000000L);
      case 104:
         if ((active0 & 0x40000000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 54, 38);
         break;
      case 105:
         return jjMoveStringLiteralDfa6_0(active0, 0x8800000000000000L);
      case 108:
         return jjMoveStringLiteralDfa6_0(active0, 0x80800000L);
      case 109:
         return jjMoveStringLiteralDfa6_0(active0, 0x1000000000L);
      case 110:
         if ((active0 & 0x2000000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 49, 38);
         return jjMoveStringLiteralDfa6_0(active0, 0x4000400000L);
      case 114:
         return jjMoveStringLiteralDfa6_0(active0, 0x80000000000000L);
      case 115:
         if ((active0 & 0x400000000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 58, 38);
         break;
      case 116:
         if ((active0 & 0x2000L) != 0L)
            return jjStartNfaWithStates_0(5, 13, 38);
         else if ((active0 & 0x2000000000L) != 0L)
            return jjStartNfaWithStates_0(5, 37, 38);
         return jjMoveStringLiteralDfa6_0(active0, 0x10400000000000L);
      default :
         break;
   }
   return jjStartNfa_0(4, active0, 0L, 0L);
}
private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0, 0L, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0, 0L, 0L);
      return 6;
   }
   switch(curChar)
   {
      case 97:
         return jjMoveStringLiteralDfa7_0(active0, 0x10000000000L);
      case 99:
         return jjMoveStringLiteralDfa7_0(active0, 0x4000001000L);
      case 101:
         if ((active0 & 0x200000000000L) != 0L)
            return jjStartNfaWithStates_0(6, 45, 38);
         else if ((active0 & 0x400000000000L) != 0L)
            return jjStartNfaWithStates_0(6, 46, 38);
         return jjMoveStringLiteralDfa7_0(active0, 0x800001000000000L);
      case 102:
         return jjMoveStringLiteralDfa7_0(active0, 0x10000000000000L);
      case 108:
         return jjMoveStringLiteralDfa7_0(active0, 0x8000000000000000L);
      case 110:
         if ((active0 & 0x4000L) != 0L)
            return jjStartNfaWithStates_0(6, 14, 38);
         break;
      case 111:
         return jjMoveStringLiteralDfa7_0(active0, 0x80000000000000L);
      case 115:
         if ((active0 & 0x10000000L) != 0L)
            return jjStartNfaWithStates_0(6, 28, 38);
         break;
      case 116:
         if ((active0 & 0x800000L) != 0L)
            return jjStartNfaWithStates_0(6, 23, 38);
         return jjMoveStringLiteralDfa7_0(active0, 0x800000000000L);
      case 117:
         return jjMoveStringLiteralDfa7_0(active0, 0x400000L);
      case 121:
         if ((active0 & 0x80000000L) != 0L)
            return jjStartNfaWithStates_0(6, 31, 38);
         break;
      default :
         break;
   }
   return jjStartNfa_0(5, active0, 0L, 0L);
}
private int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0, 0L, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0, 0L, 0L);
      return 7;
   }
   switch(curChar)
   {
      case 99:
         return jjMoveStringLiteralDfa8_0(active0, 0x10000000000L);
      case 101:
         if ((active0 & 0x400000L) != 0L)
            return jjStartNfaWithStates_0(7, 22, 38);
         else if ((active0 & 0x8000000000000000L) != 0L)
            return jjStartNfaWithStates_0(7, 63, 38);
         return jjMoveStringLiteralDfa8_0(active0, 0x804000000000L);
      case 110:
         return jjMoveStringLiteralDfa8_0(active0, 0x880001000000000L);
      case 112:
         if ((active0 & 0x10000000000000L) != 0L)
            return jjStartNfaWithStates_0(7, 52, 38);
         break;
      case 116:
         if ((active0 & 0x1000L) != 0L)
            return jjStartNfaWithStates_0(7, 12, 38);
         break;
      default :
         break;
   }
   return jjStartNfa_0(6, active0, 0L, 0L);
}
private int jjMoveStringLiteralDfa8_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(6, old0, 0L, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(7, active0, 0L, 0L);
      return 8;
   }
   switch(curChar)
   {
      case 100:
         if ((active0 & 0x800000000000L) != 0L)
            return jjStartNfaWithStates_0(8, 47, 38);
         break;
      case 101:
         if ((active0 & 0x10000000000L) != 0L)
            return jjStartNfaWithStates_0(8, 40, 38);
         break;
      case 105:
         return jjMoveStringLiteralDfa9_0(active0, 0x80000000000000L);
      case 111:
         return jjMoveStringLiteralDfa9_0(active0, 0x4000000000L);
      case 116:
         if ((active0 & 0x800000000000000L) != 0L)
            return jjStartNfaWithStates_0(8, 59, 38);
         return jjMoveStringLiteralDfa9_0(active0, 0x1000000000L);
      default :
         break;
   }
   return jjStartNfa_0(7, active0, 0L, 0L);
}
private int jjMoveStringLiteralDfa9_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(7, old0, 0L, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(8, active0, 0L, 0L);
      return 9;
   }
   switch(curChar)
   {
      case 102:
         if ((active0 & 0x4000000000L) != 0L)
            return jjStartNfaWithStates_0(9, 38, 38);
         break;
      case 115:
         if ((active0 & 0x1000000000L) != 0L)
            return jjStartNfaWithStates_0(9, 36, 38);
         break;
      case 122:
         return jjMoveStringLiteralDfa10_0(active0, 0x80000000000000L);
      default :
         break;
   }
   return jjStartNfa_0(8, active0, 0L, 0L);
}
private int jjMoveStringLiteralDfa10_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(8, old0, 0L, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(9, active0, 0L, 0L);
      return 10;
   }
   switch(curChar)
   {
      case 101:
         return jjMoveStringLiteralDfa11_0(active0, 0x80000000000000L);
      default :
         break;
   }
   return jjStartNfa_0(9, active0, 0L, 0L);
}
private int jjMoveStringLiteralDfa11_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(9, old0, 0L, 0L);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(10, active0, 0L, 0L);
      return 11;
   }
   switch(curChar)
   {
      case 100:
         if ((active0 & 0x80000000000000L) != 0L)
            return jjStartNfaWithStates_0(11, 55, 38);
         break;
      default :
         break;
   }
   return jjStartNfa_0(10, active0, 0L, 0L);
}
private int jjStartNfaWithStates_0(int pos, int kind, int state)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) { return pos + 1; }
   return jjMoveNfa_0(state, pos + 1);
}
static final long[] jjbitVec0 = {
   0xfffffffffffffffeL, 0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec2 = {
   0x0L, 0x0L, 0xffffffffffffffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec3 = {
   0xfff0000000200002L, 0xffffffffffffdfffL, 0xfffff00f7fffffffL, 0x12000000007fffffL
};
static final long[] jjbitVec4 = {
   0x0L, 0x0L, 0x420043c00000000L, 0xff7fffffff7fffffL
};
static final long[] jjbitVec5 = {
   0x7fffffffffffffL, 0xffffffffffff0000L, 0xffffffffffffffffL, 0x401f0003ffc3L
};
static final long[] jjbitVec6 = {
   0x0L, 0x400000000000000L, 0xfffffffbffffd740L, 0xfbfffffffff7fffL
};
static final long[] jjbitVec7 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0xfffffffffffffc03L, 0x33fffffffff7fffL
};
static final long[] jjbitVec8 = {
   0xfffe00000000ffffL, 0xfffffffe027fffffL, 0xffL, 0x707ffffff0000L
};
static final long[] jjbitVec9 = {
   0x7fffffe00000000L, 0xfffec000000007ffL, 0xffffffffffffffffL, 0x9c00c060002fffffL
};
static final long[] jjbitVec10 = {
   0xfffffffd0000L, 0xe000L, 0x2003fffffffffL, 0x0L
};
static final long[] jjbitVec11 = {
   0x23fffffffffffff0L, 0x3ff010000L, 0x23c5fdfffff99fe0L, 0xf0003b0000000L
};
static final long[] jjbitVec12 = {
   0x36dfdfffff987e0L, 0x1c00005e000000L, 0x23edfdfffffbbfe0L, 0x2000300010000L
};
static final long[] jjbitVec13 = {
   0x23edfdfffff99fe0L, 0x20003b0000000L, 0x3bfc718d63dc7e8L, 0x200000000000000L
};
static final long[] jjbitVec14 = {
   0x3effdfffffddfe0L, 0x300000000L, 0x23effdfffffddfe0L, 0x340000000L
};
static final long[] jjbitVec15 = {
   0x3fffdfffffddfe0L, 0x300000000L, 0x2ffbfffffc7fffe0L, 0x7fL
};
static final long[] jjbitVec16 = {
   0x800dfffffffffffeL, 0x7fL, 0x200decaefef02596L, 0x3000005fL
};
static final long[] jjbitVec17 = {
   0x1L, 0x7fffffffeffL, 0xf00L, 0x0L
};
static final long[] jjbitVec18 = {
   0x6fbffffffffL, 0x3f0000L, 0xffffffff00000000L, 0x1ffffffffff003fL
};
static final long[] jjbitVec19 = {
   0xffffffffffffffffL, 0xffffffff83ffffffL, 0xffffff07ffffffffL, 0x3ffffffffffffffL
};
static final long[] jjbitVec20 = {
   0xffffffffffffff7fL, 0xffffffff3d7f3d7fL, 0x7f3d7fffffff3d7fL, 0xffff7fffff7f7f3dL
};
static final long[] jjbitVec21 = {
   0xffffffff7f3d7fffL, 0x7ffff7fL, 0xffffffff00000000L, 0x1fffffffffffffL
};
static final long[] jjbitVec22 = {
   0xffffffffffffffffL, 0x7f9fffffffffffL, 0xffffffff07fffffeL, 0x1c7ffffffffffL
};
static final long[] jjbitVec23 = {
   0x3ffff0003dfffL, 0x1dfff0003ffffL, 0xfffffffffffffL, 0x18800000L
};
static final long[] jjbitVec24 = {
   0xffffffff00000000L, 0xffffffffffffffL, 0x1ffffffffffL, 0x0L
};
static final long[] jjbitVec25 = {
   0x1fffffffL, 0x1f3fffffff0000L, 0x0L, 0x0L
};
static final long[] jjbitVec26 = {
   0xffffffffffffffffL, 0xfffffffffffL, 0x0L, 0x0L
};
static final long[] jjbitVec27 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0xffffffff0fffffffL, 0x3ffffffffffffffL
};
static final long[] jjbitVec28 = {
   0xffffffff3f3fffffL, 0x3fffffffaaff3f3fL, 0x5fdfffffffffffffL, 0x1fdc1fff0fcf1fdcL
};
static final long[] jjbitVec29 = {
   0x8000000000000000L, 0x8002000000100001L, 0x3ffff00000000L, 0x0L
};
static final long[] jjbitVec30 = {
   0xe3fbbd503e2ffc84L, 0xffffffff000003e0L, 0xfL, 0x0L
};
static final long[] jjbitVec31 = {
   0x1f3e03fe000000e0L, 0xfffffffffffffffeL, 0xfffffffee07fffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec32 = {
   0xfffe1fffffffffe0L, 0xffffffffffffffffL, 0xffffff00007fffL, 0xffff000000000000L
};
static final long[] jjbitVec33 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0x3fffffffffffffL, 0x0L
};
static final long[] jjbitVec34 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0x3fffffffffL, 0x0L
};
static final long[] jjbitVec35 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0x1fffL, 0x0L
};
static final long[] jjbitVec36 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0xfffffffffL, 0x0L
};
static final long[] jjbitVec37 = {
   0x6L, 0x0L, 0x0L, 0x0L
};
static final long[] jjbitVec38 = {
   0xffff3fffffffffffL, 0x7ffffffffffL, 0x0L, 0x0L
};
static final long[] jjbitVec39 = {
   0x5f7ffdffa0f8007fL, 0xffffffffffffffdbL, 0x3ffffffffffffL, 0xfffffffffff80000L
};
static final long[] jjbitVec40 = {
   0x3fffffffffffffffL, 0xffffffffffff0000L, 0xfffffffffffcffffL, 0x1fff0000000000ffL
};
static final long[] jjbitVec41 = {
   0x18000000000000L, 0xffdf02000000e000L, 0xffffffffffffffffL, 0x1fffffffffffffffL
};
static final long[] jjbitVec42 = {
   0x87fffffe00000010L, 0xffffffe007fffffeL, 0x7fffffffffffffffL, 0x631cfcfcfcL
};
static final long[] jjbitVec43 = {
   0x0L, 0x0L, 0x420243cffffffffL, 0xff7fffffff7fffffL
};
static final long[] jjbitVec44 = {
   0xffffffffffffffffL, 0x400ffffe0ffffffL, 0xfffffffbffffd740L, 0xfbfffffffff7fffL
};
static final long[] jjbitVec45 = {
   0xffffffffffffffffL, 0xffffffffffffffffL, 0xfffffffffffffc7bL, 0x33fffffffff7fffL
};
static final long[] jjbitVec46 = {
   0xfffe00000000ffffL, 0xfffffffe027fffffL, 0xbbfffffbfffe00ffL, 0x707ffffff0016L
};
static final long[] jjbitVec47 = {
   0x7fffffe003f000fL, 0xffffc3ff01ffffffL, 0xffffffffffffffffL, 0x9ffffdffbfefffffL
};
static final long[] jjbitVec48 = {
   0xffffffffffff8000L, 0xe7ffL, 0x3ffffffffffffL, 0x0L
};
static final long[] jjbitVec49 = {
   0xf3fffffffffffffeL, 0xffcfff1f3fffL, 0xf3c5fdfffff99feeL, 0xfffcfb080399fL
};
static final long[] jjbitVec50 = {
   0xd36dfdfffff987eeL, 0x1fffc05e003987L, 0xf3edfdfffffbbfeeL, 0x2ffcf00013bbfL
};
static final long[] jjbitVec51 = {
   0xf3edfdfffff99feeL, 0x2ffc3b0c0398fL, 0xc3bfc718d63dc7ecL, 0x200ff8000803dc7L
};
static final long[] jjbitVec52 = {
   0xc3effdfffffddfeeL, 0xffc300603ddfL, 0xf3effdfffffddfecL, 0xffc340603ddfL
};
static final long[] jjbitVec53 = {
   0xc3fffdfffffddfecL, 0xffc300803dcfL, 0x2ffbfffffc7fffecL, 0xc0000ff5f847fL
};
static final long[] jjbitVec54 = {
   0x87fffffffffffffeL, 0x3ff7fffL, 0x3bffecaefef02596L, 0x33ff3f5fL
};
static final long[] jjbitVec55 = {
   0xc2a003ff03000001L, 0xfffe07fffffffeffL, 0x1ffffffffeff0fdfL, 0x40L
};
static final long[] jjbitVec56 = {
   0x3c7f6fbffffffffL, 0x3ff03ffL, 0xffffffff00000000L, 0x1ffffffffff003fL
};
static final long[] jjbitVec57 = {
   0xffffffff7f3d7fffL, 0x3fe0007ffff7fL, 0xffffffff00000000L, 0x1fffffffffffffL
};
static final long[] jjbitVec58 = {
   0x1fffff001fdfffL, 0xddfff000fffffL, 0xffffffffffffffffL, 0x3ff388fffffL
};
static final long[] jjbitVec59 = {
   0xffffffff03ff3800L, 0xffffffffffffffL, 0x3ffffffffffL, 0x0L
};
static final long[] jjbitVec60 = {
   0xfff0fff1fffffffL, 0x1f3fffffffffc0L, 0x0L, 0x0L
};
static final long[] jjbitVec61 = {
   0x80007c000000f000L, 0x8002fc0f00100001L, 0x3ffff00000000L, 0x7e21fff0000L
};
static final long[] jjbitVec62 = {
   0x1f3efffe000000e0L, 0xfffffffffffffffeL, 0xfffffffee67fffffL, 0xffffffffffffffffL
};
static final long[] jjbitVec63 = {
   0x10000000000006L, 0x0L, 0x0L, 0x0L
};
static final long[] jjbitVec64 = {
   0x3L, 0x0L, 0x0L, 0x0L
};
static final long[] jjbitVec65 = {
   0x0L, 0x800000000000000L, 0x0L, 0x0L
};
static final long[] jjbitVec66 = {
   0x5f7ffdffe0f8007fL, 0xffffffffffffffdbL, 0x3ffffffffffffL, 0xfffffffffff80000L
};
static final long[] jjbitVec67 = {
   0x18000f0000ffffL, 0xffdf02000000e000L, 0xffffffffffffffffL, 0x9fffffffffffffffL
};
static final long[] jjbitVec68 = {
   0x87fffffe03ff0010L, 0xffffffe007fffffeL, 0x7fffffffffffffffL, 0xe0000631cfcfcfcL
};
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 93;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStates(0, 6);
                  else if (curChar == 47)
                     jjAddStates(7, 8);
                  else if (curChar == 36)
                  {
                     if (kind > 78)
                        kind = 78;
                     jjCheckNAdd(38);
                  }
                  else if (curChar == 34)
                     jjCheckNAddStates(9, 12);
                  else if (curChar == 39)
                     jjAddStates(13, 15);
                  else if (curChar == 46)
                     jjstateSet[jjnewStateCnt++] = 1;
                  if ((0x3fe000000000000L & l) != 0L)
                  {
                     if (kind > 66)
                        kind = 66;
                     jjCheckNAddStates(16, 18);
                  }
                  else if (curChar == 48)
                  {
                     if (kind > 66)
                        kind = 66;
                     jjCheckNAddStates(19, 27);
                  }
                  break;
               case 56:
                  if (curChar == 42)
                     jjstateSet[jjnewStateCnt++] = 61;
                  else if (curChar == 47)
                  {
                     if (kind > 6)
                        kind = 6;
                     jjCheckNAddStates(28, 30);
                  }
                  break;
               case 1:
               case 2:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 71)
                     kind = 71;
                  jjCheckNAddStates(31, 33);
                  break;
               case 4:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(5);
                  break;
               case 5:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 71)
                     kind = 71;
                  jjCheckNAddTwoStates(5, 6);
                  break;
               case 7:
                  if (curChar == 39)
                     jjAddStates(13, 15);
                  break;
               case 8:
                  if ((0xffffff7fffffdbffL & l) != 0L)
                     jjCheckNAdd(9);
                  break;
               case 9:
                  if (curChar == 39 && kind > 76)
                     kind = 76;
                  break;
               case 11:
                  if ((0x8400000000L & l) != 0L)
                     jjCheckNAdd(9);
                  break;
               case 12:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(13, 9);
                  break;
               case 13:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAdd(9);
                  break;
               case 14:
                  if ((0xf000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 15;
                  break;
               case 15:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAdd(13);
                  break;
               case 17:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 18;
                  break;
               case 18:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 19;
                  break;
               case 19:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 20;
                  break;
               case 20:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(11, 9);
                  break;
               case 22:
                  if (curChar == 34)
                     jjCheckNAddStates(9, 12);
                  break;
               case 23:
                  if ((0xfffffffbffffdbffL & l) != 0L)
                     jjCheckNAddStates(9, 12);
                  break;
               case 25:
                  if ((0x8400000000L & l) != 0L)
                     jjCheckNAddStates(9, 12);
                  break;
               case 27:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 28;
                  break;
               case 28:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 29;
                  break;
               case 29:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 30;
                  break;
               case 30:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStates(9, 12);
                  break;
               case 32:
                  if (curChar == 34 && kind > 77)
                     kind = 77;
                  break;
               case 33:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAddStates(34, 38);
                  break;
               case 34:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAddStates(9, 12);
                  break;
               case 35:
                  if ((0xf000000000000L & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 36;
                  break;
               case 36:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAdd(34);
                  break;
               case 37:
                  if (curChar != 36)
                     break;
                  if (kind > 78)
                     kind = 78;
                  jjCheckNAdd(38);
                  break;
               case 38:
                  if ((0x3ff00100fffc1ffL & l) == 0L)
                     break;
                  if (kind > 78)
                     kind = 78;
                  jjCheckNAdd(38);
                  break;
               case 39:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStates(0, 6);
                  break;
               case 40:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStates(39, 41);
                  break;
               case 42:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(43);
                  break;
               case 43:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(43, 6);
                  break;
               case 44:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(44, 45);
                  break;
               case 46:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(47);
                  break;
               case 47:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 71)
                     kind = 71;
                  jjCheckNAddTwoStates(47, 6);
                  break;
               case 48:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(48, 49);
                  break;
               case 49:
                  if (curChar != 46)
                     break;
                  if (kind > 71)
                     kind = 71;
                  jjCheckNAddStates(42, 45);
                  break;
               case 50:
               case 51:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 71)
                     kind = 71;
                  jjCheckNAddStates(46, 48);
                  break;
               case 53:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(54);
                  break;
               case 54:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 71)
                     kind = 71;
                  jjCheckNAddTwoStates(54, 6);
                  break;
               case 55:
                  if (curChar == 47)
                     jjAddStates(7, 8);
                  break;
               case 57:
                  if ((0xffffffffffffdbffL & l) == 0L)
                     break;
                  if (kind > 6)
                     kind = 6;
                  jjCheckNAddStates(28, 30);
                  break;
               case 58:
                  if ((0x2400L & l) != 0L && kind > 6)
                     kind = 6;
                  break;
               case 59:
                  if (curChar == 10 && kind > 6)
                     kind = 6;
                  break;
               case 60:
                  if (curChar == 13)
                     jjstateSet[jjnewStateCnt++] = 59;
                  break;
               case 61:
                  if (curChar == 42)
                     jjstateSet[jjnewStateCnt++] = 62;
                  break;
               case 62:
                  if ((0xffff7fffffffffffL & l) != 0L && kind > 7)
                     kind = 7;
                  break;
               case 63:
                  if (curChar == 42)
                     jjstateSet[jjnewStateCnt++] = 61;
                  break;
               case 64:
                  if ((0x3fe000000000000L & l) == 0L)
                     break;
                  if (kind > 66)
                     kind = 66;
                  jjCheckNAddStates(16, 18);
                  break;
               case 65:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(65, 66);
                  break;
               case 67:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 66)
                     kind = 66;
                  jjCheckNAdd(67);
                  break;
               case 68:
                  if (curChar != 48)
                     break;
                  if (kind > 66)
                     kind = 66;
                  jjCheckNAddStates(19, 27);
                  break;
               case 70:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(70, 66);
                  break;
               case 71:
                  if ((0xff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(71, 66);
                  break;
               case 73:
                  if ((0x3000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(73, 66);
                  break;
               case 75:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 66)
                     kind = 66;
                  jjstateSet[jjnewStateCnt++] = 75;
                  break;
               case 76:
                  if ((0xff000000000000L & l) == 0L)
                     break;
                  if (kind > 66)
                     kind = 66;
                  jjCheckNAdd(76);
                  break;
               case 78:
                  if ((0x3000000000000L & l) == 0L)
                     break;
                  if (kind > 66)
                     kind = 66;
                  jjstateSet[jjnewStateCnt++] = 78;
                  break;
               case 80:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjAddStates(49, 50);
                  break;
               case 81:
                  if (curChar == 46)
                     jjstateSet[jjnewStateCnt++] = 82;
                  break;
               case 82:
               case 83:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(83, 84);
                  break;
               case 85:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(86);
                  break;
               case 86:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 71)
                     kind = 71;
                  jjCheckNAddTwoStates(86, 6);
                  break;
               case 88:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddStates(51, 53);
                  break;
               case 89:
                  if (curChar == 46)
                     jjCheckNAdd(90);
                  break;
               case 91:
                  if ((0x280000000000L & l) != 0L)
                     jjCheckNAdd(92);
                  break;
               case 92:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 71)
                     kind = 71;
                  jjCheckNAddTwoStates(92, 6);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x7fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 78)
                     kind = 78;
                  jjCheckNAdd(38);
                  break;
               case 2:
                  if (curChar != 95)
                     break;
                  if (kind > 71)
                     kind = 71;
                  jjCheckNAddStates(31, 33);
                  break;
               case 3:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(54, 55);
                  break;
               case 6:
                  if ((0x5000000050L & l) != 0L && kind > 71)
                     kind = 71;
                  break;
               case 8:
                  if ((0xffffffffefffffffL & l) != 0L)
                     jjCheckNAdd(9);
                  break;
               case 10:
                  if (curChar == 92)
                     jjCheckNAddStates(56, 58);
                  break;
               case 11:
                  if ((0x14404410000000L & l) != 0L)
                     jjCheckNAdd(9);
                  break;
               case 16:
                  if (curChar == 117)
                     jjstateSet[jjnewStateCnt++] = 17;
                  break;
               case 17:
                  if ((0x7e0000007eL & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 18;
                  break;
               case 18:
                  if ((0x7e0000007eL & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 19;
                  break;
               case 19:
                  if ((0x7e0000007eL & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 20;
                  break;
               case 20:
                  if ((0x7e0000007eL & l) != 0L)
                     jjCheckNAddTwoStates(11, 9);
                  break;
               case 21:
                  if (curChar == 92)
                     jjstateSet[jjnewStateCnt++] = 16;
                  break;
               case 23:
                  if ((0xffffffffefffffffL & l) != 0L)
                     jjCheckNAddStates(9, 12);
                  break;
               case 24:
                  if (curChar == 92)
                     jjAddStates(59, 61);
                  break;
               case 25:
                  if ((0x14404410000000L & l) != 0L)
                     jjCheckNAddStates(9, 12);
                  break;
               case 26:
                  if (curChar == 117)
                     jjstateSet[jjnewStateCnt++] = 27;
                  break;
               case 27:
                  if ((0x7e0000007eL & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 28;
                  break;
               case 28:
                  if ((0x7e0000007eL & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 29;
                  break;
               case 29:
                  if ((0x7e0000007eL & l) != 0L)
                     jjstateSet[jjnewStateCnt++] = 30;
                  break;
               case 30:
                  if ((0x7e0000007eL & l) != 0L)
                     jjCheckNAddStates(9, 12);
                  break;
               case 31:
                  if (curChar == 92)
                     jjstateSet[jjnewStateCnt++] = 26;
                  break;
               case 38:
                  if ((0x87fffffe87fffffeL & l) == 0L)
                     break;
                  if (kind > 78)
                     kind = 78;
                  jjCheckNAdd(38);
                  break;
               case 40:
                  if (curChar == 95)
                     jjCheckNAddStates(39, 41);
                  break;
               case 41:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(62, 63);
                  break;
               case 44:
                  if (curChar == 95)
                     jjAddStates(64, 65);
                  break;
               case 45:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(66, 67);
                  break;
               case 48:
                  if (curChar == 95)
                     jjAddStates(68, 69);
                  break;
               case 51:
                  if (curChar != 95)
                     break;
                  if (kind > 71)
                     kind = 71;
                  jjCheckNAddStates(46, 48);
                  break;
               case 52:
                  if ((0x2000000020L & l) != 0L)
                     jjAddStates(70, 71);
                  break;
               case 57:
                  if (kind > 6)
                     kind = 6;
                  jjAddStates(28, 30);
                  break;
               case 62:
                  if (kind > 7)
                     kind = 7;
                  break;
               case 65:
                  if (curChar == 95)
                     jjCheckNAddTwoStates(65, 66);
                  break;
               case 66:
                  if ((0x100000001000L & l) != 0L && kind > 65)
                     kind = 65;
                  break;
               case 67:
                  if (curChar != 95)
                     break;
                  if (kind > 66)
                     kind = 66;
                  jjstateSet[jjnewStateCnt++] = 67;
                  break;
               case 69:
                  if ((0x100000001000000L & l) != 0L)
                     jjCheckNAdd(70);
                  break;
               case 70:
                  if ((0x7e8000007eL & l) != 0L)
                     jjCheckNAddTwoStates(70, 66);
                  break;
               case 71:
                  if (curChar == 95)
                     jjCheckNAddTwoStates(71, 66);
                  break;
               case 72:
                  if ((0x400000004L & l) != 0L)
                     jjCheckNAdd(73);
                  break;
               case 73:
                  if (curChar == 95)
                     jjCheckNAddTwoStates(73, 66);
                  break;
               case 74:
                  if ((0x100000001000000L & l) != 0L)
                     jjCheckNAdd(75);
                  break;
               case 75:
                  if ((0x7e8000007eL & l) == 0L)
                     break;
                  if (kind > 66)
                     kind = 66;
                  jjCheckNAdd(75);
                  break;
               case 76:
                  if (curChar != 95)
                     break;
                  if (kind > 66)
                     kind = 66;
                  jjstateSet[jjnewStateCnt++] = 76;
                  break;
               case 77:
                  if ((0x400000004L & l) != 0L)
                     jjCheckNAdd(78);
                  break;
               case 78:
                  if (curChar != 95)
                     break;
                  if (kind > 66)
                     kind = 66;
                  jjCheckNAdd(78);
                  break;
               case 79:
                  if ((0x100000001000000L & l) != 0L)
                     jjCheckNAddTwoStates(80, 81);
                  break;
               case 80:
                  if ((0x7e8000007eL & l) != 0L)
                     jjCheckNAddTwoStates(80, 81);
                  break;
               case 82:
                  if ((0x7e0000007eL & l) != 0L)
                     jjCheckNAddTwoStates(83, 84);
                  break;
               case 83:
                  if ((0x7e8000007eL & l) != 0L)
                     jjCheckNAddTwoStates(83, 84);
                  break;
               case 84:
                  if ((0x1000000010000L & l) != 0L)
                     jjAddStates(72, 73);
                  break;
               case 87:
                  if ((0x100000001000000L & l) != 0L)
                     jjCheckNAdd(88);
                  break;
               case 88:
                  if ((0x7e8000007eL & l) != 0L)
                     jjCheckNAddStates(51, 53);
                  break;
               case 90:
                  if ((0x1000000010000L & l) != 0L)
                     jjAddStates(74, 75);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int hiByte = (int)(curChar >> 8);
         int i1 = hiByte >> 6;
         long l1 = 1L << (hiByte & 077);
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if (!jjCanMove_1(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 78)
                     kind = 78;
                  jjCheckNAdd(38);
                  break;
               case 8:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjstateSet[jjnewStateCnt++] = 9;
                  break;
               case 23:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2))
                     jjAddStates(9, 12);
                  break;
               case 38:
                  if (!jjCanMove_2(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 78)
                     kind = 78;
                  jjCheckNAdd(38);
                  break;
               case 57:
                  if (!jjCanMove_0(hiByte, i1, i2, l1, l2))
                     break;
                  if (kind > 6)
                     kind = 6;
                  jjAddStates(28, 30);
                  break;
               case 62:
                  if (jjCanMove_0(hiByte, i1, i2, l1, l2) && kind > 7)
                     kind = 7;
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 93 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
private int jjMoveStringLiteralDfa0_2()
{
   switch(curChar)
   {
      case 42:
         return jjMoveStringLiteralDfa1_2(0x400L);
      default :
         return 1;
   }
}
private int jjMoveStringLiteralDfa1_2(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 1;
   }
   switch(curChar)
   {
      case 47:
         if ((active0 & 0x400L) != 0L)
            return jjStopAtPos(1, 10);
         break;
      default :
         return 2;
   }
   return 2;
}
private int jjMoveStringLiteralDfa0_1()
{
   switch(curChar)
   {
      case 42:
         return jjMoveStringLiteralDfa1_1(0x200L);
      default :
         return 1;
   }
}
private int jjMoveStringLiteralDfa1_1(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      return 1;
   }
   switch(curChar)
   {
      case 47:
         if ((active0 & 0x200L) != 0L)
            return jjStopAtPos(1, 9);
         break;
      default :
         return 2;
   }
   return 2;
}
static final int[] jjnextStates = {
   40, 41, 6, 44, 45, 48, 49, 56, 63, 23, 24, 31, 32, 8, 10, 21, 
   65, 66, 67, 69, 71, 66, 72, 74, 76, 77, 79, 87, 57, 58, 60, 2, 
   3, 6, 23, 24, 34, 31, 32, 40, 41, 6, 50, 51, 52, 6, 51, 52, 
   6, 80, 81, 88, 89, 90, 4, 5, 11, 12, 14, 25, 33, 35, 42, 43, 
   44, 45, 46, 47, 48, 49, 53, 54, 85, 86, 91, 92, 
};
private static final boolean jjCanMove_0(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec2[i2] & l2) != 0L);
      default :
         if ((jjbitVec0[i1] & l1) != 0L)
            return true;
         return false;
   }
}
private static final boolean jjCanMove_1(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec4[i2] & l2) != 0L);
      case 2:
         return ((jjbitVec5[i2] & l2) != 0L);
      case 3:
         return ((jjbitVec6[i2] & l2) != 0L);
      case 4:
         return ((jjbitVec7[i2] & l2) != 0L);
      case 5:
         return ((jjbitVec8[i2] & l2) != 0L);
      case 6:
         return ((jjbitVec9[i2] & l2) != 0L);
      case 7:
         return ((jjbitVec10[i2] & l2) != 0L);
      case 9:
         return ((jjbitVec11[i2] & l2) != 0L);
      case 10:
         return ((jjbitVec12[i2] & l2) != 0L);
      case 11:
         return ((jjbitVec13[i2] & l2) != 0L);
      case 12:
         return ((jjbitVec14[i2] & l2) != 0L);
      case 13:
         return ((jjbitVec15[i2] & l2) != 0L);
      case 14:
         return ((jjbitVec16[i2] & l2) != 0L);
      case 15:
         return ((jjbitVec17[i2] & l2) != 0L);
      case 16:
         return ((jjbitVec18[i2] & l2) != 0L);
      case 17:
         return ((jjbitVec19[i2] & l2) != 0L);
      case 18:
         return ((jjbitVec20[i2] & l2) != 0L);
      case 19:
         return ((jjbitVec21[i2] & l2) != 0L);
      case 20:
         return ((jjbitVec0[i2] & l2) != 0L);
      case 22:
         return ((jjbitVec22[i2] & l2) != 0L);
      case 23:
         return ((jjbitVec23[i2] & l2) != 0L);
      case 24:
         return ((jjbitVec24[i2] & l2) != 0L);
      case 25:
         return ((jjbitVec25[i2] & l2) != 0L);
      case 29:
         return ((jjbitVec26[i2] & l2) != 0L);
      case 30:
         return ((jjbitVec27[i2] & l2) != 0L);
      case 31:
         return ((jjbitVec28[i2] & l2) != 0L);
      case 32:
         return ((jjbitVec29[i2] & l2) != 0L);
      case 33:
         return ((jjbitVec30[i2] & l2) != 0L);
      case 48:
         return ((jjbitVec31[i2] & l2) != 0L);
      case 49:
         return ((jjbitVec32[i2] & l2) != 0L);
      case 77:
         return ((jjbitVec33[i2] & l2) != 0L);
      case 159:
         return ((jjbitVec34[i2] & l2) != 0L);
      case 164:
         return ((jjbitVec35[i2] & l2) != 0L);
      case 215:
         return ((jjbitVec36[i2] & l2) != 0L);
      case 216:
         return ((jjbitVec37[i2] & l2) != 0L);
      case 250:
         return ((jjbitVec38[i2] & l2) != 0L);
      case 251:
         return ((jjbitVec39[i2] & l2) != 0L);
      case 253:
         return ((jjbitVec40[i2] & l2) != 0L);
      case 254:
         return ((jjbitVec41[i2] & l2) != 0L);
      case 255:
         return ((jjbitVec42[i2] & l2) != 0L);
      default :
         if ((jjbitVec3[i1] & l1) != 0L)
            return true;
         return false;
   }
}
private static final boolean jjCanMove_2(int hiByte, int i1, int i2, long l1, long l2)
{
   switch(hiByte)
   {
      case 0:
         return ((jjbitVec43[i2] & l2) != 0L);
      case 2:
         return ((jjbitVec5[i2] & l2) != 0L);
      case 3:
         return ((jjbitVec44[i2] & l2) != 0L);
      case 4:
         return ((jjbitVec45[i2] & l2) != 0L);
      case 5:
         return ((jjbitVec46[i2] & l2) != 0L);
      case 6:
         return ((jjbitVec47[i2] & l2) != 0L);
      case 7:
         return ((jjbitVec48[i2] & l2) != 0L);
      case 9:
         return ((jjbitVec49[i2] & l2) != 0L);
      case 10:
         return ((jjbitVec50[i2] & l2) != 0L);
      case 11:
         return ((jjbitVec51[i2] & l2) != 0L);
      case 12:
         return ((jjbitVec52[i2] & l2) != 0L);
      case 13:
         return ((jjbitVec53[i2] & l2) != 0L);
      case 14:
         return ((jjbitVec54[i2] & l2) != 0L);
      case 15:
         return ((jjbitVec55[i2] & l2) != 0L);
      case 16:
         return ((jjbitVec56[i2] & l2) != 0L);
      case 17:
         return ((jjbitVec19[i2] & l2) != 0L);
      case 18:
         return ((jjbitVec20[i2] & l2) != 0L);
      case 19:
         return ((jjbitVec57[i2] & l2) != 0L);
      case 20:
         return ((jjbitVec0[i2] & l2) != 0L);
      case 22:
         return ((jjbitVec22[i2] & l2) != 0L);
      case 23:
         return ((jjbitVec58[i2] & l2) != 0L);
      case 24:
         return ((jjbitVec59[i2] & l2) != 0L);
      case 25:
         return ((jjbitVec60[i2] & l2) != 0L);
      case 29:
         return ((jjbitVec26[i2] & l2) != 0L);
      case 30:
         return ((jjbitVec27[i2] & l2) != 0L);
      case 31:
         return ((jjbitVec28[i2] & l2) != 0L);
      case 32:
         return ((jjbitVec61[i2] & l2) != 0L);
      case 33:
         return ((jjbitVec30[i2] & l2) != 0L);
      case 48:
         return ((jjbitVec62[i2] & l2) != 0L);
      case 49:
         return ((jjbitVec32[i2] & l2) != 0L);
      case 77:
         return ((jjbitVec33[i2] & l2) != 0L);
      case 159:
         return ((jjbitVec34[i2] & l2) != 0L);
      case 164:
         return ((jjbitVec35[i2] & l2) != 0L);
      case 215:
         return ((jjbitVec36[i2] & l2) != 0L);
      case 216:
         return ((jjbitVec63[i2] & l2) != 0L);
      case 220:
         return ((jjbitVec64[i2] & l2) != 0L);
      case 221:
         return ((jjbitVec65[i2] & l2) != 0L);
      case 250:
         return ((jjbitVec38[i2] & l2) != 0L);
      case 251:
         return ((jjbitVec66[i2] & l2) != 0L);
      case 253:
         return ((jjbitVec40[i2] & l2) != 0L);
      case 254:
         return ((jjbitVec67[i2] & l2) != 0L);
      case 255:
         return ((jjbitVec68[i2] & l2) != 0L);
      default :
         if ((jjbitVec3[i1] & l1) != 0L)
            return true;
         return false;
   }
}

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, null, null, null, null, 
"\141\142\163\164\162\141\143\164", "\141\163\163\145\162\164", "\142\157\157\154\145\141\156", 
"\142\162\145\141\153", "\142\171\164\145", "\143\141\163\145", "\143\141\164\143\150", 
"\143\150\141\162", "\143\154\141\163\163", "\143\157\156\163\164", 
"\143\157\156\164\151\156\165\145", "\144\145\146\141\165\154\164", "\144\157", "\144\157\165\142\154\145", 
"\145\154\163\145", "\145\156\165\155", "\145\170\164\145\156\144\163", "\146\141\154\163\145", 
"\146\151\156\141\154", "\146\151\156\141\154\154\171", "\146\154\157\141\164", "\146\157\162", 
"\147\157\164\157", "\151\146", "\151\155\160\154\145\155\145\156\164\163", 
"\151\155\160\157\162\164", "\151\156\163\164\141\156\143\145\157\146", "\151\156\164", 
"\151\156\164\145\162\146\141\143\145", "\154\157\156\147", "\156\141\164\151\166\145", "\156\145\167", 
"\156\165\154\154", "\160\141\143\153\141\147\145", "\160\162\151\166\141\164\145", 
"\160\162\157\164\145\143\164\145\144", "\160\165\142\154\151\143", "\162\145\164\165\162\156", 
"\163\150\157\162\164", "\163\164\141\164\151\143", "\163\164\162\151\143\164\146\160", 
"\163\165\160\145\162", "\163\167\151\164\143\150", 
"\163\171\156\143\150\162\157\156\151\172\145\144", "\164\150\151\163", "\164\150\162\157\167", "\164\150\162\157\167\163", 
"\164\162\141\156\163\151\145\156\164", "\164\162\165\145", "\164\162\171", "\166\157\151\144", 
"\166\157\154\141\164\151\154\145", "\167\150\151\154\145", null, null, null, null, null, null, null, null, null, 
null, null, null, null, null, null, null, "\50", "\51", "\173", "\175", "\133", 
"\135", "\73", "\54", "\56", "\100", "\75", "\74", "\41", "\176", "\77", "\72", 
"\75\75", "\74\75", "\76\75", "\41\75", "\174\174", "\46\46", "\53\53", "\55\55", "\53", 
"\55", "\52", "\57", "\46", "\174", "\136", "\45", "\74\74", "\53\75", "\55\75", 
"\52\75", "\57\75", "\46\75", "\174\75", "\136\75", "\45\75", "\74\74\75", "\76\76\75", 
"\76\76\76\75", "\56\56\56", "\76\76\76", "\76\76", "\76", "\32", "\72\72", "\55\76", };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
   "IN_JAVA_DOC_COMMENT",
   "IN_MULTI_LINE_COMMENT",
};

/** Lex State array. */
public static final int[] jjnewLexState = {
   -1, -1, -1, -1, -1, -1, -1, 1, 2, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 
   -1, -1, -1, -1, -1, -1, -1, 
};
static final long[] jjtoToken = {
   0xfffffffffffff001L, 0xfffffffffffe7087L, 0xfL, 
};
static final long[] jjtoSkip = {
   0x67eL, 0x0L, 0x0L, 
};
static final long[] jjtoSpecial = {
   0x640L, 0x0L, 0x0L, 
};
static final long[] jjtoMore = {
   0x980L, 0x0L, 0x0L, 
};
protected SimpleCharStream input_stream;
private final int[] jjrounds = new int[93];
private final int[] jjstateSet = new int[186];
private final StringBuilder jjimage = new StringBuilder();
private StringBuilder image = jjimage;
private int jjimageLen;
private int lengthOfMatch;
protected char curChar;
/** Constructor. */
public ASTParserTokenManager(SimpleCharStream stream){
   if (SimpleCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}

/** Constructor. */
public ASTParserTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 93; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
public void SwitchTo(int lexState)
{
   if (lexState >= 3 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = ASTParser.GTToken.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token specialToken = null;
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      matchedToken.specialToken = specialToken;
      CommonTokenAction(matchedToken);
      return matchedToken;
   }
   image = jjimage;
   image.setLength(0);
   jjimageLen = 0;

   for (;;)
   {
     switch(curLexState)
     {
       case 0:
         try { input_stream.backup(0);
            while (curChar <= 32 && (0x100003600L & (1L << curChar)) != 0L)
               curChar = input_stream.BeginToken();
         }
         catch (java.io.IOException e1) { continue EOFLoop; }
         jjmatchedKind = 0x7fffffff;
         jjmatchedPos = 0;
         curPos = jjMoveStringLiteralDfa0_0();
         break;
       case 1:
         jjmatchedKind = 0x7fffffff;
         jjmatchedPos = 0;
         curPos = jjMoveStringLiteralDfa0_1();
         if (jjmatchedPos == 0 && jjmatchedKind > 11)
         {
            jjmatchedKind = 11;
         }
         break;
       case 2:
         jjmatchedKind = 0x7fffffff;
         jjmatchedPos = 0;
         curPos = jjMoveStringLiteralDfa0_2();
         if (jjmatchedPos == 0 && jjmatchedKind > 11)
         {
            jjmatchedKind = 11;
         }
         break;
     }
     if (jjmatchedKind != 0x7fffffff)
     {
        if (jjmatchedPos + 1 < curPos)
           input_stream.backup(curPos - jjmatchedPos - 1);
        if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
        {
           matchedToken = jjFillToken();
           matchedToken.specialToken = specialToken;
           TokenLexicalActions(matchedToken);
       if (jjnewLexState[jjmatchedKind] != -1)
         curLexState = jjnewLexState[jjmatchedKind];
           CommonTokenAction(matchedToken);
           return matchedToken;
        }
        else if ((jjtoSkip[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
        {
           if ((jjtoSpecial[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
           {
              matchedToken = jjFillToken();
              if (specialToken == null)
                 specialToken = matchedToken;
              else
              {
                 matchedToken.specialToken = specialToken;
                 specialToken = (specialToken.next = matchedToken);
              }
              SkipLexicalActions(matchedToken);
           }
           else
              SkipLexicalActions(null);
         if (jjnewLexState[jjmatchedKind] != -1)
           curLexState = jjnewLexState[jjmatchedKind];
           continue EOFLoop;
        }
        MoreLexicalActions();
      if (jjnewLexState[jjmatchedKind] != -1)
        curLexState = jjnewLexState[jjmatchedKind];
        curPos = 0;
        jjmatchedKind = 0x7fffffff;
        try {
           curChar = input_stream.readChar();
           continue;
        }
        catch (java.io.IOException e1) { }
     }
     int error_line = input_stream.getEndLine();
     int error_column = input_stream.getEndColumn();
     String error_after = null;
     boolean EOFSeen = false;
     try { input_stream.readChar(); input_stream.backup(1); }
     catch (java.io.IOException e1) {
        EOFSeen = true;
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
        if (curChar == '\n' || curChar == '\r') {
           error_line++;
           error_column = 0;
        }
        else
           error_column++;
     }
     if (!EOFSeen) {
        input_stream.backup(1);
        error_after = curPos <= 1 ? "" : input_stream.GetImage();
     }
     throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
   }
  }
}

void SkipLexicalActions(Token matchedToken)
{
   switch(jjmatchedKind)
   {
      default :
         break;
   }
}
void MoreLexicalActions()
{
   jjimageLen += (lengthOfMatch = jjmatchedPos + 1);
   switch(jjmatchedKind)
   {
      case 7 :
         image.append(input_stream.GetSuffix(jjimageLen));
         jjimageLen = 0;
    input_stream.backup(1);
         break;
      default :
         break;
   }
}
void TokenLexicalActions(Token matchedToken)
{
   switch(jjmatchedKind)
   {
      case 126 :
        image.append(jjstrLiteralImages[126]);
        lengthOfMatch = jjstrLiteralImages[126].length();
    matchedToken.kind = GT;
    ((ASTParser.GTToken) matchedToken).realKind = RUNSIGNEDSHIFT;
    input_stream.backup(2);
         break;
      case 127 :
        image.append(jjstrLiteralImages[127]);
        lengthOfMatch = jjstrLiteralImages[127].length();
    matchedToken.kind = GT;
    ((ASTParser.GTToken) matchedToken).realKind = RSIGNEDSHIFT;
    input_stream.backup(1);
         break;
      default :
         break;
   }
}
private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

private void jjCheckNAddStates(int start, int end)
{
   do {
      jjCheckNAdd(jjnextStates[start]);
   } while (start++ != end);
}

}
