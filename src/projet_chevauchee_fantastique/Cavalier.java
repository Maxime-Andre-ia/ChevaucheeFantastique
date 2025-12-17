/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projet_chevauchee_fantastique;

/**
 *
 * @author quent
 */
public class Cavalier {
   private int X;
   private int Y;
   
   public Cavalier(int xdepart, int ydepart){
   this.X= xdepart;
   this.Y= ydepart;}
   
   public void deplacer(int nvX, int nvY){
   this.X=nvX;
   this.Y=nvY;}
   
   public int getX(){
   return X;}
   
   public int getY(){
   return Y;}
   
   
   public boolean peutAllerEn(int cibleX, int cibleY) {
    int diffX = cibleX - this.X;
    int diffY = cibleY - this.Y;
    if (diffX < 0) diffX = -diffX;
    if (diffY < 0) diffY = -diffY;
    return (diffX == 2 && diffY == 1) || (diffX == 1 && diffY == 2);
}
   }

