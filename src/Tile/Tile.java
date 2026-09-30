package Tile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import PipeElement.Pipe;
import PipeElement.Pump;
import PipeElement.Spring;
import PipeElement.Cistern;
import PipeElement.PipeElement;

public class Tile {
    public String name;
    public BufferedImage image;
    public boolean collision = true;
    public boolean pickable = false;
    PipeElement pipeElement;

    public Tile(String name)
    {
        switch(name){
            case "pipe":
                this.name = "pipe";
                pipeElement = new Pipe();
                collision = false;
                break;
            case "pump":
                this.name = "pump";
                pipeElement = new Pump();
                collision = false;
                break;
            case "cistern":
                this.name = "cistern";
                pipeElement = new Cistern();
                collision = false;
                break;
            case "spring":
                this.name = "spring";
                pipeElement = new Spring();
                collision = false;
                break;
            case "boundary":
                this.name = "boundary";
                pipeElement = null;
                break;
            default:
                this.name = "desert";
                pipeElement = null;
        }
        try {
            if(this.name.equals("boundary"))
            {
                image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/res/desert.png")));
            }
            else
            {
                image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/res/" + name + ".png")));
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public void buildPipeElement(String name)
    {
        if(!Objects.equals(this.name, "desert"))
        {
            return;
        }

        pickable = true;
        collision = true;
        switch(name){
            case "pipe":
                this.name = "pipe";
                pipeElement = new Pipe();
                break;
            case "pump":
                this.name = "pump";
                pipeElement = new Pump();
                break;
        }
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/res/" + name + ".png")));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public PipeElement pickPipeElement()
    {
        if(!Objects.equals(name, "pump") && !Objects.equals(name, "pipe"))
        {
            return null;
        }

        if(!pickable)
        {
            return null;
        }

        PipeElement currentPipeElement = pipeElement;

        this.name = "desert";
        pickable = false;
        collision = true;
        pipeElement = null;
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/res/desert.png")));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return currentPipeElement;
    }

    public void placePipeElement(PipeElement pipeElement)
    {

        if(pipeElement == null)
        {
            return;
        }

        if(!Objects.equals(name, "desert") && !(Objects.equals(name, "pipe") && Objects.equals(pipeElement.name, "pump")))
        {
            return;
        }

        pickable = false;
        collision = false;

        switch(pipeElement.name){
            case "pipe":
                this.name = "pipe";
                break;
            case "pump":
                this.name = "pump";
                break;
        }
        try {
            image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/res/" + name + ".png")));
        }
        catch(IOException e){
            e.printStackTrace();
        }
        this.pipeElement = pipeElement;
    }

    public void breakTile()
    {
        if(pipeElement != null)
        {
            pipeElement.breakPipeElement();
        }
        if(Objects.equals(name, "pipe") || Objects.equals(name, "pump"))
        {
            try {
                image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/res/broken" + name + ".png")));
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public void fixTile()
    {
        if(pipeElement != null)
        {
            pipeElement.fixPipeElement();
        }
        if(Objects.equals(name, "pipe") || Objects.equals(name, "pump"))
        {
            try {
                image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/main/res/" + name + ".png")));
            }
            catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}