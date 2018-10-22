package com.fornew.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * jdk1.8������
 * 1.lambda���ʽ
 * 2.����ʽ�ӿ�
 * @author huangrui01
 *
 */
public class Test1 {
	
		public static void main(String[] args) {
			Product product = new Product("��ɫ", 0.96);	
			Product product2 = new Product("��ɫ", 1.96);	
			Product product3 = new Product("��ɫ", 2.96);	
			List<Product> dataList = new ArrayList<>();
			dataList.add(product);
			dataList.add(product2);
			dataList.add(product3);
			List<Product> products = filterProductByPredicate(dataList, (p) -> p.getColor().equals("��ɫ"));
		      for (Product pro : products){
		          System.out.println(pro);
		      }
	
		}
		
		public static List<Product> filterProductByPredicate(List<Product> list,MyPredicate<Product> mp){
	        List<Product> prods = new ArrayList<>();
	        for (Product prod : list){
	            if (mp.test(prod)){
	                prods.add(prod);
	            }
	        }
	        return prods;
	    }
		
		@Test
		public void test2() {
			List<Product>dataList =  buildInfo();
			dataList.stream().filter((a)->a.getColor().equals("��ɫ")).limit(2).forEach(System.out::println);
		}
		
		public List<Product> buildInfo() {
			Product product = new Product("��ɫ", 0.96);	
			Product product2 = new Product("��ɫ", 1.96);	
			Product product3 = new Product("��ɫ", 2.96);	
			List<Product> dataList = new ArrayList<>();
			dataList.add(product);
			dataList.add(product2);
			dataList.add(product3);
			
			return dataList;
		}
		
		@Test
		public void test3() {
			List<Product>dataList =  buildInfo();
			dataList.stream().map((p)->p.getColor()).forEach(System.out::println);
		}
		
		@Test
		public void test4() {
			changeStr(5, (str)->{
				int a = 10;
				System.err.println(str+a);
			});
		}
		
		//�����ͽӿ�
		/**
		 * Consumer �����ͽӿڣ��в��޷���ֵ
		 * @param str
		 * @param con
		 */
		public void changeStr(Integer str,Consumer<Integer> con) {
			con.accept(str);
		}
		
		
		@Test
		public void test6() {
			/*String value = getValue(new Supplier<String>() {

				@Override
				public String get() {
					// TODO Auto-generated method stub
					return "hello";
				}
			});*/
			String value = getValue(()->"hello");
			System.out.println(value);
		}
		
		/**
		 * Supplier �����ͽӿڣ��޲��з���ֵ
		 * @param sup
		 * @return
		 */
		public String getValue(Supplier<String> sup) {
			return sup.get();
		}
		
		
		@Test
		public void test7() {
			/*Long changNum = changNum(100L, new Function<Long, Long>() {
					@Override
					public Long apply(Long num) {
						// TODO Auto-generated method stub
						return num+num;
					}
			});*/
			Long changNum = changNum(100L, (num)->{
				Long num2 = 200L;
				return num+num2;
			});
			System.out.println(changNum);
		}
		
		/**
		 * Function ����ʽ�ӿ� ���в��з���ֵ
		 * @param num
		 * @param fun
		 * @return
		 */
		public Long changNum(Long num,Function<Long, Long> fun) {
			return fun.apply(num);
		}
		
		@Test
		public void test8() {
			Boolean boolean1 = changeBoolean("hello", (str)->str.length()>2);
			System.out.println(boolean1);
		}
		
		
		/**
		 * Predicate Ԥ��ʽ�ӿڣ��в��з���ֵ������ֵ����
		 * @param str
		 * @param pre
		 * @return
		 */
		public Boolean changeBoolean(String str,Predicate<String> pre) {
			return pre.test(str);
		}
		
		
}
