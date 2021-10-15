package apricity.genshin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.data.BlockData;

import java.util.Timer;
import java.util.TimerTask;

public class SpawnParticle {
    public static void spawnPointParticle(Particle particle, Location location,int count){
        Bukkit.getWorld("world").spawnParticle(particle,location.getX(),location.getY(),location.getZ(),count);
    }

    public static void spawnLineParticle(Particle particle, Location location1,Location location2){
        double deltaX=location1.getX()-location2.getX();
        double deltaY=location1.getY()-location2.getY();
        double deltaZ=location1.getZ()-location2.getZ();
        int time=(int) (Math.sqrt(deltaX*deltaX+deltaY*deltaY+deltaZ*deltaZ)*100/5);


        Timer timer =  new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for(int i=0;i<time;i++){
                    Bukkit.getWorld("world").spawnParticle(particle,location1.getX()-deltaX*i/time,location1.getY()-deltaY*i/time,location1.getZ()-deltaZ*i/time,1);
                    try {
                        Thread.sleep(150/time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                timer.cancel();
            }
        },0);


    }

    public static void spawnLineParticle(Particle particle, Location location1, Location location2, BlockData blockdata){
        double deltaX=location1.getX()-location2.getX();
        double deltaY=location1.getY()-location2.getY();
        double deltaZ=location1.getZ()-location2.getZ();
        int time=(int) (Math.sqrt(deltaX*deltaX+deltaY*deltaY+deltaZ*deltaZ)*100/5);


        Timer timer =  new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for(int i=0;i<time;i++){
                    Bukkit.getWorld("world").spawnParticle(particle,location1.getX()-deltaX*i/time,location1.getY()-deltaY*i/time,location1.getZ()-deltaZ*i/time,1,blockdata);
                    try {
                        Thread.sleep(150/time);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                timer.cancel();
            }
        },0);


    }

    public static void spawnConeParticle(Apricity p,Particle particle,Location location,double h,double r){
        Location centre = location.clone();
        centre.setY(location.getY()-h);
        int time=10;

        Bukkit.getScheduler().runTaskLaterAsynchronously(p, new Runnable() {
            //runTaskAsynchronously异步延迟
            @Override
            public void run() {
                Bukkit.getScheduler().runTask(p, new Runnable() {
                    //runTask强制主线程
                    @Override
                    public void run() {
                        for(int i=0;i<time;i++){
                            Location temp=centre.clone();
                            temp.setX(centre.getX()+r*Math.cos(2*i*Math.PI/time));
                            temp.setZ(centre.getZ()+r*Math.sin(2*i*Math.PI/time));
                            spawnLineParticle(particle,location,temp);
                        }

                    }
                });
            }
        }, 2);
    }

    public static void spawnConeParticle(Apricity p,Particle particle,BlockData blockdata,Location location,double h,double r){
        Location centre = location.clone();
        centre.setY(location.getY()-h);
        int time=10;

        Bukkit.getScheduler().runTaskLaterAsynchronously(p, new Runnable() {
            //runTaskAsynchronously异步延迟
            @Override
            public void run() {
                Bukkit.getScheduler().runTask(p, new Runnable() {
                    //runTask强制主线程
                    @Override
                    public void run() {
                        for(int i=0;i<time;i++){
                            Location temp=centre.clone();
                            temp.setX(centre.getX()+r*Math.cos(2*i*Math.PI/time));
                            temp.setZ(centre.getZ()+r*Math.sin(2*i*Math.PI/time));
                            spawnLineParticle(particle,location,temp,blockdata);
                        }

                    }
                });
            }
        }, 2);
    }
}
