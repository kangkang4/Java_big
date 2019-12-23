package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
 
/**
 *�����������������е�Swing��ť����¼��������ݽ�����Ȩ<br>
 *ת����ʹ����������
 *
 *@author �ư�
 *
 */
public class EventHandler implements ActionListener {
   
    //������ڵĴ������
    private Object form = null;
   
    //�ܵ�ί�еķ�����
    private String methodName = null;
   
    /**
     *���캯��
     *
     *@paramform           ������ڵĴ������
     *@parammethodName     �ܵ�ί�еķ�����
     */
    public EventHandler(Object form,String methodName) {
        this.form = form;
        this.methodName = methodName;
    }
   
    /**
     *�¼�����ί�з���
     */
    public void actionPerformed(ActionEvent e) {
       
        //�õ�������������
        Class<? extends Object> formType = this.form.getClass();
       
        try {
            //�õ�ָ��ί�з���������
            Method method =
                formType.getMethod(this.methodName, new Class[] {e.getClass()});
            //����ָ���ķ���
            method.invoke(this.form, new Object[] {e});
           
        }catch(Exception ex) {
           
            return;
        }      
   
    }
 
}