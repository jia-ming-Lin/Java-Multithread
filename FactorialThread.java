import java.math.BigInteger;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

public class FactorialThread {
	public static void  main(String[] args) throws InterruptedException{
		List<Long> inputNumbers = Arrays.asList(0L,3435L,35435L,2324L,4656L,23L,5556L);
		List<FactorialThreadfun> threads = new ArrayList<>();
		for (long input : inputNumbers){
			threads.add(new FactorialThreadfun(input));
		}
		for(Thread thread: threads){
			thread.setDaemon(true);
			thread.start();
		}
		for(Thread thread : threads){
			thread.join(2000);
		}
		for(int i = 0; i<inputNumbers.size();i++){
			FactorialThreadfun factorialThread = threads.get(i);
			if(factorialThread.isFinished()){
				System.out.println("Factorial of " + inputNumbers.get(i) + " is "+ factorialThread.getResult());
			}
			else{
				System.out.println("The calculation for " + inputNumbers.get(i) + " is still processed ");
			}
		}
	}
	public static class FactorialThreadfun extends Thread{ 
		private long inputNumber;
		private BigInteger result = BigInteger.ZERO;
		private boolean isFinished = false;
		public  FactorialThreadfun(long inputNumber){
		this.inputNumber= inputNumber;
	}
		@Override
		public void run(){
			this.result = factorial(inputNumber);
			this.isFinished=true;
		}
		public BigInteger factorial(long n){
			BigInteger tmpResult = BigInteger.ONE;
			for(long i = n; i>0 ; i--){
				tmpResult = tmpResult.multiply(new BigInteger(Long.toString(i)));
			}
			return tmpResult;
		}
		public boolean isFinished(){
			return this.isFinished;
		}
		public BigInteger getResult(){
			return this.result;
		}
}
}
