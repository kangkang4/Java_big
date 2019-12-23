package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JDesktopPane;
import java.awt.Color;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.Toolkit;

public class Main extends JFrame {
	private JPanel contentPane;
	private JDesktopPane desktopPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setDefaultCloseOperation(0);
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
	public Main() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/img/5c751000211bf.png")));
		setFont(new Font("Dialog", Font.PLAIN, 20));
		setTitle("\u6296\u9C7C\u2014\u2014\u4E0D\u7B11\u6253\u6B7B\u5236\u4F5C\u4EBA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 250, 1200, 800);
		this.setLocationRelativeTo(null);
		
		JLabel lbbg = new JLabel();
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Microsoft YaHei UI", Font.PLAIN, 20));
		setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u529F\u80FD");
		menu.setFont(new Font("楷体", Font.PLAIN, 20));
		menuBar.add(menu);
		
		JMenuItem menuItem_2 = new JMenuItem("\u4E3B\u9875\u9762");
		menuItem_2.setSize(10, 10);
		/**
		 * 查看文章主页面
		 */
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Browse Browse = new Browse();
				Browse.setVisible(true);
				desktopPane.add(Browse);
			}
		});
		menuItem_2.setFont(new Font("楷体", Font.PLAIN, 20));
		menu.add(menuItem_2);
		
		JMenuItem menuItem_1 = new JMenuItem("\u53D1\u5E03\u4F5C\u54C1");
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Add Add = new Add();
				Add.setVisible(true);
				desktopPane.add(Add);
			}
		});
		menuItem_1.setFont(new Font("楷体", Font.PLAIN, 20));
		menu.add(menuItem_1);
		
		JMenuItem menuItem = new JMenuItem("\u9000\u51FA");
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int result = JOptionPane.showConfirmDialog(null, "是否退出系统");
				if(result == 0)
					dispose();
			}
		});
		menuItem.setFont(new Font("楷体", Font.PLAIN, 20));
		menu.add(menuItem);
		
		JMenu menu_1 = new JMenu("\u5173\u4E8E\u6211\u4EEC");
		menu_1.setFont(new Font("楷体", Font.PLAIN, 20));
		menuBar.add(menu_1);
		
		JMenu menu_2 = new JMenu("\u4E2A\u4EBA\u4FE1\u606F");
		
		menu_2.setFont(new Font("楷体", Font.PLAIN, 20));
		menuBar.add(menu_2);
		
		JMenuItem menuItem_3 = new JMenuItem("\u6211\u7684\u4FE1\u606F");
		menuItem_3.setFont(new Font("楷体", Font.PLAIN, 20));
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				userMsg userMsg = new userMsg();
				userMsg.setVisible(true);
				desktopPane.add(userMsg);
			}
		});
		menu_2.add(menuItem_3);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		desktopPane = new JDesktopPane();
		desktopPane.setBackground(Color.WHITE);
		contentPane.add(desktopPane, BorderLayout.CENTER);
		GroupLayout gl_desktopPane = new GroupLayout(desktopPane);
		gl_desktopPane.setHorizontalGroup(
			gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 1172, Short.MAX_VALUE)
		);
		gl_desktopPane.setVerticalGroup(
			gl_desktopPane.createParallelGroup(Alignment.LEADING)
				.addGap(0, 718, Short.MAX_VALUE)
		);
		desktopPane.setLayout(gl_desktopPane);

		


		
	}
}
