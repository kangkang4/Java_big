package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import redis.clients.jedis.Jedis;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.List;

import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class articleDetalis extends JFrame {

	private JPanel contentPane;
	Jedis jedis = new Jedis("192.168.152.132", 6379);

	/**
	 * Launch the application.
	 */
	public void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new articleDetalis(getTitle());
				dispose();
			}
		});
	}

	/**
	 * Create the frame.
	 * @param content 
	 */
	public articleDetalis(String titleKey) {
		setIconImage(Toolkit.getDefaultToolkit().getImage(articleDetalis.class.getResource("/img/5c752381d36d6.png")));
		
		setFont(new Font("楷体", Font.PLAIN, 20));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 625, 459);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label = new JLabel("\u4F5C\u8005\u540D\uFF1A");
		label.setFont(new Font("楷体", Font.PLAIN, 20));
		
		jedis.auth("kingredis");
		String title = jedis.hget(titleKey, "title");
		String content = jedis.hget(titleKey, "content");
		String like = jedis.hget(titleKey, "like");
		String name = jedis.hget(titleKey, "authorName");
		
		setTitle(title);
		JLabel authorName = new JLabel(name);
		authorName.setFont(new Font("楷体", Font.PLAIN, 20));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label_2 = new JLabel("\u6587\u7AE0\u8BE6\u60C5\uFF1A");
		label_2.setFont(new Font("楷体", Font.PLAIN, 20));
		
		JButton button = new JButton("\u70B9\u8D5E");
		
		/**
		 * 实现点赞功能
		 */
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jedis.hincrBy(titleKey, "like", 1);
			}
		});
		
		JLabel lblLike = new JLabel("Like\uFF1A");
		lblLike.setFont(new Font("楷体", Font.PLAIN, 20));
		
		JLabel likeCount = new JLabel(like);
		likeCount.setFont(new Font("楷体", Font.PLAIN, 20));
		
		JButton button_1 = new JButton("\u5173\u6CE8");
		/**
		 * 点击关注事件
		 */
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userId = jedis.hget("loginUser", "userId");
				String collect = jedis.hget(userId, "collect");
				findTitleKey(userId,title,collect);
			}
		});
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(48)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(label)
								.addComponent(label_2))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE)
										.addGroup(gl_contentPane.createSequentialGroup()
											.addComponent(lblLike)
											.addGap(25)
											.addComponent(likeCount))))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(authorName)
									.addGap(43)
									.addComponent(button_1))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(423)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(33, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(21)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(authorName)
							.addComponent(label))
						.addComponent(button_1))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 231, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(likeCount)
								.addComponent(lblLike))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
						.addComponent(label_2))
					.addGap(31))
		);
		
		JTextArea articleDetail = new JTextArea();
		articleDetail.setText(content);
		articleDetail.setFont(new Font("楷体", Font.PLAIN, 20));
		
		scrollPane.setViewportView(articleDetail);
		contentPane.setLayout(gl_contentPane);
		try {
			
			setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	/**
	 * 通过title找到titleKey
	 * @param title
	 * @return
	 */
	public int findTitleKey(String userId, String title, String collect) {
		String articlefocus = "vsfiovfobhfiovhs";
		Map<String, String> keys = jedis.hgetAll(userId);
		//往表格里存入数据
		
		
		for(Entry<String, String> entry : keys.entrySet()) {
			if(entry.getValue().equals(title))
			{
				articlefocus = entry.getKey();
				
				  jedis.hdel(userId, articlefocus);
				  jedis.hincrBy(userId, "collect", -1);
				  JOptionPane.showConfirmDialog(null, "取消关注成功！");
				  return 0;
			}
		}
		if(!jedis.hexists(userId, articlefocus)) {
			//user添加关注文章
			  jedis.hsetnx(userId, "articlefocus"+collect, title);
			  jedis.hincrBy(userId, "collect", 1);
			  JOptionPane.showConfirmDialog(null, "关注成功！");
			  
		}
		return 0;
		
	}
	
	public void addOne() {
		
	}
}
