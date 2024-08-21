package hw3_prj.utils;
public class Visibility {
    private int visibilityTime;
    private int invisibilityTime;
    private int ticksCount;
    private boolean visible;

    public Visibility(int visibilityTime, int invisibilityTime) {
        this.visibilityTime = visibilityTime;
        this.invisibilityTime = invisibilityTime;
        ticksCount = 0;
        visible = true;
    }

    public void tick() {
        visible = ticksCount < visibilityTime;
        if (ticksCount == visibilityTime + invisibilityTime) {
            ticksCount = 0;
        } else {
            ticksCount++;
            
        }
    }

    public boolean getCurrent() {
        return visible;
    }
}
