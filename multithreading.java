public class multithreading {
    static class Task implements Runnable {
        private final String name;
        public Task(String name){
            this.name=name;
        }
        @Override
        public void run(){
            for(int i=1; i<=10; i++){
                System.out.println(name + ": "+ i);
                try{
                    Thread.sleep(1000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    
        
    }


    public static void main(String[] args){
        Thread t1 = new Thread(new Task("thread 1"));
        Thread t2 = new Thread(new Task("thread 2"));
        Thread t3 = new Thread(new Task("thread 3"));

        t1.start();
        t2.start();
        t3.start();

    }

    
}