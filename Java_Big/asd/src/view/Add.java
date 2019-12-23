package view;

import java.awt.EventQueue;
import java.awt.JobAttributes;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import model.User;
import redis.clients.jedis.Jedis;

import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Set;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;

public class Add extends JInternalFrame {
	private JTextArea addcontent; 
	private JTextField addId;
	private JTextField addTitle;
	Jedis jedis = new Jedis("192.168.152.132", 6379);
    

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Add frame = new Add();
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
	public Add() {
		setFrameIcon(new ImageIcon(Add.class.getResource("/img/5c752381d36d6.png")));
		
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("\u4E2A\u4EBA\u4F5C\u54C1\u6DFB\u52A0");
		setBounds(100, 100, 588, 429);
		
		JLabel lblid = new JLabel("\u7528\u6237id\uFF1A");
		
		addId = new JTextField();
		addId.setFont(new Font("宋体", Font.PLAIN, 20));
		addId.setColumns(10);
		
		JLabel label_1 = new JLabel("\u4F5C\u54C1\u5185\u5BB9\uFF1A");
		
		JButton reset = new JButton("\u91CD\u7F6E");
		reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		
		JButton sent = new JButton("\u53D1\u5E03");
		
		/**
		 * 发布按钮事件
		 */
		sent.addActionListener(new ActionListener() {
			
			@SuppressWarnings("unused")  //该属性在方法或类中没有使用。添加此注解可以去除属性上的黄色警告,此处去除user的警告
			public void actionPerformed(ActionEvent arg0) {
				
					if(addId.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "用户ID不能为空! ");
						return;
					}
					
					if(addcontent.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "作品内容不能为空！");
						return;
					}
					if(addTitle.getText().isEmpty()) {
						JOptionPane.showMessageDialog(null, "作品标题不能为空！");
						return;
					}
					String userId = addId.getText();
					String article = addcontent.getText();
					String title = addTitle.getText();
					User user = new User(userId);
					user.addArticle(userId,title,article);      //添加进数据库
					JOptionPane.showMessageDialog(null, "添加成功！");
								
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		
		JLabel label = new JLabel("\u4F5C\u54C1\u6807\u9898\uFF1A");
		
		addTitle = new JTextField();
		addTitle.setFont(new Font("楷体", Font.PLAIN, 20));
		addTitle.setColumns(10);
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(29)
							.addComponent(lblid)
							.addGap(18)
							.addComponent(addId, 428, 428, 428))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(addTitle, 429, 429, 429))
								.addGroup(groupLayout.createSequentialGroup()
									.addComponent(label_1)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 429, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap(36, Short.MAX_VALUE))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(138, Short.MAX_VALUE)
					.addComponent(reset, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.addGap(113)
					.addComponent(sent, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.addGap(72))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(23)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(addId, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblid))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(addTitle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1))
					.addGap(35)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(sent)
						.addComponent(reset))
					.addGap(19))
		);
		
		addcontent = new JTextArea();
		scrollPane.setViewportView(addcontent);
		addcontent.setFont(new Font("宋体", Font.PLAIN, 20));
		addcontent.setLineWrap(true);
		getContentPane().setLayout(groupLayout);

	}

	protected void resetValueActionPerformed(ActionEvent e) {
		this.resetValue();
		
	}
	private void resetValue() {
		this.addId.setText("");
		this.addcontent.setText("");
		this.addTitle.setText("");
	}
}
