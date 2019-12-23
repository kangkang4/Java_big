package model;

import java.io.Serializable;

import redis.clients.jedis.Jedis;

/**
 * userʵ����
 * @author �ư�
 *
 */
public class User implements Serializable{  //ʵ�����л��ӿ�
	private String userId;
	private String userName;
	private String passwd;
	private int like;      //ϲ��
	private int subScribe; //����
	private int articleCount; //��Ʒ����
	private String collect;  //��ע�˵�����

	//���ݿ�����
	Jedis jedis = new Jedis("192.168.152.132", 6379);
	
	public int getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(int articleCount) {
		int count = articleCount+1;
		this.articleCount = count;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public String getCollect() {
		return collect;
	}

	public void setCollect(String collect) {
		this.collect = collect;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPassWd(String passwd) {
		this.passwd = passwd;
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
	/**
	 * ������½����ݿ�
	 */
	public void addArticle(String userId, String title, String article) {
		
		jedis.auth("kingredis"); //�������ݿ�����
		this.articleCount = Integer.parseInt(jedis.hget(userId, "articleCount")); //��ȡ��Ʒ����
		
		jedis.hincrBy(userId, "articleCount", 1);
		//����User��ϣ��
		String titleKey = "title" + this.userId + ":" + String.valueOf(this.articleCount);
		String articleKey = "article:" + String.valueOf(this.articleCount);
		jedis.hset(this.userId, titleKey, title);
		jedis.hset(this.userId, articleKey, article);
		
		//��ӵ�Article��ϣ��
		String userName = jedis.hget(userId, "name");
		jedis.hset(titleKey, "content", article);
		jedis.hset(titleKey, "title", title);
		jedis.hset(titleKey, "like", "0");
		jedis.hset(titleKey, "authorId", userId);
		jedis.hset(titleKey, "authorName", userName);
	}
	

	public User(String userId) {
		this.userId = userId;
	}
	
	/**
	 * ͨ�����±����ȡ����
	 */
	public String getArticleName(String title) {
		
		char count = title.charAt(0);
		String article = "article" + count;
		return article;
	}
	
	

}
