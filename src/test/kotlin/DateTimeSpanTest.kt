import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.time.LocalDateTime

internal class DateTimeSpanTest {

    @Test
    fun testEquals() {
      val span1 = DateTimeSpan(
        LocalDateTime.of(2016, 3, 28, 0, 0),
        LocalDateTime.of(2016, 3, 28, 0, 4),
      )

      val span2 = DateTimeSpan(
        LocalDateTime.of(2016, 3, 28, 0, 0),
        LocalDateTime.of(2017, 3, 28, 0, 4),
      )

      assertEquals(span1, span2)
    }
}
