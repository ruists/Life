/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Rui
 */
public class Life {
    
    public Life(int width, int height) {
        if(width < 1) {
            throw new IllegalArgumentException("Width must be positive.");
        }
        if(height < 1) {
            throw new IllegalArgumentException("Height must be positive.");
        }
        new LifeController(new LifeView(width, height)).start();
    }
    
    public static void main(String[] args) {
        int h, w;
        if(args.length != 2) {
            h = 600;
            w = 600;
        } else {
            h = Integer.parseInt(args[0]);
            w = Integer.parseInt(args[1]);
        }
        new Life(h,w);
    }
    
}
