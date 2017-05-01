import java.io.File;

public class NBody {
    public static double readRadius(String s){
        In in= new In(s);
        int N= in.readInt();
        double R= in.readDouble();
        return R;
    }
    public static Planet[] readPlanets(String s){
        In in= new In(s);
        int N=in.readInt();
        double R=in.readDouble();
        Planet[] P= new Planet[N];
        int i=0;
        while(i<N){
            P[i]=new Planet(in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
            i+=1;
        }
        return P;
    }
    public static void main(String args[]){
        double T= Double.parseDouble(args[0]);
        double dt=Double.parseDouble(args[1]);
        String filename=args[2];
        double R=readRadius(filename);
        Planet[] P=readPlanets(filename);
        StdDraw.setScale(-R, R);
        StdDraw.clear();
        StdDraw.picture(0,0,"starfield.jpg", 2*R, 2*R);
        for(int i=0;i<P.length;i++){
            P[i].draw();;
        }
        for(double t=0; t<T;t+=dt){
            double[] xForces= new double[P.length];
            double[] yForces= new double[P.length];
            for(int j=0;j<P.length;j++){
                xForces[j]=P[j].calcNetForceExertedByX(P);
                yForces[j]=P[j].calcNetForceExertedByY(P);
            }
            for(int j=0;j<P.length;j++){
               P[j].update(dt,xForces[j],yForces[j]);
            }
            StdDraw.picture(0,0,"starfield.jpg", 2*R, 2*R);
            for(int j=0;j<P.length;j++){
                P[j].draw();
            }
            StdDraw.show(10);
        }

    }
}
