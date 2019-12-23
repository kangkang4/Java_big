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

        
        //���ö�����ʾ���ֺ�������Ŀ��ߣ�xֵ��yֵ
        setTitle("Login");
        setBounds(300, 200, 645, 370);
        Container cp = getContentPane();    //���һ��cp����
        cp.setLayout(null);    //������ӵ�cp����Ϊ�����ֹ�����

        //��������û�������
        JLabel jl = new JLabel("\u7528\u6237ID\uFF1A");
        jl.setFont(new Font("����", Font.PLAIN, 20));
        jl.setBounds(188, 159, 92, 18);
        final JTextField name = new JTextField();    //�û�����
        name.setFont(new Font("����", Font.PLAIN, 20));
        name.setBounds(290, 159, 150, 22);    //�����û�����Ŀ��ߣ�xֵ��yֵ

        //���������������
        JLabel jl2 = new JLabel("���룺");
        jl2.setFont(new Font("����", Font.PLAIN, 20));
        jl2.setBounds(204, 190, 76, 22);
        final JPasswordField password = new JPasswordField();    //�����Ϊ���ܵ�***
        password.setFont(new Font("����", Font.PLAIN, 20));
        password.setBounds(290, 189, 150, 22);    //���������Ŀ��ߣ�xֵ��yֵ

        //��jl��name��jl2��password��ӵ�����cp��
        cp.add(jl);
        cp.add(name);
        cp.add(jl2);
        cp.add(password);

        //ȷ����ť
        JButton jb = new JButton("ȷ��");    //���һ��ȷ����ť
        jb.setFont(new Font("����", Font.PLAIN, 20));
        jb.addActionListener(new ActionListener() {        //Ϊȷ����ť��Ӽ����¼�

            public void actionPerformed(ActionEvent arg0) {
            	
            	jedis.auth("kingredis");
                if (name.getText().trim().length() == 0 || new String(password.getPassword()).trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "�û������벻����Ϊ��");
                    return;
                }
                if (!code.getText().equals(label_1.getText())) {
                    JOptionPane.showMessageDialog(null, "��֤�����");
                    return;
                }
                if(jedis.hget(name.getText(),"passwd")==null){
                	int result = JOptionPane.showConfirmDialog(null, "�û��������ڣ��Ƿ����ע�᣿");
                	if(result == 0) {
                		/**
                		 * ����ע��ҳ��
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
                        JOptionPane.showMessageDialog(null, "��¼�ɹ�");
                        jedis.hset("loginUser", "userId", name.getText());
                        Main Main = new Main();
                        Main.setVisible(true);
                        dispose();
                    } else
                    	JOptionPane.showMessageDialog(null, "�������");
                    		
                }
                }
                
        });
        jb.setBounds(358, 286, 82, 25);    //����ȷ����ť�Ŀ��ߣ�xֵ��yֵ
        cp.add(jb);    //��ȷ����ť��ӵ�cp������

        //���ð�ť
        final JButton button = new JButton();
        button.setFont(new Font("����", Font.PLAIN, 20));
        button.setText("����");
        button.addActionListener(new ActionListener() {        //Ϊ���ð�ť��Ӽ����¼�
            //ͬʱ���name��password������
            public void actionPerformed(ActionEvent arg0) {
                // TODO �Զ����ɷ������
                name.setText("");
                password.setText("");
            }
        });
        button.setBounds(232, 286, 76, 25);    //�������ð�ť�Ŀ��ߣ�xֵ��yֵ
        getContentPane().add(button);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/img/5c751000211bf.png")));
        lblNewLabel.setBounds(264, 13, 537, 137);
        getContentPane().add(lblNewLabel);
        
        code = new JTextField();
        code.setFont(new Font("����", Font.PLAIN, 20));
        code.setBounds(290, 224, 150, 24);
        getContentPane().add(code);
        code.setColumns(10);
        
        JLabel label = new JLabel("\u9A8C\u8BC1\u7801\uFF1A");
        label.setFont(new Font("����", Font.PLAIN, 20));
        label.setBounds(188, 225, 88, 18);
        getContentPane().add(label);
        
        JButton button_1 = new JButton("\u751F\u6210\u9A8C\u8BC1\u7801");
        button_1.setFont(new Font("����", Font.PLAIN, 20));
        
        
        button_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent arg0) {
        		label_1.setText(String.valueOf(Math.round(Math.random()*1000000)));
        	}
        });

        button_1.setBounds(451, 223, 151, 27);
        getContentPane().add(button_1);
        
        label_1 = new JLabel("");
        label_1.setForeground(Color.RED);
        label_1.setFont(new Font("����", Font.PLAIN, 22));
        label_1.setBounds(471, 263, 113, 18);
        getContentPane().add(label_1);
        this.setLocationRelativeTo(null);
        setVisible(true);
    }

    public ActionListener actionPerformed() {
		// TODO Auto-generated method stub
		return null;
	}

	//main�������
    public static void main(String[] args) {
        new Login();    //����Login()

    }
}

