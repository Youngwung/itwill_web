package com.itwill.springboot5.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
// Builder 패턴을 사용하기 위해서는 AllArgsConstructor가 필요한데 
// 외부에서 필드를 수정하지 못하게 하기 위해서 숨겨야함.
// 이 때 애너테이션에 속성으로 (access = AccessLevel.PRIVATE)를 추가하면 생성자의 공개범위를 private으로 변경할 수 있다.
@Getter
@ToString(callSuper = true) // 상위 클래스의 toString()을 호출해서 toString() 메서드를 작성.
@EqualsAndHashCode(callSuper = true) // 상위 클래스의 필드들도 사용해서 equals(), hashCode() 작성.
// 원래 Post의 필드로만 toString을 만드는데 callSuper를 활성화시키면 상위클래스(수퍼클래스)의 toString을 현재 toString에 포함시킴.
@Entity @Table(name = "POSTS")
public class Post extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // generated as identity
	private Long id;
	@Basic(optional = false) // not null
	private String title;
	
	@Basic(optional = false) // not null
	private String content;
	
	@Basic(optional = false) // not null
	private String author;

	// update 기능(제목/내용 수정)에서 사용할 공개 메서드
	public Post update(String title, String content) {
		this.title = title;
		this.content = content;
		return this;
	}
}
