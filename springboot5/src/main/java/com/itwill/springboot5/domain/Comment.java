package com.itwill.springboot5.domain;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder @ToString(callSuper = true) @EqualsAndHashCode(callSuper = true)
@Entity @Table(name = "COMMENTS")
public class Comment extends BaseTimeEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ToString.Exclude // ToString에서 제외. 안하면 무한루프에 빠질 수 있음.
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "POST_ID") // 조인할 때 사용하는 FK제약조건이 있는 컬럼 이름을 명시.
	private Post post;

	@Basic(optional = false)
	private String ctext;

	@Basic(optional = false)
	private String writer;

	public Comment update(String ctext) {
		this.ctext = ctext;
		return this;
	}
}
