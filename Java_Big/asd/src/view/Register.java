package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.User;
import redis.clients.jedis.Jedis;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;

public class Register extends JFrame {

	private JPanel contentPane;
	private JTextField idText;
	Jedis jedis = new Jedis("192.168.152.132", 6379);
	private JLabel label;
	private JTextField nameText;
	private JPasswordField passwdText;
	private JPasswordField repasswdText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Register frame = new Register();
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
	public Register() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Register.class.getResource("/img/5c752381d36d6.png")));
		setTitle("\u6CE8\u518C\u9875\u9762 ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("\u7528\u6237ID\uFF1A");
		lblNewLabel.setFont(new Font("楷体", Font.PLAIN, 20));
		
		idText = new JTextField();
		idText.setFont(new Font("楷体", Font.PLAIN, 20));
		idText.setColumns(10);
		
		JLabel lblPasswd = new JLabel("passwd\uFF1A");
		lblPasswd.setFont(new Font("楷体", Font.PLAIN, 20));
		
		JLabel lblRepasswd = new JLabel("rePasswd\uFF1A");
		lblRepasswd.setFont(new Font("楷体", Font.PLAIN, 20));
		
		JButton button = new JButton("\u63D0\u4EA4");
		/**
		 * 按钮触发事件
		 */
		button.addActionListener(new EventHandler(this,"actionPerformed"));
		button.setFont(new Font("楷体", Font.PLAIN, 20));
		
		label = new JLabel("\u7528\u6237\u6635\u79F0\uFF1A");
		label.setFont(new Font("楷体", Font.PLAIN, 20));
		
		nameText = new JTextField();
		nameText.setFont(new Font("楷体", Font.PLAIN, 20));
		nameText.setColumns(10);
		
		passwdText = new JPasswordField();
		passwdText.setFont(new Font("楷体", Font.PLAIN, 20));
		
		repasswdText = new JPasswordField();
		repasswdText.setFont(new Font("楷体", Font.PLAIN, 20));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(67)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(button, GroupLayout.PREFERRED_SIZE, 115, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblNewLabel)
									.addGap(18))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(lblRepasswd)
									.addPreferredGap(ComponentPlacement.UNRELATED))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
										.addComponent(label)
										.addComponent(lblPasswd))
									.addGap(18)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(idText, 152, 180, Short.MAX_VALUE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.RELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(passwdText, GroupLayout.PREFERRED_SIZE, 181, GroupLayout.PREFERRED_SIZE)
										.addComponent(nameText, 180, 180, 180)
										.addComponent(repasswdText, GroupLayout.PREFERRED_SIZE, 183, GroupLayout.PREFERRED_SIZE))))))
					.addContainerGap(57, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(29)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(idText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(14)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(nameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPasswd)
						.addComponent(passwdText, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblRepasswd)
						.addComponent(repasswdText, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(button)
					.addContainerGap(14, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	protected String String(char[] password) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		try {
			int idtext = Integer.parseInt(idText.getText());
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "用户ID需全为数字");
		}
		
		String nametext = nameText.getText();
		String passwd =new String(passwdText.getPassword());
		String repasswd =new String(repasswdText.getPassword());
		if(idText.getText().isEmpty()||nametext.isEmpty()) {
			JOptionPane.showMessageDialog(null, "用户ID和用户名不允许为空！");
			return ;
		}
			
		if(!passwd.equals(repasswd)) {
			JOptionPane.showMessageDialog(null, "密码不一致");
			return ;
		}
		String idtext = idText.getText();
		jedis.auth("kingredis");
		jedis.hset(idtext, "name", nametext);
		jedis.hset(idtext, "passwd", passwd);
		jedis.hset(idtext, "articleCount", "0");
		jedis.hset(idtext, "like", "0");
		jedis.hset(idtext, "subScribe", "0");
		jedis.hset(idtext, "collect", "0");
		JOptionPane.showMessageDialog(null, "注册成功！");
		dispose();

	}
}
