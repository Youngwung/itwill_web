package com.itwill.springboot5.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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

@Entity
@Table(name = "MEMBERS")
@NoArgsConstructor @AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter @ToString(callSuper = true) @EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
// onlyExplicitlyIncluded 속성: @EqualsAndHashCode.Include 애너테이션이 설정된 필드만 사용할 건지 설정
// callSuper 속성: superclass의 equals(), hashCode() 메서드를 사용할 것인 지 여부.
public class Member extends BaseTimeEntity implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@EqualsAndHashCode.Include // username field equals()와 hashCode()를 재정의할 때 사용.
	@NaturalId // unique 제약조건
	@Basic(optional = false) // not null 제약조건
	@Column(updatable = false) // update 쿼리에서 set 절에서 제외하기 위한 설정.
	private String username;

	@Basic(optional = false) // not null
	private String password;

	@Basic(optional = false) // not null
	private String email;


	@Builder.Default // Builder 패턴에서도 null이 아닌 HashSet<> 객체로 초기화 될 수 있도록 하는 설정.
	@ToString.Exclude // ToString 메서드에서 제외
	@ElementCollection(fetch = FetchType.LAZY) // 연관 테이블(member_roles) 사용.
	// @ManyToMany 애너테이션 안씀?
	// ! 그거 대신에 ElementCollection 애너테이션 쓴거임.
	@Enumerated(EnumType.STRING) // DB 테이블에 저장될 때 상수(enum) 이름(문자열)을 사용.
	private Set<MemberRole> roles = new HashSet<>();
	// set인 이유:
	//! 하나의 Member가 여러개의 roles를 가질 수 있기 때문
	//? List 쓰면 안됨?
	//! List는 중복된 값을 가질 수 있기 때문에 중복된 값은 여러개 갖지 않는 Set을 사용.

	// field에 HashSet을 사용하면 생길 수 문제점:
	// (1) 기본생성자 호출할 때:
	// 		roles를 초기화 할 때 null을 넣는 게 아니라 길이가 0인 HashSet 객체로 초기화함:
	//    new HashSet<>() 이게 실행 되는거임
	//		roles로 메서드를 호출할 때 문제가 되지 않음.
	//		*기본 생성자를 호출할 땐 문제가 되지 않음
	// (2) 아규먼트 갖는 생성자 호출할 때: (Builder 패턴을 이용해 객체를 생성할 때)
	//		roles를 초기화할 때 유저가 값을 주지 않으면 null로 초기화해버림.
	//		roles로 HashSet이 가지고있는 메서드를 호출할 수 없음 => nullPointerException
	//		유저가 객체를 생성할 때 roles를 초기화하면 문제가 되지 않지만, 에러가 발생할 가능성이 매우 높음.
	//!		=> 필드에 @Builder.Default 애너테이션 추가: Builder로 값을 넣지 않아도 new HashSet<>()로 초기화함. null이 되지 않음.

	// 편의 메서드
	// 유저의 권한을 부여하는 메서드.
	public Member addRole(MemberRole role)	{
		roles.add(role);
		return this;
	}

	// 유저의 권한을 한 개 삭제하는 메서드
	public Member removeRole(MemberRole role) {
		roles.remove(role);
		return this;
	}

	// 유저의 권한을 삭제하는 메서드
	public Member clearRoles() {
		roles.clear(); // Set<>이 가지고 있는 모든 원소를 지움.
		return this;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		//! 람다표현식을 사용하지 않는 방법
		// ArrayList<GrantedAuthority> authorities = new ArrayList<>();
		// for(MemberRole r : roles) {
		// 	GrantedAuthority auth = new SimpleGrantedAuthority(r.getAuthority());
		// 	authorities.add(auth);
		// }

		//! 람다표현식을 사용한 방법
		List<SimpleGrantedAuthority> authorities = roles.stream().map((r) -> new SimpleGrantedAuthority(r.getAuthority())).toList();

		return authorities;
	}

}
