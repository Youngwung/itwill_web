package com.itwill.springboot5.domain;

public enum MemberRole {
	USER("ROLE_USER"), // => public static final MemberRole User = new MemberRole("");
	ADMIN("ROLE_ADMIN");

	private String authority;

	MemberRole(String authority) {
		this.authority = authority;
	}

	public String getAuthority() {
		return this.authority;
	}
}
