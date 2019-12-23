package model;

/**
 * 文章实体类
 * @author 哑巴
 *
 */
public class Article {
	
	private String title;  //文章标题
	private String content;   //文章内容
	private String autherId;   //文章作者ID
	private String authorName;   //文章作者名
	private int like;           //文章获赞数
	
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
