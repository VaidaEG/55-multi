/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package multi;

public class Multi {

    
    public static void main(String[] args) {
        
        System.out.println("main starts work");
        
        class BattleField {
            int a = 0;
            int b = 0;
        }
        BattleField bf = new BattleField();
        
        Thread t1 = new Thread(() -> {
            while (bf.a < 10) {
                try {
                    Thread.sleep(1);  
                } catch (Exception ex) {
                    // ignored
                }
                synchronized (bf) {
                    bf.a++;
                    bf.b++;
                    System.out.println("increment:" + bf.a + " " + bf.b);
                    if (bf.a != bf.b) {
                        throw new IllegalStateException();
                    }
                }
            }
        });
        Thread t2 = new Thread(() -> {
            while (bf.a > -10) {
                try {
                    Thread.sleep(1);
                } catch (Exception ex) {
                    // ignored
                }
                if (bf.a < -5) {
                    try {
                        System.out.println("Waiting for t1");
                        t1.join();
                    } catch (Exception ex) {
                        // igonred
                    }
                }
                synchronized (bf) {
                    bf.a--;
                    bf.b--;
                    System.out.println("decrement:" + bf.a + " " + bf.b);
                    if (bf.a != bf.b) {
                        throw new IllegalStateException();
                    }
                }
            }
        });
        t1.start();
        t2.start();
        
        System.out.println("main ends work");
        
        
        
        
//        System.out.println("main starts work");
//        Action a1 = new Action("first");
//        Action a2 = new Action("second");
//        OtherAction oa = new OtherAction("third");
//        Thread t1 = new Thread(kv);
//        Thread t2 = new Thread(() -> {
//            System.out.println("starts work lambda");
//            for (int i = 0; i < 1000; i++) {
//                System.out.println(": " + i + " lamda");
//            }
//            System.out.println(" end work lambda");
//        });
    } 
}
