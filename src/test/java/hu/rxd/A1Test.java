package hu.rxd;

import com.puppycrawl.tools.checkstyle.AbstractModuleTestSupport;
import com.puppycrawl.tools.checkstyle.Checker;
import com.puppycrawl.tools.checkstyle.DefaultConfiguration;
import org.junit.Test;

import static com.puppycrawl.tools.checkstyle.checks.naming.AbstractNameCheck.MSG_INVALID_PATTERN;

public class A1Test extends AbstractModuleTestSupport
{
  @Override
  protected String getPackageLocation()
  {
    return "hu/rxd";
  }

  @Test
  public void testExtendsCase() throws Exception
  {
    final DefaultConfiguration checkConfig = new DefaultConfiguration(A1.class.getName());

    final String[] expected = {
        "5:22: " + getCheckMessage(MSG_INVALID_PATTERN),
        "7:22: " + getCheckMessage(MSG_INVALID_PATTERN),
        "10:22: " + getCheckMessage(MSG_INVALID_PATTERN),
    };
    Checker checker = createChecker(checkConfig);
    verify(checker, getPath("ArgsSameLineOrAligned.java"), expected);
  }

}
