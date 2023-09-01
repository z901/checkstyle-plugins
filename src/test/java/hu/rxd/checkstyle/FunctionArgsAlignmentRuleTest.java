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
    checkConfig.addAttribute("columnAlignmentFirst", "true");

    final String[] expected = {
        "5:22: " + getCheckMessage(FunctionArgsAlignmentRule.ARGS_MIXED_LINES, "printf"),
        "7:22: " + getCheckMessage(FunctionArgsAlignmentRule.ARGS_MIXED_LINES, "printf"),
        "11:22: " + getCheckMessage(FunctionArgsAlignmentRule.ARGS_MISALIGNED, "printf"),
    };
    Checker checker = createChecker(checkConfig);
    verify(checker, getPath("ArgsSameLineOrAligned.java"), expected);
  }

  @Test
  public void testArgsSame2() throws Exception
  {
    final DefaultConfiguration checkConfig = new DefaultConfiguration(FunctionArgsAlignmentRule.class.getName());
    checkConfig.addAttribute("columnAlignmentFirst", "true");

    final String[] expected = {
    };
    Checker checker = createChecker(checkConfig);
    verify(checker, getPath("ArgsSame2.java"), expected);
  }

}
