package model;

public class login {
	private String userId;
	private String userName;
	private String passWd;
	private int like;      //喜欢
	private int subScribe; //订阅
	private int articleCount; //作品数量
	private String collect;  //关注人的数量
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWd() {
		return passWd;
	}
	public void setPassWd(String passWd) {
		this.passWd = passWd;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public int getSubScribe() {
		return subScribe;
	}
	public void setSubScribe(int subScribe) {
		this.subScribe = subScribe;
	}
	public int getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}
	public String getCollect() {
		return collect;
	}
	public void setCollect(String collect) {
		this.collect = collect;
	}
}
