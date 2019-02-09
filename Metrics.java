port java.util.Random;
public class Metrics {
	public static void main(String[] args){
		metrics metric = new metrics();
		BusinessLogic busniessThread1 = new BusinessLogic(metric);
		BusinessLogic busniessThread2 = new BusinessLogic(metric);
		MetricsPrinter Metricsprinter = new MetricsPrinter(metric);
		busniessThread2.start();
		busniessThread1.start();
		Metricsprinter.start();
	}
	public static class MetricsPrinter extends Thread{
		private metrics metrics;
		public MetricsPrinter(metrics metrics){
			this.metrics= metrics;
		};
		@Override
		public void run(){
			while(true){
			
				try{
					Thread.sleep(100);
					
				}catch (InterruptedException e){
					
				}
				double average= metrics.getAverage();
				System.out.println("Current Average"+average);
				
			}
		}
	};
	
	public static class BusinessLogic extends Thread{
			private metrics metric;
			private Random random = new Random();
			public BusinessLogic(metrics metric){
				this.metric=metric;
			};
			@Override
			public void run(){
				while(true){
					long start = System.currentTimeMillis();
					try{
						Thread.sleep(100);
						Thread.sleep(random.nextInt(10));
					}catch (InterruptedException e){
						
					}
					long end = System.currentTimeMillis();
					metric.addSample(end-start);
				}
			}
			
		};
		public static class metrics{
			private long count = 0;
			private double average = 0.0;
			public synchronized void addSample(long sample){
				double currentSum =average*count;
				count++;
				average = (currentSum + sample)/count;
				
			}
			public double getAverage(){
				return average;
			}
		};
	
}
