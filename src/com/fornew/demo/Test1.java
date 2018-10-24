package com.fornew.demo;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import com.fornew.demo.enttity.Employee;

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
		
		/**
		 * 1.若lambda体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致。
		 * 2.若lambda参数列表中的第一个餐宿是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用className::method
		 */
		@Test
		public void test9() {
			
			Consumer<Integer> consumer = (x)->System.out.println(x);
			consumer.accept(100);
			
			//方法引用 	-对象::实例方法
			Consumer<Integer> consumer2 = System.out::println;
			consumer2.accept(200);
			
			//方法引用-类名::静态方法名
			BiFunction<Integer, Integer, Integer> biFun = (x,y)->Integer.compare(x, y);
			
			BiFunction<Integer, Integer, Integer> biFun2 = Integer::compare;
			Integer apply = biFun.apply(200, 200);
			System.out.println(apply);
			
			//方法引用-类名::实例方法名
			BiFunction<String, String, Boolean> biFun3 = (x,y)->x.equals(y);
			BiFunction<String, String, Boolean> biFun4 = String::equals;
			Boolean apply2 = biFun4.apply("hello", "world");
			System.out.println(apply2);
		}
		
		/**
		 * 构造器引用
		 */
		@Test
		public void test10() {
			//无参构造  类名::new
			Supplier<Employee> sup = ()->new Employee("弟弟",new BigDecimal("22.1"),100);
			System.out.println(sup.get());
			Supplier<Employee>sup2 = Employee::new;
			System.out.println(sup2.get());
			
			//有参构造 类名::new(带参)
			Function<Integer,Employee> sup3 = (x)->new Employee(x);
			Function<Integer, Employee> sup4 = Employee::new;
			System.out.println(sup4.apply(100));
			
		}
		
		//-----------------------分割线---------------------------------//
		
		/**
		 * stream操作三个步骤
		 * 1.创建stream
		 * 2.中间操作(过滤,map)
		 * 3.终止操作
		 */
		
		
		public List<Employee> buildInfo2() {
			Employee Employee = new Employee("aaa", new BigDecimal("0.96"),11);	
			Employee Employee2 = new Employee("bbb", new BigDecimal("1.96"),44);	
			Employee Employee3 = new Employee("ccc", new BigDecimal("2.96"),22);	
			//Employee Employee4 = new Employee();
			List<Employee> dataList = new ArrayList<>();
			dataList.add(Employee);
			dataList.add(Employee2);
			dataList.add(Employee3);
			//dataList.add(Employee4);
			return dataList;
		}
		
		@Test
		public void test11() {
			//筛选 过滤  去重
			List<Employee> info = buildInfo2();
			info.stream().filter(emp -> emp.getAge()>20).
			limit(1).distinct().
			forEach(System.out::println);
		}
		
		@Test
		public void test12() {
			//map流
			List<Employee> info2 = buildInfo2();
			info2.stream().map((emp)->emp.getAge()).forEach(System.out::println);
		}
		
		@Test
		public void test13() {
			List<Employee> info2 = buildInfo2();
			info2.stream().sorted((e1,e2)->{
					return e1.getName().compareTo(e2.getName());
			}).forEach(System.out::println);
		}
		
		/**
         *      查找和匹配
         *          allMatch-检查是否匹配所有元素
         *          anyMatch-检查是否至少匹配一个元素
         *          noneMatch-检查是否没有匹配所有元素
         *          findFirst-返回第一个元素
         *          findAny-返回当前流中的任意元素
         *          count-返回流中元素的总个数
         *          max-返回流中最大值
         *          min-返回流中最小值
         */

        /**
         *  检查是否匹配元素
         */
		@Test
		public void test14() {
			List<Employee> info2 = buildInfo2();
			boolean allMatch = info2.stream().allMatch((e)->e.getName().equals("aaa"));
			System.out.println(allMatch);
			
			boolean match = info2.stream().anyMatch((e)->e.getName().equals("aaa"));
			System.out.println(match);
			
			boolean match2 = info2.stream().noneMatch((e)->e.getName().equals("aaa"));
			System.out.println(match2);
			
			Optional<Employee> opt = info2.stream().findFirst();
			System.out.println(opt.get());
			
			//并行流
			Optional<Employee> optional = info2.parallelStream().findAny();
			System.out.println(optional.get());
			
			long count = info2.stream().count();
			System.out.println(count);
			
			Optional<Employee> max = info2.stream().max((e1,e2)->Integer.compare(e1.getAge(), e2.getAge()));
			System.out.println(max.get());
			
			Optional<Employee> min = info2.stream().min((e1,e2)->Integer.compare(e1.getAge(), e2.getAge()));
			System.out.println(min.get());
			
			
			//规约操作 reduce 可以将流中所有的元素反复结合起来成一个值
			Optional<BigDecimal> reduce = info2.stream().map(Employee::getSalary).reduce(BigDecimal::add);
			System.out.println(reduce.get());
			
			//收集操作collect 将流转换为其他形式，接收一个Collection 接口的实现，用于给Stream中元素做汇总的方法
			List<Integer> collect = info2.stream().map(Employee::getAge).collect(Collectors.toList());
			collect.stream().forEach(System.out::println);
		}
		
		@Test
	    public void test(){
	        // 并行流 多个线程执行
	        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
	        numbers.parallelStream()
	                .forEach(System.out::print);

	        //
	        System.out.println("=========================");
	        numbers.stream()
	                     .sequential()
	                     .forEach(System.out::print);
	    }
		
		/**
	     *      Optional.of(T t); // 创建一个Optional实例
	     *      Optional.empty(); // 创建一个空的Optional实例
	     *      Optional.ofNullable(T t); // 若T不为null，创建一个Optional实例，否则创建一个空实例
	     *      isPresent();    // 判断是够包含值
	     *      orElse(T t);   //如果调用对象包含值，返回该值，否则返回T
	     *      orElseGet(Supplier s);  // 如果调用对象包含值，返回该值，否则返回s中获取的值
	     *      map(Function f): // 如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty();
	     *      flatMap(Function mapper);// 与map类似。返回值是Optional
	     *
	     *      总结：Optional.of(null)  会直接报NPE
	     */
			
		@Test
		public void test15() {
			Optional<Object> empty = Optional.empty();
			System.out.println(empty);
			
			
			Optional<Employee> of = Optional.of(new Employee(null,new BigDecimal("20.2"),10));
			System.out.println(of.get());
			
			Optional<Employee> of2 = Optional.of(new Employee("huang",new BigDecimal("22"),22));
			of2 = Optional.empty();
			Employee employee = of2.orElseGet(()->new Employee());
			System.out.println(employee);
			
			Optional<Employee> of3 = Optional.of(new Employee("huang",new BigDecimal("22"),22));
			System.out.println(of3.map((e)->e.getName()));
			
		}
		
		
		
		//日期设置
		@Test
		public void test16() {
			//从默认时区的系统时钟获取当前的日期时间。不用考虑时区差
			LocalDateTime date = LocalDateTime.now();
			System.out.println(date);
			
			System.out.println(date.getDayOfMonth());
			System.out.println(date.getDayOfYear());
			System.out.println(date.getHour());
			System.out.println(date.getYear());
			System.out.println(date.getMonth());
			System.out.println(date.getMinute());
			System.out.println(date.getNano());
			
			//手动创建一个LocalDateTime实例
			LocalDateTime date2 = LocalDateTime.of(2017, 12, 17, 9, 31, 31);
			System.out.println(date2);
			
			LocalDateTime days = date2.plusDays(5);
			System.out.println(days);
			
			LocalDateTime minusYears = days.minusYears(5);
			System.out.println(minusYears);
		}
		
		
		@Test
	    public void test17(){
	        // Duration:计算两个时间之间的间隔
	        // Period：计算两个日期之间的间隔

	        Instant ins1 = Instant.now();

	        try {
	            Thread.sleep(1000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        Instant ins2 = Instant.now();
	        Duration dura = Duration.between(ins1, ins2);
	        System.out.println(dura);
	        System.out.println(dura.toMillis());

	        System.out.println("======================");
	        LocalTime localTime = LocalTime.now();
	        try {
	            Thread.sleep(1000);
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        LocalTime localTime2 = LocalTime.now();
	        Duration du2 = Duration.between(localTime, localTime2);
	        System.out.println(du2);
	        System.out.println(du2.toMillis());
	    }

		

		
}
