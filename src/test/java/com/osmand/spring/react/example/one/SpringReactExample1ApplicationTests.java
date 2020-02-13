package com.osmand.spring.react.example.one;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import reactor.core.publisher.Flux;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringReactExample1ApplicationTests {
	
	Logger logger = LoggerFactory.getLogger(SpringReactExample1ApplicationTests.class);
	private List<String> names = Arrays.asList("Osmand","Sarvjit","Krishna","Tarun");
	
	//@Test
	public void contextLoads() {
	}
	
	
	@Test
	public void subscribe1() throws Exception {
		// create a publisher
		logger.info("\n\n in subscribe1 .......");
		Flux<String> team = Flux.just(names.toArray(new String[names.size()]));
		
		// subscribes to the publisher
		team.toStream().forEach(System.out::println);
	}
	
	@Test
	public void subscribe2() throws Exception {
		// create a publisher
		logger.info("\n\n in subscribe2 .......");
		Flux<String> team = Flux.just(names.toArray(new String[names.size()]));
		
		// subscribes to the publisher
		team.subscribe(System.out::println);
	}
	
	@Test
	public void subscribe3() throws Exception {
		// create a publisher
		logger.info("\n\n in subscribe3 .......");
		Flux<String> team = Flux.just(names.toArray(new String[names.size()]));
		
		//----->>>> This will not evaluate since there is no subscribe
		team.doOnEach( a -> System.out.println(a.get()));
	}
	
	@Test
	public void subscribe4() throws Exception {
		// create a publisher
		logger.info("\n\n in subscribe4 .......");
		Flux<String> team = Flux.just(names.toArray(new String[names.size()]));
		
		// subscribes to the publisher; there will be a NULL value at the end since there is no complete channel 
		team.doOnEach( a -> System.out.println(a.get())).subscribe();
	}
	
	@Test
	public void subscribe5() throws Exception {
		// create a publisher
		logger.info("\n\n in subscribe5 .......");
		Flux<String> team = Flux.just(names.toArray(new String[names.size()]));
		
		// overloaded subscriber. subscribes to the publisher with three channels -> Data, Error, and Complete
		team.subscribe(a -> System.out.println(a), null, () ->  System.out.println("All done...")  );
	}
	
	@Test
	public void subscribe6() throws Exception {
		// create a publisher
		logger.info("\n\n in subscribe6 .......");
		Flux<String> team = Flux.just(names.toArray(new String[names.size()]));
		
		// subscribes to the publisher with three channels -> Data, Error, and Complete
		// this is same as subscribe5, only difference is that it demonstrates how Consumers and a Runnable are used form channels
		
		Consumer<String> dataChannel = (data) -> System.out.println(data);
		Consumer<Throwable> errorChannel = (e) -> System.out.println(e);
		Runnable completeChannel = () -> System.out.println("All Done. Closing Channel");
		
		team.subscribe(dataChannel, errorChannel, completeChannel  );
	}
	
	@Test
	public void mapAndSubscribe1() throws Exception {
		// create a publisher
		logger.info("\n\n in mapAndSubscribe1 .......");
		Flux<String> team = Flux.just(names.toArray(new String[names.size()]));
		
		// map operation
		// check the output, it shows some inner workings
		team.map(String::length)
			.doOnEach(System.out::println)
			.subscribe( );
	}
	
	@Test
	public void filterAndSubscribe1() throws Exception {
		// create a publisher
		logger.info("\n\n in filterAndSubscribe1 .......");
		Flux<String> team = Flux.just(names.toArray(new String[names.size()]));
		
		// filter operation
		team.filter(n -> n.length() ==6)
			.doOnEach(System.out::println)
			.subscribe( );
	}
	
	@Test
	public void takeAndSubscribe1() throws Exception {
		// create a publisher
		logger.info("\n\n in takeAndSubscribe1 .......");
		Flux<String> team = Flux.just(names.toArray(new String[names.size()]));
		
		// take or limit operation
		team.take(2)
			.doOnEach(System.out::println)
			.subscribe( );
	}
	
	@Test
	public void sortAndSubscribe1() throws Exception {
		// create a publisher
		logger.info("\n\n in sortAndSubscribe1 .......");
		Flux<String> team = Flux.just(names.toArray(new String[names.size()]));
		
		// sort operation
		team.sort()
			.doOnEach(System.out::println)
			.subscribe( );
	}
	
	@Test
	public void collectAndSubscribe1() throws Exception {
		// create a publisher
		logger.info("\n\n in collectAndSubscribe1 .......");
		Flux<String> team = Flux.just(names.toArray(new String[names.size()]));
		
		// collect operation
		Consumer<String> dataChannel = (data) -> System.out.println(data);
		Consumer<Throwable> errorChannel = (e) -> System.out.println(e);
		Runnable completeChannel = () -> System.out.println("All Done. Closing Channel");
		
		team.sort()
			.collect(Collectors.joining(", "))
			.subscribe(dataChannel,errorChannel , completeChannel);
	}
	
	@Test
	public void reduceAndSubscribe1() throws Exception {
		// create a publisher
		logger.info("\n\n in reduceAndSubscribe1 .......");
		Flux<String> team = Flux.just(names.toArray(new String[names.size()]));
		
		// collect operation
		Consumer<String> dataChannel = (data) -> System.out.println(data);
		Consumer<Throwable> errorChannel = (e) -> System.out.println(e);
		Runnable completeChannel = () -> System.out.println("All Done. Closing Channel");
		
		team.sort()
			.reduce((a,b) -> a + ", " + b)
			.subscribe(dataChannel,errorChannel , completeChannel);
	}
	
	@Test
	public void flatMapAndSubscribe1() throws Exception {
		// create a publisher
		logger.info("\n\n in flatMapAndSubscribe1 .......");
		Flux<List<List<Integer>>> lists = Flux.just(Arrays.asList(Arrays.asList(1,2,3),Arrays.asList(4,5,6)));
		
		Consumer<List<Integer>> dataChannel = (data) -> System.out.println(data);
		Consumer<Throwable> errorChannel = (e) -> System.out.println(e);
		Runnable completeChannel = () -> System.out.println("All Done. Closing Channel");
		
		lists.flatMap(l -> Flux.fromIterable(l))
			.subscribe(dataChannel,errorChannel , completeChannel);
	}
	
	@Test
	public void flatMapTwiceAndSubscribe1() throws Exception {
		// create a publisher
		logger.info("\n\n in flatMapTwiceAndSubscribe1 .......");
		Flux<List<List<Integer>>> lists = Flux.just(Arrays.asList(Arrays.asList(1,2,3),Arrays.asList(4,5,6)));

		Consumer<Integer> dataChannel = (data) -> System.out.println(data);
		Consumer<Throwable> errorChannel = (e) -> System.out.println(e);
		Runnable completeChannel = () -> System.out.println("All Done. Closing Channel");
		
		lists.flatMap(l -> Flux.fromIterable(l))
				.flatMap(l -> Flux.fromIterable(l))
				.subscribe(dataChannel,errorChannel , completeChannel);
	}
	
	@Test
	public void flatMapWithJava8StreamAndSubscribe1() throws Exception {
		// create a publisher
		logger.info("\n\n in flatMapWithJava8StreamAndSubscribe1 .......");
		Flux<List<List<Integer>>> lists = Flux.just(Arrays.asList(Arrays.asList(1,2,3),Arrays.asList(4,5,6)));

		Consumer<Integer> dataChannel = (data) -> System.out.println(data);
		Consumer<Throwable> errorChannel = (e) -> System.out.println(e);
		Runnable completeChannel = () -> System.out.println("All Done. Closing Channel");
		
		lists.flatMap(pLists -> Flux.fromIterable(
												(pLists.stream()
														.flatMap(Collection::stream)
												).collect(Collectors.toList())
							)
					).subscribe(dataChannel,errorChannel , completeChannel);
	}
	
	
	

}
