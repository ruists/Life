/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rui
 */

public class LifeView implements IView{
    private final int width;
    private final int height;
    public LifeView(final int w, final int h) {
        if(w < 1) {
            throw new IllegalArgumentException("Width must be positive.");
        }
        if(h < 1) {
            throw new IllegalArgumentException("Height must be positive.");
        }
        width = w;
        height = h;
        
        StdDraw.setCanvasSize(width,height);
        StdDraw.setYscale(0,height);
        StdDraw.setXscale(0,width);
        StdDraw.setPenRadius(0);
        StdDraw.setPenColor(StdDraw.BLACK);
    }
    
    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void drawGeneration(final boolean [][] generation) {
        StdDraw.show(0);
        StdDraw.clear();
        
        for(int row = 0; row < generation.length; row++)
            for(int col = 0; col < generation[row].length; col++)
                if(generation[row][col] == true)
                    StdDraw.point(col,row);
        
        StdDraw.show(0);
    }
}
