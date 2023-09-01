package hu.rxd.checkstyle;

import com.puppycrawl.tools.checkstyle.AbstractModuleTestSupport;
import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import org.junit.Test;

public class FunctionArgsAlignmentRuleTest extends AbstractModuleTestSupport
{
  @Override
  protected String getPackageLocation()
  {
    return getClass().getPackage().getName().replaceAll("\\.", "/");
  }

  @Test
  public void testArgsSameLineOrAligned() throws Exception
  {
    final DefaultConfiguration checkConfig = new DefaultConfiguration(FunctionArgsAlignmentRule.class.getName());

    final String[] expected = {
        "5:22: " + getCheckMessage(FunctionArgsAlignmentRule.ARGS_MIXED_LINES),
        "7:22: " + getCheckMessage(FunctionArgsAlignmentRule.ARGS_MIXED_LINES),
        "10:22: " + getCheckMessage(FunctionArgsAlignmentRule.ARGS_MISALIGNED),
    };
    Checker checker = createChecker(checkConfig);
    verify(checker, getPath("ArgsSameLineOrAligned.java"), expected);
  }

}
