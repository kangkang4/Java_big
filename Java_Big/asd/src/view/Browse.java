package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import redis.clients.jedis.Jedis;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.ImageIcon;

public class Browse extends JInternalFrame {
	
	private JTable table;
	Jedis jedis = new Jedis("192.168.152.132", 6379);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Browse frame = new Browse();
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
	public Browse() {
		setFrameIcon(new ImageIcon(Browse.class.getResource("/img/5c752381d36d6.png")));
		setIconifiable(true);
		setClosable(true);
		setTitle("\u6D4F\u89C8\u754C\u9762");
		setBounds(100, 100, 744, 528);
		
		JScrollPane scrollPane = new JScrollPane();
		
		table = new JTable();

		/**
		 * 鼠标点击标题触发事件
		 */
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				articleTitleMousePressed(e);				
			}

			/**
			 * 表单触发事件
			 * @param e
			 */
			private void articleTitleMousePressed(MouseEvent e) {

				int row = table.getSelectedRow();  //获取点击的行数
				String title = (String) table.getValueAt(row, 0);  //获取点击行的value值
				
				String titleKey = findTitleKey(title);
			
				articleDetalis articleDetalis = new articleDetalis(titleKey); //创建另一个框
				articleDetalis.setVisible(true);
				articleDetalis.getDefaultCloseOperation();
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"                        \u4F5C\u54C1\u6807\u9898", "       \u4F5C\u8005", "\u70B9\u8D5E\u6570"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(475);
		table.getColumnModel().getColumn(1).setPreferredWidth(181);
		table.setFont(new Font("楷体", Font.PLAIN, 20));
		scrollPane.setViewportView(table);
		
		JButton Recommend = new JButton("\u70B9\u51FB\u6B64\u5904\u8FDB\u884C\u63A8\u8350");
		/**
		 * 推荐按钮生成页面，初始化表格并生成数据
		 */
		Recommend.addActionListener(new EventHandler(this,"actionPerformed"));
		
		
		Recommend.setFont(new Font("楷体", Font.PLAIN, 20));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 728, GroupLayout.PREFERRED_SIZE)
				.addComponent(Recommend, GroupLayout.PREFERRED_SIZE, 728, GroupLayout.PREFERRED_SIZE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 460, GroupLayout.PREFERRED_SIZE)
					.addComponent(Recommend))
		);
		getContentPane().setLayout(groupLayout);


	}
	/**
	 * 通过title找到titleKey
	 * @param title
	 * @return
	 */
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
		
		//初始化表格
		DefaultTableModel dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);  
		
		//查询所有文章，存入set集合
		Set<String> keys = jedis.keys("title" + "*");
			
		//往表格里存入数据
		for(String key : keys) {
			
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
