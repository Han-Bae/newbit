package com.newbit.www.vo;

/**
 * 이 클래스는 알림 관련 데이터를 채워넣는 VO
 * 
 * @author 김태현
 * @since 2022.07.21
 * @version v.1.0
 * 
 *          작업이력 ] 2022.07.21 - 담당자 : 김태현 -> 클래스제작
 */
public class NoticeVO {
	private String id, title, body, ischeck;
	private int no;
	
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getIscheck() {
		return ischeck;
	}

	public void setIscheck(String ischeck) {
		this.ischeck = ischeck;
	}

	@Override
	public String toString() {
		return "NoticeVO [id=" + id + ", title=" + title + ", body=" + body + ", ischeck=" + ischeck + ", no=" + no
				+ "]";
	}
	
	
}
