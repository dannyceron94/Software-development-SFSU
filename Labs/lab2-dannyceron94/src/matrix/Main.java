package matrix;

class MyThread implements Runnable{
    private int [][]a;
    private int [][]b;
    private int start;
    private int stop;
//    public int [][]theAnswer;
public int[][]theAnswer = new int [1000][1000];


    public MyThread(int[][] aa, int[][] bb,int startt, int stopp){
        this.a=aa;
        this.b=bb;
        this.start = startt;
        this.stop = stopp;
//        this.theAnswer= new int [this.a.length][this.a.length];

    }
    public void run(){
        int size = a.length;
        //int answer[][] = new int[size][size];
        for (int i = this.start; i < this.stop; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    //answer[i][j] = answer[i][j] + (a[i][k] * b[k][j]);
                    theAnswer[i][j] = theAnswer[i][j]+ (a[i][k] * b[k][j]);
                }
            }
        }
        System.out.println("Thread done!");
    }

    public int[][]answer(){
        return theAnswer;
    }

}

public class Main {

    int a[][] = new int[1000][1000];
    int b[][] = new int[1000][1000];


    public static int getRand(int min, int max) {
        int x = (int) (Math.random() * ((max - min) + 1)) + min;
        return x;
    }

    public static int[][] multiplySerial(int[][] a, int[][] b) {
        int size = a.length;
        int answer[][] = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    answer[i][j] = answer[i][j] + (a[i][k] * b[k][j]);
                }
            }
        }
        return answer;
    }

    public static int[][] multiplyParallel(int[][] a, int[][] b) {
        int size = a.length;
        int answer[][] = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < size; k++) {
                    answer[i][j] = answer[i][j] + (a[i][k] * b[k][j]);
                }
            }
        }
        return answer;
    }

    public static boolean checkAnswer(int[][] a, int[][] b) {
        int size = a.length;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (a[i][j] != b[i][j]) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws InterruptedException{
        int size = 1000;
        int a[][] = new int[size][size];
        int b[][] = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                a[i][j] = getRand(1, 1000);
                b[i][j] = getRand(1, 1000);
            }
        }
        long startTime = System.nanoTime();
        int c[][] = multiplySerial(a, b);
        long endTime = System.nanoTime();
        long serialTime = endTime - startTime;
        System.out.println("Serial Time " + serialTime + " ns");

        startTime = System.nanoTime();
        // filler, make either a new class that extends thread, or have this one extend thread
        // figure out how to split work up into at least 2 more threads
        int d[][] = multiplyParallel(a, b);
        endTime = System.nanoTime();
        long parallelTime = endTime - startTime;
        System.out.println("Parallel Time " + parallelTime + " ns");
        long diff = (parallelTime - serialTime);
        long percent = diff * 100 / serialTime;
        System.out.printf("Percent change %.2f \n", (float) percent);
        if (checkAnswer(c, d)) {
            System.out.println("Valid Answer");
        } else {
            System.out.println("Invalid Answer");
        }


        int num = a.length/ 2;
        int num2 = num+1;

        MyThread work1 = new MyThread(a, b, 0, num);
        MyThread work2 = new MyThread(a, b,num2 ,a.length);

        Thread t1 = new Thread(work1);
        Thread t2 = new Thread(work2);

        t1.start();
        t2.start();


        t1.join();
        t2.join();


    }
}