import com.google.common.collect.ImmutableMap;
import org.apache.druid.common.config.NullHandling;
import org.apache.druid.java.util.common.DateTimes;
import org.apache.druid.query.Result;
import org.apache.druid.query.topn.TopNResultValue;
import org.junit.Assert;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArgsSame2
{
  public static void main(String[] args)
  {
    Assert.assertArrayEquals(
        "Non-exist root extensionsDir should return an empty array of File",
        new File[]{},
        extnLoader.getExtensionFilesToLoad()
    );
  }
  public void name()
  {
    List<Result<TopNResultValue>> expectedResults = Collections.singletonList(
        new Result<>(
            DateTimes.of("2011-04-02T00:00:00.000Z"),
            TopNResultValue.create(
                Arrays.asList(
                    ImmutableMap.<String, Object>builder()
                        .put("index_alias", 147L)
                        .put("longNumericNull", 10L)
                        .build(),
                    makeRowWithNulls("index_alias", 166L, "longNumericNull", NullHandling.defaultLongValue())
                )
            )
        )
    );
  }
}
