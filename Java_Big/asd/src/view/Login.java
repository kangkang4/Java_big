package view;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import redis.clients.jedis.Jedis;


public class Login extends JFrame {

    private static final long serialVersionUID = 1L;
    Jedis jedis = new Jedis("192.168.152.132", 6379);
    private JTextField code;
    private JLabel label_1;

    public Login() {
    	setIconImage(Toolkit.getDefaultToolkit().getImage(Login.class.getResource("/img/5c752381d36d6.png")));

        
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        
        //设置顶部提示文字和主窗体的宽，高，x值，y值
        setTitle("Login");
        setBounds(300, 200, 645, 370);
        Container cp = getContentPane();    //添加一个cp容器
        cp.setLayout(null);    //设置添加的cp容器为流布局管理器

        //设置左侧用户名文字
        JLabel jl = new JLabel("\u7528\u6237ID\uFF1A");
        jl.setFont(new Font("楷体", Font.PLAIN, 20));
        jl.setBounds(188, 159, 92, 18);
        final JTextField name = new JTextField();    //用户名框
        name.setFont(new Font("楷体", Font.PLAIN, 20));
        name.setBounds(290, 159, 150, 22);    //设置用户名框的宽，高，x值，y值

        //设置左侧密码文字
        JLabel jl2 = new JLabel("密码：");
        jl2.setFont(new Font("楷体", Font.PLAIN, 20));
        jl2.setBounds(204, 190, 76, 22);
        final JPasswordField password = new JPasswordField();    //密码框：为加密的***
        password.setFont(new Font("楷体", Font.PLAIN, 20));
        password.setBounds(290, 189, 150, 22);    //设置密码框的宽，高，x值，y值

        //将jl、name、jl2、password添加到容器cp中
        cp.add(jl);
        cp.add(name);
        cp.add(jl2);
        cp.add(password);

        //确定按钮
        JButton jb = new JButton("确定");    //添加一个确定按钮
        jb.setFont(new Font("楷体", Font.PLAIN, 20));
        jb.addActionListener(new ActionListener() {        //为确定按钮添加监听事件

            public void actionPerformed(ActionEvent arg0) {
            	
            	jedis.auth("kingredis");
                if (name.getText().trim().length() == 0 || new String(password.getPassword()).trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "用户名密码不允许为空");
                    return;
                }
                if (!code.getText().equals(label_1.getText())) {
                    JOptionPane.showMessageDialog(null, "验证码错误");
                    return;
                }
                if(jedis.hget(name.getText(),"passwd")==null){
                	int result = JOptionPane.showConfirmDialog(null, "用户名不存在，是否进行注册？");
                	if(result == 0) {
                		/**
                		 * 进入注册页面
                		 */
                		Register Register = new Register();
                		Register.setVisible(true);
                	}
                	if(result == 1)return ;
                	if(result == 2)return ;
                	if(result == -1)return ;
                	
                }
                else {
                	String passwd =new String(password.getPassword());
                    String truePasswd = new String(jedis.hget(name.getText(),"passwd"));
                    if (truePasswd.equals(passwd)) {
                        JOptionPane.showMessageDialog(null, "登录成功");
                        jedis.hset("loginUser", "userId", name.getText());
                        Main Main = new Main();
                        Main.setVisible(true);
                        dispose();
                    } else
                    	JOptionPane.showMessageDialog(null, "密码错误！");
                    		
                }
                }
                
        });
        jb.setBounds(358, 286, 82, 25);    //设置确定按钮的宽，高，x值，y值
        cp.add(jb);    //将确定按钮添加到cp容器中

        //重置按钮
        final JButton button = new JButton();
        button.setFont(new Font("楷体", Font.PLAIN, 20));
        button.setText("重置");
        button.addActionListener(new ActionListener() {        //为重置按钮添加监听事件
            //同时清空name、password的数据
            public void actionPerformed(ActionEvent arg0) {
                // TODO 自动生成方法存根
                name.setText("");
                password.setText("");
            }
        });
        button.setBounds(232, 286, 76, 25);    //设置重置按钮的宽，高，x值，y值
        getContentPane().add(button);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/img/5c751000211bf.png")));
        lblNewLabel.setBounds(264, 13, 537, 137);
        getContentPane().add(lblNewLabel);
        
        code = new JTextField();
        code.setFont(new Font("楷体", Font.PLAIN, 20));
        code.setBounds(290, 224, 150, 24);
        getContentPane().add(code);
        code.setColumns(10);
        
        JLabel label = new JLabel("\u9A8C\u8BC1\u7801\uFF1A");
        label.setFont(new Font("楷体", Font.PLAIN, 20));
        label.setBounds(188, 225, 88, 18);
        getContentPane().add(label);
        
        JButton button_1 = new JButton("\u751F\u6210\u9A8C\u8BC1\u7801");
        button_1.setFont(new Font("楷体", Font.PLAIN, 20));
        
        
        button_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		label_1.setText(String.valueOf(Math.round(Math.random()*1000000)));
        	}
        });

        button_1.setBounds(451, 223, 151, 27);
        getContentPane().add(button_1);
        
        label_1 = new JLabel("");
        label_1.setForeground(Color.RED);
        label_1.setFont(new Font("楷体", Font.PLAIN, 22));
        label_1.setBounds(471, 263, 113, 18);
        getContentPane().add(label_1);
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    public ActionListener actionPerformed() {
		// TODO Auto-generated method stub
		return null;
	}

	//main方法入口
    public static void main(String[] args) {
        new Login();    //调用Login()

    }
}

