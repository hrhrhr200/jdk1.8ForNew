package com.fornew.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * jdk1.8新特性
 * 1.lambda表达式
 * 2.函数式接口
 * @author huangrui01
 *
 */
public class Test1 {
	
		public static void main(String[] args) {
			Product product = new Product("黄色", 0.96);	
			Product product2 = new Product("红色", 1.96);	
			Product product3 = new Product("蓝色", 2.96);	
			List<Product> dataList = new ArrayList<>();
			dataList.add(product);
			dataList.add(product2);
			dataList.add(product3);
			List<Product> products = filterProductByPredicate(dataList, (p) -> p.getColor().equals("红色"));
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
			dataList.stream().filter((a)->a.getColor().equals("红色")).limit(2).forEach(System.out::println);
		}
		
		public List<Product> buildInfo() {
			Product product = new Product("黄色", 0.96);	
			Product product2 = new Product("红色", 1.96);	
			Product product3 = new Product("蓝色", 2.96);	
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
		
		//函数型接口
		/**
		 * Consumer 消费型接口，有参无返回值
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
		 * Supplier 供给型接口，无参有返回值
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
		 * Function 函数式接口 ，有参有返回值
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
		 * Predicate 预发式接口，有参有返回值，返回值布尔
		 * @param str
		 * @param pre
		 * @return
		 */
		public Boolean changeBoolean(String str,Predicate<String> pre) {
			return pre.test(str);
		}
		
		
}
