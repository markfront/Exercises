/*
https://leetcode.com/problems/the-dining-philosophers/
*/

class DiningPhilosophers {
    ReentrantLock[] fk_locks = new ReentrantLock[5];
    
    ReentrantLock ph_lock = new ReentrantLock(true);

    public DiningPhilosophers() {
        for(int i=0; i< 5; i++) {
            fk_locks[i] = new ReentrantLock(true);
        }
    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher,
                           Runnable pickLeftFork,
                           Runnable pickRightFork,
                           Runnable eat,
                           Runnable putLeftFork,
                           Runnable putRightFork) throws InterruptedException {
        
        ph_lock.lock();
        
        fk_locks[philosopher].lock();
        pickLeftFork.run();

        int leftside = (philosopher+1)%5;
        fk_locks[leftside].lock();
        pickRightFork.run();

        eat.run();

        putRightFork.run();
        fk_locks[leftside].unlock();

        putLeftFork.run();
        fk_locks[philosopher].unlock();           
        
        
        ph_lock.unlock();        
    }
}
