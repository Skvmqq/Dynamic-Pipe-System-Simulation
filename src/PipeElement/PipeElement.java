package PipeElement;

public abstract class PipeElement {
    boolean broken = false;
    public String name;

    public PipeElement(String name)
    {
        this.name = name;
    }

    public boolean getBrokenStatus()
    {
        return broken;
    }
    public abstract void breakPipeElement();
    public abstract void fixPipeElement();
}
