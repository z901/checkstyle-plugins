package hu.rxd.checkstyle;

import com.google.common.collect.Sets;
import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class FunctionArgsAlignmentRule extends AbstractCheck
{
  public static final String ARGS_MISALIGNED = "arguments.misaligned";
  public static final String ARGS_MIXED_LINES = "arguments.mixed.lines";
  private Set<String> ignores = Collections.emptySet();

  @Override
  public int[] getRequiredTokens()
  {
    return new int[] {TokenTypes.METHOD_CALL};
  }

  public void setIgnore(String ignores)
  {
    this.ignores = Sets.newHashSet(ignores.split(","));
  }

  @Override
  public int[] getDefaultTokens()
  {
    return getRequiredTokens();
  }

  @Override
  public int[] getAcceptableTokens()
  {
    return getRequiredTokens();
  }

  @Override
  public void visitToken(DetailAST astCall)
  {
    if (true) {
      String name = collectMethodNames(astCall.getFirstChild());
      // DetailAST method = astCall.getFirstChild();
      // method.get
      // throw new RuntimeException(name);
      if (ignores.contains(name)) {
//        throw new RuntimeException(name);
        return;
      }
    }
    DetailAST args = astCall.findFirstToken(TokenTypes.ELIST);
    if (args.getChildCount() == 0) {
      return;
    }
    int childCount = 0;
    Set<Integer> childLineNos = new HashSet<>();
    Set<Integer> childColNos = new HashSet<>();

    for (DetailAST ast = args.getFirstChild(); ast != null; ast = ast.getNextSibling()) {
      if (ast.getType() != TokenTypes.EXPR) {
        continue;
      }
      childCount++;
      childLineNos.add(ast.getLineNo());
      childColNos.add(ast.getColumnNo());
    }

    if (childLineNos.size() == 1) {
      // ok; all args on same line
      return;
    }

    if (childCount != childLineNos.size()) {
      log(astCall, ARGS_MIXED_LINES);
      return;
    }

    if (childColNos.size() != 1) {
      log(astCall, ARGS_MISALIGNED);
    }
  }

  private String collectMethodNames(DetailAST ast)
  {
    switch (ast.getType())
    {
    case TokenTypes.DOT:
      return collectMethodNames(ast.getLastChild());
    case TokenTypes.IDENT:
      return ast.getText();
    default:
      throw new RuntimeException("unexpected type");
    }

  }
}
