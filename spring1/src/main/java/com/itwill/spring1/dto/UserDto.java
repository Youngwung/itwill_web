package com.itwill.spring1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
// -> @Getter, @Setter, @ToString, @EqualsAndHashCode, @RequiredArgsConstructor
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserDto {
  private String username;
  private Integer age;
  // int타입이 아니라 Integer로 하는 이유!
  // 기본값을 0이 아니라 null로 하기 위해서.
  // -> 효과: 기본값으로 들어갔을 때 0이 들어가면
  // Integer.parseInt()메서드를 쓸 때 Exception 발생(웹페이지가 안들어가짐)을
  // 방지할 수 있음.

}
