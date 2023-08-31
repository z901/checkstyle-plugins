package hu.rxd.druid.checkstyle;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

import java.util.HashSet;
import java.util.Set;

public class FunctionArgsAlignmentRule extends AbstractCheck
{
  public static final String ARGS_MISALIGNED = "arguments.misaligned";
  public static final String ARGS_MIXED_LINES = "arguments.mixed.lines";

  @Override
  public int[] getRequiredTokens()
  {

    return new int[] {TokenTypes.METHOD_CALL};
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
}
