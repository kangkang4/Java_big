package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
 
/**
 *该类是用来处理所有的Swing按钮点击事件，并根据将处理权<br>
 *转交给使用者来处理
 *
 *@author 哑巴
 *
 */
public class EventHandler implements ActionListener {
   
    //组件所在的窗体对象
    private Object form = null;
   
    //受到委托的方法名
    private String methodName = null;
   
    /**
     *构造函数
     *
     *@paramform           组件所在的窗体对象
     *@parammethodName     受到委托的方法名
     */
    public EventHandler(Object form,String methodName) {
        this.form = form;
        this.methodName = methodName;
    }
   
    /**
     *事件处理委托方法
     */
    public void actionPerformed(ActionEvent e) {
       
        //得到窗体对象的类型
        Class<? extends Object> formType = this.form.getClass();
       
        try {
            //得到指定委托方法的类型
            Method method =
                formType.getMethod(this.methodName, new Class[] {e.getClass()});
            //调用指定的方法
            method.invoke(this.form, new Object[] {e});
           
        }catch(Exception ex) {
           
            return;
        }      
   
    }
 
}