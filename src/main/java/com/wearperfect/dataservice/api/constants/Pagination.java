package com.wearperfect.dataservice.api.constants;

public interface Pagination {

	public enum PageNumber {

		DEFAULT(0);

		private final Integer value;

		PageNumber(int value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}
	}

	public enum PageSize {

		DEFAULT(15),
		USERS(50),
		POSTS(15),
		POST_COMMENTS(15),
		BUSINESS_AND_SUPPORT(10);

		private final Integer value;

		PageSize(int value) {
			this.value = value;
		}

		public Integer getValue() {
			return value;
		}
	}
}
