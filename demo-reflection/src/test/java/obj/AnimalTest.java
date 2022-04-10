package obj;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class AnimalTest {

    /**
     * 反射和调用【私有】构造函数
     */
    @Test
    void Constructor_private_newInstance() {
        try {
            Class<?> clazz = Class.forName("obj.Animal");
            //获取私有无参构造类
            Constructor private_params_construtor = clazz.getDeclaredConstructor(null);
            //设置允许访问，忽略修饰符
            private_params_construtor.setAccessible(true);
            System.out.println("反射获取私有无参构造类：" + private_params_construtor);
            //通过newInstance来创建对象
            Object obj = private_params_construtor.newInstance();

            Animal animal = (Animal) obj;
            System.out.println(animal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 反射和调用【公有】构造函数
     */
    @Test
    void Constructor_Public_newInstance() {
        try {
            Class<?> clazz = Class.forName("obj.Animal");
            //获取公有有参构造类
            // 错误： clazz.getConstructor(String.class, Integer.class); 用 Integer.class 类型 会报异常
            Constructor public_params_constructor = clazz.getConstructor(String.class, int.class);
            public_params_constructor.setAccessible(true);//设置允许访问，忽略修饰符
            System.out.println("反射获取公有有参构造类：" + public_params_constructor);
            //通过newInstance来创建对象
            Object obj = public_params_constructor.newInstance("狗子", 10);
            Animal animal = (Animal) obj;
            System.out.println(animal);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 反射和调用成员变量
     * 测试：对实例中私有和公有字段的访问和修改
     */
    @Test
    void Field_Set_Get() {
        try {
            Class clazz = Class.forName("obj.Animal");
            // 获取私有字段
            Field private_field_name = clazz.getDeclaredField("name");
            System.out.println("反射获取私有成员变量：" + private_field_name);
            // 获取公共字段
            Field public_field_age = clazz.getField("age");
            System.out.println("反射获取公共成员变量：" + public_field_age);
            // 实例化
            Object o = clazz.getConstructor(String.class, int.class)
                    .newInstance("狗子", 10);
            // 调用字段
            public_field_age.set(o, 5);
            //暴力反射，解除私有限定
            private_field_name.setAccessible(true);
            private_field_name.set(o, "小白");
            Animal animal = (Animal) o;
            System.out.println(animal);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 反射和调用【公有】成员方法
     */
    @Test
    void Method_Public_Invoke() {
        try {
            Class clazz = Class.forName("obj.Animal");
            //获取公有方法
            Method public_method = clazz.getMethod("showName", String.class);
            System.out.println("公共方法：" + public_method);
            //调用字段
            Object o = clazz.getConstructor(String.class, int.class)
                    .newInstance("狗子", 10);
            public_method.invoke(o, "小黑");

            Animal animal = (Animal) o;
            System.out.println(animal);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 反射和调用【私有】成员方法
     */
    @Test
    void Method_Private_Invoke() {
        try {
            Class clazz = Class.forName("obj.Animal");
            //获取公有方法
            Method private_method_showAge = clazz.getDeclaredMethod("showAge", int.class);
            System.out.println("私有方法：" + private_method_showAge);
            //实例化
            Object o = clazz.getConstructor(String.class, int.class)
                    .newInstance("狗子", 10);
            //暴力反射
            private_method_showAge.setAccessible(true);
            // 调用方法
            private_method_showAge.invoke(o, 4);

            Animal animal = (Animal) o;
            System.out.println(animal);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}