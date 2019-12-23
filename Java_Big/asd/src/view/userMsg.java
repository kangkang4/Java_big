package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.ResultSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.User;
import redis.clients.jedis.Jedis;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class userMsg extends JInternalFrame {
	private JTable articleTitle;
	Jedis jedis = new Jedis("192.168.152.132", 6379);
	
	private JTextField passwd;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userMsg frame = new userMsg();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public userMsg() {
		setFrameIcon(new ImageIcon(userMsg.class.getResource("/img/5c752381d36d6.png")));
		setTitle("\u6211\u7684\u4FE1\u606F");
		setIconifiable(true);
		setClosable(true);
		setBounds(100, 100, 595, 464);
		
		JLabel lblid = new JLabel("\u7528\u6237ID\uFF1A");
		lblid.setFont(new Font("宋体", Font.PLAIN, 20));
		
		jedis.auth("kingredis");
		String userId = jedis.hget("loginUser", "userId");
		String userName = jedis.hget(userId, "name");
		String password = jedis.hget(userId,"passwd");
		
		JLabel Id = new JLabel(userId);
		Id.setFont(new Font("楷体", Font.PLAIN, 20));
		
		JLabel label = new JLabel("\u7528\u6237\u540D\u79F0\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JLabel label_2 = new JLabel("\u7528\u6237\u5BC6\u7801\uFF1A");
		label_2.setFont(new Font("宋体", Font.PLAIN, 20));
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label_4 = new JLabel("\u7528\u6237\u4F5C\u54C1\uFF1A");
		label_4.setFont(new Font("宋体", Font.PLAIN, 20));
		
		passwd = new JTextField();
		passwd.setFont(new Font("楷体", Font.PLAIN, 20));
		passwd.setColumns(10);
		passwd.setText(password);
		JButton button = new JButton("\u67E5\u8BE2");
		button.setFont(new Font("楷体", Font.PLAIN, 20));
		/**
		 * 添加文章标题信息
		 */
		button.addActionListener(new EventHandler(this,"actionPerformed"));
		
		JButton button_1 = new JButton("\u4FEE\u6539\u5BC6\u7801");
		button_1.setFont(new Font("楷体", Font.PLAIN, 20));
		/**
		 * 修改用户密码
		 */
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String s = passwd.getText();
				jedis.hset(userId, "passwd", s);
			}
		});
		
		JLabel name = new JLabel();
		name.setText(userName);
		name.setFont(new Font("楷体", Font.PLAIN, 20));
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(17)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(label_4)
						.addComponent(lblid)
						.addComponent(label_2))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
								.addComponent(button, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(Id, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(54)
							.addComponent(label)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(name))
						.addComponent(passwd, GroupLayout.PREFERRED_SIZE, 118, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(102, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(406, Short.MAX_VALUE)
					.addComponent(button_1)
					.addGap(80))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(117, Short.MAX_VALUE)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 402, GroupLayout.PREFERRED_SIZE)
					.addGap(60))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblid)
						.addComponent(Id)
						.addComponent(label)
						.addComponent(name))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(passwd, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(label_4)
						.addComponent(button))
					.addGap(18)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(button_1)
					.addContainerGap(38, Short.MAX_VALUE))
		);
		
		articleTitle = new JTable();
		
		/**
		 * 点击文章标题跳出文章详细内容
		 */
		articleTitle.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				articleTitleMousePressed(e);
			}

			/**
			 * 表格行点击事件处理
			 * @param e
			 */
			private void articleTitleMousePressed(MouseEvent evt) {
				// TODO Auto-generated method stub

				int row = articleTitle.getSelectedRow();  //获取点击的行数
				String title = (String) articleTitle.getValueAt(row, 0);  //获取点击行的value值
				
				String titleKey = findTitleKey(title);
				
				articleDetalis articleDetalis = new articleDetalis(titleKey); //创建另一个框
				articleDetalis.setVisible(true);

				
			}
		});
		articleTitle.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"                 \u6587\u7AE0\u6807\u9898"
			}
		));
		articleTitle.getColumnModel().getColumn(0).setPreferredWidth(415);
		scrollPane.setViewportView(articleTitle);		
		getContentPane().setLayout(groupLayout);
		
		/**
		 * 调用初始化表格函数
		 */
//		this.fillTable(new User());

	}
	public String findTitleKey(String title) {
		String titleKey = null;
		Set<String> keys = jedis.keys("title" + "*");
		//往表格里存入数据
		for(String key : keys) {
			
			String userId = jedis.hget(key, "authorId");
			String articleTitle = jedis.hget(userId, key);
			if(title.equals(articleTitle))
				titleKey = key;
			
		}
		return titleKey;
	}
	
	public void actionPerformed(ActionEvent e) {
		jedis.auth("kingredis");
		String Id = jedis.hget("loginUser", "userId");
		//初始化表格
		DefaultTableModel dtm = (DefaultTableModel) articleTitle.getModel();
		dtm.setRowCount(0);  
		
		//查询所有文章，存入set集合
		Set<String> keys = jedis.keys("title" + "*");
			
		//往表格里存入数据
		for(String key : keys) {
			
			if(jedis.hget(key, "authorId").equals(Id)) {
				String userId = jedis.hget(key, "authorId");
				String articleTitle = jedis.hget(userId, key);
				String likeCount = jedis.hget(key, "like");
				
				Vector v = new Vector();
				v.add(articleTitle);
				v.add(userId);
				v.add(likeCount);
				dtm.addRow(v);
			}
			
		}
	}
}
