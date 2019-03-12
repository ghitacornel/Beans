package beans.random.generators.configurations;

public class SequenceConfiguration {

    private long min = 1;
    private long max = Long.MAX_VALUE;
    private long start = 0;
    private long increment = 1;
    private boolean cycle = true;

    public long getMin() {
        return min;
    }

    public void setMin(long min) {
        this.min = min;
    }

    public long getMax() {
        return max;
    }

    public void setMax(long max) {
        this.max = max;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getIncrement() {
        return increment;
    }

    public void setIncrement(long increment) {
        if (increment <= 0) {
            increment = 1;
        }
        this.increment = increment;
    }

    public boolean isCycle() {
        return cycle;
    }

    public void setCycle(boolean cycle) {
        this.cycle = cycle;
    }

}
