import java.time.LocalDateTime
import java.util.Collections.max
import java.util.Collections.min

class DateTimeSpan(var startDt: LocalDateTime, var endDt: LocalDateTime) {
  init {
    if (endDt < startDt) {
      throw RuntimeException()
    }
  }

  /**
   * Return if this is within, and not touching either edge of, other.
   */
  fun isStrictlyWithin(other: DateTimeSpan): Boolean {
    return this.startDt > other.startDt && this.endDt < other.endDt
  }

  /**
   * Return whether the given datetime is within this.
   */
  fun datetimeWithin(dt: LocalDateTime): Boolean {
    return dt >= this.startDt && dt < this.endDt
  }

  /**
   * Return whether this and other overlap.
   */
  fun intersects(other: DateTimeSpan): Boolean {
    val (first, second) = if (this.startDt < other.startDt) listOf(other, this) else listOf(this, other)
    return first.endDt > second.startDt
  }

  /**
   * Return any part of this which is also in other.
   */
  fun overlap(other: DateTimeSpan): DateTimeSpan? {
    if (this.endDt < other.startDt || other.endDt < this.startDt) {
      return null
    }

    return DateTimeSpan(
      max(listOf(this.startDt, other.startDt)),
      min(listOf(this.endDt, other.endDt)),
    )
  }

  override fun equals(other: Any?): Boolean {
    return (
      other is DateTimeSpan
        && this.startDt == other.startDt
        && this.endDt == other.endDt
      )
  }

  override fun hashCode(): Int {
    return this.startDt.hashCode() * this.endDt.hashCode()
  }

  override fun toString(): String {
    return "${this.startDt} - ${this.endDt}"
  }
}
