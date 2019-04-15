package top.ijiujiu.utils;

/**
 * 系统状态枚举
 */
public enum SystemStatusEnum {
	ENABLED(1, "启用"),
	DISABLED(2, "禁用"),
	SYSTEM_EXCEPTION(3,"系统异常");

	private int status;
	private String message;

	private SystemStatusEnum(int status, String message) {
		this.status = status;
		this.message = message;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
