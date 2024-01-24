package br.com.mmtech.messageapiv2.enumerated;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum WeekGroup {
  MIDDLE_WEEK(List.of(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.FRIDAY)),
  FINAL_WEEK(List.of(DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY));

  final List<DayOfWeek> days;

  public static List<WeekGroup> getPlansByDay(DayOfWeek day) {
    List<WeekGroup> matchingWeekGroups = new ArrayList<>();
    for (WeekGroup weekGroup : WeekGroup.values()) {
      if (weekGroup.getDays().contains(day)) {
        matchingWeekGroups.add(weekGroup);
      }
    }
    return matchingWeekGroups;
  }
}
