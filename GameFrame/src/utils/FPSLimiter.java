package utils;

public class FPSLimiter {

    private long targetFpsInMillis;
    private long deltaTime, time;

    public FPSLimiter(float targetFps) {
        this.targetFpsInMillis = (long) (1000 / targetFps);
        deltaTime = 0;
    }

    private long calcSleep() {
        deltaTime = System.currentTimeMillis() - time;
        return Math.max(0, targetFpsInMillis - deltaTime);
    }
    /*if (deltaTime > target_ms) {
     //if timestep is needed make it here
     deltaTime = target_ms;
     }*/

    public void setTargetFps(int fps) {
        this.targetFpsInMillis = fps / 60;
    }

    public void begin() {
        time = System.currentTimeMillis();
    }

    public void end() {
        try {
            Thread.sleep(calcSleep());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
