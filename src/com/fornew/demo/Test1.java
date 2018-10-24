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
		
		/**
		 * 1.��lambda���е��÷����Ĳ����б��뷵��ֵ���ͣ�Ҫ�뺯��ʽ�ӿ��г��󷽷��ĺ����б�ͷ���ֵ���ͱ���һ�¡�
		 * 2.��lambda�����б��еĵ�һ��������ʵ�������ĵ����ߣ����ڶ���������ʵ�������Ĳ���ʱ������ʹ��className::method
		 */
		@Test
		public void test9() {
			
			Consumer<Integer> consumer = (x)->System.out.println(x);
			consumer.accept(100);
			
			//�������� 	-����::ʵ������
			Consumer<Integer> consumer2 = System.out::println;
			consumer2.accept(200);
			
			//��������-����::��̬������
			BiFunction<Integer, Integer, Integer> biFun = (x,y)->Integer.compare(x, y);
			
			BiFunction<Integer, Integer, Integer> biFun2 = Integer::compare;
			Integer apply = biFun.apply(200, 200);
			System.out.println(apply);
			
			//��������-����::ʵ��������
			BiFunction<String, String, Boolean> biFun3 = (x,y)->x.equals(y);
			BiFunction<String, String, Boolean> biFun4 = String::equals;
			Boolean apply2 = biFun4.apply("hello", "world");
			System.out.println(apply2);
		}
		
		/**
		 * ����������
		 */
		@Test
		public void test10() {
			//�޲ι���  ����::new
			Supplier<Employee> sup = ()->new Employee("�ܵ�",new BigDecimal("22.1"),100);
			System.out.println(sup.get());
			Supplier<Employee>sup2 = Employee::new;
			System.out.println(sup2.get());
			
			//�вι��� ����::new(����)
			Function<Integer,Employee> sup3 = (x)->new Employee(x);
			Function<Integer, Employee> sup4 = Employee::new;
			System.out.println(sup4.apply(100));
			
		}
		
		//-----------------------�ָ���---------------------------------//
		
		/**
		 * stream������������
		 * 1.����stream
		 * 2.�м����(����,map)
		 * 3.��ֹ����
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
			//ɸѡ ����  ȥ��
			List<Employee> info = buildInfo2();
			info.stream().filter(emp -> emp.getAge()>20).
			limit(1).distinct().
			forEach(System.out::println);
		}
		
		@Test
		public void test12() {
			//map��
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
         *      ���Һ�ƥ��
         *          allMatch-����Ƿ�ƥ������Ԫ��
         *          anyMatch-����Ƿ�����ƥ��һ��Ԫ��
         *          noneMatch-����Ƿ�û��ƥ������Ԫ��
         *          findFirst-���ص�һ��Ԫ��
         *          findAny-���ص�ǰ���е�����Ԫ��
         *          count-��������Ԫ�ص��ܸ���
         *          max-�����������ֵ
         *          min-����������Сֵ
         */

        /**
         *  ����Ƿ�ƥ��Ԫ��
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
			
			//������
			Optional<Employee> optional = info2.parallelStream().findAny();
			System.out.println(optional.get());
			
			long count = info2.stream().count();
			System.out.println(count);
			
			Optional<Employee> max = info2.stream().max((e1,e2)->Integer.compare(e1.getAge(), e2.getAge()));
			System.out.println(max.get());
			
			Optional<Employee> min = info2.stream().min((e1,e2)->Integer.compare(e1.getAge(), e2.getAge()));
			System.out.println(min.get());
			
			
			//��Լ���� reduce ���Խ��������е�Ԫ�ط������������һ��ֵ
			Optional<BigDecimal> reduce = info2.stream().map(Employee::getSalary).reduce(BigDecimal::add);
			System.out.println(reduce.get());
			
			//�ռ�����collect ����ת��Ϊ������ʽ������һ��Collection �ӿڵ�ʵ�֣����ڸ�Stream��Ԫ�������ܵķ���
			List<Integer> collect = info2.stream().map(Employee::getAge).collect(Collectors.toList());
			collect.stream().forEach(System.out::println);
		}
		
		@Test
	    public void test(){
	        // ������ ����߳�ִ��
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
	     *      Optional.of(T t); // ����һ��Optionalʵ��
	     *      Optional.empty(); // ����һ���յ�Optionalʵ��
	     *      Optional.ofNullable(T t); // ��T��Ϊnull������һ��Optionalʵ�������򴴽�һ����ʵ��
	     *      isPresent();    // �ж��ǹ�����ֵ
	     *      orElse(T t);   //������ö������ֵ�����ظ�ֵ�����򷵻�T
	     *      orElseGet(Supplier s);  // ������ö������ֵ�����ظ�ֵ�����򷵻�s�л�ȡ��ֵ
	     *      map(Function f): // �����ֵ���䴦�������ش�����Optional�����򷵻�Optional.empty();
	     *      flatMap(Function mapper);// ��map���ơ�����ֵ��Optional
	     *
	     *      �ܽ᣺Optional.of(null)  ��ֱ�ӱ�NPE
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
		
		
		
		//��������
		@Test
		public void test16() {
			//��Ĭ��ʱ����ϵͳʱ�ӻ�ȡ��ǰ������ʱ�䡣���ÿ���ʱ����
			LocalDateTime date = LocalDateTime.now();
			System.out.println(date);
			
			System.out.println(date.getDayOfMonth());
			System.out.println(date.getDayOfYear());
			System.out.println(date.getHour());
			System.out.println(date.getYear());
			System.out.println(date.getMonth());
			System.out.println(date.getMinute());
			System.out.println(date.getNano());
			
			//�ֶ�����һ��LocalDateTimeʵ��
			LocalDateTime date2 = LocalDateTime.of(2017, 12, 17, 9, 31, 31);
			System.out.println(date2);
			
			LocalDateTime days = date2.plusDays(5);
			System.out.println(days);
			
			LocalDateTime minusYears = days.minusYears(5);
			System.out.println(minusYears);
		}
		
		
		@Test
	    public void test17(){
	        // Duration:��������ʱ��֮��ļ��
	        // Period��������������֮��ļ��

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
