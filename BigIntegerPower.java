import java.math.BigInteger;
public class BigIntegerPower{
	public static void main(String[] args) throws InterruptedException{
		Thread thread = new Thread(new LongComputationTask(new BigInteger("211111111"),new BigInteger("12222220")));
		thread.setDaemon(true);
		thread.start();
		Thread.sleep(100);
		thread.interrupt();
		
	}
	private static class LongComputationTask implements Runnable{
		private BigInteger base;
		private BigInteger power;
		public LongComputationTask(BigInteger base,BigInteger power){
			this.base = base;
			this.power = power;
		}
		@Override
		public void run(){
			System.out.println(base+"^"+power+" = "+ pow(base,power));
		}
		public BigInteger pow(BigInteger base,BigInteger power){
			BigInteger result = BigInteger.ONE;
			
			for(BigInteger i = BigInteger.ZERO; i.compareTo(power)!=1;i=i.add(BigInteger.ONE)){
				if(Thread.currentThread().isInterrupted()){
					System.out.println("Permaturely interrupted computation");
					return BigInteger.ONE;
				}
				result = result.multiply(base);
			}
			return result;
		}
	}
}