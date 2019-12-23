package model;

import java.io.Serializable;

import redis.clients.jedis.Jedis;

/**
 * user实体类
 * @author 哑巴
 *
 */
public class User implements Serializable{  //实现序列化接口
	private String userId;
	private String userName;
	private String passwd;
	private int like;      //喜欢
	private int subScribe; //订阅
	private int articleCount; //作品数量
	private String collect;  //关注人的数量

	//数据库连接
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
	 * 添加文章进数据库
	 */
	public void addArticle(String userId, String title, String article) {
		
		jedis.auth("kingredis"); //输入数据库密码
		this.articleCount = Integer.parseInt(jedis.hget(userId, "articleCount")); //获取作品数量
		
		jedis.hincrBy(userId, "articleCount", 1);
		//存入User哈希表
		String titleKey = "title" + this.userId + ":" + String.valueOf(this.articleCount);
		String articleKey = "article:" + String.valueOf(this.articleCount);
		jedis.hset(this.userId, titleKey, title);
		jedis.hset(this.userId, articleKey, article);
		
		//添加到Article哈希表
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
	 * 通过文章标题获取文章
	 */
	public String getArticleName(String title) {
		
		char count = title.charAt(0);
		String article = "article" + count;
		return article;
	}
	
	

}
