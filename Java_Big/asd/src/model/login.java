package model;

public class login {
	private String userId;
	private String userName;
	private String passWd;
	private int like;      //ϲ��
	private int subScribe; //����
	private int articleCount; //��Ʒ����
	private String collect;  //��ע�˵�����
	
	
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
