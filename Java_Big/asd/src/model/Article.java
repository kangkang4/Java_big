package model;

/**
 * ����ʵ����
 * @author �ư�
 *
 */
public class Article {
	
	private String title;  //���±���
	private String content;   //��������
	private String autherId;   //��������ID
	private String authorName;   //����������
	private int like;           //���»�����
	
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAutherId() {
		return autherId;
	}
	public void setAutherId(String autherId) {
		this.autherId = autherId;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	
	

}
