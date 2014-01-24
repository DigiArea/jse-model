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
/* JavaCCOptions:STATIC=false,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
package com.digiarea.jse.parser;

/**
 * An implementation of interface CharStream, where the stream is assumed to
 * contain only ASCII characters (with java-like unicode escape processing).
 */

public
class JavaCharStream
{
  /** Whether parser is static. */
  public static final boolean staticFlag = false;

  /**
   * Hexval.
   *
   * @param c the c
   * @return the int
   * @throws IOException Signals that an I/O exception has occurred.
   */
  static final int hexval(char c) throws java.io.IOException {
    switch(c)
    {
       case '0' :
          return 0;
       case '1' :
          return 1;
       case '2' :
          return 2;
       case '3' :
          return 3;
       case '4' :
          return 4;
       case '5' :
          return 5;
       case '6' :
          return 6;
       case '7' :
          return 7;
       case '8' :
          return 8;
       case '9' :
          return 9;

       case 'a' :
       case 'A' :
          return 10;
       case 'b' :
       case 'B' :
          return 11;
       case 'c' :
       case 'C' :
          return 12;
       case 'd' :
       case 'D' :
          return 13;
       case 'e' :
       case 'E' :
          return 14;
       case 'f' :
       case 'F' :
          return 15;
    }

    throw new java.io.IOException(); // Should never come here
  }

/** Position in buffer. */
  public int bufpos = -1;
  
  /**
   * The bufsize.
   */
  int bufsize;
  
  /**
   * The available.
   */
  int available;
  
  /**
   * The token begin.
   */
  int tokenBegin;
  
  /**
   * The bufline.
   */
  protected int bufline[];
  
  /**
   * The bufcolumn.
   */
  protected int bufcolumn[];

  /**
   * The column.
   */
  protected int column = 0;
  
  /**
   * The line.
   */
  protected int line = 1;

  /**
   * The prev char is cr.
   */
  protected boolean prevCharIsCR = false;
  
  /**
   * The prev char is lf.
   */
  protected boolean prevCharIsLF = false;

  /**
   * The input stream.
   */
  protected java.io.Reader inputStream;

  /**
   * The next char buf.
   */
  protected char[] nextCharBuf;
  
  /**
   * The buffer.
   */
  protected char[] buffer;
  
  /**
   * The max next char ind.
   */
  protected int maxNextCharInd = 0;
  
  /**
   * The next char ind.
   */
  protected int nextCharInd = -1;
  
  /**
   * The in buf.
   */
  protected int inBuf = 0;
  
  /**
   * The tab size.
   */
  protected int tabSize = 8;

  /**
   * Sets the tab size.
   *
   * @param i the new tab size
   */
  protected void setTabSize(int i) { tabSize = i; }
  
  /**
   * Gets the tab size.
   *
   * @param i the i
   * @return the tab size
   */
  protected int getTabSize(int i) { return tabSize; }

  /**
   * Expand buff.
   *
   * @param wrapAround the wrap around
   */
  protected void ExpandBuff(boolean wrapAround)
  {
    char[] newbuffer = new char[bufsize + 2048];
    int newbufline[] = new int[bufsize + 2048];
    int newbufcolumn[] = new int[bufsize + 2048];

    try
    {
      if (wrapAround)
      {
        System.arraycopy(buffer, tokenBegin, newbuffer, 0, bufsize - tokenBegin);
        System.arraycopy(buffer, 0, newbuffer, bufsize - tokenBegin, bufpos);
        buffer = newbuffer;

        System.arraycopy(bufline, tokenBegin, newbufline, 0, bufsize - tokenBegin);
        System.arraycopy(bufline, 0, newbufline, bufsize - tokenBegin, bufpos);
        bufline = newbufline;

        System.arraycopy(bufcolumn, tokenBegin, newbufcolumn, 0, bufsize - tokenBegin);
        System.arraycopy(bufcolumn, 0, newbufcolumn, bufsize - tokenBegin, bufpos);
        bufcolumn = newbufcolumn;

        bufpos += (bufsize - tokenBegin);
    }
    else
    {
        System.arraycopy(buffer, tokenBegin, newbuffer, 0, bufsize - tokenBegin);
        buffer = newbuffer;

        System.arraycopy(bufline, tokenBegin, newbufline, 0, bufsize - tokenBegin);
        bufline = newbufline;

        System.arraycopy(bufcolumn, tokenBegin, newbufcolumn, 0, bufsize - tokenBegin);
        bufcolumn = newbufcolumn;

        bufpos -= tokenBegin;
      }
    }
    catch (Throwable t)
    {
      throw new Error(t.getMessage());
    }

    available = (bufsize += 2048);
    tokenBegin = 0;
  }

  /**
   * Fill buff.
   *
   * @throws IOException Signals that an I/O exception has occurred.
   */
  protected void FillBuff() throws java.io.IOException
  {
    int i;
    if (maxNextCharInd == 4096)
      maxNextCharInd = nextCharInd = 0;

    try {
      if ((i = inputStream.read(nextCharBuf, maxNextCharInd,
                                          4096 - maxNextCharInd)) == -1)
      {
        inputStream.close();
        throw new java.io.IOException();
      }
      else
         maxNextCharInd += i;
      return;
    }
    catch(java.io.IOException e) {
      if (bufpos != 0)
      {
        --bufpos;
        backup(0);
      }
      else
      {
        bufline[bufpos] = line;
        bufcolumn[bufpos] = column;
      }
      throw e;
    }
  }

  /**
   * Read byte.
   *
   * @return the char
   * @throws IOException Signals that an I/O exception has occurred.
   */
  protected char ReadByte() throws java.io.IOException
  {
    if (++nextCharInd >= maxNextCharInd)
      FillBuff();

    return nextCharBuf[nextCharInd];
  }

/**
 * Begin token.
 *
 * @return starting character for token.
 * @throws IOException Signals that an I/O exception has occurred.
 */
  public char BeginToken() throws java.io.IOException
  {
    if (inBuf > 0)
    {
      --inBuf;

      if (++bufpos == bufsize)
        bufpos = 0;

      tokenBegin = bufpos;
      return buffer[bufpos];
    }

    tokenBegin = 0;
    bufpos = -1;

    return readChar();
  }

  /**
   * Adjust buff size.
   */
  protected void AdjustBuffSize()
  {
    if (available == bufsize)
    {
      if (tokenBegin > 2048)
      {
        bufpos = 0;
        available = tokenBegin;
      }
      else
        ExpandBuff(false);
    }
    else if (available > tokenBegin)
      available = bufsize;
    else if ((tokenBegin - available) < 2048)
      ExpandBuff(true);
    else
      available = tokenBegin;
  }

  /**
   * Update line column.
   *
   * @param c the c
   */
  protected void UpdateLineColumn(char c)
  {
    column++;

    if (prevCharIsLF)
    {
      prevCharIsLF = false;
      line += (column = 1);
    }
    else if (prevCharIsCR)
    {
      prevCharIsCR = false;
      if (c == '\n')
      {
        prevCharIsLF = true;
      }
      else
        line += (column = 1);
    }

    switch (c)
    {
      case '\r' :
        prevCharIsCR = true;
        break;
      case '\n' :
        prevCharIsLF = true;
        break;
      case '\t' :
        column--;
        column += (tabSize - (column % tabSize));
        break;
      default :
        break;
    }

    bufline[bufpos] = line;
    bufcolumn[bufpos] = column;
  }

/**
 *  Read a character.
 *
 * @return the char
 * @throws IOException Signals that an I/O exception has occurred.
 */
  public char readChar() throws java.io.IOException
  {
    if (inBuf > 0)
    {
      --inBuf;

      if (++bufpos == bufsize)
        bufpos = 0;

      return buffer[bufpos];
    }

    char c;

    if (++bufpos == available)
      AdjustBuffSize();

    if ((buffer[bufpos] = c = ReadByte()) == '\\')
    {
      UpdateLineColumn(c);

      int backSlashCnt = 1;

      for (;;) // Read all the backslashes
      {
        if (++bufpos == available)
          AdjustBuffSize();

        try
        {
          if ((buffer[bufpos] = c = ReadByte()) != '\\')
          {
            UpdateLineColumn(c);
            // found a non-backslash char.
            if ((c == 'u') && ((backSlashCnt & 1) == 1))
            {
              if (--bufpos < 0)
                bufpos = bufsize - 1;

              break;
            }

            backup(backSlashCnt);
            return '\\';
          }
        }
        catch(java.io.IOException e)
        {
	  // We are returning one backslash so we should only backup (count-1)
          if (backSlashCnt > 1)
            backup(backSlashCnt-1);

          return '\\';
        }

        UpdateLineColumn(c);
        backSlashCnt++;
      }

      // Here, we have seen an odd number of backslash's followed by a 'u'
      try
      {
        while ((c = ReadByte()) == 'u')
          ++column;

        buffer[bufpos] = c = (char)(hexval(c) << 12 |
                                    hexval(ReadByte()) << 8 |
                                    hexval(ReadByte()) << 4 |
                                    hexval(ReadByte()));

        column += 4;
      }
      catch(java.io.IOException e)
      {
        throw new Error("Invalid escape character at line " + line +
                                         " column " + column + ".");
      }

      if (backSlashCnt == 1)
        return c;
      else
      {
        backup(backSlashCnt - 1);
        return '\\';
      }
    }
    else
    {
      UpdateLineColumn(c);
      return c;
    }
  }

  /**
   * Gets the column.
   *
   * @return the column
   */
  @Deprecated
  /**
   * @deprecated
   * @see #getEndColumn
   */
  public int getColumn() {
    return bufcolumn[bufpos];
  }

  /**
   * Gets the line.
   *
   * @return the line
   */
  @Deprecated
  /**
   * @deprecated
   * @see #getEndLine
   */
  public int getLine() {
    return bufline[bufpos];
  }

/**
 *  Get end column.
 *
 * @return the end column
 */
  public int getEndColumn() {
    return bufcolumn[bufpos];
  }

/**
 *  Get end line.
 *
 * @return the end line
 */
  public int getEndLine() {
    return bufline[bufpos];
  }

/**
 * Gets the begin column.
 *
 * @return column of token start
 */
  public int getBeginColumn() {
    return bufcolumn[tokenBegin];
  }

/**
 * Gets the begin line.
 *
 * @return line number of token start
 */
  public int getBeginLine() {
    return bufline[tokenBegin];
  }

/**
 *  Retreat.
 *
 * @param amount the amount
 */
  public void backup(int amount) {

    inBuf += amount;
    if ((bufpos -= amount) < 0)
      bufpos += bufsize;
  }

/**
 *  Constructor.
 *
 * @param dstream the dstream
 * @param startline the startline
 * @param startcolumn the startcolumn
 * @param buffersize the buffersize
 */
  public JavaCharStream(java.io.Reader dstream,
                 int startline, int startcolumn, int buffersize)
  {
    inputStream = dstream;
    line = startline;
    column = startcolumn - 1;

    available = bufsize = buffersize;
    buffer = new char[buffersize];
    bufline = new int[buffersize];
    bufcolumn = new int[buffersize];
    nextCharBuf = new char[4096];
  }

/**
 *  Constructor.
 *
 * @param dstream the dstream
 * @param startline the startline
 * @param startcolumn the startcolumn
 */
  public JavaCharStream(java.io.Reader dstream,
                                        int startline, int startcolumn)
  {
    this(dstream, startline, startcolumn, 4096);
  }

/**
 *  Constructor.
 *
 * @param dstream the dstream
 */
  public JavaCharStream(java.io.Reader dstream)
  {
    this(dstream, 1, 1, 4096);
  }

/**
 *  Reinitialise.
 *
 * @param dstream the dstream
 * @param startline the startline
 * @param startcolumn the startcolumn
 * @param buffersize the buffersize
 */
  public void ReInit(java.io.Reader dstream,
                 int startline, int startcolumn, int buffersize)
  {
    inputStream = dstream;
    line = startline;
    column = startcolumn - 1;

    if (buffer == null || buffersize != buffer.length)
    {
      available = bufsize = buffersize;
      buffer = new char[buffersize];
      bufline = new int[buffersize];
      bufcolumn = new int[buffersize];
      nextCharBuf = new char[4096];
    }
    prevCharIsLF = prevCharIsCR = false;
    tokenBegin = inBuf = maxNextCharInd = 0;
    nextCharInd = bufpos = -1;
  }

/**
 *  Reinitialise.
 *
 * @param dstream the dstream
 * @param startline the startline
 * @param startcolumn the startcolumn
 */
  public void ReInit(java.io.Reader dstream,
                                        int startline, int startcolumn)
  {
    ReInit(dstream, startline, startcolumn, 4096);
  }

/**
 *  Reinitialise.
 *
 * @param dstream the dstream
 */
  public void ReInit(java.io.Reader dstream)
  {
    ReInit(dstream, 1, 1, 4096);
  }

/**
 *  Constructor.
 *
 * @param dstream the dstream
 * @param encoding the encoding
 * @param startline the startline
 * @param startcolumn the startcolumn
 * @param buffersize the buffersize
 * @throws UnsupportedEncodingException the unsupported encoding exception
 */
  public JavaCharStream(java.io.InputStream dstream, String encoding, int startline,
  int startcolumn, int buffersize) throws java.io.UnsupportedEncodingException
  {
    this(encoding == null ? new java.io.InputStreamReader(dstream) : new java.io.InputStreamReader(dstream, encoding), startline, startcolumn, buffersize);
  }

/**
 *  Constructor.
 *
 * @param dstream the dstream
 * @param startline the startline
 * @param startcolumn the startcolumn
 * @param buffersize the buffersize
 */
  public JavaCharStream(java.io.InputStream dstream, int startline,
  int startcolumn, int buffersize)
  {
    this(new java.io.InputStreamReader(dstream), startline, startcolumn, 4096);
  }

/**
 *  Constructor.
 *
 * @param dstream the dstream
 * @param encoding the encoding
 * @param startline the startline
 * @param startcolumn the startcolumn
 * @throws UnsupportedEncodingException the unsupported encoding exception
 */
  public JavaCharStream(java.io.InputStream dstream, String encoding, int startline,
                        int startcolumn) throws java.io.UnsupportedEncodingException
  {
    this(dstream, encoding, startline, startcolumn, 4096);
  }

/**
 *  Constructor.
 *
 * @param dstream the dstream
 * @param startline the startline
 * @param startcolumn the startcolumn
 */
  public JavaCharStream(java.io.InputStream dstream, int startline,
                        int startcolumn)
  {
    this(dstream, startline, startcolumn, 4096);
  }

/**
 *  Constructor.
 *
 * @param dstream the dstream
 * @param encoding the encoding
 * @throws UnsupportedEncodingException the unsupported encoding exception
 */
  public JavaCharStream(java.io.InputStream dstream, String encoding) throws java.io.UnsupportedEncodingException
  {
    this(dstream, encoding, 1, 1, 4096);
  }

/**
 *  Constructor.
 *
 * @param dstream the dstream
 */
  public JavaCharStream(java.io.InputStream dstream)
  {
    this(dstream, 1, 1, 4096);
  }

/**
 *  Reinitialise.
 *
 * @param dstream the dstream
 * @param encoding the encoding
 * @param startline the startline
 * @param startcolumn the startcolumn
 * @param buffersize the buffersize
 * @throws UnsupportedEncodingException the unsupported encoding exception
 */
  public void ReInit(java.io.InputStream dstream, String encoding, int startline,
  int startcolumn, int buffersize) throws java.io.UnsupportedEncodingException
  {
    ReInit(encoding == null ? new java.io.InputStreamReader(dstream) : new java.io.InputStreamReader(dstream, encoding), startline, startcolumn, buffersize);
  }

/**
 *  Reinitialise.
 *
 * @param dstream the dstream
 * @param startline the startline
 * @param startcolumn the startcolumn
 * @param buffersize the buffersize
 */
  public void ReInit(java.io.InputStream dstream, int startline,
  int startcolumn, int buffersize)
  {
    ReInit(new java.io.InputStreamReader(dstream), startline, startcolumn, buffersize);
  }

/**
 *  Reinitialise.
 *
 * @param dstream the dstream
 * @param encoding the encoding
 * @param startline the startline
 * @param startcolumn the startcolumn
 * @throws UnsupportedEncodingException the unsupported encoding exception
 */
  public void ReInit(java.io.InputStream dstream, String encoding, int startline,
                     int startcolumn) throws java.io.UnsupportedEncodingException
  {
    ReInit(dstream, encoding, startline, startcolumn, 4096);
  }

/**
 *  Reinitialise.
 *
 * @param dstream the dstream
 * @param startline the startline
 * @param startcolumn the startcolumn
 */
  public void ReInit(java.io.InputStream dstream, int startline,
                     int startcolumn)
  {
    ReInit(dstream, startline, startcolumn, 4096);
  }

/**
 *  Reinitialise.
 *
 * @param dstream the dstream
 * @param encoding the encoding
 * @throws UnsupportedEncodingException the unsupported encoding exception
 */
  public void ReInit(java.io.InputStream dstream, String encoding) throws java.io.UnsupportedEncodingException
  {
    ReInit(dstream, encoding, 1, 1, 4096);
  }

/**
 *  Reinitialise.
 *
 * @param dstream the dstream
 */
  public void ReInit(java.io.InputStream dstream)
  {
    ReInit(dstream, 1, 1, 4096);
  }

  /**
   * Gets the image.
   *
   * @return token image as String
   */
  public String GetImage()
  {
    if (bufpos >= tokenBegin)
      return new String(buffer, tokenBegin, bufpos - tokenBegin + 1);
    else
      return new String(buffer, tokenBegin, bufsize - tokenBegin) +
                              new String(buffer, 0, bufpos + 1);
  }

  /**
   * Gets the suffix.
   *
   * @param len the len
   * @return suffix
   */
  public char[] GetSuffix(int len)
  {
    char[] ret = new char[len];

    if ((bufpos + 1) >= len)
      System.arraycopy(buffer, bufpos - len + 1, ret, 0, len);
    else
    {
      System.arraycopy(buffer, bufsize - (len - bufpos - 1), ret, 0,
                                                        len - bufpos - 1);
      System.arraycopy(buffer, 0, ret, len - bufpos - 1, bufpos + 1);
    }

    return ret;
  }

  /** Set buffers back to null when finished. */
  public void Done()
  {
    nextCharBuf = null;
    buffer = null;
    bufline = null;
    bufcolumn = null;
  }

  /**
   * Method to adjust line and column numbers for the start of a token.
   *
   * @param newLine the new line
   * @param newCol the new col
   */
  public void adjustBeginLineColumn(int newLine, int newCol)
  {
    int start = tokenBegin;
    int len;

    if (bufpos >= tokenBegin)
    {
      len = bufpos - tokenBegin + inBuf + 1;
    }
    else
    {
      len = bufsize - tokenBegin + bufpos + 1 + inBuf;
    }

    int i = 0, j = 0, k = 0;
    int nextColDiff = 0, columnDiff = 0;

    while (i < len && bufline[j = start % bufsize] == bufline[k = ++start % bufsize])
    {
      bufline[j] = newLine;
      nextColDiff = columnDiff + bufcolumn[k] - bufcolumn[j];
      bufcolumn[j] = newCol + columnDiff;
      columnDiff = nextColDiff;
      i++;
    }

    if (i < len)
    {
      bufline[j] = newLine++;
      bufcolumn[j] = newCol + columnDiff;

      while (i++ < len)
      {
        if (bufline[j = start % bufsize] != bufline[++start % bufsize])
          bufline[j] = newLine++;
        else
          bufline[j] = newLine;
      }
    }

    line = bufline[j];
    column = bufcolumn[j];
  }

}
/* JavaCC - OriginalChecksum=8c7d6b8a196ed204a5c3068d98ec5e04 (do not edit this line) */
